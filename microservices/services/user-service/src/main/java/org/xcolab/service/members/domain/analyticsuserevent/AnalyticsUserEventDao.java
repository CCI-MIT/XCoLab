package org.xcolab.service.members.domain.analyticsuserevent;

import org.xcolab.client.user.pojo.IAnalyticsUserEvent;

public interface AnalyticsUserEventDao {

    boolean exists(long userId, String idString);

    IAnalyticsUserEvent create(IAnalyticsUserEvent analyticsUserEvent);
}
