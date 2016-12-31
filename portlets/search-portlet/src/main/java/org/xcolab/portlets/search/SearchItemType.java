package org.xcolab.portlets.search;

import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.portlets.search.items.AbstractSearchItem;
import org.xcolab.portlets.search.items.ContestSearchItem;
import org.xcolab.portlets.search.items.DiscussionSearchItem;
import org.xcolab.portlets.search.items.ProposalSearchItem;
import org.xcolab.portlets.search.items.UserSearchItem;
import org.xcolab.portlets.search.paging.PageLinkWrapper;

public enum SearchItemType {

    PLAN(1368503L, ProposalSearchItem.class),

    CONTEST(39701L, ContestSearchItem.class),

    USER(10038L,
            UserSearchItem.class),
//    CONTENT(1L, ContentSearchItem.class),
    DISCUSSION(39202L, DiscussionSearchItem.class);

    private Long id;
    private final Class<? extends AbstractSearchItem> searchItemClass;

    SearchItemType(Long id, Class<? extends AbstractSearchItem> searchItemClass) {
        this.searchItemClass = searchItemClass;
        this.id = id;
    }

    public boolean isOfGivenType(SearchPojo pojo) {
        return pojo.getSearchTypeId().longValue() == this.id.longValue();
    }

    public String getName() {
        return name();
    }

    //used as method in jsp!
    @SuppressWarnings("unused")
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
