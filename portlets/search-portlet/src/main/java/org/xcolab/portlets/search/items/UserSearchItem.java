package org.xcolab.portlets.search.items;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import java.io.IOException;

public class UserSearchItem extends AbstractSearchItem {

    private final static String[] TITLE_FIELDS = {"screenName"};
    private final static String[] CONTENT_FIELDS = {"firstName", "lastName"};

    @Override
    public String getTitle(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        return concatFields(TITLE_FIELDS, doc, highlighter);
    }

    @Override
    public String getLinkUrl(Document doc) throws SystemException {
        return "/web/guest/member/-/member/userId/" + getUserId(doc);
    }

    @Override
    public String getContent(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String content = concatFields(CONTENT_FIELDS, doc, highlighter);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }

    public long getUserId(Document doc) {
        String idStr = doc.get(Field.USER_ID);
        return Long.parseLong(idStr);
    }
}
