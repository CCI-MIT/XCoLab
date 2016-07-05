package org.xcolab.portlets.search.items;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.xcolab.client.search.pojo.SearchPojo;

import java.io.IOException;

public abstract class AbstractSearchItem {
    private static final String HTML_CLEAN_UP_REGEXP = "<[^>]*>";

    public final static int MAX_CONTENT_LENGTH = 255;

    public abstract String getPrintName();

    public abstract void init(SearchPojo pojo, String searchQuery);

    public abstract String getTitle();

    public abstract String getLinkUrl();

    public abstract String getContent();

    public String highlight(String content, String queryToHighlight) {
        if (content == null) {return null;}
        if (queryToHighlight == null) {return content;}
        String[] tokensInUserContent = queryToHighlight.split("[\\p{Punct}\\s]+");
        if (tokensInUserContent != null) {
            for(String token : tokensInUserContent) {
                content = content.replace(token, "<b>"+token+"</b>");
            }
        }
        content = content.replace(queryToHighlight, "<b>"+queryToHighlight+"</b>");

        return content;
    }

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

    public boolean isVisible() {
        return true;
    }
}
