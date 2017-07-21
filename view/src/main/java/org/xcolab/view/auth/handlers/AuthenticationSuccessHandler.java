package org.xcolab.view.auth.handlers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.pages.redballon.utils.BalloonCookie;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    private final AuthenticationService authenticationService;
    private final boolean allowLogin;

    public AuthenticationSuccessHandler(AuthenticationService authenticationService, boolean allowLogin) {
        this.authenticationService = authenticationService;
        this.allowLogin = allowLogin;
        setDefaultTargetUrl("/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        final Member member = authenticationService.getRealMemberOrNull();

        if (!allowLogin && !PermissionsClient.canAdminAll(member)) {
            authenticationService.logout(request, response);
            getRedirectStrategy().sendRedirect(request, response, "/loginDisabled");
            return;
        }

        Optional<BalloonCookie> balloonCookieOpt = BalloonCookie.from(request.getCookies());
        if (balloonCookieOpt.isPresent()) {
            final BalloonCookie balloonCookie = balloonCookieOpt.get();
            try {
                BalloonUserTracking but = BalloonsClient
                        .getBalloonUserTracking(balloonCookie.getUuid());
                if (but != null) {
                    but.updateUserIdIfEmpty(member.getId_());
                }
            } catch (BalloonUserTrackingNotFoundException e) {
                log.error("Invalid UUID: {}", balloonCookie);
            }
        }

        String refererHeader = request.getHeader(HttpHeaders.REFERER);
        MembersClient.createLoginLog(member.getId_(), request.getRemoteAddr(), refererHeader);

        final String redirect = request.getParameter("redirect");
        if (StringUtils.isNotBlank(redirect)) {
            getRedirectStrategy().sendRedirect(request, response, redirect);
        } else if (StringUtils.isNotBlank(refererHeader) && !refererHeader.endsWith("/login")) {
            getRedirectStrategy().sendRedirect(request, response, refererHeader);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
