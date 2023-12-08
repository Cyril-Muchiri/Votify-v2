package com.votifysoft.app.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    // @Override
    // public boolean registerChoices(int pollId, Answers topicChoice) {
    //     System.out.println("GVGVFCRTDED!!");
    //     Answers answer = new Answers();

    //     System.out.println("Poll_IDDDD_" + pollId);

    //     // Retrieve the existing Polls instance from the database
    //     Polls poll = em.find(Polls.class, pollId);

    //     if (poll != null) {
    //         System.out.println("Retrieved Poll: " + poll.getPoll_id());

    //         // Associate the Polls instance with topicChoice
    //         topicChoice.setPoll(poll);

    //         // Set values for the Answers instance
    //         answer.setPoll(topicChoice.getPoll());
    //         System.out.println("1st choice: " + topicChoice);
    //         answer.setChoice(topicChoice.getChoice());

    //         System.out.println("Answer.getChoice: " + answer.getChoice());

    //         // Persist the Answers instance
    //         getDao().addOrUpdate(answer);

    //         return true; // or any condition that determines success
    //     } else {
    //         System.out.println("Poll with ID " + pollId + " not found.");
    //         return false;
    //     }
    // }
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

    

    @Override
    public boolean registerVote(int choiceId) {

        getDao().addVote(choiceId);
        return false;
    }

}
