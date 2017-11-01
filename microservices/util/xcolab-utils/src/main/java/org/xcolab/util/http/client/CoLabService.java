package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ServiceNamespace;

public enum CoLabService {

    ACTIVITY("activities-service"),
    ADMIN("admin-service"),
    COMMENT("comment-service"),
    CONTENT("content-service"),
    CONTEST("contest-service"),
    EMAIL("emails-service"),
    MODERATION("moderation-service"),
    MEMBER("members-service"),
    MODEL("modeling-service"),
    SEARCH("search-service"),
    SHARED("sharedcolab-service"),
    TRACKING("tracking-service");

    private static final String SCHEME = "http";

    private final String serviceName;

    CoLabService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public UriBuilder getBaseUrl(ServiceNamespace namespace) {
        return new UriBuilder(String.format("%s://%s-%s", SCHEME, namespace.getNamespace(),
                serviceName));
    }
}
