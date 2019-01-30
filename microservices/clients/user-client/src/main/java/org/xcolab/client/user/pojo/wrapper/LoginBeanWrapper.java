package org.xcolab.client.user.pojo.wrapper;

import java.io.Serializable;

public class LoginBeanWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;
    private String ipAddress;
    private String redirectUrl;

    public LoginBeanWrapper() {
    }

    public LoginBeanWrapper(String password, String ipAddress, String redirectUrl) {
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
