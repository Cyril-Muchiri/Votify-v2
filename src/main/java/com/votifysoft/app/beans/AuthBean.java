package com.votifysoft.app.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.database.MySqlDb;
import com.votifysoft.model.entity.User;

@Stateless
public class AuthBean implements AuthBeanI, Serializable {

    @EJB
    MySqlDb db;

    @Inject
    private EncryptPwd hashPwd;

    User userResult = null;

    public User authenticate(User loginUser) throws SQLException {

        List<User> users = db.select(loginUser);

        for (User user : users) {
            if (user.getUserEmail().equals(loginUser.getUserEmail()) &&
                  user.getPassword().equals(hashPwd.encrypt(loginUser.getPassword()))) {
                userResult = user;
                break;
            }
        }
        return userResult;

    }

}
