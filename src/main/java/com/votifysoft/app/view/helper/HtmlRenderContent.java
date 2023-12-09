package com.votifysoft.app.view.helper;

import java.util.List;
import javax.persistence.EntityManager;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public class HtmlRenderContent {

    private final EntityManager eManager;

    public HtmlRenderContent(EntityManager eManager) {
        this.eManager = eManager;
    }

    public  String renderMainContentDiv() {
        List<Polls> pollList = eManager.createQuery("SELECT p FROM Polls p", Polls.class).getResultList();
        List<Answers> answerList = eManager.createQuery("SELECT a FROM Answers a", Answers.class).getResultList();
        StringBuilder mainContentBuilder = new StringBuilder();
        mainContentBuilder.append("<div class=\"mainContent\">");

        for (Polls poll : pollList) {
            mainContentBuilder.append("<div class=\"topicDiv\" onclick=\"toggleForm('pollForm')\">")
                .append("<div><h4>").append(poll.getPoll_id()).append(".</h4></div>")
                .append("<div><h4>").append(poll.getTopicName()).append("</h4></div>")
                .append("</div>");

            int topicId = poll.getPoll_id();

            for (Answers answer : answerList) {
                int answerId = answer.getAnswer_id();

                if (getAnswer(answer, topicId) != null) {
                    mainContentBuilder.append("<form action=\"./vote\" method=\"post\">")
                        .append("<input type=\"radio\" id=\"").append(answerId)
                        .append("\" name=\"topic\" value=\"").append(answerId).append("\">")
                        .append("<label for=\" style=\"display: inline;\"").append(answerId).append("\">")
                        .append(getAnswer(answer, topicId)).append("</label>");
                        
                        
                }
            }

            mainContentBuilder.append("<div class=\"buttonDiv\">")
                .append("<div class=\"submit-btn\" style=\"margin-top:5px\" id=\"submit-btn\">")
                .append("<button type=\"submit\">Submit Answer</button>")
                .append("</form>")
                .append("</div>")
                .append("<div class=\"view-btn\" style=\"margin-top:5px\" id=\"view-btn\">")
                .append("<button type=\"submit\">View Results</button>")
                .append("</div>")
                .append("</div>");
        }

        mainContentBuilder.append("</div></div>");
        return mainContentBuilder.toString();
    }


    private String getAnswer(Answers answer, int topicId) {
        Polls poll = answer.getPoll();

        if (poll != null && poll.getPoll_id() == topicId) {
            return answer.getChoice();
        }

        return null;
    }
}
