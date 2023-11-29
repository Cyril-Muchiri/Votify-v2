package com.votifysoft.app.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
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
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@WebServlet("/topics")
public class TopicCreatorAction extends BaseAction {

  @EJB
  PollBeanI topicBean;

  @EJB
  AnswersBeanI answersBean;

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    session.setAttribute("content", "<h1>This is the Active content.</h1>");
    req.getRequestDispatcher("app/TopicCreator.jsp").forward(req, resp);

  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    try {
      Map<String, String[]> paramMap = req.getParameterMap();
      Map<String, String[]> choiceParameters = paramMap.entrySet().stream()
          .filter(entry -> entry.getKey().contains("choice"))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

      Map<String, String[]> topicNameParameters = paramMap.entrySet().stream()
          .filter(entry -> entry.getKey().equals("topicName") || entry.getKey().equals("Deadline"))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

      choiceParameters
          .forEach((key, value) -> System.out.println("Choice Parameter: " + key + ": " + Arrays.toString(value)));
      topicNameParameters
          .forEach((key, value) -> System.out.println("Parameter: " + key + ": " + Arrays.toString(value)));

      Polls poll = serializeForm(Polls.class, topicNameParameters);
      topicBean.registerTopic(poll);
      Answers answers = serializeForm(Answers.class, choiceParameters);
      answersBean.registerChoices(answers);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    resp.sendRedirect("./home");

  }

}
