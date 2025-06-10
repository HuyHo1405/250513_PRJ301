    <%-- 
    Document   : welcome
    Created on : 23-May-2025, 07:40:53
    Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserDTO"%>
<%@page import="utils.UserUtils"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDTO user = UserUtils.getUser(request);
        %>
        <h1>Welcome <%=user.getFullName()%></h1>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="logout">
            <button type="submit">Logout</button>
        </form></br>
        <a href="product-form.jsp">add product</a>
    </body>
</html>
