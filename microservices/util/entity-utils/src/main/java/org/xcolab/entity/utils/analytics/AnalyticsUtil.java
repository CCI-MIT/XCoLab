package org.xcolab.entity.utils.analytics;

import org.xcolab.client.members.AnalyticsClient;
import org.xcolab.client.members.pojo.AnalyticsUserEvent;
import org.xcolab.entity.utils.flash.AnalyticsAttribute;
import org.xcolab.entity.utils.portlet.PortletUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public final class AnalyticsUtil {

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

        final AnalyticsUserEvent analyticsUserEvent =
                AnalyticsClient.create(userId, idString, category, action, label, value);
        AnalyticsAttribute.add(request, analyticsUserEvent);
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
