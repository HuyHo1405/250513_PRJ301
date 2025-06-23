
package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dto.UserDTO;

public class UserUtils {
    
    // helper methods
    public static UserDTO getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){
            return (UserDTO) session.getAttribute("user");
        }
        return null;
    }
    
    public static boolean isLoggedIn(HttpServletRequest request){
        return getUser(request) != null;
    }
    
    // validate role methods
    private static boolean hasRole(HttpServletRequest request, String role){
        UserDTO user = getUser(request);
        if(user != null){
            return user.getRole().equals(role);
        }
        return false;
    }
    
    public static boolean isMember(HttpServletRequest request){
        return hasRole(request, "Team Member");
    }
    
    public static boolean isFounder(HttpServletRequest request){
        return hasRole(request, "Founder");
    }
}
