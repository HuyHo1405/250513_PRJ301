
package model;

public class UserDTO {
    //fields
    private String userId;
    private String userName;
    private String password;
    private String status;

    //contructors
    public UserDTO(String userId, String userName, String password, String status) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    //getters
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    //setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
