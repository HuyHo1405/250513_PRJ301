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
import utils.AuthenticationUtils;
import utils.UserUtils;

/**
 *
 * @author ho huy
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String action = req.getParameter("action");
        String uri = req.getRequestURI();

        boolean accessingUserForm = uri.endsWith("/user-form.jsp");
        boolean accessingLogin = uri.endsWith("/UserController") && "login".equals(action);
        
        boolean isProtected = AuthenticationUtils.isProtectedJsp(uri) 
                            || AuthenticationUtils.isProtectedAction(uri, action);  
        boolean isLoggedIn = UserUtils.isLoggedIn(req);

        if((accessingUserForm || accessingLogin) && isLoggedIn){
            res.sendRedirect("welcome.jsp");
            return;
        }
        
        if (isProtected && !isLoggedIn) {
            res.sendRedirect("user-form.jsp");
            return;
        }
        
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }
}
