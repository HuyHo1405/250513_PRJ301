<%-- 
    Document   : product-form
    Created on : 06-Jun-2025, 07:36:11
    Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserDTO"%>
<%@page import="utils.UserUtils"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Form</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="addProduct">

            <div>
                <label for="productId">Product ID*</label>
                <input type="text" id="productId" name="productId" 
                       value="<%= request.getParameter("productId") != null ? request.getParameter("productId") : "" %>" required>
            </div>

            <div>
                <label for="name">Name*</label>
                <input type="text" id="name" name="productName" 
                       value="<%= request.getParameter("productName") != null ? request.getParameter("productName") : "" %>" required>
            </div>

            <div>
                <label for="description">Description*</label>
                <input type="text" id="description" name="productDescription" 
                       value="<%= request.getParameter("productDescription") != null ? request.getParameter("productDescription") : "" %>" required>
            </div>

            <div>
                <label for="image">Image URL*</label>
                <input type="text" id="image" name="productImage" 
                       value="<%= request.getParameter("productImage") != null ? request.getParameter("productImage") : "" %>" required>
            </div>

            <div>
                <label for="price">Price*</label>
                <input type="number" id="price" name="productPrice" step="any"
                       value="<%= request.getParameter("productPrice") != null ? request.getParameter("productPrice") : "" %>" required>
            </div>

            <div>
                <label for="size">Size*</label>
                <input type="text" id="size" name="productSize" 
                       value="<%= request.getParameter("productSize") != null ? request.getParameter("productSize") : "" %>" required>
            </div>

            <div>
                <label for="status">Active Status*</label>
                <input type="checkbox" id="status" name="productStatus"
                       <%= request.getParameter("productStatus") != null ? "checked" : "" %> >
            </div>

            <div>
                <button type="submit">Submit</button>
                <button type="reset">Reset</button>
            </div>
        </form>
    </body>
</html>
