package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ServiceNamespace;

/**
 * Represents a microservice together with information how to reach it.
 *
 * Each service has a name and allows retrieving the base URL. All of a service's resources are
 * relative to the base URL returned by {@link #getBaseUrl(ServiceNamespace)}.
 */
public enum CoLabService {

    ACTIVITY("activity-service"),
    ADMIN("admin-service"),
    COMMENT("comment-service"),
    CONTENT("content-service"),
    CONTEST("contest-service"),
    EMAIL("email-service"),
    MODERATION("moderation-service"),
    MEMBER("members-service"),
    MODEL("modeling-service"),
    SEARCH("search-service"),
    TRACKING("tracking-service");

    private static final String SCHEME = "http";

    private final String serviceName;

    CoLabService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param namespace the namespace of this service
     * @return a UriBuilder initialized with the base URL of this service
     */
    public UriBuilder getBaseUrl(ServiceNamespace namespace) {
        return new UriBuilder(String.format("%s://%s-%s", SCHEME, namespace.getNamespace(),
                serviceName));
    }
}
