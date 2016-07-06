package org.xcolab.client.sharedcolab;

import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ServiceQuery;


public class SharedColabClient {

    private static final RestService sharedColabService = new RestService("sharedcolab-service");

    private static final RestResource<Object> sharedColabResource = new RestResource<>(sharedColabService,
            "members");

    public static boolean isScreenNameUsed(String screenName) {
        return sharedColabResource.service("isUsed",Boolean.class)
                .queryParam("screenName", screenName).getUnchecked();
    }

    public static boolean isEmailUsed(String email) {
        return sharedColabResource.service("isUsed", Boolean.class)
                .queryParam("email", email)
                .getUnchecked();
    }

    public static Long retrieveSharedId(String email, String screenName) {

        ServiceQuery<Long> retrieveSharedId = sharedColabResource.service("retrieveSharedId", Long.class);

        if (email != null) {
            retrieveSharedId.optionalQueryParam("email", email);
        }
        if (screenName != null) {
            retrieveSharedId.optionalQueryParam("screenName", screenName);
        }

        return retrieveSharedId.getUnchecked();
    }

}
