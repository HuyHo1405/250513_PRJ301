/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ProductUtils;

/**
 *
 * @author ho huy
 */
@WebFilter(filterName = "ProductDataValidator", urlPatterns = {"/MainController"})
public class ProductDataValidator implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        boolean isAddAction = "addProduct".equals(req.getParameter("action"));
        
        if(!isAddAction){
            chain.doFilter(request, response);
            return;
        }
        
        boolean validateId = ProductUtils.validateProductId(req, "productId");
        boolean validateName = ProductUtils.validateStr(req, "productName");
        boolean validateDescription = ProductUtils.validateStr(req, "productDescription");
        boolean validateImageUrl = ProductUtils.validateStr(req, "productImage");
        boolean validatePrice = ProductUtils.validatePositiveDouble(req, "productPrice");
        boolean validateSize = ProductUtils.validateSize(req, "productSize");
        boolean validateStatus = ProductUtils.validateStatus(req, "productStatus");
        
        System.out.println(validateDescription);
        System.out.println(validateId);
        System.out.println(validateImageUrl);
        System.out.println(validateName);
        System.out.println(validatePrice);
        System.out.println(validateSize);
        System.out.println(validateStatus);
        
        
        
        if(validateDescription && validateId && validateImageUrl &&
                validatePrice && validateSize && validateStatus && validateName){
            chain.doFilter(request, response);
            return;
        }
        System.out.println("fail");
        req.getRequestDispatcher("product-form.jsp").forward(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
