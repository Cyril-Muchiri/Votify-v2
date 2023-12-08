package com.votifysoft.app.beans;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.votifysoft.app.dao.GenericDao;
import com.votifysoft.app.dao.GenericDaoI;

public class GenericBean<T> implements GenericBeanI<T> {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private GenericDaoI<T> genericDao;

    @Override
    public List<T> list(T entity) {
        genericDao.setEm(em);
        return genericDao.list(entity);
    }

    @Override
    public T addOrUpdate(T entity) {
        genericDao.setEm(em);
        return genericDao.addOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        genericDao.setEm(em);
        genericDao.delete(entity);
    }

    public GenericDao<T> getDao() {
        genericDao.setEm(em);
        return (GenericDao<T>) genericDao;
    }
}
