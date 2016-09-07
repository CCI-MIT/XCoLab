package org.xcolab.client.filtering;

import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public final class FilteringClient {

    private static final RestService filteringService = new RestService("filtering-service");
    private static final RestResource<FilteredEntry> filteredEntryResource = new RestResource<>(
            filteringService, "filteredEntries", FilteredEntry.TYPES);

    public static FilteredEntry getFilteredEntryByUuid(String uuid)
            throws FilteredEntryNotFoundException {
        try {
            return filteredEntryResource.get(uuid)
                    .withCache(CacheKeys.of(FilteredEntry.class, uuid), CacheRetention.REQUEST)
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
