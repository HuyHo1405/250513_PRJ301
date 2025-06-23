package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.dto.ProjectDTO;
import utils.DbUtils;

public class ProjectDAO {

    private static final String TABLE_NAME = "tblStartupProjects";

    // Map ResultSet thành ProjectDTO
    private ProjectDTO mapToProject(ResultSet rs) throws SQLException {
        return new ProjectDTO(
            rs.getInt("project_id"),
            rs.getString("project_name"),
            rs.getString("Description"),
            rs.getString("Status"),
            rs.getDate("estimated_launchDATE").toLocalDate()
        );
    }

    // CREATE
    public boolean create(ProjectDTO project) {
        String sql = "INSERT INTO " + TABLE_NAME + " (project_name, Description, Status, estimated_launchDATE) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getStatus());
            ps.setDate(4, Date.valueOf(project.getEstimatedLaunchDate()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in create(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // RETRIEVE
    public List<ProjectDTO> retrieve(String condition, Object... params) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + condition;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            List<ProjectDTO> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapToProject(rs));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in retrieve(): " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // UPDATE
    public boolean update(ProjectDTO project) {
        String sql = "UPDATE " + TABLE_NAME + " SET project_name = ?, Description = ?, Status = ?, estimated_launchDATE = ? WHERE project_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getStatus());
            ps.setDate(4, Date.valueOf(project.getEstimatedLaunchDate()));
            ps.setInt(5, project.getProjectId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE project_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public ProjectDTO getFirst(String condition, Object... params) {
        List<ProjectDTO> ls = retrieve(condition, params);
        return (ls != null && !ls.isEmpty()) ? ls.get(0) : null;
    }
}
