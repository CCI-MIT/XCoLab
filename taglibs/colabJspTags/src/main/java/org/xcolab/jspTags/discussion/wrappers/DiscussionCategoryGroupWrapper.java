package org.xcolab.jspTags.discussion.wrappers;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionCategoryWrapper;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.jspTags.discussion.ThreadSortColumn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DiscussionCategoryGroupWrapper {
    private final DiscussionCategoryGroup discussionCategoryGroup;
    private List<CategoryWrapper> categories;

    public DiscussionCategoryGroupWrapper(DiscussionCategoryGroup discussionCategoryGroup) {
        this.discussionCategoryGroup = discussionCategoryGroup;
    }
    
    public List<DiscussionMessageWrapper> getComments() throws SystemException, NoSuchDiscussionMessageException {
        List<DiscussionMessageWrapper> comments = new ArrayList<>();
        long commentsThread = discussionCategoryGroup.getCommentsThread();
        if (commentsThread > 0) {
            DiscussionMessage thread = DiscussionMessageLocalServiceUtil.getMessageByMessageId(commentsThread);
            for (DiscussionMessage msg: DiscussionMessageLocalServiceUtil.getThreadMessages(commentsThread)) {
                comments.add(new DiscussionMessageWrapper(msg));
            }
            if (thread != null && thread.getDeleted() == null) {
                comments.add(new DiscussionMessageWrapper(thread));
            }
        }
        Collections.reverse(comments);
        return comments;
    }
    
    public long getCommentsCount() throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(discussionCategoryGroup);
    }
    
    public String getDescription() {
        return discussionCategoryGroup.getDescription().replaceAll("\"", "'"); //remove " to ensure addthis works
    }
    
    public String getDiscussionUrl() {
        return discussionCategoryGroup.getUrl();
    }
    
    public long getId() {
        return discussionCategoryGroup.getId();
    }
    
    public long getDiscussionId() {
        return discussionCategoryGroup.getId();
    }

    public List<CategoryWrapper> getCategories() throws PortalException, SystemException {
        return getCategories(ThreadSortColumn.DATE, true);
    }

    public List<CategoryWrapper> getCategories(ThreadSortColumn sortColumn, boolean sortAscending) throws SystemException, PortalException {
        if (categories == null && discussionCategoryGroup != null) {
            categories = new ArrayList<>();
            for (DiscussionCategory category : DiscussionCategoryGroupLocalServiceUtil.getCategories(discussionCategoryGroup)) {
                CategoryWrapper catWrapper = new CategoryWrapper(category, sortColumn, sortAscending);
                categories.add(catWrapper);
            }
        }
        return categories;
    }
}
