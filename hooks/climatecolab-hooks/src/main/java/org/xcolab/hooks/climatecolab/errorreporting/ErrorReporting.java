package org.xcolab.hooks.climatecolab.errorreporting;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.parboiled.common.StringUtils;

import javax.mail.internet.InternetAddress;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        String url = request.getParameter("url");
        String description = request.getParameter("description");
        String stackTrace = request.getParameter("stackTrace");
        String userScreenName = "";
        try {
            userScreenName = PortalUtil.getUser(request).getScreenName();
        }
        catch(Exception e){
            // Couldn't find user
        }
        StringBuilder messageBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(description)){
            //messageBuilder.append("An exception occured at: " + url + "\r\n\n");
            //messageBuilder.append("Message from user:\n " + description + "\r\n\n");
            //messageBuilder.append("Stacktrace:\n " + URLDecoder.decode(stackTrace, "UTF-8") +"\n");
            messageBuilder.append("<p><strong>An exception occured at:</strong><br> " + url + "</p>");
            messageBuilder.append("<p><strong>Message from user " + userScreenName + ":</strong><br/> " + description + "</p>");
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

            String emailRecipients = "pdeboer@mit.edu,knauert@mit.edu,mangk@mit.edu";
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
