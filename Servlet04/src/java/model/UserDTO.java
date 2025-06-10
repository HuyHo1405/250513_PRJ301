
package model;

public class UserDTO {
    //fields
    private String userId;
    private String fullName;
    private String password;
    private String role;
    private String status;

    //constructor
    public UserDTO(String userId, String fullName, String password, String role, String status) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    //getters
    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    //setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
