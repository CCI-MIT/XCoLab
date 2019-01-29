package org.xcolab.view.pages.search.items;

import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public class UserSearchItem extends AbstractSearchItem {

    private ISearchPojo searchPojo;
    private String searchQuery;

    private UserWrapper member;

    @Override
    public void init(ISearchPojo pojo, String searchQuery) {
        this.searchPojo = pojo;
        this.searchQuery = searchQuery;
        try {
            member = StaticUserContext.getUserClient().getMember(searchPojo.getClassPrimaryKey());
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
