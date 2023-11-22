package com.votifysoft.app.beans;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {

    List<T> list(Class<?> entity);

    void addOrUpdate(T entity);

    void delete(T entity);

}
