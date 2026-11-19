<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Smart Campus Security</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: #eef3f8;
        }

        .header {
            background: #173b67;
            color: white;
            padding: 20px;
        }

        .header h1 {
            margin: 0;
        }

        .user-info {
            margin-top: 8px;
        }

        .container {
            padding: 30px;
        }

        .welcome {
            background: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 25px;
        }

        .welcome h2 {
            color: #173b67;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 3px 10px #bbb;
        }

        .card h2 {
            color: #173b67;
        }

        .button {
            display: inline-block;
            background: #1769aa;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }

        .button:hover {
            background: #12558c;
        }

        .logout {
            background: #b3261e;
        }

        .logout:hover {
            background: #8f1d18;
        }

    </style>

</head>

<body>


<div class="header">

    <h1>
        Smart Campus Security System
    </h1>

    <div class="user-info">

        User ID:
        ${sessionScope.username}

        &nbsp;&nbsp; | &nbsp;&nbsp;

        Role:
        ${sessionScope.role}

    </div>

</div>


<div class="container">


    <!-- Welcome message -->

    <div class="welcome">

        <c:choose>

            <c:when test="${sessionScope.role == 'Student'}">

                <h2>
                    Welcome Student!
                </h2>

                <p>
                    You can access student-related security services.
                </p>

            </c:when>


            <c:when test="${sessionScope.role == 'Doctor'}">

                <h2>
                    Welcome Doctor!
                </h2>

                <p>
                    You can access doctor-related services.
                </p>

            </c:when>


            <c:when test="${sessionScope.role == 'Security'}">

                <h2>
                    Welcome Security Officer!
                </h2>

                <p>
                    You can manage campus security activities.
                </p>

            </c:when>


            <c:when test="${sessionScope.role == 'Admin'}">

                <h2>
                    Welcome Administrator!
                </h2>

                <p>
                    You can manage the Smart Campus Security System.
                </p>

            </c:when>


            <c:otherwise>

                <h2>
                    Welcome!
                </h2>

            </c:otherwise>

        </c:choose>

    </div>


    <!-- Students -->

    <div class="cards">

        <div class="card">

            <h2>
                Students
            </h2>

            <p>
                Student information and records.
            </p>

            <a href="Students.jsp" class="button">
                Students
            </a>

        </div>


        <!-- Visitors -->

        <div class="card">

            <h2>
                Visitors
            </h2>

            <p>
                Register and manage campus visitors.
            </p>

            <a href="Visitors.jsp" class="button">
                Visitors
            </a>

        </div>


        <!-- Complaints -->

        <div class="card">

            <h2>
                Complaints
            </h2>

            <p>
                Submit and manage campus complaints.
            </p>

            <a href="Complaints.jsp" class="button">
                Complaints
            </a>

        </div>


        <!-- Users -->

        <div class="card">

            <h2>
                Users
            </h2>

            <p>
                Register and manage system users.
            </p>

            <a href="Users.jsp" class="button">
                Users
            </a>

        </div>

    </div>


    <br>
    <br>


    <!-- Logout -->

    <a href="logout" class="button logout">
        Logout
    </a>


</div>

</body>

</html>