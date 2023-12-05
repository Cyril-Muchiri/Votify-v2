package com.votifysoft.app.beans;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.votifysoft.app.dao.GenericDao;
import com.votifysoft.app.dao.GenericDaoI;

public abstract class GenericBean<T> implements GenericBeanI<T> {

    @Inject
    EntityManager em;

    @Inject
    private GenericDaoI<T> genericDao;

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> list(Object entity) {
        String jpql = "FROM " + entity.getClass().getSimpleName() + " e";

        List<T> results = (List<T>) em.createQuery(jpql, entity.getClass());

        return results;

    }

    @Override
    public void addOrUpdate(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao() {
        return (GenericDao<T>) genericDao;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
