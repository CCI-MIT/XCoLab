package org.xcolab.client.comment;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum CommentResource implements ResourceEnum {

    COMMENT("comments"),
    THREAD("threads"),
    CATEGORY("categories"),
    CATEGORY_GROUP("groups")
    ;

    private final String resourceName;

    CommentResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.COMMENT;
    }
}
