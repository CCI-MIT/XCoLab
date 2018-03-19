package org.xcolab.view.auth.handlers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.pages.redballoon.utils.BalloonService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

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

        if (!allowLogin && !PermissionsClient.canAdminAll(member)) {
            authenticationService.logout(request, response);
            getRedirectStrategy().sendRedirect(request, response, "/loginDisabled");
            return;
        }

        balloonService.associateBalloonTrackingWithUser(request, member);

        String refererHeader = request.getHeader(HttpHeaders.REFERER);
        MembersClient.createLoginLog(member.getId_(), request.getRemoteAddr(), refererHeader);

        if (redirectOnSuccess) {
            final String redirect = request.getParameter("redirect");
            if (StringUtils.isNotBlank(redirect)) {
                getRedirectStrategy().sendRedirect(request, response, redirect);
            } else if (StringUtils.isNotBlank(refererHeader) && !refererHeader.endsWith("/login")) {
                getRedirectStrategy().sendRedirect(request, response, refererHeader);
            } else {
                try {
                    super.onAuthenticationSuccess(request, response, authentication);
                } catch (ServletException e) {
                    // Not reachable - no ServletException is thrown by the implementations
                    throw new InternalException(e);
                }
            }
        }
    }
}
