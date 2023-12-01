package com.votifysoft.app.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.ActivePollsBeanI;
import com.votifysoft.model.entity.Polls;

@WebServlet("/active")
public class ActivePollsAction extends BaseAction {

    @EJB
    ActivePollsBeanI activePollsBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        List<Polls> allPolls;
        try {
            allPolls = activePollsBean.fetchAllPolls();
            renderDiv(req, resp, Polls.class, activePollsBean.fetchAllPolls());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("%%%%%%%" + session.getAttribute("content"));
        // req.getRequestDispatcher("app/activePolls.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.sendRedirect("./active");

    }

}
