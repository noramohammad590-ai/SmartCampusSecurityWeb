<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>User Registration</title>

    <style>

        body {
            margin: 0;
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        .header {
            background-color: #0b1f3a;
            color: white;
            padding: 20px;
        }

        .header h1 {
            margin: 0;
        }

        .container {
            width: 55%;
            margin: 50px auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px #cccccc;
        }

        h2 {
            text-align: center;
        }

        label {
            display: block;
            font-weight: bold;
            margin-top: 15px;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            margin-top: 5px;
        }

        button {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 12px 25px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        button:hover {
            background-color: #125ca1;
        }

        .error {
            color: red;
            font-weight: bold;
            text-align: center;
        }

        .back {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #222;
            color: white;
            text-decoration: none;
        }

    </style>

</head>

<body>

<!-- ========================= -->
<!-- HEADER -->
<!-- ========================= -->

<div class="header">

    <h1>
        Smart Campus Security
    </h1>

</div>

<!-- ========================= -->
<!-- USER REGISTRATION -->
<!-- ========================= -->

<div class="container">

    <h2>
        User Registration
    </h2>

    <!-- ========================= -->
    <!-- ERROR MESSAGE -->
    <!-- ========================= -->

    <%
        String error =
                (String) request.getAttribute("error");

        if (error != null) {
    %>

        <p class="error">
            <%= error %>
        </p>

    <%
        }
    %>

    <!-- ========================= -->
    <!-- USER FORM -->
    <!-- ========================= -->

    <form
        action="${pageContext.request.contextPath}/user"
        method="post">

        <!-- USER NAME -->

        <label>
            User Name
        </label>

        <input
            type="text"
            name="name"
            placeholder="Enter user name"
            required
            pattern="[A-Za-z ]+"
            title="Name should contain letters and spaces only">

        <!-- ROLE -->

        <label>
            Role
        </label>

        <select
            name="role"
            required>

            <option value="">
                Select Role
            </option>

            <option value="Student">
                Student
            </option>

            <option value="Doctor">
                Doctor
            </option>

            <option value="Admin">
                Admin
            </option>

            <option value="Security">
                Security
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
            Register User
        </button>

    </form>

    <!-- BACK TO HOME -->

    <a
        href="home.jsp"
        class="back">

        Back to Home

    </a>

</div>

</body>

</html>