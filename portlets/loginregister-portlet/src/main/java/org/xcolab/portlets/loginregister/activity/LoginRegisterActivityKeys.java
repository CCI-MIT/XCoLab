package org.xcolab.portlets.loginregister.activity;

import com.liferay.portal.model.User;

public enum LoginRegisterActivityKeys {
    USER_REGISTERED(1, "New account created", "%s joined ClimateColab community");
    
    private final int type;
    private final String title;
    private final String body;
    
    
    private LoginRegisterActivityKeys(int type, String title, String body) {
        this.type = type;
        this.title = title;
        this.body = body;
    }
    
    public static LoginRegisterActivityKeys getForType(int type) {
        for (LoginRegisterActivityKeys key: LoginRegisterActivityKeys.values()) {
            if (key.type == type) 
                return key;
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public String getTitle(User user) {
        return String.format(title, user.getScreenName());
    }

    public String getBody(User user) {
        return String.format(body, user.getScreenName());
    }
    
}
