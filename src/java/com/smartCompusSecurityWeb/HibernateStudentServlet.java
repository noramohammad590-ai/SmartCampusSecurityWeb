package com.smartCompusSecurityWeb;

import com.smartCompusSecurityWeb.hibernate.Student;
import com.smartCompusSecurityWeb.spring.AppConfig;
import com.smartCompusSecurityWeb.spring.StudentController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateStudentServlet extends HttpServlet {

    private ApplicationContext context;
    private StudentController controller;

    @Override
    public void init() throws ServletException {

        context =
                new AnnotationConfigApplicationContext(
                        AppConfig.class
                );

        controller =
                context.getBean(StudentController.class);

        System.out.println(
                "Spring Dependency Injection started successfully."
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter =
                request.getParameter("id");

        if (idParameter == null ||
                idParameter.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Please enter Student ID."
            );

            request.getRequestDispatcher(
                    "/Students.jsp"
            ).forward(request, response);

            return;
        }

        try {

            int id =
                    Integer.parseInt(idParameter);

            Student student =
                    controller.getStudent(id);

            if (student != null) {

                request.setAttribute(
                        "student",
                        student
                );

            } else {

                request.setAttribute(
                        "error",
                        "Student not found."
                );
            }

            request.getRequestDispatcher(
                    "/Students.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Invalid Student ID."
            );

            request.getRequestDispatcher(
                    "/Students.jsp"
            ).forward(request, response);
        }
    }

    // =========================
    // ADD / UPDATE / DELETE
    // =========================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        response.setContentType(
                "text/html;charset=UTF-8"
        );

        PrintWriter out =
                response.getWriter();

        String action =
                request.getParameter("action");

        // =========================
        // DELETE
        // =========================

        if ("delete".equals(action)) {

            try {

                int id =
                        Integer.parseInt(
                                request.getParameter("id")
                        );

                Student student =
                        controller.getStudent(id);

                if (student != null) {

                    controller.deleteStudent(id);

                    out.println(
                            "<html><body>"
                    );

                    out.println(
                            "<h1>Student Deleted Successfully</h1>"
                    );

                    out.println(
                            "<p><b>Deleted Student ID:</b> "
                            + id
                            + "</p>"
                    );

                } else {

                    out.println(
                            "<html><body>"
                    );

                    out.println(
                            "<h1>Student Not Found</h1>"
                    );

                    out.println(
                            "<p>No student exists with ID "
                            + id
                            + ".</p>"
                    );
                }

                out.println(
                        "<br><a href='Students.jsp'>"
                        + "Back to Student Page"
                        + "</a>"
                );

                out.println(
                        "</body></html>"
                );

            } catch (NumberFormatException e) {

                out.println(
                        "<html><body>"
                );

                out.println(
                        "<h2>Invalid Student ID.</h2>"
                );

                out.println(
                        "<p>Student ID must be a number.</p>"
                );

                out.println(
                        "<br><a href='Students.jsp'>"
                        + "Back to Student Page"
                        + "</a>"
                );

                out.println(
                        "</body></html>"
                );
            }

            return;
        }

        // =========================
        // ADD / UPDATE
        // =========================

        try {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            String name =
                    request.getParameter("name");

            int semester =
                    Integer.parseInt(
                            request.getParameter("semester")
                    );

            String division =
                    request.getParameter("division");

            Student student =
                    new Student();

            student.setId(id);
            student.setName(name);
            student.setSemester(semester);
            student.setDivision(division);

            if ("add".equals(action)) {

                controller.addStudent(student);

                out.println(
                        "<html><body>"
                );

                out.println(
                        "<h1>Student Added Successfully</h1>"
                );

            } else if ("update".equals(action)) {

                controller.updateStudent(student);

                out.println(
                        "<html><body>"
                );

                out.println(
                        "<h1>Student Updated Successfully</h1>"
                );

            } else {

                out.println(
                        "<html><body>"
                );

                out.println(
                        "<h2>Invalid Action.</h2>"
                );
            }

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
                    "<br><a href='Students.jsp'>"
                    + "Back to Student Page"
                    + "</a>"
            );

            out.println(
                    "</body></html>"
            );

        } catch (NumberFormatException e) {

            out.println(
                    "<html><body>"
            );

            out.println(
                    "<h2>Invalid Student ID or Semester.</h2>"
            );

            out.println(
                    "<p>"
                    + "Student ID and Semester must be numbers."
                    + "</p>"
            );

            out.println(
                    "<br><a href='Students.jsp'>"
                    + "Back to Student Page"
                    + "</a>"
            );

            out.println(
                    "</body></html>"
            );

        } catch (Exception e) {

            e.printStackTrace();

            out.println(
                    "<html><body>"
            );

            out.println(
                    "<h2>Error processing student.</h2>"
            );

            out.println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );

            out.println(
                    "<br><a href='Students.jsp'>"
                    + "Back to Student Page"
                    + "</a>"
            );

            out.println(
                    "</body></html>"
            );
        }
    }

    @Override
    public void destroy() {

        if (context instanceof
                AnnotationConfigApplicationContext) {

            ((AnnotationConfigApplicationContext)
                    context).close();
        }

        System.out.println(
                "Spring context closed."
        );
    }
}