/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.dto.ExamCategoryDTO;
import model.dto.ExamDTO;
import model.dto.QuestionDTO;

/**
 *
 * @author ho huy
 */
public class CUtils {
    
    //set attribute and send to error.jsp
    private static String ERROR = "error.jsp";
    public static String error(HttpServletRequest request, String errorMsg){
        request.setAttribute("errorMsg", errorMsg);
        return ERROR;
    }
    
    //set attribute
    public static void setExamManagementAttributes(HttpServletRequest request, List<ExamDTO> exams, List<ExamCategoryDTO> categories) {
        request.setAttribute("examList", exams);
        request.setAttribute("categoryList", categories);
        request.setAttribute("mapCategoryName", ExamUtils.getMap(categories));
    }
    
    public static void setExamFormAttributes(HttpServletRequest request, List<QuestionDTO> questions, List<ExamCategoryDTO> categories, String actionType, String errorMsg) {
        request.setAttribute("categoryList", categories);
        request.setAttribute("questionList", questions);
        request.setAttribute("errorMsg", errorMsg);
        request.setAttribute("actionType", actionType);
    }
    
    //parse
    public static int toInt(String str){
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    //get
    
    
}
