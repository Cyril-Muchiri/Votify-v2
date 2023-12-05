package com.votifysoft.model.entity;

import javax.persistence.Entity;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;

@Entity
@DbTable(name = "users")
public class User extends BaseEntity  {

    @DbTableColumn(name = "userId", definition = "INT PRIMARY KEY AUTO_INCREMENT")
    private  int userId;

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

    public User(int userId,String userName,String userEmail, String password) {
        this.userId = userId;
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

      public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}