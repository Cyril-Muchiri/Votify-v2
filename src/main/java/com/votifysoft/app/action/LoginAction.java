package com.votifysoft.app.action;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import com.votifysoft.app.beans.AuthBeanI;
import com.votifysoft.model.entity.User;

import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends BaseAction {

    @EJB
    AuthBeanI authBean;

    public  String LoggedInUser;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId")))
            resp.sendRedirect("./home");
        else
            resp.sendRedirect("./");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = serializeForm(User.class, req.getParameterMap());

        try {
            User userDetails = authBean.authenticate(loginUser);

            if (userDetails != null && StringUtils.isNotBlank(userDetails.getUserEmail())) {
                HttpSession httpSession = req.getSession(true);

                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("userName", userDetails.getUserName());
                System.out.println("TAKE NOTE OF THIS "+httpSession.getAttribute("userName"));

                // httpSession.setAttribute("username", userDetails.getUserName());

                resp.sendRedirect("./home");

            } else {
                req.getRequestDispatcher("app/actionFailed.jsp").forward(req, resp);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
