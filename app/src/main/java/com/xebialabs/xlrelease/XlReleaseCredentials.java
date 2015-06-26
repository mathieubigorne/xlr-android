package com.xebialabs.xlrelease;

public class XlReleaseCredentials {
    public static final XlReleaseCredentials INSTANCE = new XlReleaseCredentials();

    private String login;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
