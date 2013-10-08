package org.xcolab.portlets.proposals.view.interceptors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

/**
 * Take a look at:
 http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/portlet.html#portlet-handlermapping-parameterinterceptor

 Parameters passed to ACTION_PHASE aren't by default transferred to RENDER_PHASE, so this interceptor is responsible for that. I haven't 
 used Intercepotr provided by string, because it supports passing only one parameter and we can have many of them (take a look at 
 proposalsportlet-portlet.xml out there you'll find bean definitions for interceptors together with necessary configuration.
 */
public class ParametersMappingInterceptor extends HandlerInterceptorAdapter {
    
    private Map<String, String> parameters = new HashMap<String, String>();
    private Set<String> ignoreOnError = new HashSet<String>();
    private Set<String> ignoreOnSuccess = new HashSet<String>();
    private boolean mapAll;
    
    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    public Set<String> getIgnoreOnError() {
        return ignoreOnError;
    }

    public void setIgnoreOnError(Set<String> ignoreOnError) {
        this.ignoreOnError = ignoreOnError;
    }

    public Set<String> getIgnoreOnSuccess() {
        return ignoreOnSuccess;
    }

    public void setIgnoreOnSuccess(Set<String> ignoreOnSuccess) {
        this.ignoreOnSuccess = ignoreOnSuccess;
    }

    /**
     * If request is an {@link javax.portlet.ActionRequest ActionRequest},
     * get handler mapping parameters and add them to the ActionResponse.
     */
    
    @Override
    public void afterActionCompletion(ActionRequest request, ActionResponse response, Object handler, Exception ex)
            throws Exception {
        

        if (request.getAttribute("ACTION_REDIRECTING") != null) return;
        
        boolean actionError = request.getAttribute("ACTION_ERROR") != null;
        
        for (Map.Entry<String, String[]> reqParameter: request.getParameterMap().entrySet()) {
            
            if (actionError && ignoreOnError.contains(reqParameter.getKey())) continue;
            else if (!actionError && ignoreOnSuccess.contains(reqParameter.getKey())) continue;
            
            if (!mapAll && !parameters.containsKey(reqParameter.getKey())) continue;
            
            String mappedKey = reqParameter.getKey();
            if (parameters.containsKey(mappedKey)) {
                // we have a mapping for that parameter
                mappedKey = parameters.get(mappedKey);
            }
            
            response.setRenderParameter(mappedKey, reqParameter.getValue());
        }
    }

    public boolean isMapAll() {
        return mapAll;
    }

    public void setMapAll(boolean mapAll) {
        this.mapAll = mapAll;
    }
}
