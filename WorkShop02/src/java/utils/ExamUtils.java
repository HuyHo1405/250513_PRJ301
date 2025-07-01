/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dto.ExamCategoryDTO;
import model.dto.ExamDTO;
import model.dto.QuestionDTO;

/**
 *
 * @author ho huy
 */
public class ExamUtils {
    
    public static Map<Integer, String> getMap(List<ExamCategoryDTO> list){
        Map<Integer, String> map = new HashMap<>();
        for (ExamCategoryDTO category : list) {
            map.put(category.getId(), category.getName());
        }
        return map;
    }
    
    public static List<QuestionDTO> getQuestionList(HttpServletRequest request){
        String[] ids = request.getParameterValues("questionId");
        String[] texts = request.getParameterValues("questionText");
        String[] optionAs = request.getParameterValues("optionA");
        String[] optionBs = request.getParameterValues("optionB");
        String[] optionCs = request.getParameterValues("optionC");
        String[] optionDs = request.getParameterValues("optionD");
        String[] corrects = request.getParameterValues("correctOption");
        
        if(ids == null
                || texts == null
                || optionAs == null
                || optionBs == null
                || optionCs == null
                || optionDs == null
                || corrects == null){
            return null;
        }
        
        List<QuestionDTO> questionList = new ArrayList<>();
        
        for (int i = 0; i < corrects.length; i++) {
            QuestionDTO q = new QuestionDTO();
            q.setId(CUtils.toInt(ids[i]));
            q.setText(texts[i]);
            q.setOptionA(optionAs[i]);
            q.setOptionB(optionBs[i]);
            q.setOptionC(optionCs[i]);
            q.setOptionD(optionDs[i]);
            q.setCorrectOption(corrects[i].toUpperCase());
            questionList.add(q);
        }
        return questionList;
    }
    
    public static ExamDTO getParamExam(HttpServletRequest request) {
        int examId = CUtils.toInt(request.getParameter("examId"));
        String title = request.getParameter("examTitle");
        String subject = request.getParameter("examSubject");
        int categoryId = CUtils.toInt(request.getParameter("examCategoryId"));
        int totalMarks = CUtils.toInt(request.getParameter("examTotalMarks"));
        int duration = CUtils.toInt(request.getParameter("examDuration"));
        return new ExamDTO(examId, title, subject, categoryId, totalMarks, duration);
    }
}
