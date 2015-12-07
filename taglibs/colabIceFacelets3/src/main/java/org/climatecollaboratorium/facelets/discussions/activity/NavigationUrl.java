package org.climatecollaboratorium.facelets.discussions.activity;

import java.util.HashMap;
import java.util.Map;

public class NavigationUrl {
    private String url;
    private Map<String, Map<String, String>> parameters;
    
    public NavigationUrl(String url, Map<String, Map<String, String>> parameters) {
        
        this.url = url;
        this.parameters = parameters;
    }
    
    
    public NavigationUrl(String url) {
        
        if (url.indexOf("#") >= 0) {
            String[] urlAndToken = url.split("#");
            this.url = urlAndToken[0];
            this.parameters = decodeToken(urlAndToken[1]);
        }
        else {
            this.parameters = new HashMap<String, Map<String,String>>();
            this.url = url;
        }
    }

    public String toString() {
        StringBuilder urlSb = new StringBuilder();
        

        urlSb.append(url);
        urlSb.append("#");
        
        boolean addSemicolon = false;
        for (String parameter: parameters.keySet()) {
            boolean addComma = false;
            if (addSemicolon) {
                urlSb.append(";");
            }
            addSemicolon = true;
            urlSb.append(parameter);
            urlSb.append("=");
            for (String key: parameters.get(parameter).keySet()) {
                if (addComma) {
                    urlSb.append(",");
                }
                addComma = true;
                
                urlSb.append(key);
                urlSb.append(":");
                urlSb.append(parameters.get(parameter).get(key));
            }
        }
        return urlSb.toString();
    }
    
    public NavigationUrl getUrlWithParameters(String parameter, String...keyValue) {
        if (keyValue.length == 0 || keyValue.length % 2 != 0) {
            throw new IllegalArgumentException("Passed keys and values list needs to be of even size");
        }
        NavigationUrl newUrl = new NavigationUrl(url, new HashMap<String, Map<String,String>>(parameters));
        Map<String, String> parameterKeyValues = newUrl.parameters.get(parameter);
        if (parameterKeyValues == null) {
            parameterKeyValues = new HashMap<String, String>();
            newUrl.parameters.put(parameter, parameterKeyValues);
        }
        for (int i=0; i < keyValue.length; i+=2) {
            parameterKeyValues.put(keyValue[i], keyValue[i+1]);
        }
        
        return newUrl;
    }
    
    public static NavigationUrl decode(String baseUrl) {
        String[] urlParts = baseUrl.split("#");
        return new NavigationUrl(urlParts[0], decodeToken(urlParts[1]));
    }
    
    
    private static Map<String, Map<String, String>> decodeToken(String token) {
        if (token == null || token.trim().length() == 0) {
            return new HashMap<String, Map<String,String>>();
        }
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
