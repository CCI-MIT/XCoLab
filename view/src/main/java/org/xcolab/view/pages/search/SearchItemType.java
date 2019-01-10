package org.xcolab.view.pages.search;

import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.view.pages.search.items.AbstractSearchItem;
import org.xcolab.view.pages.search.items.ContestSearchItem;
import org.xcolab.view.pages.search.items.DiscussionSearchItem;
import org.xcolab.view.pages.search.items.ProposalSearchItem;
import org.xcolab.view.pages.search.items.UserSearchItem;
import org.xcolab.view.pages.search.paging.PageLinkWrapper;

public enum SearchItemType {

    PLAN(1368503L, ProposalSearchItem.class),
    CONTEST(39701L, ContestSearchItem.class),
    USER(10038L, UserSearchItem.class),
    // CONTENT(1L, ContentSearchItem.class),
    DISCUSSION(39202L, DiscussionSearchItem.class);

    private final Long id;
    private final Class<? extends AbstractSearchItem> searchItemClass;

    SearchItemType(Long id, Class<? extends AbstractSearchItem> searchItemClass) {
        this.searchItemClass = searchItemClass;
        this.id = id;
    }

    public boolean isOfGivenType(ISearchPojo pojo) {
        return pojo.getSearchTypeId().longValue() == this.id.longValue();
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
