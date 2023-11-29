package com.votifysoft.app.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.votifysoft.model.entity.Answers;

@Stateless
@Remote
public class AnswersBean extends GenericBean<Answers> implements AnswersBeanI {

    @Override
    public boolean registerChoices(Answers topicChoices) {
        getDao().addOrUpdate(topicChoices);
       
        return false;
    }


    
}

