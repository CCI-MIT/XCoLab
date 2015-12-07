package org.climatecollaboratorium.facelets.discussions.activity;

import java.util.HashMap;
import java.util.Map;

public class UrlCreatorHelper {
    
    public static Map<String, Map<String, String>> decodeToken(String token) {
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

}
