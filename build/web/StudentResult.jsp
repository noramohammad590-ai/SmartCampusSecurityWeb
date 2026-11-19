<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Student Information Result</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #eef3f8;
            margin: 0;
        }

        .header {
            background: #173b67;
            color: white;
            padding: 20px;
        }

        .container {
            width: 500px;
            margin: 50px auto;
        }

        .box {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 10px #bbb;
        }

        h2 {
            text-align: center;
            color: #173b67;
            margin-bottom: 25px;
        }

        .row {
            display: flex;
            margin: 15px 0;
            font-size: 17px;
        }

        .label {
            width: 120px;
            font-weight: bold;
        }

        .value {
            flex: 1;
        }

        .error {
            color: red;
            text-align: center;
            font-size: 18px;
        }

        .button {
            display: inline-block;
            background: #1769aa;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .button:hover {
            background: #12558c;
        }

    </style>

</head>

<body>

<div class="header">

    <h1>Smart Campus Security</h1>

</div>


<div class="container">

    <div class="box">

        <h2>Student Information</h2>


        <%

            if (request.getAttribute("error") != null) {

        %>

            <p class="error">

                <%= request.getAttribute("error") %>

            </p>

        <%

            } else {

        %>


            <div class="row">

                <div class="label">
                    ID:
                </div>

                <div class="value">
                    <%= request.getAttribute("studentId") %>
                </div>

            </div>


            <div class="row">

                <div class="label">
                    Name:
                </div>

                <div class="value">
                    <%= request.getAttribute("studentName") %>
                </div>

            </div>


            <div class="row">

                <div class="label">
                    Semester:
                </div>

                <div class="value">
                    <%= request.getAttribute("semester") %>
                </div>

            </div>


            <div class="row">

                <div class="label">
                    Division:
                </div>

                <div class="value">
                    <%= request.getAttribute("division") %>
                </div>

            </div>


        <%

            }

        %>


        <a href="Students.jsp" class="button">
            Search Another Student
        </a>


        <a href="home.jsp" class="button">
            Home
        </a>

    </div>

</div>

</body>

</html>