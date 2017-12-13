package org.xcolab.view.activityentry.provider;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.ActivityInitializationException;

public interface ActivityEntryContentProvider {

    ActivityType getActivityType();

    String getBody();

    String getTitle();

    String getName();

    void setActivityEntry(ActivityEntry activityEntry) throws ActivityInitializationException;
}
