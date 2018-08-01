package org.xcolab.view.auth.tracking;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.TrackedVisit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserTrackingService {

    private static final String COOKIE_NAME = "userTrackingUuid";
    private static final long COOKIE_MAX_AGE = Duration.of(365, ChronoUnit.DAYS).getSeconds();
    //Minimum age at which the cookie should be refreshed
    private static final long COOKIE_MIN_AGE = Duration.of(180, ChronoUnit.DAYS).getSeconds();

    private static final String[] IGNORED_HEADERS = {HttpHeaders.USER_AGENT, HttpHeaders.REFERER,
            HttpHeaders.HOST, HttpHeaders.ORIGIN, HttpHeaders.CONNECTION,
            HttpHeaders.CONTENT_LENGTH, "X-CSRF-TOKEN"};

    private static final String[] IGNORED_COOKIES = {"_ga", "_gid", "SESSION"};

    public void trackVisitor(HttpServletRequest request, HttpServletResponse response,
            Member loggedInMember, String url, String referer) {

        Cookie userTrackingCookie = WebUtils.getCookie(request, COOKIE_NAME);
        String uuid = userTrackingCookie != null ? userTrackingCookie.getValue() : null;

        String browser = request.getHeader(HttpHeaders.USER_AGENT);
        String ip = getClientIpAddress(request);
        String headers = getHeadersAsString(request);

        final Long userId = loggedInMember != null ? loggedInMember.getUserId() : null;
        final TrackedVisit trackedVisit =
                TrackingClient.addTrackedVisit(uuid, url, ip, browser, referer, headers, userId);

        if (userTrackingCookie == null) {
            userTrackingCookie =  new Cookie("userTrackingUuid", trackedVisit.getUuid_());
            userTrackingCookie.setHttpOnly(true);
            userTrackingCookie.setMaxAge((int) COOKIE_MAX_AGE);
            response.addCookie(userTrackingCookie);
        } else if (userTrackingCookie.getMaxAge() < COOKIE_MIN_AGE) {
            userTrackingCookie.setMaxAge((int) COOKIE_MAX_AGE);
            response.addCookie(userTrackingCookie);
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headerStringBuilder = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (StringUtils.equalsAnyIgnoreCase(headerName, IGNORED_HEADERS)) {
                continue;
            }

            Enumeration<String> headers = request.getHeaders(headerName);
            while (headers.hasMoreElements()) {
                String headerValue = headers.nextElement();
                if (headerName.equalsIgnoreCase(HttpHeaders.COOKIE)) {
                    headerValue = removeCookiesByName(headerValue, IGNORED_COOKIES);
                }
                headerStringBuilder.append(headerName).append(": ");
                headerStringBuilder.append(headerValue).append("\n");
            }
        }
        return headerStringBuilder.toString();
    }

    private String removeCookiesByName(String cookieHeader, String... names) {
        return Arrays.stream(cookieHeader.split("\\s*;\\s*"))
                .filter(c -> !matchesAnyCookieName(c, names))
                .collect(Collectors.joining("; "));
    }

    private boolean matchesAnyCookieName(String cookieString, String... cookieNames) {
        boolean isEmpty = StringUtils.isEmpty(cookieString) || ArrayUtils.isEmpty(cookieNames);

        return isEmpty || Arrays.stream(cookieNames)
                .anyMatch(name -> matchesCookieName(cookieString, name));
    }

    private boolean matchesCookieName(String cookieString, String cookieName) {
        return cookieString.startsWith(cookieName + "=");
    }

    private static class ResponseJson {

        private final String uuid;

        private ResponseJson(String uuid) {
            this.uuid = uuid;
        }

        public String getUuid() {
            return uuid;
        }
    }

}
