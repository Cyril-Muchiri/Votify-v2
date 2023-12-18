package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

@Stateless
@Remote
public class ActiveElectivesBean extends GenericBean<Electives> implements ActiveElectivesBeanI {

    @PersistenceContext
    EntityManager eManager;

    public List<Electives> fetchAllElectives() throws SQLException {
        System.out.println("method being for Electives executed!!");
        String jpql = "SELECT e FROM Electives e";
        TypedQuery<Electives> query = eManager.createQuery(jpql, Electives.class);

        return query.getResultList();
    }

    public List<Nominees> fetchAllNominees() throws SQLException {
        System.out.println("method being for Nominees executed!!");
        String jpql = "SELECT n FROM Nominees n";
        TypedQuery<Nominees> query = eManager.createQuery(jpql, Nominees.class);

        return query.getResultList();
    }

}
