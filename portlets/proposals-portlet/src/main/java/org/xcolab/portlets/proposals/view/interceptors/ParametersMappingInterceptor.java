package org.xcolab.portlets.proposals.view.interceptors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * If request is an {@link javax.portlet.ActionRequest ActionRequest},
     * get handler mapping parameters and add them to the ActionResponse.
     */
    @Override
    public boolean preHandleAction(ActionRequest request, ActionResponse response, Object handler) {
        for (Map.Entry<String, String> parameterMapping: parameters.entrySet()) {
            String mappingParameterValue = request.getParameter(parameterMapping.getKey());

            if (mappingParameterValue != null) {
                response.setRenderParameter(parameterMapping.getValue(), mappingParameterValue);
            }
        }
        
        return true;
    }
}
