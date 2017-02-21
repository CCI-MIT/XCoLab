package org.xcolab.client.members.pojo;

import java.io.Serializable;

public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;
    private String ipAddress;
    private String redirectUrl;

    public LoginBean() {
    }

    public LoginBean(String password, String ipAddress, String redirectUrl) {
        this.password = password;
        this.ipAddress = ipAddress;
        this.redirectUrl = redirectUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
