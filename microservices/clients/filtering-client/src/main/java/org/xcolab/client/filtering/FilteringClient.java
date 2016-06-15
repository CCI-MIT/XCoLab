package org.xcolab.client.filtering;

import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

public final class FilteringClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:" + RequestUtils.getServicesPort() + "/filtering-service";


    public static FilteredEntry getFilteredEntryByUuid(String uuid) throws FilteredEntryNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/filteredEntries/" + uuid + "");
        try {
            return RequestUtils.get(uriBuilder, FilteredEntry.class, "filteredEntryId_ " + uuid);
        } catch (EntityNotFoundException e) {
            throw new FilteredEntryNotFoundException("Proposal with id " + uuid + " not found.");
        }
    }
    public static FilteredEntry createFilteredEntry(FilteredEntry filteredEntry) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/filteredEntries");
        return RequestUtils.post(uriBuilder, filteredEntry, FilteredEntry.class);
    }

    public static void updateFilteredEntry(FilteredEntry filteredEntry) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/filteredEntries/" + filteredEntry.getFilteredEntryId());

        RequestUtils.put(uriBuilder, filteredEntry);
    }

}
