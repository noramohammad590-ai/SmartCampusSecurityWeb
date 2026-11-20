Smart Campus Security Web

A Java-Based Web Application for Campus Security Management

Smart Campus Security Web is a Java-based web application developed to support security and administrative operations within a university campus.

The system provides a centralized platform for managing students, users, visitors, and complaints while implementing authentication, session management, server-side validation, database connectivity, Hibernate ORM, and Spring Dependency Injection.

---

Live Application

Live Demo:
https://smartcampussecurityweb.onrender.com/

---

Source Code

GitHub Repository:
https://github.com/noramohammad590-ai/SmartCampusSecurityWeb

---

Project Overview

The application was developed as an academic project for Advance Java Technology (AJT).

The project demonstrates the practical use of Java web technologies to build a database-driven application with authentication, CRUD operations, validation, persistence, and deployment.

---

Features

Authentication and Security

- User login authentication
- Database-based credential verification
- HTTP session management
- Session timeout
- Protected application pages
- Authentication filter
- Secure logout
- Unauthorized access prevention

Student Management

- Add student records
- Search student records
- Update student information
- Delete student records
- Student ID validation
- Hibernate-based CRUD operations

User Management

- User registration
- Role management
- Phone number validation
- Database persistence
- Server-side validation

Visitor Management

- Visitor registration
- Visitor name
- Visit purpose
- Visitor state
- Phone number validation
- Database persistence

Complaint Management

- Complaint submission
- Complaint subject
- Complaint description
- Complaint status
- Server-side validation
- Database persistence

---

System Architecture

The application follows a layered architecture:

JSP / HTML / CSS
       |
       v
Servlet Layer
       |
       +-------------------+
       |                   |
       v                   v
Spring Layer          JDBC Layer
       |                   |
       v                   v
Service Layer          MySQL
       |
       v
Hibernate DAO
       |
       v
Hibernate ORM
       |
       v
MySQL Database

---

Frontend

JSP

JSP is used to create dynamic server-side web pages.

HTML5

HTML is used for page structure, forms, input fields, buttons, and application content.

CSS3

CSS is used for layout, styling, spacing, and user interface design.

JSTL

JSTL is used for standard JSP tags and server-side conditions.

Expression Language

EL is used to access session and request attributes directly from JSP pages.

Example:

${sessionScope.username}

---

Backend

Java 17

Java is the primary programming language used throughout the application.

Java Servlets

Servlets process HTTP requests and responses.

Main servlet modules include:

- "LoginServlet"
- "LogoutServlet"
- "StudentServlet"
- "HibernateStudentServlet"
- "UserServlet"
- "VisitorServlet"
- "ComplaintServlet"

---

Database

The application uses MySQL for persistent data storage.

Database:

smart_campus_security

Main tables:

students
users
visitors
complaints
login_accounts

---

JDBC

JDBC provides direct communication between Java and MySQL.

The application uses:

- "Connection"
- "PreparedStatement"
- "ResultSet"
- "DriverManager"

PreparedStatement

Parameterized SQL statements are used to reduce the risk of SQL injection.

Example:

PreparedStatement ps =
    con.prepareStatement(
        "SELECT * FROM login_accounts WHERE username = ? AND password = ?"
    );

---

Hibernate ORM

Hibernate is used for Object-Relational Mapping.

It maps Java objects to database records and is used for Student CRUD operations.

Main Hibernate components:

- "Student"
- "StudentHibernateDAO"
- "HibernateUtil"

Hibernate supports:

- Create
- Read
- Update
- Delete

---

Spring Framework

Spring is used to demonstrate Dependency Injection and layered application design.

Main Spring components:

- "AppConfig"
- "StudentService"
- "StudentController"

Dependency Injection

"StudentController" receives "StudentService" through Spring Dependency Injection.

Example:

@Autowired
public StudentController(
        StudentService studentService) {

    this.studentService = studentService;
}

This reduces tight coupling between application components.

---

Security Implementation

The application implements security through authentication, sessions, filtering, validation, and parameterized SQL queries.

Authentication Flow

Login.jsp
    |
    v
LoginServlet
    |
    v
login_accounts
    |
    v
Credentials Verified
    |
    v
HTTP Session
    |
    v
home.jsp

Session Management

After successful authentication, the application stores information in the HTTP session, including:

- "username"
- "role"
- "personId"

The session also uses an inactivity timeout.

Authentication Filter

"AuthFilter" checks whether the user has an active login session before allowing access to protected pages.

Request
   |
   v
AuthFilter
   |
   +---- Logged In ----> Continue
   |
   +---- Not Logged In -> Login Page

Input Validation

The application performs server-side validation including:

- Required field validation
- Student ID validation
- Phone number validation
- Empty field prevention
- Invalid input handling

---

Application Flow

Student Management

Students.jsp
     |
     v
Student Servlet
     |
     v
StudentHibernateDAO
     |
     v
Hibernate ORM
     |
     v
MySQL Database
     |
     v
Student Result

Visitor Registration

Visitors.jsp
     |
     v
VisitorServlet
     |
     v
Validation
     |
     v
JDBC PreparedStatement
     |
     v
MySQL Database

Complaint Submission

Complaints.jsp
     |
     v
ComplaintServlet
     |
     v
Validation
     |
     v
JDBC PreparedStatement
     |
     v
MySQL Database

---

Testing

Test| Result
Login Authentication| Passed
Session Management| Passed
Protected Page Access| Passed
Logout| Passed
Student Search| Passed
Student Add| Passed
Student Update| Passed
Student Delete| Passed
Visitor Registration| Passed
User Registration| Passed
Complaint Submission| Passed
Input Validation| Passed
Hibernate CRUD| Passed
Spring Dependency Injection| Passed
Production Deployment| Passed

---

Technology Stack

Frontend

Technology| Purpose
JSP| Dynamic web pages
HTML5| Page structure
CSS3| User interface
JSTL| JSP standard tags
EL| Dynamic data access

Backend

Technology| Purpose
Java 17| Core programming
Servlets| Request/response processing
JDBC| Database connectivity
Hibernate| ORM and persistence
Spring| Dependency Injection

Database

Technology| Purpose
MySQL| Data storage
JDBC| Database communication
Hibernate| Object-relational mapping

Server and Development Tools

Technology| Purpose
Apache Tomcat 9| Servlet container
NetBeans| Development IDE
XAMPP| Local development
Git| Version control
GitHub| Source code hosting
Docker| Application containerization
Railway| Cloud database
Render| Cloud deployment

---

Project Structure

SmartCampusSecurityWeb
|
├── src
│   └── com
│       └── smartCompusSecurityWeb
│           ├── LoginServlet.java
│           ├── LogoutServlet.java
│           ├── StudentServlet.java
│           ├── UserServlet.java
│           ├── VisitorServlet.java
│           ├── ComplaintServlet.java
│           ├── AuthFilter.java
│           |
│           ├── hibernate
│           │   ├── Student.java
│           │   ├── StudentHibernateDAO.java
│           │   └── HibernateUtil.java
│           |
│           └── spring
│               ├── AppConfig.java
│               ├── StudentService.java
│               └── StudentController.java
|
├── web
│   ├── Login.jsp
│   ├── home.jsp
│   ├── Students.jsp
│   ├── Users.jsp
│   ├── Visitors.jsp
│   └── Complaints.jsp
|
├── Dockerfile
├── build.xml
├── README.md
└── nbproject

---

Deployment

The application was deployed as a production web application using Docker, Render, and Railway.

Application Server

Apache Tomcat 9

Containerization

Docker

Database Hosting

Railway MySQL

Application Hosting

Render

Live Application:

https://smartcampussecurityweb.onrender.com/

---

Local Setup

Requirements

- JDK 17
- NetBeans IDE
- Apache Tomcat 9
- MySQL
- XAMPP
- Git

Setup Steps

1. Clone the repository:

git clone https://github.com/noramohammad590-ai/SmartCampusSecurityWeb.git

2. Open the project in NetBeans.

3. Configure Apache Tomcat 9.

4. Configure the MySQL database:

smart_campus_security

5. Configure the required database connection properties.

6. Run Clean and Build.

7. Deploy the application using Apache Tomcat.

---

Final Submission

The final project submission includes:

- Complete Java source code
- JSP frontend
- Database integration
- JDBC implementation
- Hibernate implementation
- Spring Dependency Injection
- Authentication and session management
- Input validation
- Docker configuration
- GitHub repository
- Final ZIP archive
- Live cloud deployment

GitHub Repository

https://github.com/noramohammad590-ai/SmartCampusSecurityWeb

Live Application

https://smartcampussecurityweb.onrender.com/

Final ZIP

SmartCampusSecurityWeb_Final_Submission.zip

---

Author

Nora Mohamed Osman

Computer Engineering / Cyber Security
Marwadi University

---

Academic Project

Course: Advance Java Technology (AJT)

Project: Smart Campus Security Web

Project Type: Java Web Application
