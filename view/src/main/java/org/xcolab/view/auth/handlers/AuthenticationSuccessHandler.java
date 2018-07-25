package org.xcolab.view.auth.handlers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication
        .SavedRequestAwareAuthenticationSuccessHandler;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.pages.redballoon.utils.BalloonService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.xcolab.view.config.spring.beans.SsoClientConfig.SsoFilter
        .SSO_SAVED_REFERER_SESSION_ATTRIBUTE;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    private static final String COLAB_URL = PlatformAttributeKey.COLAB_URL.get();

    private final AuthenticationService authenticationService;
    private final BalloonService balloonService;
    private final boolean allowLogin;

    public AuthenticationSuccessHandler(AuthenticationService authenticationService,
            BalloonService balloonService, boolean allowLogin) {
        this.authenticationService = authenticationService;
        this.balloonService = balloonService;
        this.allowLogin = allowLogin;
        setDefaultTargetUrl("/");

        //TODO COLAB-2362: Rethink circular dependency
        authenticationService.setAuthenticationSuccessHandler(this);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        onAuthenticationSuccess(request, response, authentication, true);
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication, boolean redirectOnSuccess) throws IOException {
        final Member member = authenticationService.getRealMemberOrNull();
        if (member == null) {
            // This can currently happen when there's an error during SSO
            // In that case, the CustomPrincipalExtractor returns null
            // An error message will already be saved in an AlertMessage
            log.warn("Member was null after authentication: {}", authentication.toString());
            getRedirectStrategy().sendRedirect(request, response, "/");
            return;
        }

        if (!allowLogin && !PermissionsClient.canAdminAll(member)) {
            authenticationService.logout(request, response);
            getRedirectStrategy().sendRedirect(request, response, "/loginDisabled");
            return;
        }

        balloonService.associateBalloonTrackingWithUser(request, member);

        final HttpSession session = request.getSession();
        String refererHeader;
        if (session.getAttribute(SSO_SAVED_REFERER_SESSION_ATTRIBUTE) != null) {
            refererHeader = (String) session.getAttribute(SSO_SAVED_REFERER_SESSION_ATTRIBUTE);
            session.removeAttribute(SSO_SAVED_REFERER_SESSION_ATTRIBUTE);
        } else {
            refererHeader = request.getHeader(HttpHeaders.REFERER);
        }
        MembersClient.createLoginLog(member.getId_(), request.getRemoteAddr(), refererHeader);

        if (redirectOnSuccess) {
            try {
                //Make URI relative to prevent injection of external redirect URIs
                final String redirect = LinkUtils.getRelativeUri(refererHeader);
                if (StringUtils.isNotBlank(redirect) && isLocalReferer(refererHeader)
                        && !isFromLoginPage(redirect)) {
                    getRedirectStrategy().sendRedirect(request, response, redirect);
                } else {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            } catch (ServletException e) {
                // Not reachable - no ServletException is thrown by the implementations
                throw new InternalException(e);
            }
        }
    }

    private boolean isLocalReferer(String refererHeader) {
        return refererHeader.startsWith(COLAB_URL);
    }

    private boolean isFromLoginPage(String refererHeader) {
        return refererHeader.endsWith("/login");
    }
}
