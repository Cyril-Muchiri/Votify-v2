package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Stateless
public class ActivePollsBean extends GenericBean<Polls> implements ActivePollsBeanI {

    @PersistenceContext
    EntityManager eManager;

    public List<Polls> fetchAllPolls() throws SQLException {
        System.out.println("method being for topics executed!!");
        String jpql = "SELECT p FROM Polls p";
        TypedQuery<Polls> query = eManager.createQuery(jpql, Polls.class);

        return query.getResultList();
    }

    public List<Answers> fetchAllPollChoices() throws SQLException {
        System.out.println("method being for answers executed!!");
        String jpql = "SELECT a FROM Answers a";
        TypedQuery<Answers> query = eManager.createQuery(jpql, Answers.class);

        return query.getResultList();
    }

}
