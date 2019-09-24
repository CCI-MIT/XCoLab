package org.xcolab.view.util.entity.analytics;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.IAnalyticsUserEvent;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;

import javax.servlet.http.HttpServletRequest;

public final class AnalyticsUtil {

    private AnalyticsUtil() { }

    /**
     * Method publishes an event if it hasn't been already published for given user. Publishing an
     * event is done by storing it in request session, it will be fetched from there in the theme
     * later (to report it with google analytics).
     */
    public static void publishEvent(HttpServletRequest request, long userId, String idString,
            String category, String action, String label, int value) {

        if (StaticUserContext.getAnalyticsClient().exists(userId, idString)) {
            // if event exists do nothing
            return;
        }

        final IAnalyticsUserEvent analyticsUserEvent =
                StaticUserContext.getAnalyticsClient().create(userId, idString, category, action, label, value);
        AnalyticsAttribute.add((request), analyticsUserEvent);
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
