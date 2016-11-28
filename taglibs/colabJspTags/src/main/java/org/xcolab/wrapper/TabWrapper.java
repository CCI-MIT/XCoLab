package org.xcolab.wrapper;

import com.ext.portlet.NoSuchContestDiscussionException;
import com.ext.portlet.model.ContestDiscussion;
import com.ext.portlet.service.ContestDiscussionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.interfaces.TabContext;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.interfaces.TabPermissions;
import org.xcolab.util.exceptions.DatabaseAccessException;

import java.io.Serializable;

import javax.portlet.PortletRequest;

public class TabWrapper implements Serializable {
    private TabEnum tab;
    private final TabContext context;

    private final PortletRequest request;
    private final TabPermissions permissions;

    public TabWrapper(TabEnum tab, PortletRequest request, TabContext context) {
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

    public String getTabUrl()  {
        return PortalUtil.getCurrentURL(this.request);
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

    public long getDiscussionId() {
        Long discussionId;
        Contest contest = context.getContest(request);
        try {
            discussionId = ContestDiscussionLocalServiceUtil.
                    getDiscussionIdByContestIdAndTabName(contest.getContestPK(), getName());
        } catch (NoSuchContestDiscussionException e) {
            discussionId = createNewDiscussionIdByContestIdAndTabName(contest, getName());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
        return discussionId;
    }

    private Long createNewDiscussionIdByContestIdAndTabName(Contest contest, String tabName) {
        try {
            long contestId = contest.getContestPK();

            CommentThread thread = new CommentThread();
            thread.setAuthorId(contest.getAuthorId());
            thread.setTitle(contest.getContestType().getContestName()
                            + " " + contestId + " " + tabName + " discussion");
            long discussionId = ThreadClientUtil.createThread(thread).getThreadId();

            ContestDiscussion newContestDiscussion = ContestDiscussionLocalServiceUtil.
                    createContestDiscussion(discussionId);

            newContestDiscussion.setContestId(contestId);
            newContestDiscussion.setTab(tabName);
            newContestDiscussion.persist();

            return newContestDiscussion.getDiscussionId();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

}
