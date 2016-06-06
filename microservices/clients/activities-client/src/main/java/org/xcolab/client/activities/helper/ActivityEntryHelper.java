package org.xcolab.client.activities.helper;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;

public class ActivityEntryHelper {

    public static void createActivityEntry(ActivityEntry activityEntry, ActivityEntryContentProvider provider) {
        if (activityEntry == null || provider == null) return;

        activityEntry.setActivityEntryBody(provider.getBody());
        activityEntry.setActivityEntryTitle(provider.getTitle());
        activityEntry.setActivityEntryTitle(provider.getName());

        ActivitiesClient.createActivityEntry(activityEntry);

    }

    public static void createActivityEntry(Long memberId,
                                           Long classPrimaryKey,
                                           String extraData,
                                           ActivityEntryContentProvider provider) {

        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setMemberId(memberId);
        activityEntry.setClassPrimaryKey(classPrimaryKey);
        activityEntry.setExtraData(extraData);

        provider.setActivityEntry(activityEntry);

        activityEntry.setPrimaryType(provider.getPrimaryType());
        activityEntry.setSecondaryType(provider.getSecondaryType());

        activityEntry.setActivityEntryBody(provider.getBody());
        activityEntry.setActivityEntryTitle(provider.getTitle());
        activityEntry.setActivityEntryName(provider.getName());

        ActivitiesClient.createActivityEntry(activityEntry);

    }
}
