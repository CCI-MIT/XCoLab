package org.xcolab.client.activities.helper;

import org.xcolab.client.activities.ActivitiesClient;

import org.xcolab.client.activities.pojo.ActivityEntry;

public class ActivityEntryHelper {



    public static void createActivityEntry(ActivitiesClient activityClient, Long memberId,
                                           Long classPrimaryKey,
                                           String extraData,
                                           Integer providerType) {



        activityClient.createActivityEntry(memberId, classPrimaryKey, extraData, providerType);


    }
}
