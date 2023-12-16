package com.votifysoft.app.beans;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.votifysoft.model.entity.AuditLog;

import java.io.Serializable;

@Singleton
public class AuditLogBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void log(@Observes AuditLog auditLog){
        System.out.println(" log registered!!");
        em.merge(auditLog);
    }

}