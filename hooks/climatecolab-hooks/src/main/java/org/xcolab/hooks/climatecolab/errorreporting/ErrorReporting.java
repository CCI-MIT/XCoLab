package org.xcolab.hooks.climatecolab.errorreporting;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngine;
import org.parboiled.common.StringUtils;
import javax.mail.internet.InternetAddress;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;


/**
 * Created by patrickhiesel on 08/11/14.
 */
public class ErrorReporting implements Filter {

    protected Log _log;

    public ErrorReporting(){
        _log = LogFactoryUtil.getLog(this.getClass());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getParameter("url");
        String email = request.getParameter("email");
        String descriptionInHtmlFormat = request.getParameter("description").replaceAll("(\r\n|\n)", "<br />");
        String stackTrace = request.getParameter("stackTrace");
        String userScreenName = "no user was logged in";
        try {
            userScreenName = PortalUtil.getUser(request).getScreenName();
            if(email.isEmpty()) {
                email = PortalUtil.getUser(request).getEmailAddress();
            }
        }
        catch(Exception e){
            // Couldn't find user or no user is logged in
        }
        StringBuilder messageBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(url)){
            messageBuilder.append("<p><strong>An exception occurred at:</strong><br> " + url + "</p>");
            messageBuilder.append("<p><strong>Message from user (" + userScreenName + "):</strong><br/> ");
            messageBuilder.append(descriptionInHtmlFormat + "</p>");
            if(StringUtils.isNotEmpty(email)){
                messageBuilder.append("<p><strong>Please notify user once we have a fix for the bug:</strong><br/>");
                messageBuilder.append(email + "</p>");
            }
            messageBuilder.append("<p><strong>Exception should be added to the exception list:</strong><br>https://climatecolab.atlassian.net/wiki/display/COLAB/Exception+list</p>");
            messageBuilder.append(URLDecoder.decode(stackTrace, "UTF-8"));
            sendMessage("Error Report from User", messageBuilder.toString());
        }
        response.sendRedirect("/");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doPost((HttpServletRequest) request, (HttpServletResponse) response);
    }

    protected void sendMessage(String subject, String body) {
        try {
            InternetAddress fromEmail = new InternetAddress("no-reply@climatecolab.org", "MIT Climate CoLab");

            String emailRecipients = "pdeboer@mit.edu,knauert@mit.edu,mail@klemensmang.com,lamche@mit.edu";
            String[] recipients = emailRecipients.split(",");

            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }

            MailEngine.send(fromEmail, addressTo, subject, body, true);
        } catch (Exception e) {
            _log.error("Could not send feedback message", e);
        }
    }

    public void destroy() {

    }
}
