package com.votifysoft.app.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.votifysoft.model.entity.Answers;

@Stateless
@Remote
public class AnswersBean extends GenericBean<Answers> implements AnswersBeanI {

    @Override
    public boolean registerChoices(int pollId,List<Answers> topicChoicesList) {
       
        for (Answers topicChoices : topicChoicesList) {
            System.out.println("Answer ID: " + topicChoices.getAnswer_id());
            System.out.println("Poll ID: " + topicChoices.getPoll_id());
            System.out.println("Votes: " + topicChoices.getVotes());
          
            topicChoices.setPoll_id(pollId);
            getDao().addOrUpdate(topicChoices);
            
        }
       
        return false;
    }


    
}

