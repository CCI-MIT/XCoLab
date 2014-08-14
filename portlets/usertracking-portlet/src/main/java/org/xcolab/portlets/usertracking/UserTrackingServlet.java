package org.xcolab.portlets.usertracking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.model.TrackedVisitor2User;
import com.ext.portlet.service.TrackedVisitLocalServiceUtil;
import com.ext.portlet.service.TrackedVisitor2UserLocalServiceUtil;
import com.ext.utils.iptranslation.Location;
import com.ext.utils.iptranslation.service.IpTranslationServiceUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

public class UserTrackingServlet extends HttpServlet {
    private final static Log _log = LogFactoryUtil.getLog(UserTrackingServlet.class);


    private static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String getHeadersAsString(HttpServletRequest request) {
        String headerString = "";
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(headerName);

            while (headers.hasMoreElements()) {
                headerString += headers.nextElement()+"\n";
            }
        }
        return headerString;
    }

    private static User getLoggedInUser(HttpServletRequest request) {
        String userIdStr = request.getParameter("userId");
        Integer userId;
        User user = null;
        try {
            userId = Integer.parseInt(userIdStr);
            if (userId > 0) {
                try {
                    user = UserLocalServiceUtil.getUser(userId);
                    if (user != null) {
                        //make sure that the hash is correct
                        if (!String.valueOf(user.getUuid().hashCode()).equals(request.getParameter("hash"))) {
                            user = null;
                        }//else the user is fine.
                    }
                } catch (PortalException e) {
                    user = null;
                } catch (SystemException e) {
                    user = null;
                }
            }
        } catch (NumberFormatException e) {
        }

        return user;
    }


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

        boolean uuidFoundInTrackedVisitor2UserTable = false;
        //find out uuid. if it is not sent as request parameter, try to retrieve existing token if user is logged in.
        String uuid = request.getParameter("uuid");
        if (uuid == null || uuid.isEmpty()) {
            if (user != null) {
                try {
                    uuid = TrackedVisitor2UserLocalServiceUtil.findUuidForUserId(user.getUserId());
                    if (uuid != null) {
                        uuidFoundInTrackedVisitor2UserTable = true;
                    }
                } catch (SystemException e) {
                }
            }

            //if the previous attempts were not successful, generate a new uuid
            if (uuid == null || uuid.isEmpty()) {
                //generate a new uuid
                uuid = UUID.randomUUID().toString();
            }
        }

        //create new TrackedVisit
        try {
            TrackedVisitLocalServiceUtil.addTrackedVisit(uuid, url, ip, browser, referer, headers, city, country);
        } catch (SystemException e) {
            _log.debug("Could not track visit: " + e.getMessage() + " " + uuid + " " + url + " " + ip);
        }

        //track uuid to visitor relation if logged in. skip if the uuid has been retrieved from the table.
        String isTrackedVisitor = request.getParameter("isTrackedVisitor");
        if (user != null && !uuidFoundInTrackedVisitor2UserTable && (isTrackedVisitor == null || isTrackedVisitor.isEmpty())) {
            try {
                TrackedVisitor2User trackedVisitor2User = TrackedVisitor2UserLocalServiceUtil.addIfNotExists(uuid, user.getUserId());
                isTrackedVisitor = "true";
            } catch (SystemException e) {
                _log.debug("Could not track visitor: " + e.getMessage() + " " + uuid + " " + user.getUserId() + " " + url + " " + ip);
            }
        }


        //write back the uuid as json. this will be set as a cookie and will be sent on future requests.
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"uuid\":\""+uuid+"\"");
        //return the isTrackedVisitor to prevent additional unnecessary database queries
        if (isTrackedVisitor != null && !isTrackedVisitor.isEmpty()) {
            out.print(",\"isTrackedVisitor\":\""+isTrackedVisitor+"\"");
        }
        out.print("}");
        out.flush();
    }

}
