package org.xcolab.view.activityentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;

import java.util.List;
import java.util.Optional;

@Component
public class ActivityEntryHelper {

    private static final Logger log = LoggerFactory.getLogger(ActivityEntryHelper.class);

    private final List<ActivityEntryContentProvider> providerList;

    @Autowired
    public ActivityEntryHelper(List<ActivityEntryContentProvider> providerList) {
        this.providerList = providerList;
    }

    public String getActivityBody(ActivityEntry entry) {
        final ActivityEntryContentProvider provider = getProvider(entry);
        return provider != null ? provider.getBody() : "";
    }

    public String getActivityTitle(ActivityEntry entry) {
        final ActivityEntryContentProvider provider = getProvider(entry);
        return provider != null ? provider.getTitle() : "";
    }

    private ActivityEntryContentProvider getProvider(ActivityEntry entry) {
        try {
            final Optional<ActivityEntryContentProvider> providerOpt = providerList.stream()
                    .filter(provider -> provider.getPrimaryType() == entry.getPrimaryType()
                            .longValue())
                    .filter(provider -> provider.getSecondaryType() == entry.getSecondaryType()
                            .longValue()).findAny();
            if (!providerOpt.isPresent()) {
                throw new IllegalStateException("Activity provider for entry "
                        + entry.getActivityEntryId() + " does not exist");
            }
            final ActivityEntryContentProvider provider = providerOpt.get();
            provider.setActivityEntry(entry);
            return provider;
        } catch (ActivityInitializationException e) {
            log.warn("Error when getting activity body: {}", e.getMessage());
            return null;
        }
    }
}
