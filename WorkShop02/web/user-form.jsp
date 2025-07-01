<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login Page Redesign</title>

    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/userForm.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Login Form</h1>
        <hr>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/>

            <div class="form-group">
                <label for="strUsername">Username:</label>
                <input type="text" id="strUsername" name="strUsername" value="" placeholder="Enter your username"/>
            </div>

            <div class="form-group">
                <label for="strPassword">Password:</label>
                <input type="password" id="strPassword" name="strPassword" value="" placeholder="Enter your password"/>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Login</button>

            <c:if test="${not empty errorMsg}">
                <p class="message error">${errorMsg}</p>
            </c:if>
        </form>
    </div>
</body>
</html>
