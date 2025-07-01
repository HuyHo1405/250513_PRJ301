/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author ho huy
 */
public class QuestionDTO {
    
    //fields
    private int id;
    private int examId;
    private String text;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;
    
    //constructors
    public QuestionDTO() {
    }

    public QuestionDTO(String text, String optionA, String optionB, String optionC, String optionD, String correctOption) {
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }
    
    public QuestionDTO(int id, int examId, String text, String optionA, String optionB, String optionC, String optionD, String correctOption) {
        this.id = id;
        this.examId = examId;
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    //getters
    public int getId() {
        return id;
    }

    public int getExamId() {
        return examId;
    }

    public String getText() {
        return text;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }
    
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
     
    //toString
    @Override
    public String toString() {
        return "QuestionDTO{" + "id=" + id + ", examId=" + examId + ", text=" + text + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", correctOption=" + correctOption + '}';
    }
    

}
