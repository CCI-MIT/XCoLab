package org.xcolab.portlets.search.items;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.search.pojo.SearchPojo;

public class ContestSearchItem extends AbstractSearchItem {
    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content"};

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
        final ContestType contestType = ContestClientUtil
                .getContestType(contestTypeId);
        return contestType.getContestNamePlural();
    }

    @Override
    public String getTitle(){
        return highlight(contest.getContestShortName(),searchQuery);
    }

    @Override
    public String getLinkUrl() {
            return contest.getContestLinkUrl();
    }

    @Override
    public String getContent() {
        String content = highlight(contest.getContestDescription(),searchQuery);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }

}
