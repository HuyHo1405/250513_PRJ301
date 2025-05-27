
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.DbUtils;

public class UserDAO {

    public UserDAO() {
        
    }
    
    public boolean login(String userName, String password){
        String sql = "SELECT * FROM tblUsers WHERE "
                + "userID = '"+ userName + "' AND password = '" + password + "'";
            
        try(Connection conn = DbUtils.getConnection();){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                UserDTO user = new UserDTO(
                        rs.getString("userID"),
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getString("roleID"),
                        rs.getString("status")
                );
                return user.getStatus().equals("1");
            }
        }catch(Exception e){
        }
        return false;  
    }
    
    public UserDTO getUserByUserName(String userName){
        String sql = "SELECT * FROM tblUsers WHERE "
                + "userID = '"+ userName + "'";
        
        try(Connection conn = DbUtils.getConnection();){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
        UserDAO a = new UserDAO();
        System.out.println(a.login("admin", "1"));
    }
    
}
