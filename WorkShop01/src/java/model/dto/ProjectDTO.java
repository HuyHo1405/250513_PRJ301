package model.dto;

import java.time.LocalDate;

public class ProjectDTO {
    private int projectId;
    private String projectName;
    private String description;
    private String status;
    private LocalDate estimatedLaunchDate;

    public ProjectDTO(String projectName, String description, LocalDate estimatedLaunchDate) {
        this.projectId = -1;
        this.projectName = projectName;
        this.description = description;
        this.status = "Ideation";
        this.estimatedLaunchDate = estimatedLaunchDate;
    }

    public ProjectDTO(int projectId, String projectName, String description, String status, LocalDate estimatedLaunchDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimatedLaunchDate = estimatedLaunchDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEstimatedLaunchDate() {
        return estimatedLaunchDate;
    }

    public void setEstimatedLaunchDate(LocalDate estimatedLaunchDate) {
        this.estimatedLaunchDate = estimatedLaunchDate;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", estimatedLaunchDate=" + estimatedLaunchDate +
                '}';
    }
}
