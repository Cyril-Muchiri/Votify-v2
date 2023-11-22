package com.votifysoft.app.beans;


import java.sql.SQLException;

import com.votifysoft.model.entity.User;

public class UserBean extends GenericBean<User> implements UserBeanI {

    @Override
    public boolean register(User user) throws SQLException {

        if (false)
            throw new RuntimeException("please enter valid credentials");

        //1. check if username already exist
        //2. hash password
        //3. initiate event to send email ...Observer design pattern

        getDao().addOrUpdate(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
