<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="utils.UserUtils"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh; /* Ensure it takes full viewport height */
                margin: 0;
            }

            .form-container {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 400px;
                box-sizing: border-box; /* Include padding in element's total width and height */
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
                text-transform: capitalize; /* Capitalize the first letter of 'login' or 'register' */
            }

            hr {
                border: 0;
                height: 1px;
                background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
                margin-bottom: 25px;
            }

            form label {
                display: block; /* Make labels take full width */
                margin-bottom: 15px; /* Space between labels */
            }

            form label span {
                display: block; /* Make "User Name", "Full Name", "Password" on their own line */
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }

            form input[type="text"],
            form input[type="password"] {
                width: calc(100% - 20px); /* Full width minus padding */
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1em;
                box-sizing: border-box;
            }

            form input[type="text"]:focus,
            form input[type="password"]:focus {
                border-color: #007bff;
                outline: none;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            form button[type="submit"] {
                width: 100%;
                padding: 12px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                font-size: 1.1em;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-top: 20px;
                text-transform: capitalize;
            }

            form button[type="submit"]:hover {
                background-color: #0056b3;
            }

            p {
                color: red;
                text-align: center;
                margin-top: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%
            //filter?
            if(UserUtils.isLoggedIn(request)){
                response.sendRedirect("welcome.jsp");
            }
        %>
        <c:set var="action" value="${empty actionType ? 'login' : actionType}" />
        
        <div class="form-container">
            <h1>${action} form</h1>
            <hr>
            
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="${action}"/>
                
                <label>
                    <span>User Name</span>
                    <input type="text" name="strUserName" value="${empty inputUsername ? '' : inputUsername}"/>
                </label>
                
                <c:if test="${action == 'register'}">
                    <label>
                        <span>Full Name</span>
                        <input type="text" name="strUserFullName"/>
                    </label>
                </c:if>
                <label>
                    <span>Password</span>
                    <input type="password" name="strPassword"/>
                </label>
                
                <button type="submit">${action}</button>
            </form>
            
            <p>${empty errorMsg ? '' : errorMsg}</p>
        </div>
    </body>
</html>