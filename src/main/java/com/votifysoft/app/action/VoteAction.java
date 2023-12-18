package com.votifysoft.app.action;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.AnswersBeanI;
import com.votifysoft.app.beans.NominationBeanI;

@WebServlet("/vote")
public class VoteAction extends BaseAction {

    @EJB
    AnswersBeanI answersBean;

    @EJB
    NominationBeanI nominationBeanI;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession(true);

        String userId = String.valueOf(httpSession.getAttribute("userId"));
        if (userId.contains("null")) {
            userId = userId.replace("null", "");
        }

        String topicParameter = request.getParameter("topic");
        String electiveParam=request.getParameter("nomineeId");

        int electiveId=0;
        int topicId = 0;

        if (topicParameter != null) {
            try {
                topicId = Integer.parseInt(topicParameter);
                answersBean.registerVote(userId.concat(","), topicId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (electiveParam !=null) {
             try {
                electiveId = Integer.parseInt(electiveParam);
                nominationBeanI.registerVote(userId.concat(","), electiveId);
                response.sendRedirect("./showElectives");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } 

        response.sendRedirect("./active");
    }
}
