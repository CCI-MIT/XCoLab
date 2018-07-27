package org.xcolab.view.config.spring.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;

import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.auth.AuthenticationContext;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

    private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    private final AuthenticationContext authenticationContext = new AuthenticationContext();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (accessDeniedException instanceof InvalidCsrfTokenException) {
            if (!response.isCommitted()) {
                final boolean isLoggingIn = request.getRequestURI().startsWith("/login");
                if (!authenticationContext.isLoggedIn() && !isLoggingIn) {
                    log.warn("Invalid CSRF token used by anonymous user: {}",
                            accessDeniedException.getMessage());

                    AlertMessage.warning("It looks like you were logged out in a different tab. "
                            + "Please try again.").flash(request);
                } else {
                    if (isLoggingIn) {
                        log.warn("Invalid CSRF token used while logging in: {}",
                                accessDeniedException.getMessage());
                        AlertMessage.warning("You're already logged in!").flash(request);
                    } else {
                        log.warn("Invalid CSRF token used by user: {}",
                                accessDeniedException.getMessage());
                        AlertMessage.warning("Error while processing your request. " +
                                "Please try again.")
                                .flash(request);
                    }
                }

                final String refererHeader = request.getHeader(HttpHeaders.REFERER);
                response.sendRedirect(LinkUtils.getLocalUrl(refererHeader, "/"));
                return;
            }
        }
        super.handle(request, response, accessDeniedException);
    }
}
