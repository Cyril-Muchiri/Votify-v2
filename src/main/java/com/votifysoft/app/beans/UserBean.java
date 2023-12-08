package com.votifysoft.app.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.model.entity.User;

import java.sql.SQLException;
import java.util.List;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {

    @Inject
    private EncryptPwd encryptText;

    @Override
    public User addOrUpdate(User user) {
        List<User> checkUser = list(new User());
        if (!checkUser.isEmpty()) {
            throw new RuntimeException("User already exists!");
        }

        try {
            user.setPassword(encryptText.encrypt(user.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return getDao().addOrUpdate(user);
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }

    @Override
    public User getUserById(int userId) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public boolean register(User user) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
}
