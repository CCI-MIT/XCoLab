package org.xcolab.client.filtering;

import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public final class FilteringClient {

    private static final RestService moderationService = new RestService(CoLabService.MODERATION,
            ServiceRequestUtils.getNamespace());

    private static final RestResource<FilteredEntry, String> filteredEntryResource =
            new RestResource1<>(moderationService, "filteredEntries", FilteredEntry.TYPES);

    public static FilteredEntry getFilteredEntryByUuid(String uuid)
            throws FilteredEntryNotFoundException {
        try {
            return filteredEntryResource.get(uuid)
                    .withCache(CacheKeys.of(FilteredEntry.class, uuid), CacheName.MISC_REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException ignored) {
            throw new FilteredEntryNotFoundException(uuid);
        }
    }

    public static FilteredEntry createFilteredEntry(FilteredEntry filteredEntry) {
        return filteredEntryResource.create(filteredEntry).execute();
    }

    public static void updateFilteredEntry(FilteredEntry filteredEntry) {
        filteredEntryResource.update(filteredEntry, filteredEntry.getUuid());
    }
}
