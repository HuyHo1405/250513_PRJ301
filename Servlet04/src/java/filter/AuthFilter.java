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
import utils.UserUtils;

/**
 *
 * @author ho huy
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        if (uri.endsWith("MainController")) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = UserUtils.isLoggedIn(req);
        boolean isLoginPage = uri.endsWith("login.jsp") || uri.endsWith("index.jsp");
        boolean isLoginAction = "login".equals(req.getParameter("action"));

        if (isLoggedIn) {
            if (isLoginPage || isLoginAction) {
                res.sendRedirect("welcome.jsp");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (isLoginPage || isLoginAction) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("login.jsp");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
