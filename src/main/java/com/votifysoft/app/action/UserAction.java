package com.votifysoft.app.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.votifysoft.app.beans.UserBean;
import com.votifysoft.app.beans.UserBeanI;
import com.votifysoft.model.entity.User;

import java.io.IOException;
import java.sql.SQLException;

 @WebServlet("/user")
public class UserAction extends BaseAction {

    UserBeanI userBean = new UserBean();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            userBean.register(serializeForm(User.class, req.getParameterMap()));
        } catch (Exception ex){
            ex.printStackTrace();
        }

        resp.sendRedirect("./");


    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.sendRedirect("./register.html");
    }
}
