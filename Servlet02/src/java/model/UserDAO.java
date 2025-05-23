
package model;

import java.util.ArrayList;

public class UserDAO {
    ArrayList<UserDTO> list;

    public UserDAO() {
        list = new ArrayList<>();
        list.add(new UserDTO("1", "admin", "1", "administrator"));
        list.add(new UserDTO("2", "user", "1", "user"));
    }
    
    public boolean login(String userName, String password){
        for (UserDTO userDTO : list) {
            if(userDTO.getUserName().equals(userName) && userDTO.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public UserDTO getUserByUserName(String userName){
        for (UserDTO userDTO : list) {
            if(userDTO.getUserName().equals(userName)){
                return userDTO;
            }
        }
        return null;
    }
    
}
