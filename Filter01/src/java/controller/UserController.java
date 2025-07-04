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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
                    url = handleLogout(request);
                    break;
                default:
                    url = "error.jsp";
            }
        } catch (Exception e) {
        } finally {
            response.sendRedirect(url);
        }
    }

    //handle methods or service
    private String handleLogin(HttpServletRequest request) {
        UserDAO udao = new UserDAO();
        
        String url = LOGIN_PAGE;
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

    private String handleLogout(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute("user");
        
        String url = LOGIN_PAGE;
        
        if(user != null){
            session.removeAttribute("user");
        }else{
            url = ERROR_PAGE;
        }
        return url;
    }
    
    //get & post
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

