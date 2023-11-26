package com.votifysoft.database;


import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;
import com.votifysoft.database.helper.DbTableId;
import com.votifysoft.model.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


@Singleton
@Startup
public class MySqlDb implements Serializable {

    private static MySqlDb database;

    private Connection connection;

    @PostConstruct
    private void init() throws SQLException, NamingException {
        Context ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("java:/VotifyDS");
        connection = dataSource.getConnection();

        System.out.println("Database is Up!!!");

        this.updateSchema();

    }

    private MySqlDb() throws SQLException, NamingException {
        Context ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("java:/VotifyDS");
        connection = dataSource.getConnection();
    }

    public static MySqlDb getInstance(){
        if (database == null) {
            try {
                database = new MySqlDb();

            } catch (SQLException | NamingException e) {
                throw new RuntimeException(e);
            }
        }

        return database;

    }

    public static void updateSchema(){
        System.out.println("*************** updating schema database *************");

        try {
            Connection connection = MySqlDb.getInstance().getConnection();

            List<Class<?>> entities = new ArrayList<>();
            entities.add(User.class);
           

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTable.class))
                    continue;

                DbTable dbTable = clazz.getAnnotation(DbTable.class);

                StringBuilder sqlBuilder = new StringBuilder();

                sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");

                List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    if (!field.isAnnotationPresent(DbTableColumn.class))
                        continue;

                    DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                    sqlBuilder.append(dbTableColumn.name()).append(" ")
                        .append(dbTableColumn.definition())
                        .append(field.isAnnotationPresent(DbTableId.class)?" NOT NULL AUTO_INCREMENT PRIMARY KEY" : "")
                        .append(",");
                }

                sqlBuilder.append(")");

                String tableCreationSql = sqlBuilder.toString().replace(",)", ")");
                System.out.println("Creating table: " + tableCreationSql);
                connection.prepareStatement(tableCreationSql).executeUpdate();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public static void saveOrUpdate(Object entity) {

        try {

            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(DbTable.class))
                return;

            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder paramPlaceHolderBuilder = new StringBuilder();
            List<Object> parameters = new ArrayList<>();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;

                field.setAccessible(true);
                if (field.get(entity) == null)
                    continue;

                DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                columnBuilder.append(dbTableColumn.name()).append(",");
                paramPlaceHolderBuilder.append("?").append(",");
                parameters.add(field.get(entity));

            }

            String queryBuilder = "insert into " +
                dbTable.name() +
                "(" +
                columnBuilder +
                ")" +
                " values(" +
                paramPlaceHolderBuilder +
                ")";

            String query = queryBuilder.replace(",)", ")");
            System.out.println("Query: " + query);

            PreparedStatement sqlStmt = MySqlDb.getInstance().getConnection()
                .prepareStatement(query);

            int paramIdx = 1;
            for (Object param : parameters) {
                if (param.getClass().isAssignableFrom(BigDecimal.class))
                    sqlStmt.setBigDecimal(paramIdx++, (BigDecimal) param);
                else if (param.getClass().isAssignableFrom(Long.class))
                    sqlStmt.setLong(paramIdx++, (long) param);
                else
                    sqlStmt.setString(paramIdx++, (String) param);
            }

            sqlStmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

  public static <T> List<T> select(Class<T> filter) {
    try {
        Class<?> clazz = filter;
        System.out.println();
        System.out.println("Clazz>>>" + clazz.getName());

        if (!clazz.isAnnotationPresent(DbTable.class))
            return new ArrayList<>();

        DbTable dbTable = clazz.getAnnotation(DbTable.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(dbTable.name()).append(";");
        Connection conn = MySqlDb.getInstance().getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());

        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> result = new ArrayList<>();

        while (resultSet.next()) {
            T object = (T) clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                DbTableColumn dbColumn = field.getAnnotation(DbTableColumn.class);
                if (dbColumn != null) {
                    String columnName = dbColumn.name();

                    Object value = resultSet.getObject(columnName);
                    field.setAccessible(true);
                    field.set(object, value);
                }
            }

            result.add(object);
        }
        return result;

    } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
             NoSuchMethodException ex) {
        throw new RuntimeException(ex);
    }
}

    @SuppressWarnings("unchecked")
    public <T> List<T> fetch(T entity) {

        List<T> resultList = new ArrayList<>();

        try {
            Class<?> clazz = entity.getClass();

            if (!clazz.isAnnotationPresent(DbTable.class))
                return resultList;

            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            String tableAlias = dbTable.name().charAt(0) + "_e_";
            System.out.println("table alias " + tableAlias);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder paramPlaceHolderBuilder = new StringBuilder();
            List<Object> whereParams = new ArrayList<>();

            DateConverter converter = new DateConverter( null );
            converter.setPattern("yyyy-mm-dd");
            ConvertUtils.register(converter, Date.class);
            

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;

                DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                columnBuilder.append(tableAlias).append(".").append(dbTableColumn.name()).append(",");

                field.setAccessible(true);
                if (field.get(entity) != null) {
                    paramPlaceHolderBuilder
                        .append(whereParams.isEmpty()?"":" and ")
                        .append(tableAlias).append(".").append(dbTableColumn.name()).append("=?");
                    whereParams.add(field.get(entity));
                }

            }

            String queryBuilder =
                "select " +
                columnBuilder +
                " from " +
                dbTable.name() + " " + tableAlias +
                (whereParams.isEmpty() && StringUtils.isBlank(paramPlaceHolderBuilder) ? "" : " where " + paramPlaceHolderBuilder);

            String query = queryBuilder.replace(", from", " from");
            System.out.println("Query: " + query);

            PreparedStatement sqlStmt = connection.prepareStatement(query);

            int paramIdx = 1;
            for (Object whereParam : whereParams) {
                if (whereParam.getClass().isAssignableFrom(BigDecimal.class))
                    sqlStmt.setBigDecimal(paramIdx++, (BigDecimal) whereParam);
                else if (whereParam.getClass().isAssignableFrom(Long.class))
                    sqlStmt.setLong(paramIdx++, (long) whereParam);
                else
                    sqlStmt.setString(paramIdx++, (String) whereParam);
            }

            ResultSet resultSet = sqlStmt.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int resultSetMetaDataCnt = resultSetMetaData.getColumnCount();

            while (resultSet.next()){
                T bean = (T) entity.getClass().getDeclaredConstructor().newInstance();

                for (int idx = 1; idx <= resultSetMetaDataCnt; idx++ ) {
                    String colName = resultSetMetaData.getColumnName(idx);

                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                            continue;

                        DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                        field.setAccessible(true);
                        if (dbTableColumn.name().equals(colName)) {
                            BeanUtils.setProperty(bean, field.getName(), resultSet.getObject(idx));
                            break;
                        }
                    }

                }

                resultList.add(bean);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return resultList;

    }



    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}