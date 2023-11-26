package com.votifysoft.app.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/active")
public class ActivePollsAction  extends BaseAction{

     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session=req.getSession(true);
        session.setAttribute("content", "<h1>This is the Active content.</h1>");
        System.out.println("%%%%%%%"+ session.getAttribute("content"));
        req.getRequestDispatcher("app/activePolls.jsp").forward(req, resp);
       
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
        resp.sendRedirect("./active");

    }
    
}
