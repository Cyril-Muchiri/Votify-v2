package com.votifysoft.app.beans;


import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.votifysoft.model.entity.User;

@Stateless
public class UserBean extends GenericBean<User> implements UserBeanI {

    @Override
    public boolean register(User user) throws SQLException {

        if (false)
            throw new RuntimeException("please enter valid credentials");


        getDao().addOrUpdate(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
