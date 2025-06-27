<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="utils.UserUtils"%>
<%@page import="model.dto.ProjectDTO"%>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Project Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f9fa; /* Light background for the page */
            }

            /* Top bar styles */
            .top-bar {
                width: 100%;
                background-color: #007bff; /* Main theme blue */
                color: white;
                padding: 15px 20px;
                box-sizing: border-box;
                display: flex;
                align-items: center;
                justify-content: space-between; /* To push items to ends if needed */
            }

            .top-bar h1 {
                margin: 0;
                color: white;
                font-size: 1.8em;
            }

            /* Main content area */
            .main-content-area {
                margin: 20px; /* Consistent margin around main content */
                background-color: #fff; /* White background for the form area */
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                max-width: 600px; /* Limit width for better readability */
                margin-left: auto; /* Center the form */
                margin-right: auto; /* Center the form */
            }

            hr {
                border: 0;
                height: 1px;
                background-color: #007bff; /* Matching the main theme blue */
                margin: 20px 0;
            }

            /* Form element styling */
            form {
                display: flex;
                flex-direction: column; /* Stack labels and inputs vertically */
                gap: 15px; /* Space between form rows */
            }

            label {
                display: flex;
                flex-direction: column; /* Stack span and input */
                font-weight: bold;
                color: #343a40; /* Darker text for labels */
            }

            label span {
                margin-bottom: 5px; /* Space between label text and input */
                font-weight: normal; /* Make span text less bold than label if needed */
            }

            input[type="text"],
            input[type="number"],
            input[type="date"],
            select {
                padding: 10px 12px;
                border: 1px solid #ced4da;
                border-radius: 4px;
                font-size: 1em;
                width: 100%; /* Full width within its container */
                box-sizing: border-box; /* Include padding in width */
            }

            input[type="text"]:focus,
            input[type="number"]:focus,
            input[type="date"]:focus,
            select:focus {
                border-color: #007bff;
                outline: none;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            input[readonly] {
                background-color: #e9ecef; /* Grey background for readonly fields */
                cursor: not-allowed;
            }

            /* Submit Button */
            .submit-button {
                background-color: #007bff; /* Main theme blue for submit */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1.1em;
                align-self: flex-start; /* Align button to the start of the form column */
                margin-top: 20px; /* Space above the button */
                transition: background-color 0.2s ease-in-out;
            }

            .submit-button:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

            /* Back Button (if you add one to this page later, style it similarly to welcome.jsp) */
            .back-button {
                background-color: #FF5733; /* Grey for back button */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1.1em;
                margin-top: 20px;
                transition: background-color 0.2s ease-in-out;
            }
            .back-button:hover {
                background-color: #E04E2D;
            }

            /* Error message styling */
            .error-message {
                color: #dc3545; /* Bootstrap's red color for errors */
                background-color: #f8d7da; /* Light red background */
                border: 1px solid #f5c6cb;
                padding: 10px 15px;
                border-radius: 4px;
                margin-top: 10px;
                font-size: 0.95em;
            }

        </style>
    </head>
    <body>
        <%
            if(!UserUtils.isLoggedIn(request)){
                response.sendRedirect("user-form.jsp");
                return;
            }
            
            if(!UserUtils.isFounder(request)){
                response.sendRedirect("welcome.jsp");
                return;
            }
            
            String action = (String) request.getAttribute("actionType");
            if(action == null || action.isEmpty()){
                response.sendRedirect("welcome.jsp");
                return;
            }
            
            String keyword = (String) request.getAttribute("strKeyword");
            if(keyword == null) keyword = "";
            
            String inputProjectName = (String) request.getAttribute("inputProjectName");
            if(inputProjectName == null) inputProjectName = "";
            
            String inputDescription = (String) request.getAttribute("inputDescription");
            if(inputDescription == null) inputDescription = "";
            
            String name = "";
            if (action.equals("createProject")) {
                name = "Create Project";
            } else if (action.equals("updateProjectStatus") || action.equals("editProject")) { /* Added "editProject" case */
                name = "Edit Project";
            } else {
                name = "Project"; // Default if action type is unexpected
            }
            
            ProjectDTO project = (ProjectDTO) request.getAttribute("project");
            if(project == null){
                project = new ProjectDTO(inputProjectName, inputDescription, null);
            }
        %>
    
        <div class="top-bar">
            <h1><%= name %> Form</h1>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="toProjectManagement"/>
                <input type="hidden" name="strName" value="${not empty param.strName ? param.strName : ''}"/>
                <button type="submit" class="back-button">Back to Projects</button>
            </form>
        </div>

        <div class="main-content-area">
            <hr>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="<%= action %>"/>
                <input type="hidden" name="strName" value="${not empty param.strName ? param.strName : ''}"/>
                
                <% if(!action.equals("createProject")) {%>
                <label>
                    <span>Project Id</span>
                    <input type="number" name="projectId" value="<%= project != null ? project.getProjectId() : "" %>" readonly />
                </label>
                <% } %>
                
                <label>
                    <span>Project Name</span>
                    <input type="text" name="strProjectName" value="<%= project != null ? project.getProjectName() : "" %>" <%= action.equals("updateProjectStatus") ? "readonly" : "" %> />
                </label>

                <label>
                    <span>Description</span>
                    <input type="text" name="strDescription" value="<%= project != null ? project.getDescription() : "" %>" <%= action.equals("updateProjectStatus") ? "readonly" : "" %> />
                </label>

                <% if(action.equals("updateProjectStatus")) { %>
                <label>
                    <span>Status</span>
                    <select name="strProjectStatus">
                        <option value="Ideation" <%= project != null && "Ideation"  .equals(project.getStatus()) ? "selected" : "" %>>Ideation</option>
                        <option value="Development" <%= project != null && "Development".equals(project.getStatus()) ? "selected" : "" %>>Development</option>
                        <option value="Launch" <%= project != null && "Launch".equals(project.getStatus()) ? "selected" : "" %>>Launch</option>
                        <option value="Scaling" <%= project != null && "Scaling".equals(project.getStatus()) ? "selected" : "" %>>Scaling</option>
                    </select>
                </label>
                <% } %>

                <label>
                    <span>Estimated Launch Date</span>
                    <input type="date" name="strEstimatedLaunchDate"
                           required min="<%= LocalDate.now().plusDays(1) %>"
                            value="<%= (project != null && project.getEstimatedLaunchDate() != null) ? project.getEstimatedLaunchDate() : "" %>" 
                            <%= action.equals("updateProjectStatus") ? "readonly" : "" %> />
                </label>
                
                <c:if test="${not empty errorMsg}">
                    <div class="error-message">${errorMsg}</div>
                </c:if>

                <button type="submit" class="submit-button">Submit</button> 
            </form>
        </div>
    </body>
</html>