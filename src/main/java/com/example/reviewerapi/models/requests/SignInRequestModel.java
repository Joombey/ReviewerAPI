package com.example.reviewerapi.models.requests;

public class SignInRequestModel {

    private String login;
    private String password;

    public SignInRequestModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInRequestModel userId = (SignInRequestModel) o;
        return login.equals(userId.login) && password.equals(userId.password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
