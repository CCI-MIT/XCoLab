package org.xcolab.view.activityentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ActivityEntryHelper {

    private static final Logger log = LoggerFactory.getLogger(ActivityEntryHelper.class);

    private final List<ActivityEntryContentProvider> providerList;

    @Autowired
    public ActivityEntryHelper(List<ActivityEntryContentProvider> providerList) {
        this.providerList = providerList;
    }

    public String getActivityBody(IActivityEntry entry) {
        final ActivityEntryContentProvider provider = getProvider(entry);
        return provider != null ? provider.getBody() : "";
    }

    public String getActivityTitle(IActivityEntry entry) {
        final ActivityEntryContentProvider provider = getProvider(entry);
        return provider != null ? provider.getTitle() : "";
    }

    private ActivityEntryContentProvider getProvider(IActivityEntry entry) {
        try {
            final Optional<ActivityEntryContentProvider> providerOpt = providerList.stream()
                    .filter(provider ->
                            Objects.equals(provider.getActivityType(), entry.getActivityTypeEnum()))
                    .findAny();
            if (!providerOpt.isPresent()) {
                log.warn("Activity entry {} has no valid content provider",
                        entry.getId());
                return null;

                //TODO COLAB-2486: Fix legacy activity entries and throw an exception on error
                // throw new IllegalStateException("Activity provider for entry "
                //        + entry.getId() + " does not exist");
            }
            final ActivityEntryContentProvider provider = providerOpt.get();
            provider.initialize(entry);
            return provider;
        } catch (ActivityInitializationException e) {
            log.warn("Error when getting activity body: {}", e.getMessage());
            return null;
        }
    }
}
