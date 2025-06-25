<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="utils.UserUtils"%>
<%@page import="model.dto.UserDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0; /* Reset default body margin */
                padding: 0; /* Reset default body padding */
            }

            /* Updated Top Bar styles to match Project Management button color */
            .top-bar {
                width: 100%;
                background-color: #007bff; /* Changed to the same blue as Project Management button */
                color: white; /* Default text color for elements inside top-bar */
                padding: 15px 20px; /* Padding for content inside the bar (top/bottom, left/right) */
                box-sizing: border-box; /* Include padding in the element's total width */
                display: flex; /* Use flexbox to align content (h1 and logout button) */
                justify-content: space-between; /* Puts h1 on left, logout on right */
                align-items: center; /* Vertically centers items */
            }

            .top-bar h1 {
                margin: 0; /* Remove default h1 margin */
                color: white; /* Ensure h1 text is white for contrast */
                font-size: 1.8em; /* Adjust font size if needed */
            }

            /* Logout button color remains Orange-Red */
            .logout-form button {
                background-color: #FF5733; /* Orange-Red color */
                color: white;
                padding: 8px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1em;
            }
            .logout-form button:hover {
                background-color: #E04E2D; /* Darker Orange-Red on hover */
            }
            
            /* HR color also changed to match the new top bar/project management button color */
            hr {
                border: 0; /* Remove default border */
                height: 1px;
                background-color: #007bff; /* Changed to match top bar/project management button */
                margin: 20px 0; /* Space above and below the HR */
                clear: both; /* Clear any floats */
            }

            .main-content-area {
                margin: 0 20px; /* Apply left/right margin to the main content below the top bar */
                padding-top: 0; /* No top padding needed here as top-bar handles spacing */
            }

            .project-management-container {
                margin-top: 10px; /* Space below the HR line */
                text-align: left;
            }
            .project-management-form button {
                background-color: #007bff; /* This button's color is the reference */
                color: white;
                padding: 8px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1em;
            }
            .project-management-form button:hover {
                background-color: #0056b3; 
            }
        </style>
    </head>
    <body>
        <div class="top-bar">
            <h1>Hello, ${sessionScope.user.name}</h1>
            <div>
                <form action="MainController" method="post" class="logout-form">
                    <input type="hidden" name="action" value="logout"/>
                    <button type="submit">Logout</button>
                </form>
            </div>
        </div>

        <div class="main-content-area">
            <hr>
            <div class="project-management-container">
                <form action="MainController" method="post" class="project-management-form">
                    <input type="hidden" name="action" value="toProjectManagement"/>
                    <button type="submit">Project Management</button>
                </form>
            </div>
        </div>
    </body>
</html>