package org.xcolab.portlets.loginregister;

import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class PortletUtils {

    public static HttpServletRequest getOriginalRequest(PortletRequest request) {

        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
        while (httpRequest instanceof HttpServletRequestWrapper) {
            httpRequest = (HttpServletRequest) ((HttpServletRequestWrapper) httpRequest).getRequest();
        }

        return httpRequest;
    }

    public static HttpServletResponse getHttpServletResponse(PortletResponse response) {
        return PortalUtil.getHttpServletResponse(response);
    }
}
