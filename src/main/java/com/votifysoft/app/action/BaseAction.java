package com.votifysoft.app.action;



import javax.servlet.http.HttpServlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

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
}
