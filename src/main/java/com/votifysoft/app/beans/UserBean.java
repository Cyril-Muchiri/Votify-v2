package com.votifysoft.app.beans;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.model.entity.User;

@Stateless
public class UserBean extends GenericBean<User> implements UserBeanI {

    @Inject
    private EncryptPwd hashPwd;

    @Override
    public boolean register(User user) throws SQLException {

        // if (false)
        //     throw new RuntimeException("please enter valid credentials");

        // user.setPassword(hashPwd.encrypt(user.getPassword()));

        getDao().addOrUpdate(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
