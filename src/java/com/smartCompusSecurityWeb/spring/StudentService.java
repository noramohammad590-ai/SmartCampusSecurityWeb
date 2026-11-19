package com.smartCompusSecurityWeb.spring;

import com.smartCompusSecurityWeb.hibernate.Student;
import com.smartCompusSecurityWeb.hibernate.StudentHibernateDAO;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentHibernateDAO dao;

    public StudentService() {
        dao = new StudentHibernateDAO();
    }

    public void addStudent(Student student) {
        dao.addStudent(student);
    }

    public Student getStudent(int id) {
        return dao.getStudent(id);
    }

    public void updateStudent(Student student) {
        dao.updateStudent(student);
    }

    public void deleteStudent(int id) {
        dao.deleteStudent(id);
    }
}