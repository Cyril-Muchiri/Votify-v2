package com.votifysoft.model.entity;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;


@DbTable(name = "users")
public class User extends BaseEntity {

    @DbTableColumn(name = "userName")
    private  String userName;

    @DbTableColumn(name = "userEmail")
    private String userEmail;

     @DbTableColumn(name = "password")
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

   
    public User(){}

    public User(String userName,String userEmail, String password) {
        // this.id = id;
        this.userName = userName;
        this.password = password;
        this.userEmail= userEmail;
    }


    public  String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}