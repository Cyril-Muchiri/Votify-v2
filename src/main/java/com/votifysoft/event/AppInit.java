// package com.votifysoft.event;

// import java.lang.reflect.Field;
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import javax.servlet.ServletContextEvent;
// import javax.servlet.ServletContextListener;
// import javax.servlet.annotation.WebListener;

// import com.votifysoft.database.MySqlDb;
// import com.votifysoft.database.helper.DbTable;
// import com.votifysoft.database.helper.DbTableColumn;
// import com.votifysoft.model.entity.User;

// @WebListener
// public class AppInit implements ServletContextListener {

//       @Override
//     public void contextInitialized(ServletContextEvent sce) {
//         System.out.println("*************** Initializing database *************");

//         try {
//             Connection connection = MySqlDb.getInstance().getConnection();

//             List<Class<?>> entities = new ArrayList<>();
//             entities.add(User.class);
          

//             for (Class<?> clazz : entities) {
//                 if (!clazz.isAnnotationPresent(DbTable.class))
//                     continue;

//                 DbTable dbTable = clazz.getAnnotation(DbTable.class);

//                 StringBuilder sqlBuilder = new StringBuilder();

//                 sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");
//                 for (Field field : clazz.getDeclaredFields()) {
//                     if (!field.isAnnotationPresent(DbTableColumn.class))
//                         continue;

//                     DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

//                     sqlBuilder.append(dbTableColumn.name()).append(" ").append(dbTableColumn.definition()).append(",");
//                 }

//                 sqlBuilder.append(")");

//                 connection.prepareStatement(sqlBuilder.toString().replace(",)", ")")).executeUpdate();

//             }



//             //auto create databse -- if does not exist
//             //auto create create tables; if does not exist

//             //create custom annotation.. use java reflection to create table....


//         } catch (SQLException ex) {
//             System.out.println(ex);
//         }

//     }

//     @Override
//     public void contextDestroyed(ServletContextEvent sce) {

//         try {
//             MySqlDb database = MySqlDb.getInstance();

//             if (database.getConnection() != null){
//                 database.getConnection().close();
//             }

//         } catch (SQLException ex) {
//             System.out.println(ex);
//         }

//     }

// }
