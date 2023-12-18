package com.votifysoft.app.beans;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

@Stateless
@Remote
public class NominationBean extends GenericBean<Nominees> implements NominationBeanI {

    @PersistenceContext
    private EntityManager em;

    public boolean fetchChoices() {
        return false;
    }

    @Override
    public boolean registerNominee(Electives elective, List<Nominees> nomineeList,List<String>photopaths) {

        try {
           

            int i=0;
            for (Nominees nomineeChoice : nomineeList) {
                Nominees nominee = new Nominees();
                System.out.println("Poll_IDDDD_" + elective.getElective_id());
                nominee.setElective(elective);
                nominee.setNomineeName(nomineeChoice.getNomineeName());

    
                nominee.setNomineePhoto(photopaths.get(i));
                i++;
                System.out.println("1st choice: " + nomineeChoice);
                System.out.println("Answer.getChoice: " + nominee.getNomineeName());
                System.out.println("This is the nominee photo: "+nominee.getNomineePhoto());

                getDao().addOrUpdate(nominee);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void registerVote(String participant, int nomineeId) {
        try {
            Nominees nominee = em.find(Nominees.class, nomineeId);

            if (nominee == null) {
                System.out.println("nominee is null for Id: " + nomineeId);
                return;
            }

            Electives votedElective = nominee.getElective();

            if (votedElective == null) {
                System.out.println("Voted elective is null for nomineeId: " + nomineeId);
                return;
            }

            int electiveId = votedElective.getElective_id();
            System.out.println("This is the votedElective id voted on " + electiveId);

            Electives existingElectives = em.find(Electives.class, electiveId);

            if (existingElectives == null) {
                System.out.println("Existing elective is null for electiveId: " + electiveId);
                return;
            }

            String currentParticipants = existingElectives.getParticipants();
            String updatedParticipants = currentParticipants + participant;

            System.out.println("Current participants ---->" + currentParticipants);

            if (currentParticipants.contains("null")) {
                System.out.println("No one has voted yet!!");
                updatedParticipants = updatedParticipants.replace("null", "");
            }
            // take account of participants for a specific poll/election
            String jpqlUpdate = "UPDATE electives e SET e.participants = :participants WHERE e.elective_id = :elective_id";
            Query userQuery = em.createQuery(jpqlUpdate);
            userQuery.setParameter("participants", updatedParticipants);
            userQuery.setParameter("elective_id", electiveId);

            userQuery.executeUpdate();

            String jpql = "UPDATE nominees SET votes = votes + 1 WHERE nominee_id = :nomineeId";
            Query query = em.createQuery(jpql);
            query.setParameter("nominee_id", nomineeId);

            int updatedCount = query.executeUpdate();

            if (updatedCount > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("No records were updated for nomineeID: " + nomineeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
