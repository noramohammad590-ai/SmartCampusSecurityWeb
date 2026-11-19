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

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smart_campus_security";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // =========================
    // GET REQUEST
    // =========================
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String studentId =
                request.getParameter("studentId");

        // If no Student ID is provided
        if (studentId == null ||
                studentId.trim().isEmpty()) {

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<html>"
                    + "<head>"
                    + "<title>Student Search</title>"
                    + "</head>"

                    + "<body>"

                    + "<h2>Student Search</h2>"

                    + "<form method='post' action='student'>"

                    + "Student ID: "

                    + "<input type='text' "
                    + "name='studentId'>"

                    + "<input type='submit' "
                    + "value='Search'>"

                    + "</form>"

                    + "</body>"
                    + "</html>"
            );

            return;
        }

        searchStudent(
                request,
                response,
                studentId
        );
    }


    // =========================
    // POST REQUEST
    // =========================
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String studentId =
                request.getParameter("studentId");

        // Validation
        if (studentId == null ||
                studentId.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Student ID is required."
            );

            request.getRequestDispatcher(
                    "StudentResult.jsp"
            ).forward(request, response);

            return;
        }

        searchStudent(
                request,
                response,
                studentId
        );
    }


    // =========================
    // SEARCH STUDENT
    // =========================
    private void searchStudent(
            HttpServletRequest request,
            HttpServletResponse response,
            String studentId)
            throws ServletException, IOException {

        String sql =
                "SELECT id, name, semester, division "
                + "FROM students WHERE id = ?";

        try {

            // Load MySQL Driver
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

                int id;

                // Convert Student ID to integer
                try {

                    id = Integer.parseInt(
                            studentId.trim()
                    );

                } catch (NumberFormatException e) {

                    request.setAttribute(
                            "error",
                            "Please enter a valid numeric Student ID."
                    );

                    request.getRequestDispatcher(
                            "StudentResult.jsp"
                    ).forward(request, response);

                    return;
                }

                // Set ID
                ps.setInt(1, id);

                try (
                    ResultSet rs =
                            ps.executeQuery()
                ) {

                    if (rs.next()) {

                        // Student ID
                        request.setAttribute(
                                "studentId",
                                rs.getInt("id")
                        );

                        // Student Name
                        request.setAttribute(
                                "studentName",
                                rs.getString("name")
                        );

                        // Semester
                        request.setAttribute(
                                "semester",
                                rs.getInt("semester")
                        );

                        // Division
                        request.setAttribute(
                                "division",
                                rs.getString("division")
                        );

                    } else {

                        request.setAttribute(
                                "error",
                                "Student not found."
                        );
                    }

                    // Open result page
                    request.getRequestDispatcher(
                            "StudentResult.jsp"
                    ).forward(request, response);
                }
            }

        } catch (ClassNotFoundException e) {

            throw new ServletException(
                    "MySQL JDBC Driver not found.",
                    e
            );

        } catch (Exception e) {

            throw new ServletException(
                    "Student search error: "
                    + e.getMessage(),
                    e
            );
        }
    }
}