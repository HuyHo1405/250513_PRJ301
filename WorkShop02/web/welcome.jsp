<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Welcome Page</title>

    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/welcome.css" rel="stylesheet">
</head>
<body>
    <div class="header-container">
        <h1>Welcome, <c:out value="${sessionScope.user.name}"/> (<c:out value="${sessionScope.user.role}"/>)</h1>
        <div class="logout-form">
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="logout"/>
                <button type="submit" class="btn-logout">Log out</button>
            </form>
        </div>
    </div>

    <div class="horizontal-line"></div>

    <div class="buttons-container">
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="toExamCategory"/>
            <button type="submit" class="btn btn-primary">Exam Category</button>
        </form>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="toExamManagement"/>
            <button type="submit" class="btn btn-primary">Exam Management</button>
        </form>
    </div>

    <div class="bottom-content-container"></div>
</body>
</html>