package org.xcolab.client.members;

import org.xcolab.client.members.pojo.AnalyticsUserEvent;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;

public final class AnalyticsClient {

    private static final RestService memberService = new RestService("members-service");

    private static final RestResource<Member> memberResource =
            new RestResource<>(memberService, "members", Member.TYPES);

    private static final RestResource<AnalyticsUserEvent> analyticsUserEventResource =
            new RestResource<>(memberService, "analyticsUserEvent", AnalyticsUserEvent.TYPES);

    public static AnalyticsUserEvent create(long userId, String idString, String category,
            String action, String label, int value) {
        AnalyticsUserEvent analyticsUserEvent = new AnalyticsUserEvent();
        analyticsUserEvent.setUserId(userId);
        analyticsUserEvent.setIdString(idString);
        analyticsUserEvent.setCategory(category);
        analyticsUserEvent.setAction(action);
        analyticsUserEvent.setLabel(label);
        analyticsUserEvent.setValue(value);

        return analyticsUserEventResource.create(analyticsUserEvent).execute();
    }

    public static boolean exists(long memberId, String idString) {
        return memberResource
                .getSubRestResource(memberId, "analyticsEvent", AnalyticsUserEvent.TYPES)
                .service(idString, Boolean.class)
                .get();
    }
}
