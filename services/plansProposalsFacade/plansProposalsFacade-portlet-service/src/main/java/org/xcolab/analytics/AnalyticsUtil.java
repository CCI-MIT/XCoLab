package org.xcolab.analytics;

import com.ext.portlet.model.AnalyticsUserEvent;
import com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public final class AnalyticsUtil {
	
	private final static String ANALYTICS_EVENTS_SESSION_KEY = "xcolab_analytics_events";

	private AnalyticsUtil() { }

	/** 
	 * Method publishes an event if it hasn't been already published for given user. Publishing an event is 
	 * done by storing it in request session, it will be fetched from there in the theme later (to report it with  
	 * google analytics).
	 *
	 * @throws SystemException 
	 */
	public static void publishEvent(PortletRequest portletRequest, long userId, String idString, String category, 
			String action, String label, int value) throws SystemException {
		
		if (AnalyticsUserEventLocalServiceUtil.eventExists(userId, idString)) {
			// if event exists do nothing
			return;
		}
		HttpServletRequest request = PortalUtil.getHttpServletRequest(portletRequest);
		
        HttpSession session = request.getSession(true);

        //noinspection unchecked
        List<AnalyticsUserEvent> events = (List<AnalyticsUserEvent>) session.getAttribute(ANALYTICS_EVENTS_SESSION_KEY);
        
        if (events == null) {
            events = new ArrayList<>();
            session.setAttribute(ANALYTICS_EVENTS_SESSION_KEY, events);
        }
        
        events.add(AnalyticsUserEventLocalServiceUtil.createEvent(userId, idString, category, action, label, value));
	}


    public static int getAnalyticsValueForCount(int count) {
        if (count < 1) {
            return 0;
        }
        if (count < 5) {
            return count == 1 ? 1 : 2;
        }
        return 3;
    }
}
