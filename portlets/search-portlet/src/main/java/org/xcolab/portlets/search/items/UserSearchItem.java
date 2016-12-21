package org.xcolab.portlets.search.items;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.search.pojo.SearchPojo;

public class UserSearchItem extends AbstractSearchItem {

    private final static String[] TITLE_FIELDS = {"screenName"};
    private final static String[] CONTENT_FIELDS = {"firstName", "lastName"};

    private SearchPojo searchPojo;
    private String searchQuery;

    private Member member;

    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        this.searchPojo = pojo;
        this.searchQuery = searchQuery;
        try {
            member = MembersClient.getMember(searchPojo.getClassPrimaryKey());
        } catch (MemberNotFoundException ignored) {

        }
    }

    @Override
    public String getPrintName() {
        return "Members";
    }

    @Override
    public String getTitle() {
        return highlight(member.getScreenName(),searchQuery);
    }

    @Override
    public String getLinkUrl() {
        return "/web/guest/member/-/member/userId/" + member.getId_();
    }

    @Override
    public String getContent() {
        String content = highlight(member.getFirstName() + " " + member.getLastName() + " " + member.getShortBio(),searchQuery);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }


}
