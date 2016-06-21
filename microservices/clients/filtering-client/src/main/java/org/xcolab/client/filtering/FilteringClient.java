package org.xcolab.client.filtering;

import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public final class FilteringClient {

    private static final RestService filteringService = new RestService("filtering-service");
    private static final RestResource filteredEntryResource = new RestResource(filteringService,
            "filteredEntries");

    public static FilteredEntry getFilteredEntryByUuid(String uuid)
            throws FilteredEntryNotFoundException {
        try {
            final UriBuilder uriBuilder = filteredEntryResource.getResourceUrl(uuid);
            return RequestUtils
                    .get(uriBuilder, FilteredEntry.class, "filteredEntryId_ " + uuid);
        } catch (EntityNotFoundException ignored) {
        }
        throw new FilteredEntryNotFoundException("FilteredEntry with uuid " + uuid + " not found.");
    }

    public static FilteredEntry createFilteredEntry(FilteredEntry filteredEntry) {
        final UriBuilder uriBuilder = filteredEntryResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, filteredEntry, FilteredEntry.class);
    }

    public static void updateFilteredEntry(FilteredEntry filteredEntry) {
        final UriBuilder uriBuilder = filteredEntryResource
                .getResourceUrl(filteredEntry.getFilteredEntryId());
        RequestUtils.put(uriBuilder, filteredEntry);
    }
}
