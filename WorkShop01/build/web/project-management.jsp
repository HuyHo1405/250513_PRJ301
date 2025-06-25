<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.UserUtils"%>
<%@page import="model.dto.UserDTO"%>
<%@page import="model.dto.ProjectDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Management</title>
        <style>
            /* General Body and Layout */
            body {
                font-family: Arial, sans-serif;
                margin: 0; /* Reset default body margin */
                padding: 0; /* Reset default body padding */
                background-color: #f8f9fa; /* Light background for the page */
            }

            /* Top bar styles */
            .top-bar {
                width: 100%;
                background-color: #007bff; /* Main theme blue (matching Project Management button) */
                color: white;
                padding: 15px 20px; /* Adjust padding as needed */
                box-sizing: border-box; /* Include padding in the element's total width */
                display: flex; /* Use flexbox for alignment within the bar */
                align-items: center; /* Vertically centers content */
            }

            .top-bar h1 {
                margin: 0; /* Remove default h1 margin */
                color: white; /* Ensure h1 text is white for contrast */
                font-size: 1.8em;
            }

            /* Main content area below the top bar */
            .main-content-area {
                margin: 20px; /* Consistent margin around main content */
            }

            hr {
                border: 0;
                height: 1px;
                background-color: #007bff; /* Matching the main theme blue */
                margin: 20px 0;
            }

            /* Form and Button Styles */
            form {
                display: inline-block; /* Keep forms inline */
                margin-right: 10px; /* Space between forms/buttons */
                margin-bottom: 20px; /* Space below forms before table */
            }

            input[type="text"] {
                padding: 8px 12px;
                border: 1px solid #ced4da;
                border-radius: 4px;
                font-size: 1em;
                margin-right: 5px; /* Space between input and button */
                box-sizing: border-box; /* Ensures padding doesn't push width out */
            }

            input[type="text"]:focus {
                border-color: #007bff;
                outline: none;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            button {
                padding: 8px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1em;
                color: white; /* Default button text color */
                transition: background-color 0.2s ease-in-out;
            }

            /* Specific button colors */
            .search-button, .back-button {
                background-color: #007bff; /* Main theme blue */
            }
            .search-button:hover, .back-button:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

            .add-project-button {
                background-color: #28a745; /* Green for add action */
            }
            .add-project-button:hover {
                background-color: #218838; /* Darker green on hover */
            }

            .edit-project-button {
                background-color: #ffc107; /* Yellow/orange for edit action */
                color: #343a40; /* Dark text for contrast on light background */
                border: 1px solid #ffc107; /* Add a border to visually distinguish */
            }
            .edit-project-button:hover {
                background-color: #e0a800;
                border-color: #e0a800;
            }

            /* Table Styles (enhanced) */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px; /* Space below forms */
                font-family: Arial, sans-serif;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1); /* Subtle shadow for the table */
                background-color: #fff; /* White background for table content */
            }

            th, td {
                border: 1px solid #dee2e6; /* Lighter border color */
                padding: 12px 15px; /* More padding */
                text-align: left;
            }

            th {
                background-color: #e9ecef; /* Light grey header background */
                font-weight: bold;
                color: #495057; /* Darker text color for headers */
            }

            tr:nth-child(even) {
                background-color: #f2f2f2; /* Subtle alternating row color */
            }

            tr:hover {
                background-color: #e2f0ff; /* Light blue on hover for rows */
            }

            /* For the 'No projects found' message */
            .no-projects-message {
                text-align: center;
                color: #6c757d;
                padding: 15px;
                background-color: #f0f0f0;
                border-radius: 5px;
                margin-top: 15px;
            }
            .back-button { /* This is a new class you'll add to your HTML form */
                margin-top: 20px; /* Adds 20 pixels of space above the form */
            }
        </style>
    </head>
    <body>
        <%
            boolean isFounder = UserUtils.isFounder(request);
        %>
        
        <div class="top-bar">
            <h1> <%= isFounder? "Founder ": "" %>Project Management</h1>
            </div>

        <div class="main-content-area">
            <hr>
            
            <% if(isFounder){ %>
                <form action="MainController" method="post" style="display:inline-block;">
                    <input type="hidden" name="action" value="searchProjectsByName"/>
                    <input type="text" name="strName" placeholder="Enter project name" value="${not empty param.strName ? param.strName : ''}"/>
                    <button type="submit" class="search-button">Search</button>
                </form>

                <form action="MainController" method="post" style="display:inline-block;">
                    <input type="hidden" name="action" value="toAddProject"/>
                    <button type="submit" class="add-project-button">+ Add Project</button>
                </form>
            <% } %>
            
            <table border="0">
                <thead> <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Estimated Launch Date</th>
                        <% if(isFounder){ %>
                            <th>Action</th>
                        <% } %>
                    </tr>
                </thead>
                <tbody> <c:forEach var="p" items="${projectList}">
                        <tr>
                            <td>${p.projectId}</td> 
                            <td>${p.projectName}</td> 
                            <td>${p.description}</td> 
                            <td>${p.status}</td> 
                            <td>${p.estimatedLaunchDate}</td> 
                            <% if(isFounder){ %>
                                <td>
                                    <form action="MainController" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="toEditProject"/>
                                        <input type="hidden" name="projectId" value="${p.projectId}"/>
                                        <button type="submit" class="edit-project-button">Edit</button>
                                    </form>
                                </td>
                            <% } %>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty projectList}">
                        <tr>
                            <td colspan="<%= isFounder ? 6 : 5 %>" class="no-projects-message">No projects found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="toWelcome"/>
                <button type="submit" class="back-button">Back</button>
            </form>
        </div>
    </body>
</html>