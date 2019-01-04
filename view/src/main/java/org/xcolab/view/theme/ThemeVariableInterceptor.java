package org.xcolab.view.theme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.config.spring.beans.SsoClientConfig.SsoServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ThemeVariableInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationService authenticationService;
    private final SsoServices ssoServices;

    @Autowired
    public ThemeVariableInterceptor(AuthenticationService authenticationService,
            SsoServices ssoServices) {
        Assert.notNull(authenticationService, "AuthenticationContext is required");
        Assert.notNull(authenticationService, "SsoServices bean is required");
        this.authenticationService = authenticationService;
        this.ssoServices = ssoServices;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {
        if (modelAndView != null && !isRedirectView(modelAndView)) {
            ThemeContext themeContext = new ThemeContext(authenticationService, ssoServices,
                    request);
            modelAndView.addObject("_themeContext", themeContext);
        }
    }

    private boolean isRedirectView(ModelAndView modelAndView) {
        return (modelAndView.getView() != null && modelAndView.getView() instanceof RedirectView)
                || modelAndView.getViewName().startsWith("redirect:");
    }

}
