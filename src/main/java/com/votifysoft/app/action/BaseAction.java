package com.votifysoft.app.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import com.votifysoft.app.view.helper.HtmlRenderActiveContent;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Nominees;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class BaseAction extends HttpServlet {

    @PersistenceContext
    private EntityManager entityManager;

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

    protected List<Nominees> serializeNominees(List<String[]> choiceValues) {
        List<Nominees> nomineeList = new ArrayList<>();

        for (String[] choice : choiceValues) {
            String nomineeName = choice[0].trim();
            Nominees nominee = new Nominees();
            
            if (!isNumeric(nomineeName)) {
                nominee.setNomineeName(nomineeName);
                 nomineeList.add(nominee);
            }

           
        }

        return nomineeList;
    }

    protected <T> List<T> serialize(List<String[]> values, Function<String[], T> converter) {
        List<T> resultList = new ArrayList<>();

        for (String[] value : values) {
            T convertedValue = converter.apply(value);

            if (convertedValue != null) {
                resultList.add(convertedValue);
            }
        }

        return resultList;
    }

    public void renderPoll(HttpServletRequest request, HttpServletResponse response,
            Class<?> poll, Class<?> answer, List<?> pollList, List<?> answerList, int activeUserId)
            throws ServletException, IOException {

        String actionParam = StringUtils.trimToEmpty(request.getParameter("action"));
        System.out.println("Action Parameter: " + actionParam);

        HtmlRenderActiveContent renderContent = new HtmlRenderActiveContent(entityManager);
        String content = renderContent.renderMainContentDiv(activeUserId);

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