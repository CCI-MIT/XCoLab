package org.xcolab.util.clients;

public enum CoLabService {
    ACTIVITY("activities-service"),
    ADMIN("admin-service"),
    BALLOON("balloons-service"),
    COMMENT("comment-service"),
    CONTENT("content-service"),
    CONTEST("contest-service"),
    PROPOSAL("proposals-service"),
    EMAIL("emails-service"),
    FILE("files-service"),
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
