/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDAO;

/**
 *
 * @author ho huy
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController", "/login"})
public class MainController extends HttpServlet {

    private static final String WELCOME = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = WELCOME;
        try {
            UserDAO udao = new UserDAO();
           
            String action = request.getParameter("action");
            if (action.equals("login")) {
                String strUsername = request.getParameter("username");
                String strPassword = request.getParameter("password");

                if (udao.login(strUsername, strPassword)) {
                    //redirect to main page
                    url = "welcome.jsp";
                    request.setAttribute("user", udao.getUserByUserName(strUsername));
                } else {
                    //redirect to login.jsp with notification
                    url = "login.jsp";
                    request.setAttribute("message", "Username or Password Error");
                    
                }
            }
        } catch (Exception e) {

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
