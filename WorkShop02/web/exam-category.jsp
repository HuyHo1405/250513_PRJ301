<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Exam Category</title>
    
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/examCategory.css" rel="stylesheet">
    
    
</head>
<body>
    <div class="header-container">
        <h1>Exam Category</h1>
    </div>

    <div class="horizontal-line"></div>

    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>Ord</th>
                    <th>Category Name</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cat" items="${categoryList}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${cat.name}</td>
                        <td>${cat.description}</td>
                    </tr>
                </c:forEach>

                <c:if test="${empty categoryList}">
                    <tr>
                        <td colspan="3" style="text-align:center;">No category found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <form action="MainController" method="post" class="back-button-form">
        <input type="hidden" name="action" value="toWelcome"/>
        <button type="submit" class="btn btn-primary">Back</button>
    </form>

</body>
</html>