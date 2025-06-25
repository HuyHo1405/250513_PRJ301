/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ho huy
 */
public class AuthorizationUtils {
    
    private static final List<String> PROTECTED_JSP = Arrays.asList(
            "/project-form.jsp",
            "/example.jsp"
    );
    
    private static final List<String> USER_ACTIONS = Arrays.asList(
    );

    private static final List<String> PROJECT_ACTIONS = Arrays.asList(
            "toAddProject",
            "toEditProject",
            "createProject",
            "updateProjectStatus",
            "searchProjectsByName"
    );
    
    private static final List<String> MAIN_ACTIONS = Arrays.asList(
            "toAddProject",
            "toEditProject",
            "createProject",
            "updateProjectStatus",
            "searchProjectsByName"
    );
   
    private static HashMap<String, List<String>> getProtectedActions(){
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("MainController", MAIN_ACTIONS);
        map.put("UserController", USER_ACTIONS);
        map.put("ProjectController", PROJECT_ACTIONS);
        return map;
    }
    
    
    public static boolean isProtectedAction(String uri, String action){
        if (action == null || uri == null) return false;

        String controller = uri.substring(uri.lastIndexOf("/") + 1);
        
        Map<String, List<String>> protectedActions = getProtectedActions();

        List<String> actions = protectedActions.get(controller);
        if (actions == null) return false;

        return actions.contains(action);
    }
    
    public static boolean isProtectedJsp(String uri){
        return (uri == null)? false: PROTECTED_JSP.stream().anyMatch(uri::endsWith);
    }
    
}
