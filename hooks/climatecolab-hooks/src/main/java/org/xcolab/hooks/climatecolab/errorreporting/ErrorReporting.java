package org.xcolab.hooks.climatecolab.errorreporting;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.parboiled.common.StringUtils;

import javax.mail.internet.InternetAddress;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


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
        if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(description)){
           sendMessage("Error Report from User","An exception occured at: " + url + "\n\n" + "Message from user:\n" + description);
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
            InternetAddress toEmail = new InternetAddress("pdeboer@mit.edu", "Patrick de Boer");
            MailEngine.send(fromEmail, toEmail, subject, body, true);
        } catch (Exception e) {
            _log.error("Could not send vote message", e);
        }
    }

    public void destroy() {

    }
}
