package com.xebialabs.xlrelease.core.api;

public class XLReleaseCredentials {
    public static final XLReleaseCredentials INSTANCE = new XLReleaseCredentials();

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
