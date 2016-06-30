package org.xcolab.client.sharedcolab;

import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.util.RequestUtils;


public class SharedColabClient {

    private static final String EUREKA_APPLICATION_ID = ":" + RequestUtils.getServicesPort() + "/sharedcolab-service";

    public static boolean isScreenNameUsed(String screenName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                ConfigurationAttributeKey.SHARED_COLAB_LOCATION.getStringValue() +
                EUREKA_APPLICATION_ID + "/members/isUsed")
                .queryParam("screenName", screenName);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static boolean isEmailUsed(String email) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                ConfigurationAttributeKey.SHARED_COLAB_LOCATION.getStringValue() +
                EUREKA_APPLICATION_ID + "/members/isUsed")
                .queryParam("email", email);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static Long retrieveSharedId(String email, String screenName) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" +
                        ConfigurationAttributeKey.SHARED_COLAB_LOCATION.getStringValue() +
                        EUREKA_APPLICATION_ID + "/members/retrieveSharedId");
        if (email != null) {
            uriBuilder.queryParam("email", email);
        }
        if (screenName != null) {
            uriBuilder.queryParam("screenName", screenName);
        }

        return RequestUtils.post(uriBuilder, null, Long.class);
    }

}
