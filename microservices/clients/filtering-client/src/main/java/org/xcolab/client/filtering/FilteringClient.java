package org.xcolab.client.filtering;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class FilteringClient {

    private static final RestService filteringService = new RestService("filtering-service");
    private static final RestResource<FilteredEntry> filteredEntryResource = new RestResource<>(
            filteringService,
            "filteredEntries", FilteredEntry.class,
            new ParameterizedTypeReference<List<FilteredEntry>>() {
            });

    public static FilteredEntry getFilteredEntryByUuid(String uuid)
            throws FilteredEntryNotFoundException {
        try {
            return filteredEntryResource.get(uuid)
                    .cacheIdentifier("filteredEntryId_ " + uuid)
                    .execute();
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
