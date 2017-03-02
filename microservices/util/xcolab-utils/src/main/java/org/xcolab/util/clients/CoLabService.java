package org.xcolab.util.clients;

public enum CoLabService {
    ACTIVITY("activities-service"),
    ADMIN("admin-service"),
    BALLOON("balloons-service"),
    COMMENT("comment-service"),
    CONTENT("contents-service"),
    CONTEST("contest-service"),
    PROPOSAL("proposals-service"),
    EMAIL("emails-service"),
    FILE("files-service"),
    FILTER("filtering-service"),
    FLAG("flagging-service"),
    MEMBER("members-service"),
    MODEL("modeling-service"),
    SEARCH("search-service"),
    SHARED("sharedcolab-service"),
    TRACK("tracking-service");

    private final String serviceName;

    CoLabService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
