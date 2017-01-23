package org.xcolab.view.pages.proposals.view.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.proposals.wrappers.ProposalTabWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ValidateTabPermissionsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws IOException {

        if (request.getRequestURL().indexOf("/contests") > 0) {
            if (modelAndView != null) {
                if (modelAndView.getModel().containsKey("currentTabWrapped")) {
                    ProposalTabWrapper currentTabWrapped =
                            (ProposalTabWrapper) modelAndView.getModelMap()
                                    .get("currentTabWrapped");
                    if (!currentTabWrapped.getCanAccess()) {
                        modelAndView.setViewName(ErrorText.ACCESS_DENIED.flashAndReturnView(request));
                    }
                }
            }
        }
    }
}

