package org.xcolab.portlets.proposals.view.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ValidateTabPermissionsInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private ProposalsContext proposalsContext;

    @Override
    public void postHandleRender(RenderRequest request, RenderResponse response, Object handler,
            org.springframework.web.portlet.ModelAndView modelAndView) throws Exception {
        super.postHandleRender(request, response, handler, modelAndView);
        
        if (modelAndView != null) {
            if (modelAndView.getModel().containsKey("currentTabWrapped")) {
                ProposalTabWrapper currentTabWrapped = (ProposalTabWrapper) modelAndView.getModelMap().get("currentTabWrapped");
                if (! currentTabWrapped.getCanAccess() ) {
                    throw new ProposalsAuthorizationException("User isn't allowed to access requested tab");
                }
            }
        }
    }
}

