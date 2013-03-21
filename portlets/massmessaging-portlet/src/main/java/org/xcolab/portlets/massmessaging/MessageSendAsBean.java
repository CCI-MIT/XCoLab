package org.xcolab.portlets.massmessaging;

public class MessageSendAsBean {
    private String name;
    private String host;
    private int port;
    private String password;
    private String fullName;
    private boolean useAuth;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return name + "@" + host;
    }
    public boolean isUseAuth() {
        return useAuth;
    }
    public void setUseAuth(boolean useAuth) {
        this.useAuth = useAuth;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
    

}
