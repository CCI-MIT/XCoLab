package org.xcolab.portlets.proposals.view.interceptors;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

public class ParametersMappingInterceptor extends HandlerInterceptorAdapter {
    
    private List<String> parameters = new ArrayList<String>();
    
    

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    /**
     * If request is an {@link javax.portlet.ActionRequest ActionRequest},
     * get handler mapping parameters and add them to the ActionResponse.
     */
    @Override
    public boolean preHandleAction(ActionRequest request, ActionResponse response, Object handler) {
        for (String parameterName: parameters) {
            String mappingParameter = request.getParameter(parameterName);

            if (mappingParameter != null) {
                response.setRenderParameter(parameterName, mappingParameter);
            }
        }
        
        return true;
    }
}
