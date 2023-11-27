package com.votifysoft.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.TopicBeanI;
import com.votifysoft.model.entity.Topics;
import com.votifysoft.model.entity.User;

@WebServlet("/topics")
public class TopicCreatorAction extends BaseAction {

    @EJB
    TopicBeanI topicBean;
    
      public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session=req.getSession(true);
        session.setAttribute("content", "<h1>This is the Active content.</h1>");
        // System.out.println("%%%%%%%"+ session.getAttribute("content"));
        req.getRequestDispatcher("app/TopicCreator.jsp").forward(req, resp);
       
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
        

         try {
        Map<String, String[]> paramMap = req.getParameterMap();
        paramMap.forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));

      Topics topic= serializeForm(Topics.class, paramMap);
        System.out.println("Serialized User: " + topic.getName());
        System.out.println("Serialized User: " + topic.getId());

        topicBean.registerTopic(topic);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    resp.sendRedirect("./");

    }
    
}
