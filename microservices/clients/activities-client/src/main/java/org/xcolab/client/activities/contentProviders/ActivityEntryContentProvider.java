package org.xcolab.client.activities.contentProviders;

import org.xcolab.client.activities.pojo.ActivityEntry;

public interface ActivityEntryContentProvider {

    Integer getPrimaryType();

    Integer getSecondaryType();

    String getBody();

    String getTitle();

    String getName();

    void setActivityEntry(ActivityEntry activityEntry);

}
