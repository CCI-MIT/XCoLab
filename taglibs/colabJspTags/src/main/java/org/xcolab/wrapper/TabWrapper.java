package org.xcolab.wrapper;

import com.ext.portlet.NoSuchContestDiscussionException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestDiscussion;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.ContestDiscussionLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.interfaces.TabContext;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.interfaces.TabPermissions;
import org.xcolab.utils.UrlBuilder;

import javax.portlet.PortletRequest;

public class TabWrapper {
    private TabEnum tab;
    private TabContext context;

    private final PortletRequest request;
    private final TabPermissions permissions;

    public TabWrapper(TabEnum tab, PortletRequest request, TabContext context) throws PortalException, SystemException{
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

    public String getElementType()  {
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

    public long getDiscussionId() throws SystemException, PortalException{
        Long discussionId = 0L;
        Contest contest = context.getContest(request);
        try {
            discussionId = ContestDiscussionLocalServiceUtil.
                    getDiscussionIdByContestIdAndTabName(contest.getContestPK(), getName());
        } catch (NoSuchContestDiscussionException e) {
            discussionId = createNewDiscussionIdByContestIdAndTabName(contest.getContestPK(), getName());
        }
        return discussionId;
    }

    private Long createNewDiscussionIdByContestIdAndTabName(Long contestId, String tabName)
            throws SystemException, NoSuchContestDiscussionException{

        DiscussionCategoryGroup newDiscussionCategoryGroup = DiscussionCategoryGroupLocalServiceUtil.
                createDiscussionCategoryGroup("Contest " + contestId + " " + tabName + " discussion");

        newDiscussionCategoryGroup.setUrl(UrlBuilder.getContestCreationTabCommentsUrl(contestId, tabName));
        DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(newDiscussionCategoryGroup);
        newDiscussionCategoryGroup.persist();

        ContestDiscussion newContestDiscussion = ContestDiscussionLocalServiceUtil.
                createContestDiscussion(newDiscussionCategoryGroup.getId());

        newContestDiscussion.setContestId(contestId);
        newContestDiscussion.setTab(tabName);
        newContestDiscussion.persist();

        return newContestDiscussion.getDiscussionId();
    }

}
