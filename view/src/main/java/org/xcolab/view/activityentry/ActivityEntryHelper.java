package org.xcolab.view.activityentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;

import java.util.List;

@Component
public class ActivityEntryHelper {

    private final List<ActivityEntryContentProvider> providerList;

    @Autowired
    public ActivityEntryHelper(List<ActivityEntryContentProvider> providerList) {
        this.providerList = providerList;
    }

    public String getActivityBody(ActivityEntry entry) {

        for (ActivityEntryContentProvider provider : providerList) {

            if (provider.getPrimaryType() == entry.getPrimaryType().longValue()
                    && provider.getSecondaryType() == entry.getSecondaryType().longValue()) {
                provider.setActivityEntry(entry);
                return provider.getBody();
            }
        }
        return "";

    }
}
