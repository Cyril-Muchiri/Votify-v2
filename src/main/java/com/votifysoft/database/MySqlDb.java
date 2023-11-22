package com.votifysoft.database;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;
import com.votifysoft.database.helper.DbTableId;
import com.votifysoft.model.entity.User;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySqlDb implements Serializable {

    private static MySqlDb database;

    private Connection connection;

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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
