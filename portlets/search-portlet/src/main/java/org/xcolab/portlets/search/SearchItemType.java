package org.xcolab.portlets.search;

import com.liferay.portal.kernel.search.Document;
import org.xcolab.portlets.search.items.AbstractSearchItem;
import org.xcolab.portlets.search.items.BlogSearchItem;
import org.xcolab.portlets.search.items.ContentSearchItem;
import org.xcolab.portlets.search.items.ContestSearchItem;
import org.xcolab.portlets.search.items.DiscussionSearchItem;
import org.xcolab.portlets.search.items.ProposalSearchItem;
import org.xcolab.portlets.search.items.UserSearchItem;
import org.xcolab.portlets.search.paging.PageLinkWrapper;

public enum SearchItemType {

    PLAN(new String[]{"entryClassName", "com.ext.portlet.model.Proposal"},
            new String[]{"content", "title", "pitch", "sections"}, ProposalSearchItem.class),

    CONTEST(new String[]{"entryClassName", "com.ext.portlet.model.Contest"},
            new String[]{"content", "title"}, ContestSearchItem.class),

    USER(new String[]{"entryClassName", "com.liferay.portal.model.User"},
            new String[]{"screenName", "firstName", "lastName"}, UserSearchItem.class),

    CONTENT(new String[]{"entryClassName",
            "com.liferay.portlet.wiki.model.* OR com.liferay.portlet.journal.model.JournalArticle"},
            new String[]{"title"}, ContentSearchItem.class),

    BLOG(new String[]{"entryClassName", "com.liferay.portlet.blogs.model.*"},
            new String[]{"title", "content"}, BlogSearchItem.class),

    DISCUSSION(new String[]{"entryClassName", "com.ext.portlet.model.DiscussionMessage"},
            new String[]{"title", "content"}, DiscussionSearchItem.class);

    private final String[] determinatorFieldValue;
    private final String[] searchFields;
    private final Class<? extends AbstractSearchItem> searchItemClass;

    SearchItemType(String[] determinatorInfo, String[] searchFields,
            Class<? extends AbstractSearchItem> searchItemClass) {
        this.searchItemClass = searchItemClass;
        if (determinatorInfo.length != 2) {
            throw new IllegalArgumentException("Determinator info table needs to have 2 values");
        }
        this.determinatorFieldValue = determinatorInfo;
        this.searchFields = searchFields;
    }

    public String getQuery(String userQuery) {
        StringBuilder sb = new StringBuilder();
        // first add determinator fields query
        sb.append("(");

        sb.append(determinatorFieldValue[0]);
        sb.append(":(");
        sb.append(determinatorFieldValue[1]);
        sb.append(") ");

        if (userQuery != null && !userQuery.isEmpty()) {
            sb.append(" AND (");
            boolean addSeparator = false;
            for (String field : searchFields) {
                if (addSeparator) {
                    sb.append(" OR ");
                }
                sb.append(field);
                sb.append(":(");
                sb.append(userQuery);
                sb.append(") ");
                addSeparator = true;
            }
            sb.append(")");
        }
        sb.append(")");

        return sb.toString();
    }

    public boolean isOfGivenType(Document doc) {
        String detFieldVal = doc.get(determinatorFieldValue[0]);
        if (detFieldVal != null) {
            String[] detFieldVals = determinatorFieldValue[1].split(" ");
            for (String fv : detFieldVals) {
                if (detFieldVal.matches(fv)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getName() {
        return name();
    }

    public PageLinkWrapper getPageLink(SearchBean searchBean) {
        return new PageLinkWrapper(getPrintName(), 1, searchBean.getSearchPhrase(), name());
    }

    public String getPrintName() {
        return getSearchItem().getPrintName();
    }

    public AbstractSearchItem getSearchItem() {
        return AbstractSearchItem.newInstance(searchItemClass);
    }
}
