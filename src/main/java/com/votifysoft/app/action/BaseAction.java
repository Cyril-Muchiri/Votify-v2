package com.votifysoft.app.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import com.votifysoft.app.view.helper.HtmlRenderContent;
import com.votifysoft.model.entity.Answers;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class BaseAction extends HttpServlet {
    public static int sessionUserId;

    @SuppressWarnings("unchecked")
    public <T> T serializeForm(Class<?> clazz, Map<String, String[]> requestMap) {

        T clazzInstance;

        try {
            clazzInstance = (T) clazz.getDeclaredConstructor().newInstance();

            DateConverter converter = new DateConverter(null);
            converter.setPattern("yyyy-MM-dd");

            ConvertUtils.register(converter, Date.class);

            requestMap.forEach((k, v) -> System.out.println(k + " " + v[0]));

            BeanUtils.populate(clazzInstance, requestMap);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                | InstantiationException e) {
            throw new RuntimeException(e);
        }

        return clazzInstance;
    }

    protected List<Answers> serializeChoices(List<String[]> choiceValues) {
        List<Answers> answersList = new ArrayList<>();

        for (String[] choice : choiceValues) {
            Answers answers = new Answers();
            String choiceValue = choice[0].trim();

            if (!isNumeric(choiceValue)) {
                answers.setChoice(choiceValue);
                answersList.add(answers);
            }
        }

        return answersList;
    }

    public void renderPoll(HttpServletRequest request, HttpServletResponse response,
            Class<?> poll, Class<?> answer, List<?> pollList,List<?> answerList)
            throws ServletException, IOException {

        String actionParam = StringUtils.trimToEmpty(request.getParameter("action"));
        System.out.println("Action Parameter: " + actionParam);

        

        String content = HtmlRenderContent.renderMainContentDiv(pollList, answerList,poll,answer);

        if ("add".equals(actionParam)) {
            request.setAttribute("content", content);
        } else {
            request.setAttribute("content", content);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("app/activePolls.jsp");
        dispatcher.forward(request, response);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
