package com.votifysoft.app.dao;

import java.io.Serializable;
import java.util.List;

import com.votifysoft.database.MySqlDb;

public interface GenericDaoI <T> extends Serializable {

    List<T> list(Object entity);

    void addOrUpdate(T entity);

    void delete(T entity);

    MySqlDb getDatabase();

    void setDatabase(MySqlDb database);

}
