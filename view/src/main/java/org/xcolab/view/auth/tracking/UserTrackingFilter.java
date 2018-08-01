package org.xcolab.view.auth.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.view.auth.AuthenticationContext;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTrackingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(UserTrackingFilter.class);

    private final UserTrackingService userTrackingService;

    public UserTrackingFilter(UserTrackingService userTrackingService) {
        this.userTrackingService = userTrackingService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest && response instanceof HttpServletResponse)) {
            throw new UnsupportedOperationException("This filter can only handle HttpServletRequests");
        }
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String originalUri = RequestUtil.getOriginalUri(httpRequest);

        if (Stream.of("/js", "/vendor", "/images", "/css", "/widgets", "/image", "/notificationMessage")
                .anyMatch(originalUri::startsWith)) {
            chain.doFilter(request, response);
            return;
        }

        final Member realMemberOrNull = new AuthenticationContext().getRealMemberOrNull();
        userTrackingService.trackVisitor(httpRequest, httpResponse, realMemberOrNull,
                originalUri
                , httpRequest.getHeader(HttpHeaders.REFERER));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
