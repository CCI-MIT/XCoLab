package org.xcolab.activityEntry.discussion;

import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;

public abstract class DiscussionBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    private static final Log _log = LogFactoryUtil.getLog(DiscussionAddProposalCommentActivityEntry.class);

    protected DiscussionCategoryGroup categoryGroup;

    protected DiscussionCategory discussionCategory;

    protected DiscussionMessage discussion;

    public static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;
        try {
            DiscussionMessage comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(this.activityEntry.getClassPrimaryKey());
            discussion = DiscussionMessageLocalServiceUtil.getThread(comment);

            categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion);
            discussionCategory = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(comment.getCategoryId());
        }catch( SystemException | PortalException ignore){

        }
    }

    protected String getDiscussionMessageLink() {
        try {
            DiscussionMessage thread = discussion.getThreadId() > 0 ? DiscussionMessageLocalServiceUtil
                    .getThread(discussion) : discussion;

            String linkText;
            if (Validator.isNull(thread.getSubject())) {
                linkText = "Discussion in "
                        + categoryGroup.getDescription().replaceAll("\"", "'");
            } else {
                linkText = thread.getSubject();
            }

            return String.format(
                    HYPERLINK_FORMAT,
                    StringEscapeUtils.escapeHtml4("/web/guest/discussion/-/discussion/thread/" + thread.getThreadId()), linkText);

        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<message not found>";
    }

    protected String getDiscussionCategoryLink() {
        String linkUrl = "/web/guest/discussion/-/discussion/category/"+discussionCategory.getCategoryId();

        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml4(linkUrl), discussionCategory.getName());
    }

    protected String getCategoryGroupLink() {
        return String.format(HYPERLINK_FORMAT, StringEscapeUtils.escapeHtml4(categoryGroup.getUrl()), categoryGroup.getDescription());
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
    public Integer getPrimaryType() {
        return 3;
    }

}
