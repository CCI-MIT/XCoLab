package org.xcolab.view.theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.config.spring.beans.SsoClientConfig.SsoServices;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class ThemeVariableFilter extends GenericFilterBean {

    public static final String REQUEST_ATTRIBUTE_NAME = "xcolab__ThemeContext";

    private final AuthenticationService authenticationService;
    private final SsoServices ssoServices;

    @Autowired
    public ThemeVariableFilter(AuthenticationService authenticationService,
            SsoServices ssoServices) {
        Assert.notNull(authenticationService, "AuthenticationContext is required");
        Assert.notNull(authenticationService, "SsoServices bean is required");
        this.authenticationService = authenticationService;
        this.ssoServices = ssoServices;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("ThemeVariableFilter just supports HTTP requests");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUri = httpRequest.getRequestURI();

        if(!(requestUri.equals("/notificationMessage") && !requestUri.contains("/image/"))&&(!requestUri.contains("/vendor/"))) {

            ThemeContext themeContext = new ThemeContext(authenticationService, ssoServices,
                    httpRequest);
            httpRequest.setAttribute(REQUEST_ATTRIBUTE_NAME, themeContext);
        }

        chain.doFilter(request, response);
    }
}
