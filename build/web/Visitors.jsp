<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Visitors Management</title>

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

        .header h1 {
            margin: 0;
        }

        .container {
            width: 600px;
            margin: 40px auto;
        }

        .box {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 10px #bbb;
        }

        h2 {
            color: #173b67;
            text-align: center;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        button {
            margin-top: 25px;
            padding: 11px 25px;
            background: #1769aa;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 15px;
        }

        button:hover {
            background: #12558c;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            background: #555;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
        }

        .back-button:hover {
            background: #333;
        }

        .error {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 15px;
        }

    </style>

</head>

<body>

<div class="header">

    <h1>
        Smart Campus Security
    </h1>

</div>


<div class="container">

    <div class="box">

        <h2>
            Visitor Registration
        </h2>


        <!-- ========================= -->
        <!-- ERROR MESSAGE -->
        <!-- ========================= -->

        <%
            if (error != null) {
        %>

            <p class="error">
                <%= error %>
            </p>

        <%
            }
        %>


        <!-- ========================= -->
        <!-- VISITOR FORM -->
        <!-- ========================= -->

        <form
            action="${pageContext.request.contextPath}/visitor"
            method="post">

            <!-- VISITOR NAME -->

            <label>
                Visitor Name
            </label>

            <input
                type="text"
                name="name"
                placeholder="Enter visitor name"
                required
                pattern="[A-Za-z ]+"
                title="Name should contain letters and spaces only">


            <!-- PURPOSE -->

            <label>
                Purpose
            </label>

            <input
                type="text"
                name="purpose"
                placeholder="Enter visit purpose"
                required>


            <!-- STATE -->

            <label>
                State
            </label>

            <select
                name="state"
                required>

                <option value="Pending">
                    Pending
                </option>

                <option value="Approved">
                    Approved
                </option>

                <option value="Rejected">
                    Rejected
                </option>

            </select>


            <!-- PHONE -->

            <label>
                Phone
            </label>

            <input
                type="tel"
                name="phone"
                placeholder="Enter 10 digit phone number"
                required
                pattern="[0-9]{10}"
                maxlength="10"
                title="Phone number must contain exactly 10 digits">


            <!-- REGISTER -->

            <button type="submit">
                Register Visitor
            </button>

        </form>


        <!-- BACK TO HOME -->

        <a
            href="home.jsp"
            class="back-button">

            Back to Home

        </a>

    </div>

</div>

</body>

</html>