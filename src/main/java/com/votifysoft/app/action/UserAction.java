package com.votifysoft.app.action;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.votifysoft.app.beans.UserBeanI;
import com.votifysoft.model.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/register")
public class UserAction extends BaseAction {

    @EJB
    private UserBeanI userBean;
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> paramMap = req.getParameterMap();
            paramMap.forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));
    
            User user = serializeForm(User.class, paramMap);
            System.out.println("Serialized User: " + user.getUserName());
            System.out.println("Serialized User: " + user.getUserEmail());
    
            userBean.addOrUpdate(user);
            resp.sendRedirect("./");
    
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
    
            if (isDuplicateEntryViolation(sqlex)) {
                req.setAttribute("errorMessage", "Email is already registered.");
            } else {
                req.setAttribute("errorMessage", "Registration failed due to a database error.");
            }
    
            req.getRequestDispatcher("app/registrationFailed.jsp").forward(req, resp);
    
        } catch (Exception ex) {
            ex.printStackTrace();
    
            req.setAttribute("errorMessage", "Registration failed due to an unexpected error.");
            req.getRequestDispatcher("app/registrationFailed.jsp").forward(req, resp);
    
        }
    }
    
    // Helper method to check if the SQLException is a duplicate entry violation
    private boolean isDuplicateEntryViolation(SQLException sqlex) {
        return sqlex.getErrorCode() == 1062; // MySQL error code for duplicate entry violation
    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {

            e.printStackTrace();
        }
    }
}
