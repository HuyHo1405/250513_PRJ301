/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import model.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ho huy
 */
public class UserUtils {
    
    public static UserDTO getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){
            return (UserDTO) session.getAttribute("user");
        }else{
            return null;
        }
    }
    
    public static boolean isLoggedIn(HttpServletRequest request){
        return getUser(request) != null;
    }
    
    public static boolean hasRole(HttpServletRequest request, String role){
        return getUser(request).getRole().equals(role);
    }
    
    public static boolean isAdmin(HttpServletRequest request){
        return hasRole(request, "AD");
    }
    
    public static boolean isManager(HttpServletRequest request){
        return hasRole(request, "MA");
    }
    
    public static boolean isMenber(HttpServletRequest request){
        return hasRole(request, "MB");
    }
}
