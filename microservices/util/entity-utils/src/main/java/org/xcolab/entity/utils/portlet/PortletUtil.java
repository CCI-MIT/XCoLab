package org.xcolab.entity.utils.portlet;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public final class PortletUtil {

    private PortletUtil() {
    }

    public static HttpServletRequest getHttpServletRequest(PortletRequest portletRequest) {
        return (HttpServletRequest) portletRequest.getAttribute("javax.servlet.request");
    }

    public static String getCurrentUrl(PortletRequest portletRequest) {
        return (String)portletRequest.getAttribute("CURRENT_URL");
    }
}
