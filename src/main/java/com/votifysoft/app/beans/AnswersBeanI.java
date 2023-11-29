package com.votifysoft.app.beans;

import java.util.List;

import com.votifysoft.model.entity.Answers;

public interface AnswersBeanI extends GenericBeanI<Answers> {

     public boolean registerChoices(List<Answers> topicChoicesList);
    

}