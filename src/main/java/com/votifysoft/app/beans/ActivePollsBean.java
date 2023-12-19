package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Stateless
public class ActivePollsBean extends GenericBean<Polls> implements ActivePollsBeanI {

    @PersistenceContext
    EntityManager eManager;

    public List<Polls> fetchAllPolls() throws SQLException {
        System.out.println("Method for fetching polls executed!!");

        String nativeQuery = "SELECT * FROM polls";
        Query query = eManager.createNativeQuery(nativeQuery, Polls.class);

        return query.getResultList();
    }

    public List<Answers> fetchAllPollChoices() throws SQLException {
        System.out.println("Method for fetching answers executed!!");
        String nativeQuery = "SELECT * FROM answers";
        Query query = eManager.createNativeQuery(nativeQuery, Answers.class);

        return query.getResultList();
    }
}
