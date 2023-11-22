package com.votifysoft.app.beans;

import java.util.List;

import com.votifysoft.app.dao.GenericDao;
import com.votifysoft.app.dao.GenericDaoI;

public class GenericBean<T> implements GenericBeanI<T>{

    private final GenericDaoI<T> genericDao = new GenericDao<>();

    @Override
    public List<T> list(Class<?> entity) {
        return genericDao.list(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        genericDao.addOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao(){
        return (GenericDao<T>) genericDao;
    }

}
