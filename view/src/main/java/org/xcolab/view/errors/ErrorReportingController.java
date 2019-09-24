package org.xcolab.view.errors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.util.entity.email.EmailToAdminDispatcher;

import java.io.IOException;
import java.net.URLDecoder;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ErrorReportingController {

    private static final String MESSAGE_BODY_HEADER_FORMAT_STRING =
            "<p><strong>An exception occurred at:</strong><br>%s</p>"
                    + "<p><strong>Error:</strong><br/>%d - %s<br/>Message: %s</p>"
                    + "<p><strong>User Agent:</strong><br/>%s</p>"
                    + "<p><strong>Tracking UUID:</strong> %s</p>"
                    + "<p><strong>Cookies:</strong><br/>%s</p>"
                    + "<p><strong>Session info:</strong><br/>%s</p>"
                    + "<p><strong>Referer:</strong><br/>%s</p>"
                    + "<p><strong>Message from user (%s):</strong><br/>" + "%s</p>";

    private static final String MESSAGE_BODY_EMAIL_FORMAT_STRING =
            "<p><strong>Please notify user once we have a fix for the bug:</strong><br/>%s</p>";

    private static final String EMAIL_SUBJECT = "Error Report from User";

    @PostMapping("/reportError")
    public void reportError(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String url, @RequestParam String email, @RequestParam String description,
            @RequestParam Integer status, @RequestParam String error, @RequestParam String message,
            @RequestParam String stackTrace, @RequestParam String referer) throws IOException {

        final String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        final Cookie[] cookies = request.getCookies();
        final String cookieNames = Arrays.stream(cookies)
            .map(Cookie::getName)
            .collect(Collectors.joining(", "));

        final Cookie trackingCookie = WebUtils.getCookie(request, "userTrackingUuid");
        final String trackingCookieValue = trackingCookie != null ? trackingCookie.getValue()
                : "no cookie found";

        final HttpSession session = request.getSession(false);
        String sessionInfo;
        if (session == null) {
            sessionInfo = "No session found";
        } else {
            final String sessionAttributeNames = Collections.list(session.getAttributeNames())
                    .stream().collect(Collectors.joining(", "));
            sessionInfo = String.format("ID: %s,<br/>"
                            + "Created: %s,<br/>"
                            + "Last accessed: %s,<br/>"
                            + "Attribute names: %s",
                    session.getId().substring(0, 7) + "...",
                    Instant.ofEpochMilli(session.getCreationTime()).toString(),
                    Instant.ofEpochMilli(session.getLastAccessedTime()).toString(),
                    sessionAttributeNames);
        }

        if (!stackTrace.equals("${exception.stackTrace}")) {
            String userScreenName = "no user was logged in";
            final UserWrapper member = MemberAuthUtil.getMemberOrNull();
            if (member != null) {
                userScreenName = member.getScreenName();

                if (email.isEmpty()) {
                    email = member.getEmailAddress();
                }
            }

            if (StringUtils.isNotEmpty(url)) {
                final String userAgentString = userAgent != null ? userAgent : "Unknown";
                StringBuilder messageBodyBuilder = new StringBuilder();

                String descriptionInHtmlFormat = description.replaceAll("(\r\n|\n)", "<br />");
                messageBodyBuilder.append(String.format(MESSAGE_BODY_HEADER_FORMAT_STRING, url,
                        status, error, message, userAgentString, trackingCookieValue, cookieNames,
                        sessionInfo, referer, userScreenName, descriptionInHtmlFormat));

                if (StringUtils.isNotEmpty(email)) {
                    messageBodyBuilder.append(String.format(MESSAGE_BODY_EMAIL_FORMAT_STRING, email));
                }

                messageBodyBuilder.append(URLDecoder.decode(stackTrace, "UTF-8"));

                new EmailToAdminDispatcher(EMAIL_SUBJECT, messageBodyBuilder.toString()).sendMessage();
            }
        }
        response.sendRedirect("/");
    }
}
