/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.List;
import model.dto.ExamDTO;
import model.dto.QuestionDTO;

/**
 *
 * @author ho huy
 */
public class ValidationUtils {
    
    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
    
    public static boolean isInvalidId(int id){
        return id < 1;
    }
    
    public static boolean isInvalidExam(ExamDTO exam){
        return isNullOrEmpty(exam.getExamTitle())
                || isNullOrEmpty(exam.getSubject())
                || isInvalidId(exam.getCategoryId())
                || isInvalidId(exam.getTotalMarks())
                || isInvalidId(exam.getDuration());
    }
    
    public static boolean isInvalidQuestion(QuestionDTO question){
        return isNullOrEmpty(question.getText())
                || isNullOrEmpty(question.getOptionA())
                || isNullOrEmpty(question.getOptionB())
                || isNullOrEmpty(question.getOptionC())
                || isNullOrEmpty(question.getOptionD())
                || isNullOrEmpty(question.getCorrectOption());
    }
    
    public static boolean isInvalidQuestions(List<QuestionDTO> list){
        for (QuestionDTO q : list) {
            if(isInvalidQuestion(q)){
                return true;
            }
        }
        return false;
    }
    
}
