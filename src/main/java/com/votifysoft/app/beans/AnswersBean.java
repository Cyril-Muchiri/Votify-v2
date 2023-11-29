package com.votifysoft.app.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.votifysoft.model.entity.Answers;

@Stateless
@Remote
public class AnswersBean extends GenericBean<Answers> implements AnswersBeanI {

    @Override
    public boolean registerChoices(List<Answers> topicChoicesList) {
       
        for (Answers topicChoices : topicChoicesList) {
            // Inspect the content of each Answers object
            System.out.println("Answer ID: " + topicChoices.getAnswer_id());
            System.out.println("Poll ID: " + topicChoices.getPoll_id());
            System.out.println("Votes: " + topicChoices.getVotes());
          
            getDao().addOrUpdate(topicChoices);
        }
       
        return false;
    }


    
}

