package org.xcolab.view.auth.handlers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.view.auth.login.AuthenticationError;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException {
        String refererHeader = request.getHeader(HttpHeaders.REFERER);
        String redirectBaseUrl;
        if (StringUtils.isNotBlank(refererHeader)) {
            redirectBaseUrl = refererHeader;
        } else {
            redirectBaseUrl = "/login";
        }

        UriComponentsBuilder redirectUrlBuilder = UriComponentsBuilder.fromUriString(redirectBaseUrl);
        redirectUrlBuilder.replaceQueryParam("isSigningIn", true);
        final AuthenticationError authenticationError =
                AuthenticationError.fromException(exception);
        redirectUrlBuilder.replaceQueryParam("signinRegError", authenticationError);
        getRedirectStrategy().sendRedirect(request, response, redirectUrlBuilder.toUriString());
    }
}
