package org.xcolab.view.pages.search.items;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.search.pojo.SearchPojo;

public class ContestSearchItem extends AbstractSearchItem {

    private SearchPojo searchPojo;
    private String searchQuery;

    private Contest contest;

    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        this.searchPojo = pojo;
        this.searchQuery = searchQuery;
        contest = ContestClientUtil.getContest(searchPojo.getClassPrimaryKey());
    }

    @Override
    public String getPrintName() {
        final long contestTypeId =
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        final ContestType contestType = ContestTypeClient
                .getContestType(contestTypeId);
        return contestType.getContestNamePlural();
    }

    @Override
    public String getTitle(){
        return highlight(contest.getContestShortNameWithEndYear(), searchQuery);
    }

    @Override
    public String getLinkUrl() {
            return contest.getContestLinkUrl();
    }


    @Override
    public String getContent() {
        return super.getContent(contest.getContestDescription(), searchQuery);
    }

}
