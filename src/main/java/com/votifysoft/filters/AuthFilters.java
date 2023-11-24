package com.votifysoft.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@WebFilter(urlPatterns = "/*")
public class AuthFilters implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpRequest.getSession();

        String servletPath = httpRequest.getServletPath();
        System.out.println("servlet path: " + servletPath);
        System.out.println("context path: " + httpRequest.getContextPath());
        System.out.println("context URI: " + httpRequest.getRequestURI());

        if (httpSession.isNew() || StringUtils.isBlank((String) httpSession.getAttribute("loggedInId"))) {
            System.out.println("1.New Session");
            httpSession.invalidate();

            if (servletPath.equals("/login") || servletPath.equals("/user") || servletPath.contains(".jsp")) {
                filterChain.doFilter(servletRequest, servletResponse);

            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
                servletResponse.getWriter().flush();

            }

        } else {
            if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
                httpResponse.addHeader("AuthTime", DateFormat.getDateTimeInstance().format(new Date()));
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
                servletResponse.getWriter().flush();

            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
