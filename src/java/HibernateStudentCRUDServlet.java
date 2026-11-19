package com.smartCompusSecurityWeb;

import com.smartCompusSecurityWeb.hibernate.Student;
import com.smartCompusSecurityWeb.hibernate.StudentHibernateDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HibernateStudentCRUDServlet extends HttpServlet {

    private final StudentHibernateDAO dao =
            new StudentHibernateDAO();

    // =========================
    // GET STUDENT
    // =========================
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        String idParameter =
                request.getParameter("id");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student CRUD</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Hibernate Student CRUD</h1>");

        if (idParameter == null ||
                idParameter.trim().isEmpty()) {

            out.println(
                    "<p>Please enter Student ID.</p>"
            );

        } else {

            try {

                int id =
                        Integer.parseInt(idParameter);

                Student student =
                        dao.getStudent(id);

                if (student != null) {

                    out.println(
                            "<h2>Student Details</h2>"
                    );

                    out.println(
                            "<p><b>Student ID:</b> "
                            + student.getId()
                            + "</p>"
                    );

                    out.println(
                            "<p><b>Name:</b> "
                            + student.getName()
                            + "</p>"
                    );

                    out.println(
                            "<p><b>Semester:</b> "
                            + student.getSemester()
                            + "</p>"
                    );

                    out.println(
                            "<p><b>Division:</b> "
                            + student.getDivision()
                            + "</p>"
                    );

                } else {

                    out.println(
                            "<h2>Student not found.</h2>"
                    );
                }

            } catch (NumberFormatException e) {

                out.println(
                        "<h2>Invalid Student ID.</h2>"
                );
            }
        }

        out.println("</body>");
        out.println("</html>");
    }

    // =========================
    // ADD STUDENT
    // =========================
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        response.setContentType(
                "text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        try {

            int id =
                    Integer.parseInt(
                            request.getParameter("id"));

            String name =
                    request.getParameter("name");

            int semester =
                    Integer.parseInt(
                            request.getParameter("semester"));

            String division =
                    request.getParameter("division");

            Student student =
                    new Student();

            student.setId(id);
            student.setName(name);
            student.setSemester(semester);
            student.setDivision(division);

            dao.addStudent(student);

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Student</title>");
            out.println("</head>");

            out.println("<body>");

            out.println(
                    "<h1>Student Added Successfully</h1>"
            );

            out.println(
                    "<p><b>Student ID:</b> "
                    + id
                    + "</p>"
            );

            out.println(
                    "<p><b>Name:</b> "
                    + name
                    + "</p>"
            );

            out.println(
                    "<p><b>Semester:</b> "
                    + semester
                    + "</p>"
            );

            out.println(
                    "<p><b>Division:</b> "
                    + division
                    + "</p>"
            );

            out.println(
                    "<br><a href='student.jsp'>"
                    + "Back to Student Page"
                    + "</a>"
            );

            out.println("</body>");
            out.println("</html>");

        } catch (NumberFormatException e) {

            out.println("<html>");
            out.println("<body>");

            out.println(
                    "<h2>Invalid Student ID or Semester.</h2>"
            );

            out.println(
                    "<p>"
                    + "Student ID and Semester must be numbers."
                    + "</p>"
            );

            out.println(
                    "<br><a href='student.jsp'>"
                    + "Back to Student Page"
                    + "</a>"
            );

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {

            e.printStackTrace();

            out.println("<html>");
            out.println("<body>");

            out.println(
                    "<h2>Error adding student.</h2>"
            );

            out.println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );

            out.println(
                    "<br><a href='student.jsp'>"
                    + "Back to Student Page"
                    + "</a>"
            );

            out.println("</body>");
            out.println("</html>");
        }
    }
}