package com.votifysoft.app.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.votifysoft.app.beans.AnswersBeanI;
import com.votifysoft.app.beans.PollBeanI;
import com.votifysoft.app.beans.UserBeanI;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;
import com.votifysoft.model.entity.User;

@WebServlet("/topics")
public class TopicCreatorAction extends BaseAction {

  @EJB
  PollBeanI topicBean;

  @EJB
  AnswersBeanI answersBean;

  @EJB
  UserBeanI userBeanI;

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    session.setAttribute("content", "<h1>This is the Topics content.</h1>");
    req.getRequestDispatcher("app/TopicCreator.jsp").forward(req, resp);

  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    try {
      Map<String, String[]> paramMap = req.getParameterMap();

      List<String[]> choiceValues = paramMap.entrySet().stream()
          .filter(entry -> entry.getKey().contains("choice"))
          .map(Map.Entry::getValue)
          .collect(Collectors.toList());

      Map<String, String[]> topicNameParameters = paramMap.entrySet().stream()
          .filter(entry -> entry.getKey().equals("topicName") || entry.getKey().equals("Deadline"))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

      Polls poll = serializeForm(Polls.class, topicNameParameters);
      User creator = userBeanI.getUserById((int)(req.getSession(false).getAttribute("userId")));
      poll.setCreator(creator);
      
      topicBean.registerTopic(poll);

      Polls latestPoll=topicBean.getLatestPoll();

      List<Answers> answersList = serializeChoices(choiceValues);
      answersBean.registerChoices(latestPoll, answersList);
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    resp.sendRedirect("./home");
  }

}
