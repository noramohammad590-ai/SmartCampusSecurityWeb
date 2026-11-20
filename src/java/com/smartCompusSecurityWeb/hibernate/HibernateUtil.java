package com.smartCompusSecurityWeb.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {

            String host =
                    System.getenv("MYSQL_PUBLIC_HOST");

            String port =
                    System.getenv("MYSQL_PUBLIC_PORT");

            String user =
                    System.getenv("MYSQLUSER");

            String password =
                    System.getenv("MYSQLPASSWORD");

            String url =
                    "jdbc:mysql://"
                    + host
                    + ":"
                    + port
                    + "/smart_campus_security";

            Configuration configuration =
                    new Configuration()
                    .configure(
                        "com/smartCompusSecurityWeb/hibernate/hibernate.cfg.xml"
                    );

            configuration.setProperty(
                    "hibernate.connection.url",
                    url
            );

            configuration.setProperty(
                    "hibernate.connection.username",
                    user
            );

            configuration.setProperty(
                    "hibernate.connection.password",
                    password
            );

            return configuration.buildSessionFactory();

        } catch (Throwable ex) {

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }
}