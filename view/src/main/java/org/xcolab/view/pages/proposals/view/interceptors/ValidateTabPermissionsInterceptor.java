package org.xcolab.view.pages.proposals.view.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import org.xcolab.entity.utils.flash.ErrorMessage;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.pages.loginregister.singlesignon.SsoEndpoint;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalTabWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ValidateTabPermissionsInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private ProposalsContext proposalsContext;


    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

        if(request.getRequestURL().indexOf("/contests")>0) {
            if (modelAndView != null) {
                if (modelAndView.getModel().containsKey("currentTabWrapped")) {
                    ProposalTabWrapper currentTabWrapped =
                            (ProposalTabWrapper) modelAndView.getModelMap()
                                    .get("currentTabWrapped");
                    if (!currentTabWrapped.getCanAccess()) {
                        ErrorMessage.error(AuthenticationError.CREDENTIALS.getMessage())
                                .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());
                        //throw new ProposalsAuthorizationException(
                         //       "User isn't allowed to access requested tab");
                    }
                }
            }
        }
    }
}

