package org.xcolab.portlets.loginregister;

import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PortletUtils {

    public static HttpServletRequest getOryginalRequest(PortletRequest request) {

        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
        while (httpRequest instanceof HttpServletRequestWrapper) {
            httpRequest = (HttpServletRequest) ((HttpServletRequestWrapper) httpRequest).getRequest();
        }

        return httpRequest;
    }
}
