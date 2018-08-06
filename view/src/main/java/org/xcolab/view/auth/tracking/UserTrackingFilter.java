package org.xcolab.view.auth.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.pojo.TrackedVisit;
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.view.auth.AuthenticationContext;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTrackingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(UserTrackingFilter.class);

    private static final String COOKIE_NAME = "userTrackingUuid";
    private static final long COOKIE_MAX_AGE = Duration.of(365, ChronoUnit.DAYS).getSeconds();
    //Minimum age at which the cookie should be refreshed
    private static final long COOKIE_MIN_AGE = Duration.of(180, ChronoUnit.DAYS).getSeconds();


    private final UserTrackingService userTrackingService;

    public UserTrackingFilter(UserTrackingService userTrackingService) {
        this.userTrackingService = userTrackingService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String originalUri = RequestUtil.getOriginalUri(request);

        if (Stream.of("/js/", "/vendor/", "/images/", "/css/", "/widgets/", "/image/",
                "/notificationMessage").anyMatch(originalUri::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        final Member realMemberOrNull = new AuthenticationContext().getRealMemberOrNull();
        Cookie userTrackingCookie = WebUtils.getCookie(request, COOKIE_NAME);
        String uuid = userTrackingCookie != null ? userTrackingCookie.getValue() : null;

        final Future<TrackedVisit> trackedVisitFuture = userTrackingService
                .trackVisitor(request, uuid, realMemberOrNull, originalUri,
                        request.getHeader(HttpHeaders.REFERER));

        //Run the rest of the request while the TrackedVisit is being created
        filterChain.doFilter(request, response);

        if (userTrackingCookie == null) {
            //If no tracking cookie is set yet, we need to wait for the TrackedVisit
            // that is being generated
            try {
                final TrackedVisit trackedVisit = trackedVisitFuture.get();
                userTrackingCookie =  new Cookie(COOKIE_NAME, trackedVisit.getVisitorUuid());
                userTrackingCookie.setHttpOnly(true);
                userTrackingCookie.setMaxAge((int) COOKIE_MAX_AGE);
                response.addCookie(userTrackingCookie);
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error while retrieving new TrackedVisit: ", e);
            }
        } else if (userTrackingCookie.getMaxAge() < COOKIE_MIN_AGE) {
            userTrackingCookie.setMaxAge((int) COOKIE_MAX_AGE);
            response.addCookie(userTrackingCookie);
        }
    }
}
