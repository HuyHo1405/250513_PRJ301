/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author ho huy
 */
public class ExamCategoryDTO {
    
    //fields
    private int id;
    private String name;
    private String description;

    //constructors
    public ExamCategoryDTO() {
    }

    public ExamCategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ExamCategoryDTO(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    //toString
    @Override
    public String toString() {
        return "ExamCategoryDTO{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
}
