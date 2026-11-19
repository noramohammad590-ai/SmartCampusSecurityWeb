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

    private static final String URL =
            "jdbc:mysql://localhost:3306/smart_campus_security";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String purpose = request.getParameter("purpose");
        String state = request.getParameter("state");
        String phone = request.getParameter("phone");

        // Validation
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

        // Phone validation
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

        String sql =
                "INSERT INTO visitors "
                + "(name, purpose, state, phone) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            DB_USER,
                            DB_PASSWORD
                    );

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, name.trim());
            ps.setString(2, purpose.trim());
            ps.setString(3, state.trim());
            ps.setString(4, phone.trim());

            ps.executeUpdate();

            ps.close();
            con.close();

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