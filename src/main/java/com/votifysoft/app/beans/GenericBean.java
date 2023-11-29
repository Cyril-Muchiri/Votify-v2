package com.votifysoft.app.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

import com.votifysoft.app.dao.GenericDao;
import com.votifysoft.app.dao.GenericDaoI;
import com.votifysoft.database.MySqlDb;

public abstract class GenericBean<T> implements GenericBeanI<T>{

    @Inject
    private GenericDaoI<T> genericDao;

    @EJB
    private MySqlDb database;

    @PostConstruct
    private void initialize() {
        genericDao.setDatabase(database);
    }

    @Override
    public List<T> list(Object entity) {
        return genericDao.list(entity);
    }

    @Override
    public void addOrUpdate(T entity) {
        genericDao.addOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        // Implement deletion logic or provide a default behavior.
    }

    public GenericDao<T> getDao() {
        return (GenericDao<T>) genericDao;
    }
}
