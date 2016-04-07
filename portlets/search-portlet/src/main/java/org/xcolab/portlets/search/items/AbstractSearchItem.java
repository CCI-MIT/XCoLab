package org.xcolab.portlets.search.items;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;

import java.io.IOException;

public abstract class AbstractSearchItem {
    private static final String HTML_CLEAN_UP_REGEXP = "<[^>]*>";

    public final static int MAX_CONTENT_LENGTH = 255;

    public abstract String getTitle(Document doc, Highlighter highlighter)
            throws SystemException, IOException, InvalidTokenOffsetsException;

    public abstract String getLinkUrl(Document doc) throws SystemException;

    public abstract String getContent(Document doc, Highlighter highlighter)
            throws SystemException, IOException, InvalidTokenOffsetsException;

    public static AbstractSearchItem newInstance(Class<? extends AbstractSearchItem> clazz) {
        if (BlogSearchItem.class == clazz) {
            return new BlogSearchItem();
        }
        if (ContentSearchItem.class == clazz) {
            return new ContentSearchItem();
        }
        if (ContestSearchItem.class == clazz) {
            return new ContestSearchItem();
        }
        if (DiscussionSearchItem.class == clazz) {
            return new DiscussionSearchItem();
        }
        if (ProposalSearchItem.class == clazz) {
            return new ProposalSearchItem();
        }
        if (UserSearchItem.class == clazz) {
            return new UserSearchItem();
        }
        throw new IllegalArgumentException("Invalid class name provided to factory method: " + clazz.getName());
    }

    protected String concatFields(String[] fields, Document doc, Highlighter highlighter)
            throws IOException, InvalidTokenOffsetsException {
        StringBuilder sb = new StringBuilder();
        boolean addSeparator = false;
        for (String field : fields) {
            if (addSeparator) {
                sb.append(" ");
            }
            sb.append(doc.get(field));
            addSeparator = true;
        }

        String concatenated = sb.toString().replaceAll(HTML_CLEAN_UP_REGEXP, "");
        String highlighted =
                highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_34), fields[0], concatenated);

        if (highlighted == null || highlighted.trim().isEmpty()) {
            return concatenated;
        }
        return highlighted;

    }

    public boolean isVisible(Document doc) {
        return true;
    }
}
