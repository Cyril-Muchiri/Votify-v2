package com.votifysoft;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.votifysoft.app.beans.PollBean;
import com.votifysoft.app.dao.GenericDao;
import com.votifysoft.model.entity.Polls;

public class PollBeanTest {

    private PollBean pollBean;
    private EntityManager entityManager;

    @BeforeTest
    public void setUp() {
        pollBean = new PollBean(); 
        entityManager = mock(EntityManager.class);
        
    }

    @Test
    public void testRegisterTopic() {
        Polls pollTopic = new Polls();
        pollTopic.setPoll_id(1);
    
        GenericDao<Polls> dao = mock(GenericDao.class);
    
        when(dao.addOrUpdate(pollTopic)).thenReturn(pollTopic);
    
        Integer generatedPollId = pollBean.registerTopic(pollTopic);
    
        assertNull(generatedPollId); 
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testGetLatestPoll() {
        Polls poll1 = new Polls();
        poll1.setPoll_id(1);
        Polls poll2 = new Polls();
        poll2.setPoll_id(2);

        List<Polls> pollsList = new ArrayList<>();
        pollsList.add(poll1);
        pollsList.add(poll2);

        TypedQuery<Polls> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT p FROM Polls p ORDER BY p.createdAt DESC", Polls.class)).thenReturn(query);
        when(query.setMaxResults(1)).thenReturn(query);
        when(query.getResultList()).thenReturn(pollsList);

        Polls latestPoll = pollBean.getLatestPoll();

        assertEquals(Integer.valueOf(1), latestPoll.getPoll_id()); 
    }
}
