package org.xcolab.client.contents;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum ContentResource implements ResourceEnum {
    CONTENT_ARTICLE("contentArticles"),
    CONTENT_ARTICLE_VERSION("contentArticleVersions"),
    CONTENT_FOLDER("contentFolders"),
    CONTENT_PAGE("contentPages")
    ;

    private final String resourceName;

    ContentResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.CONTENT;
    }
}
