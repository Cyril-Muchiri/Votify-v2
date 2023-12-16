package com.votifysoft.app.beans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Polls;

public class ElectiveBean extends GenericBean<Electives> implements ElectiveBeanI {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Integer registerElective(Electives elective) {

        try {
            System.out.println("Registering elective with ID: " + elective.getElective_id());
            getDao().addOrUpdate(elective);

            Integer generatedPollId = elective.getElective_id();

            return generatedPollId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Electives getLatestElective() {
        try {
            String jpql = "SELECT p FROM Polls p ORDER BY p.createdAt DESC";

            TypedQuery<Electives> query = entityManager.createQuery(jpql, Electives.class);
            query.setMaxResults(1);

            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
