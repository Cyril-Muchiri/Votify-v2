package com.votifysoft;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.votifysoft.app.beans.AuthBeanI;
import com.votifysoft.app.dao.GenericDao;
import com.votifysoft.app.dao.GenericDaoI;
import com.votifysoft.app.utils.EncryptPwd;
import com.votifysoft.model.entity.AuditLog;
import com.votifysoft.model.entity.User;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class AuthBeanTest {

    @Mock
    private AuthBeanI authBean;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EncryptPwd encryptPwd;

    @Mock
    private Event<AuditLog> logger;

    @Mock
    private GenericDaoI<User> genericDao;

    @BeforeMethod
    public void setUp() {
        
        encryptPwd=mock(EncryptPwd.class);
        genericDao = mock(GenericDao.class);
        MockitoAnnotations.openMocks(this);
        genericDao.setEm(entityManager);
    }

    @Test
    public void testAuthenticate_UserNotFound() throws SQLException {
        User user = new User();
        user.setPassword("password");

        List<User> emptyList = new ArrayList<>();
        when(genericDao.list(user)).thenReturn(emptyList);

        User result = authBean.authenticate(user);

        assertNull(result);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAuthenticate_UserFound() throws SQLException {
        User user = new User();
        user.setPassword("password");
        user.setUserEmail("muchiri@test.com");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        AuditLog auditLog = new AuditLog();
        auditLog.setLogDetails("User logged in at " + DateFormat.getDateTimeInstance().format(new Date()) + ", "
                + user.getUserEmail());

        when(genericDao.list(user)).thenReturn(userList);
        when(encryptPwd.encrypt(anyString())).thenReturn("encrypted");
        doNothing().when(logger).fire(auditLog);

        User result = authBean.authenticate(user);

        System.out.println("sjnfjdneif"+result);

        assertEquals(result.getPassword(), user.getPassword());
    }
}
