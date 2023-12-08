package com.votifysoft.app.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.votifysoft.model.entity.Polls;

@Stateless
public class PollBean extends GenericBean<Polls> implements PollBeanI {
    
    @PersistenceContext
    EntityManager entityManager;

    public Integer registerTopic(Polls pollTopic) {
        try {
            System.out.println("Registering topic with ID: " + pollTopic.getPoll_id());
            getDao().addOrUpdate(pollTopic);

            entityManager.refresh(pollTopic);
            Integer generatedPollId = pollTopic.getPoll_id();

            return generatedPollId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

}
