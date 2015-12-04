package org.xcolab.jspTags.discussion.wrappers;

import com.ext.portlet.discussions.DiscussionMessageFlagType;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.HtmlUtil;

import java.util.Date;

public class DiscussionMessageWrapper implements Comparable<DiscussionMessageWrapper> {
    private final DiscussionMessage wrapped;

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
        return HtmlUtil.filterAndFormatContent(wrapped.getBody());
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

    public CategoryWrapper getCategory() throws SystemException, PortalException {
        return new CategoryWrapper(DiscussionCategoryLocalServiceUtil.fetchDiscussionCategory(getCategoryGroupId()));
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
        final Date lastActivityDate = wrapped.getLastActivityDate();
        if (lastActivityDate == null) {
            return wrapped.getCreateDate();
        }
        return lastActivityDate;
    }

    public long getLastActivityAuthorId() {
        return wrapped.getLastActivityAuthorId();
    }

    public boolean isExpertReview() throws SystemException {
        return hasFlag(DiscussionMessageFlagType.EXPERT_REVIEW);
    }

    public int getSpamReportCount() throws SystemException {
        return SpamReportLocalServiceUtil.getByDiscussionMessageId(getMessageId()).size();
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

    @Override
    public int compareTo(DiscussionMessageWrapper o) {
        if (getThreadId() == 0 || o.getThreadId() == 0) {
            return getCreateDate().compareTo(o.getCreateDate());
        }
        return getLastActivityDate().compareTo(o.getLastActivityDate());
    }
}
