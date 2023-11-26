package com.votifysoft.app.beans;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.votifysoft.database.MySqlDb;
import com.votifysoft.model.entity.User;

@Stateless
public class AuthBean implements AuthBeanI, Serializable {

    // private MySqlDb db;

    public User authenticate(User loginUser) throws SQLException {

        PreparedStatement sqlStmt = MySqlDb.getInstance().getConnection()
                .prepareStatement("select * from users where userEmail=? and password=? limit 1");
        sqlStmt.setString(1, loginUser.getUserEmail());
        sqlStmt.setString(2, loginUser.getPassword());

        ResultSet result = sqlStmt.executeQuery();

        User user = new User();

        while (result.next()) {
            // user.setId(result.getLong("id"));
            user.setUserName(result.getString("userName"));
            user.setUserEmail(result.getString("userEmail"));
        }

        return user;
    }
}
