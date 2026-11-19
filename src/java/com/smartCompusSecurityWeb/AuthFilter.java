package com.smartCompusSecurityWeb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

        System.out.println("AuthFilter started.");
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        HttpSession session =
                req.getSession(false);

        boolean loggedIn =
                session != null
                && session.getAttribute("username") != null;

        String loginURI =
                req.getContextPath() + "/Login.jsp";

        String loginServletURI =
                req.getContextPath() + "/login";

        String requestURI =
                req.getRequestURI();

        boolean loginRequest =
                requestURI.equals(loginURI)
                || requestURI.equals(loginServletURI);

        if (loggedIn || loginRequest) {

            chain.doFilter(
                    request,
                    response
            );

        } else {

            res.sendRedirect(
                    loginURI
            );
        }
    }

    @Override
    public void destroy() {

        System.out.println("AuthFilter stopped.");
    }
}