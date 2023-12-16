package com.votifysoft.app.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {

    List<T> list(T entity);

    T addOrUpdate(T entity) throws SQLException;

    void delete(T entity);

}
