package org.xcolab.view.auth.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.view.auth.AuthenticationContext;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTrackingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(UserTrackingFilter.class);

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
        userTrackingService.trackVisitor(request, response, realMemberOrNull, originalUri,
                request.getHeader(HttpHeaders.REFERER));
        filterChain.doFilter(request, response);
    }
}
