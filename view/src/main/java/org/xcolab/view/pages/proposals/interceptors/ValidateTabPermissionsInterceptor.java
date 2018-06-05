package org.xcolab.view.pages.proposals.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.proposals.tabs.ProposalTabWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ValidateTabPermissionsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {

        if (modelAndView != null) {
            if (modelAndView.getModel().containsKey("currentTabWrapped")) {
                final Object tabObject = modelAndView.getModelMap()
                        .get("currentTabWrapped");
                if (tabObject instanceof ProposalTabWrapper) {
                    ProposalTabWrapper currentTabWrapped = (ProposalTabWrapper) tabObject;
                    if (!currentTabWrapped.getCanAccess()) {
                        modelAndView
                                .setViewName(ErrorText.ACCESS_DENIED.flashAndReturnView(request));
                    }
                }
            }
        }
    }
}

