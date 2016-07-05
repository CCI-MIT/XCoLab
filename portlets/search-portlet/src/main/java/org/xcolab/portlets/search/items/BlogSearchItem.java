package org.xcolab.portlets.search.items;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.xcolab.client.search.pojo.SearchPojo;

import java.io.IOException;

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
