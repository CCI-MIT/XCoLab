package org.xcolab.view.activityentry.provider;

import org.xcolab.client.activities.pojo.ActivityEntry;

public interface ActivityEntryContentProvider {

    Long getPrimaryType();

    Long getSecondaryType();

    String getBody();

    String getTitle();

    String getName();

    void setActivityEntry(ActivityEntry activityEntry);
}
