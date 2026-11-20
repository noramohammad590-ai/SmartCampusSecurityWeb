package com.smartCompusSecurityWeb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComplaintServlet extends HttpServlet {

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

        String subject =
                request.getParameter("subject");

        String description =
                request.getParameter("description");

        String status =
                request.getParameter("status");


        // =========================
        // VALIDATION
        // =========================

        if (subject == null
                || subject.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Complaint subject is required."
            );

            request.getRequestDispatcher(
                    "Complaints.jsp"
            ).forward(request, response);

            return;
        }


        if (description == null
                || description.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Complaint description is required."
            );

            request.getRequestDispatcher(
                    "Complaints.jsp"
            ).forward(request, response);

            return;
        }


        if (status == null
                || status.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Complaint status is required."
            );

            request.getRequestDispatcher(
                    "Complaints.jsp"
            ).forward(request, response);

            return;
        }


        // =========================
        // INSERT SQL
        // =========================

        String sql =
                "INSERT INTO complaints "
                + "(subject, description, status) "
                + "VALUES (?, ?, ?)";


        try {

            // Load MySQL JDBC Driver
            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );


            // Connect to Railway MySQL
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

                ps.setString(
                        1,
                        subject.trim()
                );

                ps.setString(
                        2,
                        description.trim()
                );

                ps.setString(
                        3,
                        status
                );


                ps.executeUpdate();


                // Success page
                response.sendRedirect(
                        request.getContextPath()
                        + "/ComplaintSuccess.jsp"
                );
            }

        } catch (ClassNotFoundException e) {

            throw new ServletException(
                    "MySQL JDBC Driver not found.",
                    e
            );

        } catch (Exception e) {

            throw new ServletException(
                    "Complaint submission error: "
                    + e.getMessage(),
                    e
            );
        }
    }
}