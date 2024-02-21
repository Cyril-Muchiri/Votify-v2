package com.votifysoft;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.votifysoft.app.beans.GenericBean;
import com.votifysoft.app.beans.UserBean;
import com.votifysoft.app.beans.UserBeanI;
import com.votifysoft.model.entity.User;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserBeanTest extends GenericBean<User>{

    @PersistenceContext
    private EntityManager entityManager;
    
    @EJB
    UserBeanI userBean;
    
    @SuppressWarnings("deprecation")
    @BeforeClass
    public void setUp() {
        //  MockitoAnnotations.initMocks(this);
        userBean = new UserBean();
        
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testAddOrUpdateUser_Success() {
        User user = new User();
        user.setUserId(1);
        user.setUserEmail("test@example.com");
        user.setPassword("password");
        
        try {
            User result = userBean.addOrUpdate(user);
            Assert.assertNotNull(result);
        } catch (SQLException e) {
            Assert.fail("Exception thrown: " + e.getMessage());
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testAddOrUpdateUser_DuplicateUser() throws SQLException {
        User user = new User();
        user.setUserId(2);
        user.setUserEmail("test@example.com");
        user.setPassword("password");
        
        userBean.addOrUpdate(user);
    }
    
    @Test(expectedExceptions = RuntimeException.class)
    public void testAddOrUpdateUser_Exception() throws SQLException {
        User user = new User();
        user.setUserId(3);
        user.setUserEmail("test@example.com");
        user.setPassword("password");
        
        userBean.addOrUpdate(user);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testGetUserById() {
        int userId = 1; // Assuming user with ID 1 exists in the database
        User user = userBean.getUserById(userId);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUserId(), userId);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testGetUserById_UserNotFound() {
        int userId = 100; // Assuming user with ID 100 does not exist in the database
        User user = userBean.getUserById(userId);
        Assert.assertNull(user);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testGetAllUsers() {
        int totalUsers = userBean.getAllUsers();
        
    }
}

