package com.votifysoft.app.beans;

import java.sql.SQLException;

import com.votifysoft.model.entity.User;

public interface UserBeanI extends GenericBeanI<User>{

    public User addOrUpdate(User user) throws SQLException;

    public User getUserById(int userId);

    public int getAllUsers();
}
