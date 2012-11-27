package com.ext.portlet.discussions.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.discussions.service.base.DiscussionMessageLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;

/**
 * The implementation of the discussion message local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.discussions.service.DiscussionMessageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.discussions.service.base.DiscussionMessageLocalServiceBaseImpl
 * @see com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil
 */
public class DiscussionMessageLocalServiceImpl
    extends DiscussionMessageLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil} to access the discussion message local service.
     */
    
 private final static Log _log = LogFactoryUtil.getLog(DiscussionMessageLocalServiceImpl.class);
    
    public List<DiscussionMessage> getThreadsByCategory(Long categoryId) throws SystemException {
        return discussionMessagePersistence.findByCategoryIdThreadId(categoryId, null);
    }
    
    public List<DiscussionMessage> getThreadMessages(Long threadId) throws SystemException {
        return discussionMessagePersistence.findByThreadId(threadId);
    }
    
    public int getThreadMessagesCount(Long threadId) throws SystemException {
        return discussionMessagePersistence.countByThreadId(threadId);
    }
    
    public DiscussionMessage getThreadByThreadId(Long threadId) throws NoSuchDiscussionMessageException, SystemException {
        return discussionMessagePersistence.findBySingleThreadId(threadId);
    }
    
    public DiscussionMessage addThread(Long categoryGroupId, Long categoryId, String subject, String body, User author) throws SystemException  {
        return addMessage(categoryGroupId, categoryId, null, subject, body, author);
    }
    
    public DiscussionMessage addMessage(Long categoryGroupId, Long categoryId, Long threadId, String subject, String body, User author) throws SystemException {
        Long id = CounterLocalServiceUtil.increment(DiscussionMessage.class.getName());
        Long messageId = CounterLocalServiceUtil.increment(DiscussionMessage.class.getName() + ".discussion");
        
        DiscussionMessage message = DiscussionMessageLocalServiceUtil.createDiscussionMessage(id);
        
        message.setMessageId(messageId);
        message.setCategoryId(categoryId);
        message.setSubject(subject);
        message.setBody(body);
        message.setAuthorId(author.getUserId());
        message.setCreateDate(new Date());
        message.setThreadId(threadId);
        message.setResponsesCount(0);
        message.setCategoryGroupId(categoryGroupId);
        
        message.store();
        /*
        try {
            Indexer.addEntry(10112L, message);
        } catch (SearchException e) {
            _log.error("Can't update message with id: " + message.getMessageId() + " in search cache", e);
        }*/
        
        return message;
    }
    
    public List<DiscussionMessage> search(String query, Long categoryGroupId) throws SystemException {
        // preprocess query
        query = "%" + query.replaceAll("\\s", "%") + "%";
        Set<DiscussionMessage> messages = new HashSet<DiscussionMessage>();
        messages.addAll(discussionMessagePersistence.findByBodyLike(query, categoryGroupId));
        messages.addAll(discussionMessagePersistence.findBySubjectLike(query, categoryGroupId));
        
        return new ArrayList<DiscussionMessage>(messages);
    }
    
    public DiscussionMessage getMessageByMessageId(Long messageId) throws NoSuchDiscussionMessageException, SystemException {
        return discussionMessagePersistence.findByMessageId(messageId);
    }
    
    private final static long defaultCompanyId = 10112L;
    public void reIndex() throws SystemException {
        // reindex concrete plan
        for (DiscussionMessage message : DiscussionMessageLocalServiceUtil.getDiscussionMessages(0, Integer.MAX_VALUE)) {
            /*try {
                if (message.getDeleted() == null) {
                    //Indexer.updateEntry(defaultCompanyId, message);
                }
                else {
                    //Indexer.deleteEntry(defaultCompanyId, message.getMessageId());
                }
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing message with id: " + message.getMessageId(), e);
            }*/
        }
    }

    public void reIndex(long messageId) throws SystemException {
        // reindex concrete plan
        /*
        try {
            DiscussionMessage message = getMessageByMessageId(messageId);
            if (message.getDeleted() != null) {
                return;
            }
            //Indexer.updateEntry(defaultCompanyId, message);
        } catch (NoSuchDiscussionMessageException e) {
            _log.error("An exception has been thrown when reindexing message with id: " + messageId, e);
        } catch (SearchException e) {
            _log.error("An exception has been thrown when reindexing message with id: " + messageId, e);
        }
        */
    }
}
