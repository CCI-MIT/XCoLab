package org.xcolab.view.config.tomcat;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.tomcat.util.buf.MessageBytes;

import java.io.IOException;

import javax.servlet.ServletException;

public class ForwardedHostValve extends ValveBase {

    @Override
    public void invoke(final Request request, final Response response)
            throws IOException, ServletException {

        final MessageBytes serverNameMB = request.getCoyoteRequest().serverName();
        String originalServerName = null;
        final String forwardedHost = request.getHeader("X-Forwarded-Host");
        if (forwardedHost != null) {
            originalServerName = serverNameMB.getString();
            serverNameMB.setString(forwardedHost);
        }

        try {
            getNext().invoke(request, response);
        } finally {
            if (forwardedHost != null) {
                serverNameMB.setString(originalServerName);
            }
        }
    }

}
