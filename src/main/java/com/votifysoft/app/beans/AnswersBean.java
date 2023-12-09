package com.votifysoft.app.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Stateless
@Remote
public class AnswersBean extends GenericBean<Answers> implements AnswersBeanI {

    @PersistenceContext
    private EntityManager em;

    public boolean fetchChoices() {
        return false;
    }

    @Override
    public boolean registerChoices(Polls poll, List<Answers> topicChoices) {
        System.out.println("GVGVFCRTDED!!");
        try {
            for (Answers topicChoice : topicChoices) {
                Answers answer = new Answers();
                System.out.println("Poll_IDDDD_" + poll.getPoll_id());
                answer.setPoll(poll);
                answer.setChoice(topicChoice.getChoice());
                System.out.println("1st choice: " + topicChoice);
                System.out.println("Answer.getChoice: " + answer.getChoice());

                getDao().addOrUpdate(answer);
                
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void registerVote(String participant, int answerId) {
        try {

            Answers answer = em.find(Answers.class, answerId);
            Polls votedPoll=answer.getPoll();

            int pollId=votedPoll.getPoll_id();

            System.out.println("This is the pollId voted on "+pollId);
           
            String jpqlUpdate = "UPDATE Polls p SET p.participants = :participants WHERE p.poll_id = :poll_id";
            Query userQuery = em.createQuery(jpqlUpdate);
            userQuery.setParameter("participants", participant);
            userQuery.setParameter("poll_id", pollId);

            userQuery.executeUpdate();
            
            

            String jpql = "UPDATE Answers SET votes = votes + 1 WHERE answer_id = :answerId";
            Query query = em.createQuery(jpql);
            query.setParameter("answerId", answerId);

            int updatedCount = query.executeUpdate();

            if (updatedCount > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("No records were updated for answerId: " + answerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
