package com.smartcampus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public static class LoginResult {

        public final int accountId;
        public final int personId;
        public final String role;

        public LoginResult(
                int accountId,
                int personId,
                String role) {

            this.accountId = accountId;
            this.personId = personId;
            this.role = role;
        }
    }

    // =========================
    // LOGIN
    // =========================

    public LoginResult authenticate(
            int userId,
            String password)
            throws SQLException {

        String sql =
                "SELECT id, role, person_id "
                + "FROM login_accounts "
                + "WHERE username = ? "
                + "AND password = ?";

        try (
            Connection con =
                    DBconnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            ps.setString(1,
                    String.valueOf(userId));

            ps.setString(2,
                    password);

            try (
                ResultSet rs =
                        ps.executeQuery()
            ) {

                if (rs.next()) {

                    return new LoginResult(
                            rs.getInt("id"),
                            rs.getInt("person_id"),
                            rs.getString("role")
                    );
                }

                return null;
            }
        }
    }

    // =========================
    // GET STUDENT NAME
    // =========================

    public String getStudentName(
            int studentId)
            throws SQLException {

        String sql =
                "SELECT name "
                + "FROM students "
                + "WHERE id = ?";

        try (
            Connection con =
                    DBconnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            try (
                ResultSet rs =
                        ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getString("name");
                }
            }
        }

        return null;
    }
}