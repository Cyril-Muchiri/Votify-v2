package com.votifysoft.app.action;

import java.io.IOException;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            renderPoll(req, resp, Polls.class,Answers.class, activePollsBean.fetchAllPolls(),activePollsBean.fetchAllPollChoices(),sessionUserId);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.sendRedirect("./active");

    }

}
