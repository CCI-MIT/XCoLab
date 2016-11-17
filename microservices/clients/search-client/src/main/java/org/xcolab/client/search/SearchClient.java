package org.xcolab.client.search;

import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class SearchClient {

    private static final RestService searchService = new RestService(CoLabService.SEARCH);

    private static final RestResource<SearchPojo, Long> searchResource = new RestResource1<>(searchService,
            "search", SearchPojo.TYPES);

    public static List<SearchPojo> search(Integer startRecord, Integer limitRecord, String filter, String query) {
        ListQuery<SearchPojo> searchPojoListQuery = searchResource.list();
        if (startRecord != null && limitRecord != null) {
            searchPojoListQuery.addRange(startRecord,limitRecord);
        }

        if (filter != null) {
            searchPojoListQuery.optionalQueryParam("filter", filter);
        }
        if (query != null) {
            searchPojoListQuery.optionalQueryParam("query", query);
        }
        return searchPojoListQuery.execute();

    }
    public static Integer searchCount(String sort, String query) {
        try {
            return searchResource.service("count", Integer.class)
                    .optionalQueryParam("sort", sort)
                    .optionalQueryParam("query", query)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }

    }


}
