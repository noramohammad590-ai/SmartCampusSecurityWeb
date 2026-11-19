<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

    <title>Complaints</title>

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

        h1 {
            color: #173b67;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input,
        textarea,
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        textarea {
            height: 120px;
            resize: vertical;
        }

        button {
            margin-top: 20px;
            padding: 11px 25px;
            background: #1769aa;
            color: white;
            border: none;
            cursor: pointer;
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

        <h1>
            Submit Complaint
        </h1>


        <form action="complaint" method="post">

            <label>
                Subject
            </label>

            <input
                type="text"
                name="subject"
                placeholder="Enter complaint subject"
                required>


            <label>
                Description
            </label>

            <textarea
                name="description"
                placeholder="Enter complaint description"
                required></textarea>


            <label>
                Status
            </label>

            <select name="status" required>

                <option value="Pending">
                    Pending
                </option>

                <option value="In Progress">
                    In Progress
                </option>

                <option value="Resolved">
                    Resolved
                </option>

            </select>


            <button type="submit">
                Submit Complaint
            </button>

        </form>


        <a href="home.jsp" class="back-button">
            Back to Home
        </a>

    </div>

</div>

</body>

</html>