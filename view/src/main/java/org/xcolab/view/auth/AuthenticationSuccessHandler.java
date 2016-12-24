package org.xcolab.view.auth;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public AuthenticationSuccessHandler(String defaultSuccessUrl) {
        setDefaultTargetUrl(defaultSuccessUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        String refererHeader = request.getHeader("referer");
        if (StringUtils.isNotBlank(refererHeader)) {
            getRedirectStrategy().sendRedirect(request, response, refererHeader);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
