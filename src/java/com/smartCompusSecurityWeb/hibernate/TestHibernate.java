package com.smartCompusSecurityWeb.hibernate;

public class TestHibernate {

    public static void main(String[] args) {

        StudentHibernateDAO dao =
                new StudentHibernateDAO();

        Student student =
                dao.getStudent(101);

        if (student != null) {

            student.setSemester(6);

            dao.updateStudent(student);

            System.out.println(
                    "Student 101 restored successfully."
            );

            Student updatedStudent =
                    dao.getStudent(101);

            System.out.println(
                    "Student ID: "
                    + updatedStudent.getId()
            );

            System.out.println(
                    "Student Name: "
                    + updatedStudent.getName()
            );

            System.out.println(
                    "Semester: "
                    + updatedStudent.getSemester()
            );

            System.out.println(
                    "Division: "
                    + updatedStudent.getDivision()
            );

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }
}