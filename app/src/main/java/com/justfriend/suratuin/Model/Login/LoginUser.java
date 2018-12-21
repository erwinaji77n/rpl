package com.justfriend.suratuin.Model.Login;

public class LoginUser {

    private String nim;
    private String password;

    public LoginUser(String nim, String password) {
        this.nim = nim;
        this.password = password;
    }

    public String getNim() {
        return nim;
    }

    public void setName(String name) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
