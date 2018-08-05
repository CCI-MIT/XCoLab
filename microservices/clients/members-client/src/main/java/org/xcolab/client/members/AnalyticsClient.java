package org.xcolab.client.members;

import org.xcolab.client.members.pojo.AnalyticsUserEvent;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;

public final class AnalyticsClient {

    private static final RestResource1<Member, Long> memberResource =
            new RestResource1<>(UserResource.USER, Member.TYPES);

    private static final RestResource2L<Member, AnalyticsUserEvent> analyticsUserEventResource =
            new RestResource2L<>(memberResource, "analyticsEvents", AnalyticsUserEvent.TYPES);

    public static AnalyticsUserEvent create(long userId, String idString, String category,
            String action, String label, int value) {
        AnalyticsUserEvent analyticsUserEvent = new AnalyticsUserEvent();
        analyticsUserEvent.setUserId(userId);
        analyticsUserEvent.setIdString(idString);
        analyticsUserEvent.setCategory(category);
        analyticsUserEvent.setAction(action);
        analyticsUserEvent.setLabel(label);
        analyticsUserEvent.setValue(value);

        return analyticsUserEventResource.resolveParentId(memberResource.id(userId))
                .create(analyticsUserEvent).execute();
    }

    public static boolean exists(long userId, String idString) {
        return analyticsUserEventResource.resolveParentId(memberResource.id(userId))
                .elementService(idString, "exists", Boolean.class)
                .get();
    }
}
