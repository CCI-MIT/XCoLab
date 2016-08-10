package org.xcolab.portlets.usertracking;

import com.ext.utils.iptranslation.Location;
import com.ext.utils.iptranslation.service.IpTranslationServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.tracking.TrackingClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTrackingServlet extends HttpServlet {
    private final static Log _log = LogFactoryUtil.getLog(UserTrackingServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get ip
        String ip = getClientIpAddress(request);
        //city, country
        String url = request.getParameter("url");
        String referer = request.getParameter("referer");
        String browser = request.getHeader("User-Agent");

        //get headers
        String headers = getHeadersAsString(request);

        //get location
        String city = "";
        String country = "";
        try {
            Location location = IpTranslationServiceUtil.getLocationForIp(ip);
            if (location != null) {
                city = location.getCity();
                country = location.getCountry();
            }
        } catch (Exception e) {
            //silently fail to avoid spamming the logs
        }

        //if user is logged in, check if tuple (uuid, userid) already exists. if not, create it.
        User user = getLoggedInUser(request);

        //find out uuid. if it is not sent as request parameter, try to retrieve existing token if user is logged in.
        String uuid = request.getParameter("uuid");
        String isTrackedVisitor = request.getParameter("isTrackedVisitor");
        if (StringUtils.isBlank(uuid)) {
            if (user != null) {
                uuid = TrackingClient.getTrackedVisitorOrCreate(user.getUserId()).getUuid_();
                isTrackedVisitor = "true";
            } else {
                uuid = TrackingClient.generateUUID();
            }
        }

        TrackingClient.addTrackedVisit(uuid, url, ip, browser, referer, headers, city, country);

        //write back the uuid as json. this will be set as a cookie and will be sent on future requests.
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"uuid\":\""+ StringEscapeUtils.escapeEcmaScript(uuid)+"\"");
        //return the isTrackedVisitor to prevent additional unnecessary database queries
        if (!StringUtils.isEmpty(isTrackedVisitor)) {
            out.print(",\"isTrackedVisitor\":\""+StringEscapeUtils.escapeEcmaScript(isTrackedVisitor)+"\"");
        }
        out.print("}");
        out.flush();
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

    private User getLoggedInUser(HttpServletRequest request) {
        String userIdStr = request.getParameter("userId");
        User user = null;
        try {
            Integer userId = Integer.parseInt(userIdStr);
            if (userId > 0) {
                try {
                    user = UserLocalServiceUtil.getUser(userId);
                    if (user != null) {
                        //make sure that the hash is correct
                        if (!String.valueOf(user.getUuid().hashCode()).equals(request.getParameter("hash"))) {
                            user = null;
                        }//else the user is fine.
                    }
                } catch (PortalException | SystemException e) {
                    user = null;
                }
            }
        } catch (NumberFormatException ignored) { }

        return user;
    }
}
