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

            if (answer == null) {
                System.out.println("Answer is null for answerId: " + answerId);
                return;
            }

            Polls votedPoll = answer.getPoll();

            if (votedPoll == null) {
                System.out.println("Voted poll is null for answerId: " + answerId);
                return;
            }

            int pollId = votedPoll.getPoll_id();
            System.out.println("This is the pollId voted on " + pollId);

            Polls existingPoll = em.find(Polls.class, pollId);

            if (existingPoll == null) {
                System.out.println("Existing poll is null for pollId: " + pollId);
                return;
            }

            String currentParticipants = existingPoll.getParticipants();
            String updatedParticipants = currentParticipants + participant;

            System.out.println("Current participants ---->" + currentParticipants);

            if (currentParticipants.contains("null")) {
                System.out.println("No one has voted yet!!");
                updatedParticipants = updatedParticipants.replace("null", "");
            }

            // Update participants
            String nativeUpdate = "UPDATE Polls SET participants = :participants WHERE poll_id = :poll_id";
            Query userQuery = em.createNativeQuery(nativeUpdate);
            userQuery.setParameter("participants", updatedParticipants);
            userQuery.setParameter("poll_id", pollId);

            userQuery.executeUpdate();
            
            // update votes
            String nativeQuery = "UPDATE Answers SET votes = votes + 1 WHERE answer_id = :answerId";
            Query query = em.createNativeQuery(nativeQuery);
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
