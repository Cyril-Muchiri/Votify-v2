package com.votifysoft.app.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.ActivePollsBeanI;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@WebServlet("/active")
public class ActivePollsAction extends BaseAction {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    ActivePollsBeanI activePollsBean;


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        
        try {
            List<Polls> allPolls= activePollsBean.fetchAllPolls();

            for (Polls poll : allPolls) {
                System.out.println(" *****Topic: " + poll.getTopicName());
               
            }

            List<Answers> allAnswers=activePollsBean.fetchAllPollChoices();
            for (Answers answer : allAnswers) {
               
                System.out.println("****Choice: " + answer.getChoice());
            }

            renderPoll(req, resp, Polls.class,Answers.class, activePollsBean.fetchAllPolls(),activePollsBean.fetchAllPollChoices());
        } catch (SQLException e) {

            e.printStackTrace();
        }

        System.out.println("%%%%%%%" + session.getAttribute("content"));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.sendRedirect("./active");

    }

}
