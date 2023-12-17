package com.votifysoft.app.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.votifysoft.model.entity.Electives;


@Stateless
@Remote
public class ElectiveBean extends GenericBean<Electives> implements ElectiveBeanI {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Integer registerElective(Electives elective) {

        try {
            System.out.println("Registering elective with title : " + elective.getElective_title());
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
            String jpql = "SELECT e FROM Electives e ORDER BY e.createdAt DESC";

            TypedQuery<Electives> query = entityManager.createQuery(jpql, Electives.class);
            query.setMaxResults(1);

            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
