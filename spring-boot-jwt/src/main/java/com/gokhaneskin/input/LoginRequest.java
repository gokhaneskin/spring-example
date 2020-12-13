package com.gokhaneskin.input;

public class LoginRequest {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public LoginRequest() {
    }

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
