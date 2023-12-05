package com.votifysoft.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDao<T> implements GenericDaoI<T> {

  

    @PersistenceContext
    private EntityManager em;

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

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void addVote(int id) {
        em.merge(id);
    }

}
