package org.xcolab.service.members.domain.analyticsuserevent;

import org.xcolab.model.tables.pojos.AnalyticsUserEvent;

public interface AnalyticsUserEventDao {

    boolean exists(long userId, String idString);

    AnalyticsUserEvent create(AnalyticsUserEvent analyticsUserEvent);
}
