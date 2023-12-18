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

import com.votifysoft.app.beans.ActiveElectivesBeanI;
import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

@WebServlet("/showElectives")
public class ElectiveAction extends BaseAction {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    ActiveElectivesBeanI activeElectives;


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            renderPoll(req, resp, Electives.class,Nominees.class, activeElectives.fetchAllElectives(),activeElectives.fetchAllNominees(),sessionUserId);
             System.out.println("doget for showing electives is being called");
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.sendRedirect("./showElectives");
        System.out.println("dopost for showing electives is being called");

    }

}
