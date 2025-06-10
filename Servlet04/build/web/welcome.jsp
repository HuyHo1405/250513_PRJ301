<%-- 
Document   : welcome
Created on : 23-May-2025, 07:40:53
Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserDTO"%>
<%@ page import="java.util.List" %>
<%@page import="model.ProductDTO"%>
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

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="searchProduct">

            <div>
                <label for="search">Search*</label>
                <input type="text" id="name" name="strKeyword" 
                       value="<%= request.getParameter("strKeyword") != null ? request.getParameter("strKeyword") : "" %>">
            </div>

            <button type="submit">Search</button>
        </form></br>

        <h2>product list</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Size</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("productList");
                    if (productList != null && !productList.isEmpty()) {
                        for (ProductDTO product : productList) {
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getImage() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getSize() %></td>
                    <td><%= product.isStatus() ? "Active" : "Inactive" %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="7">No products found.</td></tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
