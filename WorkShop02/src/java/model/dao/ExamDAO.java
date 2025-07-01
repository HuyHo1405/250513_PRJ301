package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.ExamDTO;
import utils.DbUtils;

public class ExamDAO {

    private final String TABLE_NAME = "tblExams";

    private ExamDTO mapToDto(ResultSet rs) throws SQLException {
        return new ExamDTO(
            rs.getInt("exam_id"),
            rs.getString("exam_title"),
            rs.getString("Subject"),
            rs.getInt("category_id"),
            rs.getInt("total_marks"),
            rs.getInt("Duration")
        );
    }

    public boolean create(ExamDTO exam) {
        String sql = "INSERT INTO " + TABLE_NAME + " (exam_title, Subject, category_id, total_marks, Duration) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryId());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in create(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public Integer createReturnId(ExamDTO exam) {
        String sql = "INSERT INTO " + TABLE_NAME + " (exam_title, Subject, category_id, total_marks, Duration) VALUES (?, ?, ?, ?, ?)";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryId());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về exam_id vừa được sinh ra
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in createReturnId(): " + e.getMessage());
            e.printStackTrace();
        }
        return null; // nếu lỗi
    }

    public List<ExamDTO> retrieve(String condition, Object... params) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + condition;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();
            List<ExamDTO> list = new ArrayList<>();
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

    public ExamDTO getFirst(String condition, Object... params) {
        List<ExamDTO> list = retrieve(condition, params);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    public boolean update(ExamDTO exam) {
        String sql = "UPDATE " + TABLE_NAME + " SET exam_title = ?, Subject = ?, category_id = ?, total_marks = ?, Duration = ? WHERE exam_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryId());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());
            ps.setInt(6, exam.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int examId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE exam_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
