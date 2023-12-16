package com.votifysoft.app.beans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.action.BaseAction;

@WebServlet("/message")
public class MessageAction extends BaseAction {
     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session=req.getSession(true);
        session.setAttribute("content", "<h1>This is the Message content.</h1>");
        System.out.println("%%%%%%%"+ session.getAttribute("content"));
        req.getRequestDispatcher("app/message.jsp").forward(req, resp);
        
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
        resp.sendRedirect("./message");

    }
    
}
