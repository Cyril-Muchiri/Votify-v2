package com.votifysoft.app.view.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public class HtmlRenderActiveContent {

    private final EntityManager eManager;

    public HtmlRenderActiveContent(EntityManager eManager) {
        this.eManager = eManager;
    }

    public String renderMainContentDiv(Integer userId) {
        List<Polls> pollList = eManager.createQuery("SELECT p FROM Polls p", Polls.class).getResultList();
        List<Answers> answerList = eManager.createQuery("SELECT a FROM Answers a", Answers.class).getResultList();

        StringBuilder mainContentBuilder = new StringBuilder();
        mainContentBuilder.append("<div class=\"mainContent\">");

        for (Polls poll : pollList) {
            mainContentBuilder.append("<div class=\"topicDiv\">")
                    .append("<div><h4>").append(poll.getPoll_id()).append(". ").append(poll.getTopicName())
                    .append("</h4></div>");
            int topicId = poll.getPoll_id();
            String participantId = poll.getParticipants();
            int[] participantArrays = convertToIntArray(participantId);
            List<Integer> participantList = new ArrayList<>();

            for (int participant : participantArrays) {
                participantList.add(participant);
            }

            int[] allParticipants = participantList.stream().mapToInt(Integer::intValue).toArray();
            System.out.println("HJBHBHBB Take Note " + Arrays.toString(allParticipants));

            for (Answers answer : answerList) {
                int answerId = answer.getAnswer_id();
                System.out.println("gfgred----> "+ answerId);

                if (getAnswer(answer, topicId) != null) {
                    mainContentBuilder.append("<form action=\"./vote\" method=\"post\">")
                            .append("<input type=\"radio\" id=\"").append("answer").append(answerId)
                            .append("\" name=\"topic").append(topicId).append("\" value=\"").append(answerId)
                            .append("\">")
                            .append("<label for=\"").append("answer").append(answerId).append("\">")
                            .append(getAnswer(answer, topicId)).append("</label>");
                }
            }

            mainContentBuilder.append("<div class=\"buttonDiv\">")
                    .append("<div class=\"submit-btn\" style=\"margin-top:5px\" id=\"submit-btn\">");
            if (Arrays.toString(allParticipants).contains(userId.toString())) {
                mainContentBuilder.append("<button type=\"submit\" disabled>Submit Answer</button>");
            } else {
                mainContentBuilder.append("<button type=\"submit\">Submit Answer</button>");
            }

            mainContentBuilder.append("</div>")
                    .append("<div class=\"view-btn\" style=\"margin-top:5px\" id=\"view-btn\">")
                    .append("<button type=\"submit\">View Results</button>")
                    .append("</div>")
                    .append("</form>")
                    .append("</div>"); 

            mainContentBuilder.append("</div>");
        }

        mainContentBuilder.append("</div>"); 
        return mainContentBuilder.toString();
    }

    private String getAnswer(Answers answer, int topicId) {
        Polls poll = answer.getPoll();

        if (poll != null && poll.getPoll_id() == topicId) {
            return answer.getChoice();
        }

        return null;
    }

    public int[] convertToIntArray(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] result = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            try {
                result[i] = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}