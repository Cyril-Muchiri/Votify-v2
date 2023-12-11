package com.votifysoft.app.beans;

import com.votifysoft.model.entity.User;

public interface UserBeanI extends GenericBeanI<User>{

    public User addOrUpdate(User user);

    public User getUserById(int userId);
}
