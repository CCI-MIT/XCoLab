package org.xcolab.view.auth.endpoints;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.view.config.spring.resolvers.RealMember;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserTrackingController {

    @PostMapping("/trackVisitor")
    protected ResponseJson trackVisitor(HttpServletRequest request, HttpServletResponse response,
            @RealMember Member loggedInMember) {

        String ip = getClientIpAddress(request);
        String url = request.getParameter("url");
        String referer = request.getParameter(HttpHeaders.REFERER);
        String browser = request.getHeader(HttpHeaders.USER_AGENT);

        //get headers
        String headers = getHeadersAsString(request);

        // If UUID is not sent as parameter, try to retrieve existing token if user is logged in.
        String uuid = request.getParameter("uuid");
        String isTrackedVisitor = request.getParameter("isTrackedVisitor");
        if (StringUtils.isBlank(uuid)) {
            if (loggedInMember != null) {
                uuid = TrackingClient.getTrackedVisitorOrCreate(loggedInMember.getId_()).getUuid_();
                isTrackedVisitor = "true";
            } else {
                uuid = TrackingClient.generateUUID();
            }
        }

        TrackingClient.addTrackedVisit(uuid, url, ip, browser, referer, headers);

        return new ResponseJson(uuid, Boolean.valueOf(isTrackedVisitor));
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
            Enumeration<String> headers = request.getHeaders(headerName);

            while (headers.hasMoreElements()) {
                headerStringBuilder.append(headers.nextElement()).append("\n");
            }
        }
        return headerStringBuilder.toString();
    }

    private static class ResponseJson {

        private final String uuid;
        private final boolean isTrackedVisitor;

        private ResponseJson(String uuid, boolean isTrackedVisitor) {
            this.uuid = uuid;
            this.isTrackedVisitor = isTrackedVisitor;
        }

        public String getUuid() {
            return uuid;
        }

        public boolean isTrackedVisitor() {
            return isTrackedVisitor;
        }
    }

}
