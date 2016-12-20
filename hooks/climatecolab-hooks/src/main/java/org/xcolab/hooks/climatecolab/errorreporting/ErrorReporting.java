package org.xcolab.hooks.climatecolab.errorreporting;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.email.EmailToAdminDispatcher;
import org.xcolab.entity.utils.members.MemberAuthUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorReporting implements Filter {

    private static final String MESSAGE_BODY_HEADER_FORMAT_STRING =
            "<p><strong>An exception occurred at:</strong><br>%s</p>" +
                    "<p><strong>Message from user (%s):</strong><br/>" +
                    "%s</p>";

    private static final String MESSAGE_BODY_EMAIL_FORMAT_STRING =
            "<p><strong>Please notify user once we have a fix for the bug:</strong><br/>%s</p>";

    private static final String MESSAGE_BODY_FOOTER_FORMAT_STRING =
            "<p><strong>Exception should be added to the exception list:</strong><br>https://climatecolab.atlassian.net/wiki/display/COLAB/Exception+list</p>" +
                    "%s";

    private static final String EMAIL_SUBJECT = "Error Report from User";

    protected static final Logger _log = LoggerFactory.getLogger(ErrorReporting.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getParameter("url");
        String email = request.getParameter("email");
        String descriptionInHtmlFormat = request.getParameter("description").replaceAll("(\r\n|\n)", "<br />");
        String stackTrace = request.getParameter("stackTrace");

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
                String body = buildErrorReportBody(url, userScreenName, email, stackTrace, descriptionInHtmlFormat);
                new EmailToAdminDispatcher(EMAIL_SUBJECT, body).sendMessage();
            }
        }
        response.sendRedirect("/");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doPost((HttpServletRequest) request, (HttpServletResponse) response);
    }

    @Override
    public void destroy() {

    }

    private String buildErrorReportBody(String url, String userScreenName, String email,
            String stackTrace, String descriptionInHtmlFormat)
            throws UnsupportedEncodingException {
        StringBuilder messageBuilder = new StringBuilder();

        if (StringUtils.isNotEmpty(url)){
            messageBuilder.append(String.format(MESSAGE_BODY_HEADER_FORMAT_STRING, url, userScreenName, descriptionInHtmlFormat));

            if(StringUtils.isNotEmpty(email)){
                messageBuilder.append(String.format(MESSAGE_BODY_EMAIL_FORMAT_STRING, email));
            }

            messageBuilder.append(String.format(MESSAGE_BODY_FOOTER_FORMAT_STRING,
                    URLDecoder.decode(stackTrace, "UTF-8")));
        }

        return messageBuilder.toString();
    }
}
