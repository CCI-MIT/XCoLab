package org.xcolab.jspTags.discussion.wrappers;

import com.ext.portlet.discussions.DiscussionMessageFlagType;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.commons.utils.ContentFilterHelper;

import java.util.Date;

public class DiscussionMessageWrapper {
    private DiscussionMessage wrapped;

    public DiscussionMessageWrapper(DiscussionMessage msg) {
        this.wrapped = msg;
    }

    public long getMessageId() {
        return wrapped.getMessageId();
    }

    public String getSubject() {
        return wrapped.getSubject();
    }

    public String getBody() {
        return ContentFilterHelper.filterContent(wrapped.getBody());
    }

    public long getThreadId() {
        return wrapped.getThreadId();
    }

    public long getCategoryId() {
        return wrapped.getCategoryId();
    }

    public long getCategoryGroupId() {
        return wrapped.getCategoryGroupId();

    }

    public long getAuthorId() {
        return wrapped.getAuthorId();
    }

    public Date getCreateDate() {
        return wrapped.getCreateDate();
    }

    public Date getDeleted() {
        return wrapped.getDeleted();
    }

    public int getResponsesCount() {
        return wrapped.getResponsesCount();
    }

    public Date getLastActivityDate() {
        return wrapped.getLastActivityDate();
    }

    public long getLastActivityAuthorId() {
        return wrapped.getLastActivityAuthorId();
    }

    public boolean isExpertReview() throws SystemException {
        return hasFlag(DiscussionMessageFlagType.EXPERT_REVIEW);
    }
    
    public boolean hasFlag(DiscussionMessageFlagType flagType) throws SystemException {
        return hasFlag(flagType.name());
    }
    
    public boolean hasFlag(String flagType) throws SystemException {
        if (wrapped != null && wrapped.getMessageId() > 0) {
            for (DiscussionMessageFlag flag: DiscussionMessageLocalServiceUtil.getFlags(wrapped)) {
                if (flag.getFlagType().equals(flagType)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(wrapped.getAuthorId());
    }
}
