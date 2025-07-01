package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dto.UserDTO;
import utils.CUtils;
import utils.HashUtils;
import utils.ValidationUtils;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    //static fields
    public static final String USER_FORM = "user-form.jsp";
    public static final String ERROR = "error.jsp";
    public static final String WELCOME = "welcome.jsp";
    
    public static final UserDAO UDAO = new UserDAO();
    
    //process request
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "";
        try {
            String action = request.getParameter("action");
            if(action.equals("login")){
                url = handleLogin(request, response);
            }else if(action.equals("logout")){
                url = handleLogout(request, response);
            }else {
                url = CUtils.error(request, "No Action Founded!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = CUtils.error(request, "Internal Error!");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    //doGet & doPost
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
    
    //handle methods
    private String handleLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("strUsername");
        String password = request.getParameter("strPassword");
        
        if(ValidationUtils.isNullOrEmpty(username) || ValidationUtils.isNullOrEmpty(password)){
            request.setAttribute("errorMsg","Empty Input Parameters! Please Fill in All the Required Information!");
            request.setAttribute("actionType", "login");
            return USER_FORM;
        }
        
        UserDTO user = UDAO.getFirst("username = ? AND password = ?", username, HashUtils.hashPassword(password));
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return WELCOME;
        }else{
            request.setAttribute("errorMsg","Incorrect username or password!");
            request.setAttribute("actionType", "login");
            return USER_FORM;
        }
    }

    private String handleLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return USER_FORM;
    }
}
