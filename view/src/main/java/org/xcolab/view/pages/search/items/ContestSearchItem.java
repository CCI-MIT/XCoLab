package org.xcolab.view.pages.search.items;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.search.pojo.ISearchPojo;

public class ContestSearchItem extends AbstractSearchItem {

    private ISearchPojo searchPojo;
    private String searchQuery;

    private ContestWrapper contest;

    @Override
    public void init(ISearchPojo pojo, String searchQuery) {
        this.searchPojo = pojo;
        this.searchQuery = searchQuery;
        contest = new ContestWrapper(StaticContestContext.getContestClient()
                .getContest(searchPojo.getClassPrimaryKey()));
    }

    @Override
    public String getPrintName() {
        final long contestTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        final ContestType contestType =
                StaticAdminContext.getContestTypeClient().getContestType(contestTypeId);
        return contestType.getContestNamePlural();
    }

    @Override
    public String getTitle() {
        return highlight(contest.getTitleWithEndYear(), searchQuery);
    }

    @Override
    public String getLinkUrl() {
        return contest.getContestLinkUrl();
    }

    @Override
    public String getContent() {
        return super.getContent(contest.getDescription(), searchQuery);
    }
}
