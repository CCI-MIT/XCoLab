package org.xcolab.view.pages.search.items;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.search.pojo.SearchPojo;

public class UserSearchItem extends AbstractSearchItem {

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
        return highlight(member.getDisplayName(), searchQuery);
    }

    @Override
    public String getLinkUrl() {
        return "/members/profile/" + member.getId();
    }


    @Override
    public String getContent() {
        return getContent(
                member.getFirstName() + " " + member.getLastName() + " " + member.getShortBio(),
                searchQuery);
    }

}
