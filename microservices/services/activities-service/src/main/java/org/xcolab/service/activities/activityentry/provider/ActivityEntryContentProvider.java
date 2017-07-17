package org.xcolab.service.activities.activityentry.provider;

import org.xcolab.model.tables.pojos.ActivityEntry;

public interface ActivityEntryContentProvider {

    Long getPrimaryType();

    Long getSecondaryType();

    String getBody();

    String getTitle();

    String getName();

    void setActivityEntry(ActivityEntry activityEntry);

}
