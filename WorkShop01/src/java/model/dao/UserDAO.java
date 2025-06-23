package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.UserDTO;
import utils.DbUtils;

public class UserDAO {
    
    //static fields
    private final String TABLE_NAME = "tblUsers";

    //useful methods
    private UserDTO mapToDto(ResultSet rs) throws SQLException {
        return new UserDTO(
            rs.getString("Username"),
            rs.getString("Name"),
            rs.getString("Password"),
            rs.getString("Role")
        );
    }

    //base crud
    public boolean create(UserDTO user) {
        String sql = "INSERT INTO " + TABLE_NAME + " (Username, Name, Password, Role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in create(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<UserDTO> retrieve(String condition, Object... params) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + condition;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();
            List<UserDTO> list = new ArrayList<>();
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

    public boolean update(UserDTO user) {
        String sql = "UPDATE " + TABLE_NAME + " SET Name = ?, Password = ?, Role = ? WHERE Username = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String username) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE Username = ?";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public UserDTO getFirst(String condition, Object... params){
        List<UserDTO> ls = retrieve(condition, params);
        return (ls != null && !ls.isEmpty())? ls.get(0): null;
    }
    
}
