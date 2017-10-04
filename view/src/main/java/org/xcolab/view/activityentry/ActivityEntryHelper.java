package org.xcolab.view.activityentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;

import java.util.List;

@Component
public class ActivityEntryHelper {

    private static final Logger log = LoggerFactory.getLogger(ActivityEntryHelper.class);

    private final List<ActivityEntryContentProvider> providerList;

    @Autowired
    public ActivityEntryHelper(List<ActivityEntryContentProvider> providerList) {
        this.providerList = providerList;
    }

    public String getActivityBody(ActivityEntry entry) {
        try {
            for (ActivityEntryContentProvider provider : providerList) {

                if (provider.getPrimaryType() == entry.getPrimaryType().longValue()
                        && provider.getSecondaryType() == entry.getSecondaryType().longValue()) {
                    provider.setActivityEntry(entry);
                    return provider.getBody();
                }
            }
        } catch (ActivityInitializationException e) {
            log.warn("Error when getting activity body: {}", e.getMessage());
        }
        return "";
    }
}
