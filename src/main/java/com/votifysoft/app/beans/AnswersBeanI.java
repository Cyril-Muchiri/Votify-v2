package com.votifysoft.app.beans;

import com.votifysoft.model.entity.Answers;

public interface AnswersBeanI extends GenericBeanI<Answers> {

    boolean registerChoices(Answers answers);
    

}