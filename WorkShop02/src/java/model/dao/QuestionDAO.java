package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.dto.QuestionDTO;
import utils.DbUtils;

public class QuestionDAO {

    private final String TABLE_NAME = "tblQuestions";

    private QuestionDTO mapToDto(ResultSet rs) throws SQLException {
        return new QuestionDTO(
            rs.getInt("question_id"),
            rs.getInt("exam_id"),
            rs.getString("question_text"),
            rs.getString("option_a"),
            rs.getString("option_b"),
            rs.getString("option_c"),
            rs.getString("option_d"),
            rs.getString("correct_option")
        );
    }

    public boolean create(QuestionDTO question) {
        String sql = "INSERT INTO " + TABLE_NAME +
                     " (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, question.getExamId());
            ps.setString(2, question.getText());
            ps.setString(3, question.getOptionA());
            ps.setString(4, question.getOptionB());
            ps.setString(5, question.getOptionC());
            ps.setString(6, question.getOptionD());
            ps.setString(7, question.getCorrectOption());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in create(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<QuestionDTO> retrieve(String condition, Object... params) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + condition;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();
            List<QuestionDTO> list = new ArrayList<>();
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

    public QuestionDTO getFirst(String condition, Object... params) {
        List<QuestionDTO> list = retrieve(condition, params);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    public boolean update(QuestionDTO question) {
        String sql = "UPDATE " + TABLE_NAME +
                     " SET exam_id = ?, question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_option = ? " +
                     "WHERE question_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, question.getExamId());
            ps.setString(2, question.getText());
            ps.setString(3, question.getOptionA());
            ps.setString(4, question.getOptionB());
            ps.setString(5, question.getOptionC());
            ps.setString(6, question.getOptionD());
            ps.setString(7, question.getCorrectOption());
            ps.setInt(8, question.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int questionId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE question_id = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
