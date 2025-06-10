
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DbUtils;

public class UserDAO {

    public UserDAO() {
        
    }
    
    public boolean login(String userName, String password){
        UserDTO udto = getUserByUserName(userName);
        if(udto != null && udto.getPassword().equals(password)){
            return udto.getStatus().equals("1");
        }else{
            return false;
        }
    }
    
    public UserDTO getUserByUserName(String userName){
        String sql = "SELECT * FROM tblUsers WHERE userID = ?";
        
        try(Connection conn = DbUtils.getConnection();){
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new UserDTO(
                        rs.getString("userID"),
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getString("roleID"),
                        rs.getString("status")
                );
            }
        }catch(Exception e){
        }
        return null;
    }
    
    public static void main(String[] args) {
        UserDAO udao = new UserDAO();
        System.out.println(udao.login("admin", "1"));
    }
}
