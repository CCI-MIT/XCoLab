package org.xcolab.client.activities.helper;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.enums.ActivityProvidersType;

public class ActivityEntryHelper {



    public static void createActivityEntry(ActivitiesClient activityClient, Long memberId,
                                           Long classPrimaryKey,
                                           String extraData,
                                           Integer providerType) {


        ActivityProvidersType apt = ActivityProvidersType.getActivityProviderByType(providerType);
        activityClient.createActivityEntry(memberId, classPrimaryKey, extraData,
                apt.getActivityPrimaryType(),apt.getActivitySecondaryType());


    }
}
