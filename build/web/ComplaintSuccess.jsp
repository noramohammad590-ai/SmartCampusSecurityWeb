<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Complaint Submitted</title>

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
            margin: 80px auto;
        }

        .box {
            background: white;
            padding: 35px;
            border-radius: 10px;
            box-shadow: 0 3px 10px #bbb;
            text-align: center;
        }

        h1 {
            color: #173b67;
        }

        .success {
            color: green;
            font-size: 20px;
            font-weight: bold;
        }

        .button {
            display: inline-block;
            margin-top: 25px;
            background: #1769aa;
            color: white;
            padding: 11px 22px;
            text-decoration: none;
            border-radius: 5px;
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

        <h1>Complaint Submitted</h1>

        <p class="success">
            Your complaint has been submitted successfully.
        </p>

        <p>
            The complaint has been saved in the system.
        </p>

        <a href="Complaints.jsp" class="button">
            Submit Another Complaint
        </a>

        <a href="home.jsp" class="button">
            Home
        </a>

    </div>

</div>

</body>

</html>