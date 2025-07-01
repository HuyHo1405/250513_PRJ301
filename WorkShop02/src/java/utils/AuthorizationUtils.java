package utils;

import java.util.*;

public class AuthorizationUtils {

    private static final List<String> PROTECTED_JSP = Arrays.asList(
//            "/result.jsp",
//            "/exam.jsp",
            "/exam-form.jsp"
//            "/exam-management.jsp",
//            "/exam-category.jsp",
//            "/welcome.jsp",
//            "/user-form.jsp",
//            "/index.jsp",
//            "/error.jsp"
    );

    private static final List<String> USER_ACTIONS = Arrays.asList(
//            "login",
//            "logout"
    );

    private static final List<String> EXAM_ACTIONS = Arrays.asList(
//            "toExam",
//            "toExamCategory",
//            "toExamManagement",
            "toAddExam",
            "toEditExam",
//            "searchExam",
            "createExam",
            "updateExam"
    );
    private static final List<String> SUBMIT_EXAM_ACTIONS = Arrays.asList(
//            "calculateResult"
    );

    private static final List<String> MAIN_ACTIONS = Arrays.asList(
//            "login",
//            "logout",
//            "toExam",
//            "toExamCategory",
//            "toExamManagement",
            "toAddExam",
            "toEditExam",
//            "searchExam",
            "createExam",
            "updateExam"
//            "calculateResult"
    );
    
    private static HashMap<String, List<String>> getProtectedActions(){
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("MainController", MAIN_ACTIONS);
        map.put("UserController", USER_ACTIONS);
        map.put("ExamController", EXAM_ACTIONS);
        map.put("SubmitExamServlet", SUBMIT_EXAM_ACTIONS);
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
