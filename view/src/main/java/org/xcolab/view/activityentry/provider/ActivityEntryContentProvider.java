package org.xcolab.view.activityentry.provider;

import org.xcolab.commons.activities.enums.ActivityType;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.ActivityInitializationException;

public interface ActivityEntryContentProvider {

    ActivityType getActivityType();

    String getBody();

    String getTitle();

    //TODO COLAB-2496: this method should return a new object that holds the state
    void initialize(ActivityEntry activityEntry) throws ActivityInitializationException;
}
