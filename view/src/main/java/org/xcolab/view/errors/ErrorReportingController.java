package org.xcolab.view.errors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.util.entity.email.EmailToAdminDispatcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorReportingController {

    private static final String MESSAGE_BODY_HEADER_FORMAT_STRING =
            "<p><strong>An exception occurred at:</strong><br>%s</p>"
                    + "<p><strong>HTTP status:</strong><br/>%d</p>"
                    + "<p><strong>Error:</strong><br/>%s</p>"
                    + "<p><strong>Message:</strong><br/>%s</p>"
                    + "<p><strong>User Agent:</strong><br/>%s</p>"
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

        if (!stackTrace.equals("${exception.stackTrace}")) {
            String userScreenName = "no user was logged in";
            final Member member = MemberAuthUtil.getMemberOrNull(request);
            if (member != null) {
                userScreenName = member.getScreenName();

                if (email.isEmpty()) {
                    email = member.getEmailAddress();
                }
            }

            if (StringUtils.isNotEmpty(url)) {
                String descriptionInHtmlFormat = description.replaceAll("(\r\n|\n)", "<br />");
                String body = buildErrorReportBody(url, status, error, message, userScreenName,
                        email, stackTrace, descriptionInHtmlFormat,
                        userAgent != null ? userAgent : "Unknown", referer);
                new EmailToAdminDispatcher(EMAIL_SUBJECT, body).sendMessage();
            }
        }
        response.sendRedirect("/");
    }

    private String buildErrorReportBody(String url, Integer status, String error, String message,
            String userScreenName, String email, String stackTrace, String descriptionInHtmlFormat,
            String userAgent, String referer) throws UnsupportedEncodingException {
        StringBuilder messageBuilder = new StringBuilder();

        if (StringUtils.isNotEmpty(url)) {
            messageBuilder.append(String
                    .format(MESSAGE_BODY_HEADER_FORMAT_STRING, url, status, error, message,
                            userAgent, referer, userScreenName, descriptionInHtmlFormat));

            if (StringUtils.isNotEmpty(email)) {
                messageBuilder.append(String.format(MESSAGE_BODY_EMAIL_FORMAT_STRING, email));
            }

            messageBuilder.append(URLDecoder.decode(stackTrace, "UTF-8"));
        }

        return messageBuilder.toString();
    }
}
