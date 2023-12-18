package com.votifysoft.app.view.helper;

import java.util.List;

import javax.persistence.EntityManager;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public class HtmlRenderActiveElections {

       private final EntityManager eManager;

    public HtmlRenderActiveElections(EntityManager eManager) {
        this.eManager = eManager;
    }

    public String renderElections( int userId){
          List<Polls> pollList = eManager.createQuery("SELECT p FROM Polls p", Polls.class).getResultList();
        List<Answers> answerList = eManager.createQuery("SELECT a FROM Answers a", Answers.class).getResultList();
        return "<h1>This is the rendered elections content</h1> ";
    }
}
