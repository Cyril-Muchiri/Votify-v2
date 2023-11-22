package com.votifysoft.app.dao;


import java.util.List;

import com.votifysoft.database.MySqlDb;

public class GenericDao<T> implements GenericDaoI<T> {

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) MySqlDb.select(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        MySqlDb.saveOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }
}
