<%-- 
    Document   : index
    Created on : 16-May-2025, 08:14:45
    Author     : ho huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <h1>HTML Forms</h1>
        <form action="#" method="post">
            <lable>Textbox</lable>
            <input type="text" name="textbox"></br>
            <lable>Password</lable>
            <input type="password" name="password"></br>
            <lable>Hidden</lable>
            <input type="hidden" name="hidden"></br>
            <lable>Male</lable>
            <input type="checkbox" name="checkbox"></br>
            <lable>Status</lable>
            <input type="radio" name="radioStatus">Single</br>
            <input type="radio" name="radioStatus">Marriage</br>
            <input type="radio" name="radioStatus">Divorsed</br>
            <lable>ComboBox</lable>
            <select name="comboBox">
                <option value="Servlet">JSP and Servlet</option>
                <option value="EJB">EJB</option>
            </select></br>
            <lable>Multiple</lable>
            <select name="list" multiple="multiple">
                <option value="Servlet">JSP and Servlet</option>
                <option value="EJB">EJB</option>
                <option value="Java">Core Java</option>
            </select></br>
            <lable>TextArea</lable>
            <textarea name="textArea" rows="4" cols="20">This is a form parameter demo!!!</textarea></br>
            <input type="submit" name="b">
            <input type="submit" name="action" value="register">
            <input type="reset" name="b">
            <input type="button" name="b" value="JavaScript">
        </form>
    </body>
</html>
