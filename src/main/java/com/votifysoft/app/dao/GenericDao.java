package com.votifysoft.app.dao;

import java.util.List;

import com.votifysoft.database.MySqlDb;

public class GenericDao<T> implements GenericDaoI<T> {

    private MySqlDb database;

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> list(Object entity) {
        return (List<T>) database.select(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        database.saveOrUpdate(entity);

    }

    public void addVote(int id){
        database.updateVotes(id);
    }

    @Override
    public void delete(T entity) {

    }

    public MySqlDb getDatabase() {
        return database;
    }

    public void setDatabase(MySqlDb database) {
        this.database = database;
    }

}
