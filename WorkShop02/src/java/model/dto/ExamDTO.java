/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author ho huy
 */
public class ExamDTO {
    
    //fields
    private int id;
    private String examTitle;
    private String subject;
    private int categoryId;
    private int totalMarks;
    private int duration;
    
    //constructors
    public ExamDTO() {
    }
    
    public ExamDTO(String examTitle, String subject, int categoryId, int totalMarks, int duration) {
        this.examTitle = examTitle;
        this.subject = subject;
        this.categoryId = categoryId;
        this.totalMarks = totalMarks;
        this.duration = duration;
    }

    public ExamDTO(int id, String examTitle, String subject, int categoryId, int totalMarks, int duration) {
        this.id = id;
        this.examTitle = examTitle;
        this.subject = subject;
        this.categoryId = categoryId;
        this.totalMarks = totalMarks;
        this.duration = duration;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public String getSubject() {
        return subject;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public int getDuration() {
        return duration;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ExamDTO{" + "id=" + id + ", examTitle=" + examTitle + ", subject=" + subject + ", categoryId=" + categoryId + ", totalMarks=" + totalMarks + ", duration=" + duration + '}';
    }
}
