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
import java.util.List;
import model.ProductDAO;
import model.ProductDTO;

/**
 *
 * @author ho huy
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private static final String WELCOME_PAGE = "welcome.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME_PAGE;
        
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "addProduct":
                    url = handleAdd(request);
                    break;
                case "searchProduct":
                    url = handleSearch(request);
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
    
    public String handleAdd(HttpServletRequest request){
        
        String id = request.getParameter("productId");
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String image = request.getParameter("productImage");
        double price = Double.parseDouble(request.getParameter("productPrice"));
        String size = request.getParameter("productSize");
        boolean status = request.getParameter("productStatus") != null;
        
        ProductDAO pdao = new ProductDAO();
        ProductDTO pdto = new ProductDTO(id, name, description, image, price, size, status);
        if(pdao.create(pdto)){
            return WELCOME_PAGE;
        }else{
            return "error.jsp";
        }
        
    }
    
    public String handleSearch(HttpServletRequest request){
        String keyword = request.getParameter("strKeyword");
        
        ProductDAO pdao = new ProductDAO();
        List<ProductDTO> list = pdao.retrieveByName(keyword);
        System.out.println(list);
        request.setAttribute("productList", list);
        
        return WELCOME_PAGE;
    }

}
