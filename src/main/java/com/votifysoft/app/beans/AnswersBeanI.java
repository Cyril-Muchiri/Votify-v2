package com.votifysoft.app.beans;

import java.util.List;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public interface AnswersBeanI extends GenericBeanI<Answers> {

   public boolean registerChoices(Polls poll, List<Answers> topicChoices);

     public void registerVote(String poll,int choiceId);

     public boolean fetchChoices();

}