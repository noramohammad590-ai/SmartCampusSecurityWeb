package com.smartCompusSecurityWeb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitorServlet extends HttpServlet {

    // =========================
    // Railway MySQL Database Connection
    // =========================

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

        String name =
                request.getParameter("name");

        String purpose =
                request.getParameter("purpose");

        String state =
                request.getParameter("state");

        String phone =
                request.getParameter("phone");


        // =========================
        // VALIDATION
        // =========================

        if (name == null || name.trim().isEmpty()
                || purpose == null || purpose.trim().isEmpty()
                || state == null || state.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "All fields are required."
            );

            request.getRequestDispatcher(
                    "Visitors.jsp"
            ).forward(request, response);

            return;
        }


        // =========================
        // PHONE VALIDATION
        // =========================

        if (!phone.matches("[0-9]{10}")) {

            request.setAttribute(
                    "error",
                    "Phone number must contain exactly 10 digits."
            );

            request.getRequestDispatcher(
                    "Visitors.jsp"
            ).forward(request, response);

            return;
        }


        // =========================
        // INSERT SQL
        // =========================

        String sql =
                "INSERT INTO visitors "
                + "(name, purpose, state, phone) "
                + "VALUES (?, ?, ?, ?)";


        try {

            // Load MySQL JDBC Driver
            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );


            // Connect to Railway MySQL
            Connection con =
                    DriverManager.getConnection(
                            URL,
                            DB_USER,
                            DB_PASSWORD
                    );

            PreparedStatement ps =
                    con.prepareStatement(sql);


            // Set values
            ps.setString(
                    1,
                    name.trim()
            );

            ps.setString(
                    2,
                    purpose.trim()
            );

            ps.setString(
                    3,
                    state.trim()
            );

            ps.setString(
                    4,
                    phone.trim()
            );


            // Execute INSERT
            ps.executeUpdate();


            // Close resources
            ps.close();
            con.close();


            // Success page
            response.sendRedirect(
                    request.getContextPath()
                    + "/VisitorSuccess.jsp"
            );


        } catch (Exception e) {

            throw new ServletException(
                    "Visitor registration failed: "
                    + e.getMessage(),
                    e
            );
        }
    }
}