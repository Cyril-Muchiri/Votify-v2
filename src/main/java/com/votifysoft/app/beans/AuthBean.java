package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.model.entity.AuditLog;
import com.votifysoft.model.entity.User;

@Stateless
public class AuthBean extends GenericBean<User> implements AuthBeanI {

    @PersistenceContext
    EntityManager eManager;

    @Inject
    private EncryptPwd hashPwd;

    @Inject
    private Event<AuditLog> logger;

    User userResult = null;

    public User authenticate(User loginUser) throws SQLException {
        try {
            loginUser.setPassword(hashPwd.encrypt(loginUser.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        List<User> users = list(loginUser);

        if (users == null || users.isEmpty() || users.get(0) == null) {
            userResult = null;
        } else {
            AuditLog log = new AuditLog();
            log.setLogDetails("User logged in at " + DateFormat.getDateTimeInstance().format(new Date())
                    + ", " + users.get(0).getUserEmail());
                    System.out.println("Logger ==-> "+ log.getLogDetails());

            logger.fire(log);
            

            userResult = users.get(0);
        }

        return userResult;
    }

}
