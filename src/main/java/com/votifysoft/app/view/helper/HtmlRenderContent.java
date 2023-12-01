package com.votifysoft.app.view.helper;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class HtmlRenderContent {

    public static String renderMainContentDiv(List<?> dataList, Class<?> dataClass) {
        StringBuilder mainContentBuilder = new StringBuilder();
        System.out.println("This is the HtmlRenderContent class method");
        // Opening div with class attribute
        mainContentBuilder.append("<div class=\"height-100 bg-light\">");

        mainContentBuilder.append("<div class=\"mainContent\">");

        for (Object data : dataList) {

            boolean displayTopicDiv = true;

            if (displayTopicDiv) {

                mainContentBuilder.append("<div class=\"topicDiv\" onclick=\"toggleForm('pollForm')\">")
                        .append("<div><h4>1.</h4></div>")
                        .append("<div><h4>").append(getTopic(data)).append("</h4></div>")
                        .append("</div>");
            }

            mainContentBuilder.append("<div class=\"topic\" id=\"pollForm\" style=\"display: ;\">")
                    .append("<form action=\"./answer\" method=\"post\">")
                    .append("<label for=\"topic\">Choose:</label>")
                    .append("<input type=\"radio\" id=\"topic1\" name=\"topic\" value=\"topic1\">")
                    .append("<label for=\"topic1\">Topic 1</label>")
                    .append("<input type=\"radio\" id=\"topic2\" name=\"topic\" value=\"topic2\">")
                    .append("<label for=\"topic2\">Topic 2</label>")
                    .append("<div class=\"buttonDiv\">")
                    .append("<div class=\"submit-btn\" style=\"margin-top:5px\" id=\"submit-btn\">")
                    .append("<button type=\"submit\">Submit Answer</button>")
                    .append("</div>")
                    .append("<div class=\"view-btn\" style=\"margin-top:5px\" id=\"view-btn\">")
                    .append("<button type=\"submit\">View Results</button>")
                    .append("</div>")
                    .append("</div>")
                    .append("</form>")
                    .append("</div>");

        }

        // Closing mainContent and outer div
        mainContentBuilder.append("</div></div>");

        return mainContentBuilder.toString();
    }

    // Assume this method extracts the favorite color from your data object
    private static String getTopic(Object data) {
        // Implement your logic to get the favorite color from the data object
        return "What is your favourite colour"; // Placeholder, replace with actual logic
    }

}
