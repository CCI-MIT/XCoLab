package org.xcolab.view.auth.handlers;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.pages.loginregister.BalloonCookie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final AuthenticationContext authenticationContext;

    public AuthenticationSuccessHandler(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
        setDefaultTargetUrl("/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        final Member member = authenticationContext.getRealMemberOrNull();
        BalloonCookie bc = BalloonCookie.fromCookieArray(request.getCookies());
        if (StringUtils.isNotBlank(bc.getUuid())) {
            // cookie is present, get BalloonUserTracking if it exists and update association to the current user
            try {
                BalloonUserTracking but = BalloonsClient.getBalloonUserTracking(bc.getUuid());
                if (but == null) {
                    List<BalloonUserTracking> buts =
                            BalloonsClient
                                    .getBalloonUserTrackingByEmail(member.getEmailAddress());
                    if (!buts.isEmpty()) {
                        but = buts.get(0);
                    }
                }

                if (but != null && but.getUserId() != member.getUserId()) {
                    but.setUserId(member.getUserId());
                    BalloonsClient.updateBalloonUserTracking(but);

                }
            } catch (BalloonUserTrackingNotFound ignored) {
            }
        }

        String refererHeader = request.getHeader("referer");
        MembersClient.createLoginLog(member.getId_(), request.getRemoteAddr(), refererHeader);
        if (StringUtils.isNotBlank(refererHeader)) {
            getRedirectStrategy().sendRedirect(request, response, refererHeader);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
