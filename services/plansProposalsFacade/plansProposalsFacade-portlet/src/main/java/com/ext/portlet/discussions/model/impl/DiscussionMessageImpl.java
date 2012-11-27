package com.ext.portlet.discussions.model.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.model.DiscussionMessageFlag;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageFlagLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the DiscussionMessage service. Represents a row in the &quot;Discussions_DiscussionMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.discussions.model.DiscussionMessage} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class DiscussionMessageImpl extends DiscussionMessageBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a discussion message model instance should use the {@link com.ext.portlet.discussions.model.DiscussionMessage} interface instead.
     */

    private final static Log _log = LogFactoryUtil.getLog(DiscussionMessageImpl.class);
    
    public DiscussionMessageImpl() {
    }
    
    public List<DiscussionMessage> getThreadMessages() throws SystemException {
        if (this.getThreadId() == null) {
            // threadId is null so we have first message (that represents the thread) 
            // use messageId instead of threadId
            return DiscussionMessageLocalServiceUtil.getThreadMessages(this.getMessageId());
        }
        return DiscussionMessageLocalServiceUtil.getThreadMessages(this.getThreadId());
        
    }
    
    public int getThreadMessagesCount() throws SystemException {
        if (this.getThreadId() == null) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(getMessageId());
        }
        return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(getThreadId());
        
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            DiscussionMessageLocalServiceUtil.addDiscussionMessage(this);
        }
        else {
            DiscussionMessageLocalServiceUtil.updateDiscussionMessage(this);
        }
    }
    
    public DiscussionMessage addThreadMessage(String subject, String body, User author) throws SystemException, NoSuchDiscussionCategoryException {
        Long threadId = getThreadId();
        if (threadId == null) {
            // threadId is null so we have first message (that represents the thread) 
            // use messageId instead of threadId
            threadId = getMessageId();
        }
        DiscussionMessage msg = DiscussionMessageLocalServiceUtil.addMessage(this.getCategoryGroupId(), this.getCategoryId(), threadId, subject, body, author);
        
        this.setResponsesCount(this.getResponsesCount() + 1);
        this.setLastActivityAuthorId(msg.getAuthorId());
        this.setLastActivityDate(msg.getCreateDate());
        this.store();
        
        // set last activity info in category
        if (getCategoryId() != null && getCategoryId() > 0L) {
            DiscussionCategory category = getCategory();
        
            category.setLastActivityAuthorId(msg.getAuthorId());
            category.setLastActivityDate(msg.getCreateDate());
            category.store();
        }
        
        
        return msg;
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getAuthorId());
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        Long lastActAuthorId = getLastActivityAuthorId();
        if (lastActAuthorId == null) {
            return getAuthor();
        }
        return UserLocalServiceUtil.getUser(lastActAuthorId);
    }
    
    public void delete() throws SystemException, PortalException {
        setDeleted(new Date());
        store();
        /*
        try {
            Indexer.deleteEntry(10112L, getMessageId());
            // if this is a thread, then remove all sub messages from search cache
            if (getThreadId() == null) {
                for (DiscussionMessage msg: DiscussionMessageLocalServiceUtil.getThreadMessages(getMessageId())) {
                    Indexer.deleteEntry(10112L, msg.getMessageId());
                }
            }
            if (getCategoryGroup().getCommentsThread() == getMessageId()) {
                // this is a comment thread, remove it from discussion
                DiscussionCategoryGroup discussion = getCategoryGroup();
                discussion.setCommentsThread(null);
                discussion.store();
            }
        } catch (SearchException e) {
            _log.warn("Can't remove message with id: " + getMessageId() + " from search cache", e);
        }
        */
    }
    
    public void update(String subject, String body) throws SystemException {
        setSubject(subject);
        setBody(body);
        store();
        /*
        try {
            Indexer.updateEntry(10112L, this);
        } catch (SearchException e) {
            _log.error("Can't update message with id: " + getMessageId() + " in search cache", e);
        }
        */
    }
    
    public DiscussionCategory getCategory() throws NoSuchDiscussionCategoryException, SystemException {
        return DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(getCategoryId());
    }
    
    public DiscussionCategoryGroup getCategoryGroup() throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(getCategoryGroupId());
    }
    
    public DiscussionMessage getThread() throws NoSuchDiscussionMessageException, SystemException {
        if (getThreadId() != null) {
            // this is a comment of a thread
            return DiscussionMessageLocalServiceUtil.getThreadByThreadId(getThreadId());
        }
        // this is a thread itself
        return this;
    }
    
    public List<DiscussionMessageFlag> getFlags() throws SystemException {
        return DiscussionMessageFlagLocalServiceUtil.findMessageFlags(getMessageId());
    }
    
    public void addFlag(String flagType, String data, User user) throws SystemException  {
        DiscussionMessageFlagLocalServiceUtil.createFlag(getMessageId(), flagType, data, user.getUserId());
    }
    
    public void removeFlag(String flagType) throws SystemException {
        DiscussionMessageFlag flag = findFlag(flagType);
        if (flag != null) {
            DiscussionMessageFlagLocalServiceUtil.deleteDiscussionMessageFlag(flag);
        }
    }
    
    private DiscussionMessageFlag findFlag(String flagType) throws SystemException {
        for (DiscussionMessageFlag flag: getFlags()) {
            if (flag.getFlagType().equals(flagType)) {
                return flag;
            }
        }
        return null;
    }
}
