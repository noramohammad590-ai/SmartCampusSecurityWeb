<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Student Management</title>

</head>

<body>

<h1>Student Management</h1>

<hr>

<!-- ========================= -->
<!-- JSTL + EL TEST -->
<!-- ========================= -->

<c:set
    var="pageTitle"
    value="JSTL and EL are Working"
/>

<p>
    <b>${pageTitle}</b>
</p>

<hr>

<!-- ========================= -->
<!-- SEARCH STUDENT -->
<!-- ========================= -->

<h2>Search Student</h2>

<form
    action="${pageContext.request.contextPath}/hibernateStudent"
    method="get">

    <label>Student ID:</label>

    <input
        type="number"
        name="id"
        min="1"
        required
        title="Student ID must be a positive number">

    <input
        type="submit"
        value="Search">

</form>

<hr>

<!-- ========================= -->
<!-- DISPLAY STUDENT -->
<!-- ========================= -->

<c:if test="${not empty student}">

    <h2>Student Details</h2>

    <p>
        <b>Student ID:</b>
        ${student.id}
    </p>

    <p>
        <b>Name:</b>
        ${student.name}
    </p>

    <p>
        <b>Semester:</b>
        ${student.semester}
    </p>

    <p>
        <b>Division:</b>
        ${student.division}
    </p>

</c:if>

<!-- ========================= -->
<!-- DISPLAY ERROR -->
<!-- ========================= -->

<c:if test="${not empty error}">

    <h2>${error}</h2>

</c:if>

<hr>

<!-- ========================= -->
<!-- ADD STUDENT -->
<!-- ========================= -->

<h2>Add Student</h2>

<form
    action="${pageContext.request.contextPath}/hibernateStudent"
    method="post">

    <input
        type="hidden"
        name="action"
        value="add">

    <p>
        Student ID:

        <input
            type="number"
            name="id"
            min="1"
            required
            title="Student ID must be a positive number">
    </p>

    <p>
        Name:

        <input
            type="text"
            name="name"
            pattern="[A-Za-z ]+"
            required
            title="Name should contain letters and spaces only">
    </p>

    <p>
        Semester:

        <input
            type="number"
            name="semester"
            min="1"
            max="8"
            required
            title="Semester must be between 1 and 8">
    </p>

    <p>
        Division:

        <input
            type="text"
            name="division"
            pattern="[A-Za-z0-9]+"
            required
            title="Enter a valid division">
    </p>

    <input
        type="submit"
        value="Add Student">

</form>

<hr>

<!-- ========================= -->
<!-- UPDATE STUDENT -->
<!-- ========================= -->

<h2>Update Student</h2>

<form
    action="${pageContext.request.contextPath}/hibernateStudent"
    method="post">

    <input
        type="hidden"
        name="action"
        value="update">

    <p>
        Student ID:

        <input
            type="number"
            name="id"
            min="1"
            required
            title="Student ID must be a positive number">
    </p>

    <p>
        Name:

        <input
            type="text"
            name="name"
            pattern="[A-Za-z ]+"
            required
            title="Name should contain letters and spaces only">
    </p>

    <p>
        Semester:

        <input
            type="number"
            name="semester"
            min="1"
            max="8"
            required
            title="Semester must be between 1 and 8">
    </p>

    <p>
        Division:

        <input
            type="text"
            name="division"
            pattern="[A-Za-z0-9]+"
            required
            title="Enter a valid division">
    </p>

    <input
        type="submit"
        value="Update Student">

</form>

<hr>

<!-- ========================= -->
<!-- DELETE STUDENT -->
<!-- ========================= -->

<h2>Delete Student</h2>

<form
    action="${pageContext.request.contextPath}/hibernateStudent"
    method="post">

    <input
        type="hidden"
        name="action"
        value="delete">

    <p>
        Student ID:

        <input
            type="number"
            name="id"
            min="1"
            required
            title="Student ID must be a positive number">
    </p>

    <input
        type="submit"
        value="Delete Student">

</form>

<hr>

<!-- ========================= -->
<!-- NAVIGATION -->
<!-- ========================= -->

<a href="index.jsp">
    Back to Home
</a>

<br><br>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>

</html>