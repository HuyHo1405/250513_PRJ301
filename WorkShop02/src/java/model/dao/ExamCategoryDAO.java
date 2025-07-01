package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.ExamCategoryDTO;
import utils.DbUtils;

public class ExamCategoryDAO {

    private final String TABLE_NAME = "tblExamCategories";

    private ExamCategoryDTO mapToDto(ResultSet rs) throws SQLException {
        return new ExamCategoryDTO(
            rs.getInt("category_id"),
            rs.getString("category_name"),
            rs.getString("description")
        );
    }

    public boolean create(ExamCategoryDTO category) {
        String sql = "INSERT INTO " + TABLE_NAME + " (category_name, description) VALUES (?, ?)";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in create(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<ExamCategoryDTO> retrieve(String condition, Object... params) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + condition;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();
            List<ExamCategoryDTO> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapToDto(rs));
            }
            return list;
        } catch (Exception e) {
            System.err.println("Error in retrieve(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ExamCategoryDTO getFirst(String condition, Object... params) {
        List<ExamCategoryDTO> list = retrieve(condition, params);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    public boolean update(ExamCategoryDTO category) {
        String sql = "UPDATE " + TABLE_NAME + " SET category_name = ?, description = ? WHERE category_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int categoryId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE category_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
