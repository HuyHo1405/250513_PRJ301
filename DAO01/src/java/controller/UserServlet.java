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
import jakarta.servlet.http.HttpSession; 
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author ho huy
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    //static final fields
    private static final String WELCOME_PAGE = "welcome.jsp";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ERROR_PAGE = "error.jsp";

    //process request
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = WELCOME_PAGE;
        
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "login":
                    url = handleLogin(request);
                    break;
                case "logout":
                    url = handleLogout(request, response);
                    break;
                default:
                    url = "error.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = "error.jsp";
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    //handle methods or service
    private String handleLogin(HttpServletRequest request) {
        String url = LOGIN_PAGE;
        
        UserDAO udao = new UserDAO();
        String strUsername = request.getParameter("username");
        String strPassword = request.getParameter("password");

        if (udao.login(strUsername, strPassword)) {
            url = WELCOME_PAGE;
            HttpSession session = request.getSession();
            session.setAttribute("user", udao.getUserByUserName(strUsername));
        } else {
            request.setAttribute("message", "Username or Password Error");
        }
        return url;
    }

    private String handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = LOGIN_PAGE;
        
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute("user");
        
        if(user != null){
            session.removeAttribute("user");
        }else{
            url = ERROR_PAGE;
        }
        return url;
    }
    
    //get & post
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

