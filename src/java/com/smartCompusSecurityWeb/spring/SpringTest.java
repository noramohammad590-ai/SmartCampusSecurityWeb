package com.smartCompusSecurityWeb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    public static void main(String[] args) {

        System.out.println("Starting Spring...");

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentController controller =
                context.getBean(StudentController.class);

        System.out.println(
                "Spring Dependency Injection is working!"
        );

        System.out.println(
                "StudentController Bean: "
                + controller.getClass().getName()
        );

        ((AnnotationConfigApplicationContext) context).close();
    }
}