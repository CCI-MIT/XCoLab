package org.xcolab.activityEntry.discussion;

import com.ext.portlet.community.CommunityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xcolab.activityEntry.ActivityEntryType;
import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;

public abstract class DiscussionBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    private static final Log _log = LogFactoryUtil.getLog(DiscussionAddProposalCommentActivityEntry.class);

    protected Comment comment;

    protected CommentThread thread;

    protected Category category;

    public static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;
        try {
            comment = CommentClient.getComment(activityEntry.getClassPrimaryKey());
            thread = CommentClient.getThread(comment.getThreadId());
            category = CommentClient.getCategory(thread.getCategoryId());
        }catch(CommentNotFoundException | ThreadNotFoundException | CategoryNotFoundException ignored){
        }
    }

    protected String getThreadLink(){
        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml4(thread.getLinkUrl()), thread.getTitle());
    }

    protected String getCategoryLink(){
        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml4(category.getLinkUrl()), category.getName());
    }
    protected String getUserLink() {
        try {
            return CommunityUtil.generateUserURL(this.activityEntry.getMemberId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }

    @Override
    public Long getPrimaryType() {
        return ActivityEntryType.DISCUSSION.getPrimaryTypeId();
    }


    public enum DiscussionActivitySubType{
        DISCUSSION_PROPOSAL_COMMENT(1l),
        DISCUSSION_CATEGORY_ADDED(2l),
        DISCUSSION_ADDED(3l),
        DISCUSSION_FORUM_COMMENT(4l),
        DISCUSSION_ADDED_COMMENT(5l);

        private final Long secondaryTypeId;
        DiscussionActivitySubType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId(){
            return this.secondaryTypeId;
        }
    }
}
