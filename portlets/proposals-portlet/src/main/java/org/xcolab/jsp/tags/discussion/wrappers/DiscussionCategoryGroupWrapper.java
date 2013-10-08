package org.xcolab.jsp.tags.discussion.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class DiscussionCategoryGroupWrapper {
    private DiscussionCategoryGroup discussionCategoryGroup;

    public DiscussionCategoryGroupWrapper(DiscussionCategoryGroup discussionCategoryGroup) {
        this.discussionCategoryGroup = discussionCategoryGroup;
    }
    
    public List<DiscussionMessageWrapper> getComments() throws PortalException, SystemException {
        List<DiscussionMessageWrapper> comments = new ArrayList<DiscussionMessageWrapper>();
        long commentsThread = discussionCategoryGroup.getCommentsThread();
        if (commentsThread > 0) {
            for (DiscussionMessage msg: DiscussionMessageLocalServiceUtil.getThreadMessages(commentsThread)) {
                comments.add(new DiscussionMessageWrapper(msg));
            }
            comments.add(new DiscussionMessageWrapper(DiscussionMessageLocalServiceUtil.getMessageByMessageId(commentsThread)));
        }
        Collections.reverse(comments);
        return comments;
    }
    
    public long getCommentsCount() throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(discussionCategoryGroup);
    }
    
    public String getDescription() {
        return discussionCategoryGroup.getDescription();
    }
    
    public String getDiscussionUrl() {
        return discussionCategoryGroup.getUrl();
    }
    
    public long getDiscussionId() {
        return discussionCategoryGroup.getId();
    }
}
