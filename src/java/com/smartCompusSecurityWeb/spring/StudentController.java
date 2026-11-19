package com.smartCompusSecurityWeb.spring;

import com.smartCompusSecurityWeb.hibernate.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void addStudent(Student student) {
        studentService.addStudent(student);
    }

    public Student getStudent(int id) {
        return studentService.getStudent(id);
    }

    public void updateStudent(Student student) {
        studentService.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentService.deleteStudent(id);
    }
}