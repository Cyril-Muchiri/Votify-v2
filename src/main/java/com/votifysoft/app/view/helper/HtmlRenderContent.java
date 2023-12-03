package com.votifysoft.app.view.helper;

import java.util.List;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public class HtmlRenderContent {

    public static String renderMainContentDiv(List<?> pollList, List<?> answerList, Class<?> pollClass,
            Class<?> answerClass) {
        StringBuilder mainContentBuilder = new StringBuilder();
        System.out.println("This is the HtmlRenderContent class method");

        // mainContentBuilder.append("<div class=\"height-100 bg-light\">");

        mainContentBuilder.append("<div class=\"mainContent\">");

        for (Object topic : pollList) {

            boolean displayTopicDiv = true;

            if (displayTopicDiv) {

                mainContentBuilder.append("<div class=\"topicDiv\" onclick=\"toggleForm('pollForm')\">")
                        .append("<div><h4>").append(getTopicId(topic)).append(".</h4></div>")
                        .append("<div><h4>").append(getTopic(topic)).append("</h4></div>")
                        .append("</div>");
            }

            int topicId = getTopicId(topic);
        

            for (Object answerValue : answerList) {
                int answerId = getAnswerId(answerValue);
             

                if (getAnswer(answerValue, topicId) != null) {
                    
                    mainContentBuilder.append("<form action=\"./vote\" method=\"post\">")
                            .append("<input type=\"radio\" id=\"").append(answerId)
                            .append("\" name=\"topic\" value=\"").append(answerId).append("\">")
                            .append("<label for=\"").append(answerId).append("\">")
                            .append(getAnswer(answerValue, topicId)).append("</label>");

                            System.out.println("This is the anser Id***"+answerId);
                }
            }

            mainContentBuilder.append("<div class=\"buttonDiv\">")
                    .append("<div class=\"submit-btn\" style=\"margin-top:5px\" id=\"submit-btn\">")
                    .append("<button type=\"submit\">Submit Answer</button>")
                    .append("</div>")
                    .append("<div class=\"view-btn\" style=\"margin-top:5px\" id=\"view-btn\">")
                    .append("<button type=\"submit\">View Results</button>")
                    // .append("</div>")
                    .append("</div>")
                    .append("</form>")
                    .append("</div>");

        }
        mainContentBuilder.append("</div></div>");

        return mainContentBuilder.toString();
    }

    private static String getTopic(Object data) {
        if (data != null) {
            Polls poll = (Polls) data;
            return poll.getTopicName();
        }
        return null;
    }

    private static String getAnswer(Object data, int topicId) {
        if (data != null) {
            Answers answer = (Answers) data;
            if (answer.getPoll_id() == topicId) {
                return answer.getChoice().toString();
            }
        }
        return null;
    }

    private static int getTopicId(Object data) {
        if (data != null) {
            Polls poll = (Polls) data;
            return poll.getPoll_id();
        }
        return -1;
    }


    private static int getAnswerId(Object data) {
        if (data != null) {
            Answers answer = (Answers) data;
            return answer.getAnswer_id();
        }
        return -1;
    }

}
