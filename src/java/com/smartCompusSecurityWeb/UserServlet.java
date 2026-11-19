package com.smartCompusSecurityWeb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smart_campus_security";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name =
                request.getParameter("name");

        String role =
                request.getParameter("role");

        String phone =
                request.getParameter("phone");

        // =========================
        // VALIDATION
        // =========================

        if (name == null
                || name.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Name is required."
            );

            request.getRequestDispatcher(
                    "Users.jsp"
            ).forward(request, response);

            return;
        }

        if (role == null
                || role.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Role is required."
            );

            request.getRequestDispatcher(
                    "Users.jsp"
            ).forward(request, response);

            return;
        }

        if (phone == null
                || phone.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Phone number is required."
            );

            request.getRequestDispatcher(
                    "Users.jsp"
            ).forward(request, response);

            return;
        }

        // Phone must contain exactly 10 digits
        if (!phone.matches("[0-9]{10}")) {

            request.setAttribute(
                    "error",
                    "Phone number must contain exactly 10 digits."
            );

            request.getRequestDispatcher(
                    "Users.jsp"
            ).forward(request, response);

            return;
        }

        // =========================
        // INSERT SQL
        // =========================

        String sql =
                "INSERT INTO users "
                + "(name, role, phone) "
                + "VALUES (?, ?, ?)";

        // =========================
        // DATABASE CONNECTION
        // =========================

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

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
                        name.trim()
                );

                ps.setString(
                        2,
                        role.trim()
                );

                ps.setString(
                        3,
                        phone.trim()
                );

                ps.executeUpdate();

                // Success page
                response.sendRedirect(
                        request.getContextPath()
                        + "/UserSuccess.jsp"
                );
            }

        } catch (Exception e) {

            throw new ServletException(
                    "User registration error: "
                    + e.getMessage(),
                    e
            );
        }
    }
}