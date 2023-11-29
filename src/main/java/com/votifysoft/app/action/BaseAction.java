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

import com.votifysoft.model.entity.Answers;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class BaseAction extends HttpServlet {

    
    @SuppressWarnings("unchecked")
    public <T> T serializeForm(Class<?> clazz, Map<String, String[]> requestMap) {

        T clazzInstance;

        try {
            clazzInstance = (T) clazz.getDeclaredConstructor().newInstance();

            DateConverter converter = new DateConverter( null );
            converter.setPattern("yyyy-MM-dd");

            ConvertUtils.register(converter, Date.class);

            requestMap.forEach((k,v)-> System.out.println(k + " " + v[0]));

            BeanUtils.populate(clazzInstance, requestMap);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e ) {
            throw new RuntimeException(e);
        }

        return clazzInstance;
    }

    protected List<Answers> serializeChoices(Map<String, String[]> choiceParameters) {
        List<Answers> answersList = new ArrayList<>();
    
        // Extract and serialize each choice
        for (Map.Entry<String, String[]> entry : choiceParameters.entrySet()) {
            Answers answers = new Answers();
            
            // Assuming that your Answers class has a 'choice' attribute
            String choiceKey = entry.getKey();
            int choiceIndex = Integer.parseInt(choiceKey.substring("choice".length())) - 1;
    
            if (choiceIndex >= 0) {
                String choiceValue = entry.getValue()[0]; // Assuming each choice parameter has a single value
    
                // Set the 'choice' attribute of Answers
                answers.setChoice(choiceValue);
    
                // You may need to set other attributes of Answers based on your data model
                // For example: answers.setPollId(somePollId);
    
                answersList.add(answers);
            }
        }
    
        return answersList;
    }
    


   
}
