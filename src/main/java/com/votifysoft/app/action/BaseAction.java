package com.votifysoft.app.action;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

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
            converter.setPattern("yyyy-mm-dd");
            ConvertUtils.register(converter, Date.class);

            requestMap.forEach((k,v)-> System.out.println(k + " " + v[0]));

            BeanUtils.populate(clazzInstance, requestMap);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e ) {
            throw new RuntimeException(e);
        }

        return clazzInstance;
    }

    //  public void renderPage(HttpServletRequest request, HttpServletResponse response, int activeMenu,
    //     Class<?> entity, List<?> entityList)
    //         throws ServletException, IOException {

    //     request.setAttribute("activeMenu", activeMenu);

    //     if (StringUtils.trimToEmpty(request.getParameter("action")).equals("add"))
    //         request.setAttribute("content", HtmlCmpRender.form(entity));
    //     else
    //         request.setAttribute("content", HtmlCmpRender.table(entityList, entity));

    //     RequestDispatcher dispatcher = request.getRequestDispatcher("./app/index.jsp");
    //     dispatcher.forward(request, response);
    // }
}