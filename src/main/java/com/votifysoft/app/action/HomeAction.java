package com.votifysoft.app.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.ActiveElectivesBeanI;
import com.votifysoft.app.beans.ActivePollsBeanI;
import com.votifysoft.app.beans.UserBeanI;

@WebServlet("/home")
public class HomeAction extends BaseAction {

    @EJB
    ActiveElectivesBeanI activeElectivesBeanI;

    @EJB
    ActivePollsBeanI activePollsBeanI;

    @EJB
    UserBeanI userBeanI;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        try {
            httpSession.setAttribute("totalNominees", activeElectivesBeanI.fetchAllNominees().size());
            httpSession.setAttribute("totalActivePolls", activePollsBeanI.fetchAllPolls().size());
            httpSession.setAttribute("totalElectives", activeElectivesBeanI.fetchAllElectives().size());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpSession.setAttribute("totalUsers", userBeanI.getAllUsers());

        req.getRequestDispatcher("app/home.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("./home");
    }

}
