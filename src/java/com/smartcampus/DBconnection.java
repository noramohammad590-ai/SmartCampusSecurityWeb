package com.smartcampus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    // =========================
    // Railway MySQL Connection
    // =========================

    private static final String URL =
            "jdbc:mysql://"
            + System.getenv("MYSQL_PUBLIC_HOST")
            + ":"
            + System.getenv("MYSQL_PUBLIC_PORT")
            + "/smart_campus_security";

    private static final String USER =
            System.getenv("MYSQLUSER");

    private static final String PASSWORD =
            System.getenv("MYSQLPASSWORD");

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}