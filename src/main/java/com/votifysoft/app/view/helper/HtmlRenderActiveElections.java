package com.votifysoft.app.view.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;


public class HtmlRenderActiveElections {

    private final EntityManager eManager;

    public HtmlRenderActiveElections(EntityManager eManager) {
        this.eManager = eManager;
    }

   public String renderElections(Integer userId) {
    List<Electives> electivesList = eManager.createQuery("SELECT e FROM Electives e", Electives.class)
            .getResultList();
    List<Nominees> nomineesList = eManager.createQuery("SELECT n FROM Nominees n", Nominees.class)
            .getResultList();

    StringBuilder mainContentBuilder = new StringBuilder();
    mainContentBuilder.append("<div class=\"mainContentElections\">");

    for (Electives elective : electivesList) {
        mainContentBuilder.append("<div class=\"electiveTitle\"><h4>")
                .append(elective.getElective_id()).append(". ")
                .append(elective.getElectiveTitle()).append("</h4></div>")
                .append("<div class=\"styleCards\">");

        int topicId = elective.getElective_id();
        String participantId = elective.getParticipants();
        int[] participantArrays = convertToIntArray(participantId);
        List<Integer> participantList = Arrays.stream(participantArrays).boxed().collect(Collectors.toList());

        for (Nominees nominee : nomineesList) {
            int nomineeId = nominee.getNominee_id();

            if (getNomineeName(nominee, topicId) != null) {
                mainContentBuilder.append("<div class=\"card-container\">")
                .append("<div><img class=\"round\" src=\"app/artifacts/").append(getPhoto(nominee, topicId)).append("\"/></div>")
                        .append("<div>")
                        .append("<h3>").append(getNomineeName(nominee, topicId)).append("</h3>")
                        .append("<form action=\"./vote\" method=\"post\">")
                        .append("<input type=\"hidden\" name=\"nomineeId\" value=\"").append(nomineeId).append("\">")
                        .append("<div class=\"buttons\">");

                if (participantList.contains(userId)) {
                    mainContentBuilder.append("<button class=\"primary\" type=\"submit\" disabled>already voted</button>");
                } else {
                    mainContentBuilder.append("<button type=\"submit\">Vote</button>");
                }

                mainContentBuilder.append("</div>")
                        .append("<h5>Total : ").append(getVoteTotal(nominee, topicId)).append(" </h5>")
                        .append("</form></div></div>");
            }
        }
        mainContentBuilder.append("</div></div>");
    }

    return mainContentBuilder.toString();
}

    private String getNomineeName(Nominees nominee, int electiveId) {
        Electives electives = nominee.getElective();

        if (electives != null && electives.getElective_id() == electiveId) {
            return nominee.getNomineeName();
        }

        return null;
    }

    private String getPhoto(Nominees nominee, int electiveId) {
        Electives electives = nominee.getElective();

        if (electives != null && electives.getElective_id() == electiveId) {
            return nominee.getNomineePhoto();
        }

        return null;
    }

      private int getVoteTotal(Nominees nominee, int electiveId) {
        Electives electives = nominee.getElective();

        if (electives != null && electives.getElective_id() == electiveId) {
            return nominee.getVotes();
        }
        return 0;
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
