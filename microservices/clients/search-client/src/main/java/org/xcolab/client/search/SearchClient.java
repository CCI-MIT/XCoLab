package org.xcolab.client.search;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;


public final class SearchClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:" + RequestUtils.getServicesPort() + "/search-service";

    public static List<SearchPojo> search(Integer startRecord, Integer limitRecord, String sort, String query) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/search");
        if (startRecord != null) {
            uriBuilder.queryParam("startRecord", startRecord);
        }
        if (limitRecord != null) {
            uriBuilder.queryParam("limitRecord", limitRecord);
        }
        if (sort != null) {
            uriBuilder.queryParam("sort", sort);
        }
        if (query != null) {
            uriBuilder.queryParam("query", query);
        }
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<SearchPojo>>() {
                });

    }
    public static Integer searchCount(String sort, String query) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/search/count");
        if (sort != null) {
            uriBuilder.queryParam("sort", sort);
        }
        if (query != null) {
            uriBuilder.queryParam("query", query);
        }
        try {
            return RequestUtils.get(uriBuilder, Integer.class);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }


}
