package com.votifysoft.app.beans;


import java.sql.SQLException;

import com.votifysoft.model.entity.User;

public interface UserBeanI extends GenericBeanI<User>{

    boolean register(User user) throws SQLException;

    boolean unregister(User user);

    User getUserById(int userId);
}
