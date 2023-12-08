package com.votifysoft.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;


public interface GenericDaoI <T> extends Serializable {

 List<T> list(T entity);

    T addOrUpdate(T entity);

    void delete(T entity);

    EntityManager getEm();

    void setEm(EntityManager em);


}
