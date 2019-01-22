package org.xcolab.view.taglibs.xcolab.wrapper;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.view.taglibs.xcolab.interfaces.TabContext;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class TabWrapper implements Serializable {

    private TabEnum tab;
    private final TabContext context;

    private final HttpServletRequest request;
    private final TabPermissions permissions;

    public TabWrapper(TabEnum tab, HttpServletRequest request, TabContext context) {
        super();
        this.tab = tab;
        this.request = request;
        this.context = context;
        this.permissions = context.getPermissions(request);
    }

    public TabEnum getTab() {
        return tab;
    }

    public void setTab(TabEnum tab) {
        this.tab = tab;
    }

    public String getName() {
        return tab.getName();
    }

    public String getDisplayName() {
        return tab.getDisplayName();
    }

    public String getTabUrl() {
        return RequestUtil.getOriginalUri(request);
    }

    public String getElementType() {
        return tab.getElementType();
    }

    public boolean getIsDefault() {
        return tab.getIsDefault();
    }

    public boolean getCanEdit() {
        return tab.getCanEdit(permissions, context, request);
    }

    public boolean getCanView() {
        return tab.getCanView(permissions, context, request);
    }

    public boolean getCanAddComment() {
        return tab.getCanAddComment(permissions, context, request);
    }

    public int getActivityCount() {
        return tab.getActivityCount(context, request);
    }

    public long getDiscussionId() {
        ContestWrapper contest = context.getContest(request);
        return StaticContestContext.getContestClient()
                .getContestDiscussion(contest.getId(), getName()).getId();
    }
}
