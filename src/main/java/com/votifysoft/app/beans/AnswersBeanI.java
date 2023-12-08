package com.votifysoft.app.beans;

import java.util.List;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public interface AnswersBeanI extends GenericBeanI<Answers> {

     // public boolean registerChoices(int pollId,List<Answers> topicChoicesList);
   public boolean registerChoices(Polls poll, List<Answers> topicChoices);

     public boolean registerVote(int choiceId);

}