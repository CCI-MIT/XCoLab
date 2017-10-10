package org.xcolab.util.http.client;

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

    private final String serviceName;

    CoLabService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
