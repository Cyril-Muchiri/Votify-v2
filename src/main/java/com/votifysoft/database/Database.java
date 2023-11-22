package com.votifysoft.database;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Database implements Serializable {

    private String databaseCreateAt;

    private List<Object> data = new ArrayList<>();

    private static Database dbInstance;

    private Database(){}

    public static Database getDbInstance(){
        if (dbInstance == null) {
            dbInstance = new Database();
            dbInstance.databaseCreateAt = DateFormat.getDateTimeInstance().format(new Date());
            System.out.println("Database created at " + dbInstance.getDatabaseCreateAt());
        }

        return dbInstance;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }


    public List<Object> getData(Class<?> clazz) {

        return data
            .stream()
            .filter(clazz::isInstance)
            .collect(Collectors.toList());

    }

    public String getDatabaseCreateAt() {
        return databaseCreateAt;
    }

}
