
package utils;

import model.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserUtils {
    
    //static fields
    private static final String EMAIL_REGEX = "";
    private static final String PASSWORD_REGEX = "";
    
    //permission
    private static boolean hasRole(HttpServletRequest request, String role){
        return getUser(request).getRole().equals(role);
    }
    
    public static UserDTO getUser(HttpServletRequest request){
        HttpSession ss = request.getSession();
        if(ss != null){
            return (UserDTO) ss.getAttribute("user");
        }
        return null;
    }
    
    public static boolean isMember(HttpServletRequest request){
        return hasRole(request, "MB");
    }
    
    public static boolean isManager(HttpServletRequest request){
        return hasRole(request, "MA");
    }
    
    public static boolean isAdmin(HttpServletRequest request){
        return hasRole(request, "AD");
    }
    
    public static boolean isLoggedIn(HttpServletRequest request){
        return getUser(request) != null;
    }
    
    //data validation
    private static boolean validateRgx(HttpServletRequest request, String name, String regex){
        String str = request.getParameter(name);
        if(str != null){
            return str.matches(regex);
        }
        return false;
    }
    
    public static boolean validateEmail(HttpServletRequest request){
        return validateRgx(request, "email", EMAIL_REGEX);
    }
    
    public static boolean validatePassword(HttpServletRequest request){
        return validateRgx(request, "password", PASSWORD_REGEX);
    }

}
