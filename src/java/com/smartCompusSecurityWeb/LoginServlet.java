package com.smartCompusSecurityWeb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    // ==============================
    // Railway MySQL Database Connection
    // ==============================

    private static final String URL =
            "jdbc:mysql://"
            + System.getenv("MYSQL_PUBLIC_HOST")
            + ":"
            + System.getenv("MYSQL_PUBLIC_PORT")
            + "/smart_campus_security";

    private static final String DB_USER =
            System.getenv("MYSQLUSER");

    private static final String DB_PASSWORD =
            System.getenv("MYSQLPASSWORD");

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get ID and password from Login.jsp
        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        // Check login account
        String sql =
                "SELECT * FROM login_accounts "
                + "WHERE username = ? AND password = ?";

        try {

            // Load MySQL JDBC Driver
            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            // Connect to Railway MySQL database
            try (
                Connection con =
                        DriverManager.getConnection(
                                URL,
                                DB_USER,
                                DB_PASSWORD
                        );

                PreparedStatement ps =
                        con.prepareStatement(sql)
            ) {

                // Set ID and password
                ps.setString(
                        1,
                        username
                );

                ps.setString(
                        2,
                        password
                );

                // Execute query
                try (
                    ResultSet rs =
                            ps.executeQuery()
                ) {

                    // Login successful
                    if (rs.next()) {

                        HttpSession session =
                                request.getSession();

                        // Store login information
                        session.setAttribute(
                                "username",
                                username
                        );

                        session.setAttribute(
                                "role",
                                rs.getString("role")
                        );

                        session.setAttribute(
                                "personId",
                                rs.getObject("person_id")
                        );

                        // Session expires after 15 minutes
                        session.setMaxInactiveInterval(
                                15 * 60
                        );

                        // Go to Home page
                        response.sendRedirect(
                                request.getContextPath()
                                + "/home.jsp"
                        );

                    } else {

                        // Login failed
                        response.sendRedirect(
                                request.getContextPath()
                                + "/Login.jsp?error=invalid"
                        );
                    }
                }
            }

        } catch (Exception e) {

            throw new ServletException(
                    "Login error: "
                    + e.getMessage(),
                    e
            );
        }
    }
}