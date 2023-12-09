package com.votifysoft.app.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.AnswersBeanI;

@WebServlet("/vote")
public class VoteAction extends BaseAction {

    @EJB
    AnswersBeanI answersBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession(true);

        Map<String, String[]> paramMap = request.getParameterMap();
        paramMap.forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));

        System.out.println("Take note of this***** " + request.getParameter("topic"));
        String userId = String.valueOf(httpSession.getAttribute("userId"));

        answersBean.registerVote(userId.concat(","), Integer.parseInt(request.getParameter("topic")));
System.out.println("Method skipped!!");
        response.sendRedirect("./active");
    }
}
