package org.xcolab.utils;

import java.io.IOException;
import java.util.Enumeration;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.filter.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletSession;
import com.liferay.portal.kernel.portlet.PortletFilterUtil;
import com.liferay.portal.kernel.servlet.PortletSessionTracker;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletServlet extends HttpServlet {

    public static final String PORTLET_APP = "com.liferay.portal.model.PortletApp";

    /**
     * @deprecated {@link PluginContextListener#PLUGIN_CLASS_LOADER}
     */
    /*
     * public static final String PORTLET_CLASS_LOADER =
     * PluginContextListener.PLUGIN_CLASS_LOADER;
     */

    public static final String PORTLET_SERVLET_CONFIG = "com.liferay.portal.kernel.servlet.PortletServletConfig";

    public static final String PORTLET_SERVLET_FILTER_CHAIN = "com.liferay.portal.kernel.servlet.PortletServletFilterChain";

    public static final String PORTLET_SERVLET_REQUEST = "com.liferay.portal.kernel.servlet.PortletServletRequest";

    public static final String PORTLET_SERVLET_RESPONSE = "com.liferay.portal.kernel.servlet.PortletServletResponse";

    public static final String EXTEND_SESSION = "EXTEND_SESSION";

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getAttribute(EXTEND_SESSION) != null) {
            if (request.getSession(false) == null) return;
            request.removeAttribute(EXTEND_SESSION);

            HttpSession session = request.getSession(false);

            if (session != null) {
                session.setAttribute(EXTEND_SESSION, Boolean.TRUE);

                session.removeAttribute(EXTEND_SESSION);
            }
            XCoLabSessionTracker.extendSession(session);
            return;
        }
        String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        PortletRequest portletRequest = (PortletRequest) request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        PortletResponse portletResponse = (PortletResponse) request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

        String lifecycle = (String) request.getAttribute(PortletRequest.LIFECYCLE_PHASE);

        FilterChain filterChain = (FilterChain) request.getAttribute(PORTLET_SERVLET_FILTER_CHAIN);

        LiferayPortletSession portletSession = (LiferayPortletSession) portletRequest.getPortletSession();

        portletRequest.setAttribute(WebKeys.PORTLET_ID, portletId);
        portletRequest.setAttribute(PORTLET_SERVLET_CONFIG, getServletConfig());
        portletRequest.setAttribute(PORTLET_SERVLET_REQUEST, request);
        portletRequest.setAttribute(PORTLET_SERVLET_RESPONSE, response);

        HttpSession session = request.getSession();

        PortletSessionTracker.add(session);
        XCoLabSessionTracker.add(session);

        portletSession.setHttpSession(session);
        try {
            portletSession.setAttribute(EXTEND_SESSION, Boolean.TRUE);

            portletSession.removeAttribute(EXTEND_SESSION);
        }
        catch (Throwable t) {
            _log.error(t, t);
        }

        try {
            PortletFilterUtil.doFilter(portletRequest, portletResponse, lifecycle, filterChain);
        } catch (PortletException pe) {
            _log.error(pe, pe);

            throw new ServletException(pe);
        }


    }

    private static Log _log = LogFactoryUtil.getLog(PortletServlet.class);

}
