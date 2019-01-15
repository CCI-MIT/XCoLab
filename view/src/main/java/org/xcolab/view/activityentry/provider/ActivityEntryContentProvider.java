package org.xcolab.view.activityentry.provider;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.view.activityentry.ActivityInitializationException;

public interface ActivityEntryContentProvider {

    ActivityType getActivityType();

    String getBody();

    String getTitle();

    //TODO COLAB-2496: this method should return a new object that holds the state
    void initialize(IActivityEntry activityEntry) throws ActivityInitializationException;
}
