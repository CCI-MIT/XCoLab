package org.xcolab.view.auth.endpoints;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.TrackedVisit;
import org.xcolab.view.config.spring.resolvers.RealMember;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserTrackingController {

    private static final String[] IGNORED_HEADERS = {HttpHeaders.USER_AGENT,
            HttpHeaders.CONTENT_LENGTH, HttpHeaders.HOST, HttpHeaders.REFERER};
    @PostMapping("/trackVisitor")
    protected ResponseJson trackVisitor(HttpServletRequest request, HttpServletResponse response,
            @RealMember Member loggedInMember, @RequestParam String uuid, @RequestParam String url,
            @RequestParam(required = false) String referer) {

        String browser = request.getHeader(HttpHeaders.USER_AGENT);
        String ip = getClientIpAddress(request);
        String headers = getHeadersAsString(request);

        final Long userId = loggedInMember != null ? loggedInMember.getUserId() : null;
        final TrackedVisit trackedVisit =
                TrackingClient.addTrackedVisit(uuid, url, ip, browser, referer, headers, userId);

        return new ResponseJson(trackedVisit.getUuid_());
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
                headerStringBuilder.append(headerName).append(": ");
                headerStringBuilder.append(headers.nextElement()).append("\n");
            }
        }
        return headerStringBuilder.toString();
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
