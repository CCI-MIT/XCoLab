package org.xcolab.portlets.search.items;

import org.xcolab.client.search.pojo.SearchPojo;

public class BlogSearchItem extends AbstractSearchItem {


    private static final String BLOG_URL_FORMAT =
            "/c/blogs/find_entry?redirect=/web/guest/community&noSuchEntryRedirect=/web/guest/community&entryId=%1$s";

    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content"};

    @Override
    public void init(SearchPojo pojo, String searchQuery) {

    }

    @Override
    public String getPrintName() {
        return "News";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getLinkUrl() {
        return "";
    }

    @Override
    public String getContent() {
        return " ...";
    }
}
