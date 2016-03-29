package org.xcolab.portlets.search.items;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import java.io.IOException;

public class BlogSearchItem extends AbstractSearchItem {

    private static final String BLOG_URL_FORMAT =
            "/c/blogs/find_entry?redirect=/web/guest/community&noSuchEntryRedirect=/web/guest/community&entryId=%1$s";

    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content"};

    @Override
    public String getTitle(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        return concatFields(TITLE_FIELDS, doc, highlighter);
    }

    @Override
    public String getLinkUrl(Document doc) throws SystemException {
        String id = doc.get(Field.ENTRY_CLASS_PK);
        return String.format(BLOG_URL_FORMAT, id);
    }

    @Override
    public String getContent(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String content = concatFields(CONTENT_FIELDS, doc, highlighter);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }
}
