package com.votifysoft.app.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.model.entity.User;

import java.sql.SQLException;
import java.util.List;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {

    @Inject
    private EncryptPwd encryptText;

    @PersistenceContext
    private EntityManager em;

    User userResult = null;

    @Override
    public User addOrUpdate(User user) throws SQLException {

        List<User> checkUser = list(user);
        if (!checkUser.isEmpty()) {
            userResult = null;
            // System.out.println("This is the user email" + user.getUserEmail());
            throw new SQLException("User with email " + user.getUserEmail() + " already exists.");
            
        }

        try {
            user.setPassword(encryptText.encrypt(user.getPassword()));
            userResult = getDao().addOrUpdate(user);
        }catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return userResult;
    }

    @SuppressWarnings("unchecked")
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE userId = :userId";

        List<User> userList = em.createNativeQuery(sql, User.class)
                .setParameter("userId", userId)
                .getResultList();

        return userList.isEmpty() ? null : userList.get(0);
    }

}
