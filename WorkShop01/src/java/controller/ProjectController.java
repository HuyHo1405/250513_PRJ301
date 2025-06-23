/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import model.dao.ProjectDAO;
import model.dto.ProjectDTO;
import utils.CUtils;
import utils.UserUtils;
import utils.ValidationUtils;

/**
 *
 * @author ho huy
 */
@WebServlet(name = "ProjectController", urlPatterns = {"/ProjectController"})
public class ProjectController extends HttpServlet {

    //static fields
    public static final String ERROR_PAGE = "error.jsp";
    public static final String PROJECT_MANAGEMENT_PAGE = "project-management.jsp";
    public static final String PROJECT_FORM_PAGE = "project-form.jsp";
    
    private static final ProjectDAO PDAO = new ProjectDAO();
    
    //doGet, doPost & process
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "";
        try {
            String action = request.getParameter("action");
            if(action.equals("toProjectManagement")){
                request.setAttribute("projectList", PDAO.retrieve("1 = 1"));
                url = PROJECT_MANAGEMENT_PAGE;
            }else if(action.equals("toAddProject")){
                request.setAttribute("actionType", "createProject");
                request.setAttribute("strKeyword", request.getParameter("strKeyword"));
                url = PROJECT_FORM_PAGE;
            }else if(action.equals("toEditProject")){
                url = handleToEdit(request, response);
            }else if(action.equals("createProject")){
                url = handleCreateProject(request, response);
            }else if(action.equals("searchProjectsByName")){
                url = handleSearch(request, response);
            }else if(action.equals("updateProjectStatus")){
                url = handleUpdateStatus(request, response);
            }else {
                url = ERROR_PAGE;
            }
        } catch (Exception e) {
            url = ERROR_PAGE;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String handleSearch(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("strName");
        if(name == null){
            request.setAttribute("errorMsg", "Keyword not found");
            return PROJECT_MANAGEMENT_PAGE;
        }
        
        request.setAttribute("projectList", PDAO.retrieve("project_name LIKE ?", "%" + name + "%"));
        return PROJECT_MANAGEMENT_PAGE;
    }

    private String handleToEdit(HttpServletRequest request, HttpServletResponse response) {
        int projectId = CUtils.toInt(request.getParameter("projectId"));
        if(ValidationUtils.isInvalidId(projectId)){
            return CUtils.error(request, "Invalid Project ID.");
        }
        request.setAttribute("strKeyword", request.getParameter("strKeyword"));
        request.setAttribute("project", PDAO.getFirst("project_id = ?", projectId));
        request.setAttribute("actionType", "updateProjectStatus");
        return PROJECT_FORM_PAGE;
    }

    private String handleCreateProject(HttpServletRequest request, HttpServletResponse response) {
        String projectName = request.getParameter("strProjectName");
        String description = request.getParameter("strDescription");
        LocalDate date = LocalDate.parse(request.getParameter("strEstimatedLaunchDate"));
        
        if(ValidationUtils.isNullOrEmpty(projectName) || ValidationUtils.isNullOrEmpty(description)){
            return CUtils.error(request, "Empty input data!");
        }
        
        if(date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())){
            return CUtils.error(request, "Date must be in the future!");
        }
        
        PDAO.create(new ProjectDTO(projectName, description, date));
        request.setAttribute("projectList", PDAO.retrieve("1 = 1"));
        return PROJECT_MANAGEMENT_PAGE;
    }

    private String handleUpdateStatus(HttpServletRequest request, HttpServletResponse response) {
        int projectId = CUtils.toInt(request.getParameter("projectId"));
        String projectName = request.getParameter("strProjectName");
        String description = request.getParameter("strDescription");
        String status = request.getParameter("strProjectStatus");
        LocalDate date = LocalDate.parse(request.getParameter("strEstimatedLaunchDate"));
        
        if(ValidationUtils.isNullOrEmpty(projectName) || ValidationUtils.isNullOrEmpty(description)
                || ValidationUtils.isNullOrEmpty(status) || ValidationUtils.isInvalidId(projectId)){
            return CUtils.error(request, "Empty input data!");
        }
        
        if(date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())){
            return CUtils.error(request, "Date must be in the future!");
        }
        
        PDAO.update(new ProjectDTO(projectId, projectName, description, status, date));
        request.setAttribute("projectList", PDAO.retrieve("1 = 1"));
        return PROJECT_MANAGEMENT_PAGE;
    }
    

}
