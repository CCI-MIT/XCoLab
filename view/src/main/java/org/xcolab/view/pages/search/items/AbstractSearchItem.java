package org.xcolab.view.pages.search.items;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import org.xcolab.client.search.pojo.ISearchPojo;

import java.util.Objects;

public abstract class AbstractSearchItem {

    private static final int MAX_CONTENT_LENGTH = 255;

    public static AbstractSearchItem newInstance(Class<? extends AbstractSearchItem> clazz) {
        if (Objects.equals(ContentSearchItem.class, clazz)) {
            return new ContentSearchItem();
        }
        if (Objects.equals(ContestSearchItem.class, clazz)) {
            return new ContestSearchItem();
        }
        if (Objects.equals(DiscussionSearchItem.class, clazz)) {
            return new DiscussionSearchItem();
        }
        if (Objects.equals(ProposalSearchItem.class, clazz)) {
            return new ProposalSearchItem();
        }
        if (Objects.equals(UserSearchItem.class, clazz)) {
            return new UserSearchItem();
        }
        throw new IllegalArgumentException(
                "Invalid class name provided to factory method: " + clazz.getName());
    }

    public abstract String getPrintName();

    public abstract void init(ISearchPojo pojo, String searchQuery);

    public abstract String getTitle();

    public abstract String getLinkUrl();

    public abstract String getContent();

    public String getContent(String contentToHighlight, String searchQuery) {
        if (StringUtils.isBlank(contentToHighlight)) {
            return "No description found";
        }
        final String plainText = Jsoup.parse(contentToHighlight).text();
        final String abbreviatedText = StringUtils.abbreviate(plainText, MAX_CONTENT_LENGTH);
        return highlight(abbreviatedText, searchQuery);
    }

    public String highlight(String plainText, String queryToHighlight) {

        if (plainText == null) {
            return null;
        }
        if (queryToHighlight == null) {
            return plainText;
        }
        String[] tokensInUserContent = queryToHighlight.split("[\\p{Punct}\\s]+");
        for (String token : tokensInUserContent) {
            plainText = plainText.replace(token, "<b>" + token + "</b>");
        }

        return plainText;
    }

    public boolean isVisible() {
        return true;
    }
}
