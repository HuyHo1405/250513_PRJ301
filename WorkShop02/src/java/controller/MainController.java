package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import utils.CUtils;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    //static fields
    public static final String USER_FORM = "user-form.jsp";
    public static final String ERROR = "error.jsp";
    public static final String WELCOME = "welcome.jsp";

    //action list
    public static final List<String> WELCOME_ACTIONS = Arrays.asList(
            "toWelcome"
    );
    
    public static final List<String> USER_ACTIONS = Arrays.asList(
            "login",
            "logout"
    );
    
    public static final List<String> EXAM_ACTIONS = Arrays.asList(
            "toExam",
            "toExamCategory",
            "toExamManagement",
            "toAddExam",
            "toEditExam",
            "searchExam",
            "createExam",
            "updateExam"
    );
    
    public static final List<String> SUBMIT_EXAM_ACTIONS = Arrays.asList(
            "calculateResult"
    );

    //process
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "";
        try {
            String action = request.getParameter("action");
            System.out.println(action);
            if (USER_ACTIONS.contains(action)) {
                System.out.println("enter user controller");
                url = "/UserController";
            }else if (EXAM_ACTIONS.contains(action)) {
                System.out.println("enter exam controller");
                url = "/ExamController";
            }else if (SUBMIT_EXAM_ACTIONS.contains(action)) {
                System.out.println("enter submit exam controller");
                url = "/SubmitExamServlet";
            }else if (WELCOME_ACTIONS.contains(action)) {
                System.out.println("enter welcome.jsp");
                url = WELCOME;
            } else {
                System.out.println("error.jsp");
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
}
