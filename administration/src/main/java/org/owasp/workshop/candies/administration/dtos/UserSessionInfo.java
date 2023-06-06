package org.owasp.workshop.candies.administration.dtos;

public class UserSessionInfo {

    private Long studentID;
    private Long userID;
    private String username;
    private String sessionToken;
    private String userRole;

    public UserSessionInfo(){

    }

    public UserSessionInfo(Long studentID, Long userID, String username, String userRole) {
        this.studentID = studentID;
        this.userID = userID;
        this.username = username;
        this.userRole = userRole;
    }

    public UserSessionInfo(String username, String userRole, String sessionToken) {
        this.username = username;
        this.sessionToken = sessionToken;
        this.userRole = userRole;
    }

    public UserSessionInfo(Long userID, String username, String userRole, String sessionToken) {
        this.userID = userID;
        this.username = username;
        this.sessionToken = sessionToken;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
