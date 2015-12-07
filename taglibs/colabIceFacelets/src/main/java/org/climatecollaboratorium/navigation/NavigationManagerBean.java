package org.climatecollaboratorium.navigation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.climatecollaboratorium.events.EventBus;

public class NavigationManagerBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String navigationToken;
    private EventBus eventBus;
    private boolean sendUnchanged;
    private int token = 0;
    
    public boolean isSendUnchanged() {
        return sendUnchanged;
    }
    public void setSendUnchanged(boolean sendUnchanged) {
        this.sendUnchanged = sendUnchanged;
    }
    public String getNavigationToken() {
        return navigationToken;
    }
    public void setNavigationToken(String navigationToken) {
        this.navigationToken = navigationToken;
    }
    public EventBus getEventBus() {
        return eventBus;
    }
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public String navigate() {
        token++;
        Map<String, Map<String, String>> tokenMap = decodeToken(navigationToken);
        NavigationEvent e = new NavigationEvent(tokenMap);
        eventBus.fireEvent(e);
        return e.getResultName();
    }
    
    private Map<String, Map<String, String>> decodeToken(String token) {
        Map<String, Map<String, String>> tokenMap = new HashMap<String, Map<String,String>>();
        String[] tokenParts = token.split(";");
        for (String tokenPart: tokenParts) {
            String[] keyValue = tokenPart.split("=");

            Map<String, String> tokenParametersMap = new HashMap<String, String>();
            tokenMap.put(keyValue[0], tokenParametersMap);
            if (keyValue.length < 2) {
                continue;
            }
            
            String[] tokenParameters = keyValue[1].split(",");
            for (String tokenParameter: tokenParameters) {
                String[] paramKeyValue = tokenParameter.split(":");
                if (paramKeyValue.length < 2) {
                    continue;
                }
                tokenParametersMap.put(paramKeyValue[0], paramKeyValue[1]);
            }
        }
        
        return tokenMap;
    }

    public int getToken() {
        return token;
    }
    
    public String ping() {
        System.out.println("ping");
        return null;
    }
}
