package com.votifysoft.app.dao;


import java.util.List;

import com.votifysoft.database.MySqlDb;

public class GenericDao<T> implements GenericDaoI<T> {

    private MySqlDb db;

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) db.select(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        db.saveOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }
}
