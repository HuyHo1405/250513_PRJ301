<%-- 
    Document   : login
    Created on : 23-May-2025, 07:18:37
    Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <input type="hidden" name="action" value="login">
            User Name:
            <input type="text" name="username"></br>
            Password:
            <input type="password" name="password"></br>
            <button type="submit">Login
        </form>
        
        <%
            Object objMsg = request.getAttribute("message");
            String strMsg = objMsg==null? "": objMsg + "";
        %>
        <span style="color: red;"><%=strMsg%></span>
    </body>
</html>
