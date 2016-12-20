package org.xcolab.entity.utils.analytics;

import org.xcolab.client.members.AnalyticsClient;
import org.xcolab.client.members.pojo.AnalyticsUserEvent;
import org.xcolab.entity.utils.portlet.PortletUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class AnalyticsUtil {
	
	private final static String ANALYTICS_EVENTS_SESSION_KEY = "xcolab_analytics_events";

	private AnalyticsUtil() { }

	/** 
	 * Method publishes an event if it hasn't been already published for given user. Publishing an event is 
	 * done by storing it in request session, it will be fetched from there in the theme later (to report it with  
	 * google analytics).
	 */
	public static void publishEvent(PortletRequest portletRequest, long userId, String idString, String category, 
			String action, String label, int value) {
		
		if (AnalyticsClient.exists(userId, idString)) {
			// if event exists do nothing
			return;
		}
		HttpServletRequest request = PortletUtil.getHttpServletRequest(portletRequest);
        HttpSession session = request.getSession(true);

        //noinspection unchecked
        List<AnalyticsUserEvent> events = (List<AnalyticsUserEvent>) session.getAttribute(ANALYTICS_EVENTS_SESSION_KEY);
        if (events == null) {
            events = new ArrayList<>();
            session.setAttribute(ANALYTICS_EVENTS_SESSION_KEY, events);
        }
        
        events.add(AnalyticsClient.create(userId, idString, category, action, label, value));
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
