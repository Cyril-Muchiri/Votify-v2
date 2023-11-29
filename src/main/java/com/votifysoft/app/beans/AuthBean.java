package com.votifysoft.app.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.votifysoft.database.MySqlDb;
import com.votifysoft.model.entity.User;

@Stateless
@Remote
public class AuthBean implements AuthBeanI, Serializable {

    @EJB
    MySqlDb db;

    User userResult;

    public User authenticate(User loginUser) throws SQLException {

        List<User> users = db.fetch(loginUser);

        if (users.isEmpty() || users.get(0) == null) {

            userResult=null;

        }else{
            userResult=users.get(0);
        }
          return userResult;

    }
}
