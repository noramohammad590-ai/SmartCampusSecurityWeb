package com.smartcampus;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.awt.Desktop;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private final LoginController controller =
            new LoginController();

    public LoginWindow() {

        setTitle("Smart Campus Security - Login");
        setSize(450, 320);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(10, 15)
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 35, 25, 35
                )
        );

        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "Smart Campus Security",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2, 2, 10, 15
                        )
                );

        usernameField =
                new JTextField();

        passwordField =
                new JPasswordField();

        formPanel.add(
                new JLabel("ID:")
        );

        formPanel.add(
                usernameField
        );

        formPanel.add(
                new JLabel("Password:")
        );

        formPanel.add(
                passwordField
        );

        // =========================
        // LOGIN BUTTON
        // =========================

        loginButton =
                new JButton("Login");

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                loginButton,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // =========================
        // LOGIN BUTTON ACTION
        // =========================

        loginButton.addActionListener(
                e -> login()
        );

        getRootPane().setDefaultButton(
                loginButton
        );
    }

    // ==================================================
    // LOGIN METHOD
    // ==================================================

    private void login() {

        String idText =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        // ==================================================
        // VALIDATE EMPTY FIELDS
        // ==================================================

        if (idText.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter ID and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==================================================
        // CONVERT ID TO INTEGER
        // ==================================================

        int userId;

        try {

            userId =
                    Integer.parseInt(idText);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID must contain numbers only.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // ==================================================
        // AUTHENTICATE
        // ==================================================

        try {

            LoginController.LoginResult result =
                    controller.authenticate(
                            userId,
                            password
                    );

            // ==================================================
            // INVALID LOGIN
            // ==================================================

            if (result == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid ID or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // ==================================================
            // GET STUDENT NAME IF STUDENT
            // ==================================================

            String welcomeName =
                    result.role;

            if (result.role.equalsIgnoreCase(
                    "Student")) {

                String studentName =
                        controller.getStudentName(
                                result.personId
                        );

                if (studentName == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student ID not found in the students table.",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                welcomeName =
                        studentName;
            }

            // ==================================================
            // SUCCESS
            // ==================================================

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, "
                    + welcomeName
                    + "!\nRole: "
                    + result.role
            );

            // ==================================================
            // OPEN WEB APPLICATION
            // ==================================================

            openWebApplication();

            dispose();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login error: "
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // ==================================================
    // OPEN WEB APPLICATION
    // ==================================================

    private void openWebApplication() {

        try {

            String url =
                    "http://localhost:8080/"
                    + "SmartCampusSecurityWeb/"
                    + "home.jsp";

            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().browse(
                        new URI(url)
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Could not open the web browser.",
                        "Browser Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not open web application:\n"
                    + ex.getMessage(),
                    "Browser Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==================================================
    // MAIN METHOD
    // ==================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginWindow login =
                    new LoginWindow();

            login.setVisible(true);
        });
    }
}