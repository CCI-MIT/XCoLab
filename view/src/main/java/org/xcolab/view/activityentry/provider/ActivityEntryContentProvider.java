package org.xcolab.view.activityentry.provider;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.i18n.ResourceMessageResolver;


public interface ActivityEntryContentProvider {


    Long getPrimaryType();

    Long getSecondaryType();

    String getBody();

    String getTitle();

    String getName();

    void setActivityEntry(ActivityEntry activityEntry);

}
