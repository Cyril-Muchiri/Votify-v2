package com.votifysoft.app.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.model.entity.User;

@Stateless
public class AuthBean implements AuthBeanI, Serializable {

  @PersistenceContext
   EntityManager eManager;

    @Inject
    private EncryptPwd hashPwd;

    User userResult = null;

    public User authenticate(User loginUser) throws SQLException {
        List<User> users = eManager.createQuery(
                "FROM User u WHERE u.userEmail=:username AND u.password=:password", User.class)
                .setParameter("username", loginUser.getUserEmail())
                .setParameter("password", hashPwd.encrypt(loginUser.getPassword()))
                .getResultList();
    
        if (!users.isEmpty()) {
            userResult = users.get(0);
        }
    
        return userResult;
    }
    

}
