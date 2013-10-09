package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ext.portlet.NoSuchDiscussionCategoryException;
import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.NoSuchDiscussionMessageFlagException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.impl.DiscussionMessageLocalServiceImpl;
import com.ext.portlet.service.base.DiscussionMessageLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the discussion message local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.DiscussionMessageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.DiscussionMessageLocalServiceBaseImpl
 * @see com.ext.portlet.service.DiscussionMessageLocalServiceUtil
 */
public class DiscussionMessageLocalServiceImpl
    extends DiscussionMessageLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.DiscussionMessageLocalServiceUtil} to access the discussion message local service.
     */

    private final static Log _log = LogFactoryUtil.getLog(DiscussionMessageLocalServiceImpl.class);
       
       public List<DiscussionMessage> getThreadsByCategory(long categoryId) throws SystemException {
           return discussionMessagePersistence.findByCategoryIdThreadId(categoryId, 0);
       }
       
       public List<DiscussionMessage> getThreadMessages(long threadId) throws SystemException {
           return discussionMessagePersistence.findByThreadId(threadId);
       }
       
       public int getThreadMessagesCount(long threadId) throws SystemException {
           return discussionMessagePersistence.countByThreadId(threadId);
       }
       
       public DiscussionMessage getThreadByThreadId(long threadId) throws NoSuchDiscussionMessageException, SystemException {
           return discussionMessagePersistence.findBySingleThreadId(threadId);
       }
       
       public DiscussionMessage addThread(long categoryGroupId, long categoryId, String subject, String body, User author) throws SystemException  {
           return addMessage(categoryGroupId, categoryId, 0, subject, body, author);
       }
       
       public DiscussionMessage addMessage(long categoryGroupId, long categoryId, long threadId, String subject, String body, User author) throws SystemException {
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
           
           store(message);
           /*
           try {
               Indexer.addEntry(10112L, message);
           } catch (SearchException e) {
               _log.error("Can't update message with id: " + message.getMessageId() + " in search cache", e);
           }*/
           
           return message;
       }
       
       public List<DiscussionMessage> search(String query, long categoryGroupId) throws SystemException {
           // preprocess query
           query = "%" + query.replaceAll("\\s", "%") + "%";
           Set<DiscussionMessage> messages = new HashSet<DiscussionMessage>();
           messages.addAll(discussionMessagePersistence.findByBodyLike(query, categoryGroupId));
           messages.addAll(discussionMessagePersistence.findBySubjectLike(query, categoryGroupId));
           
           return new ArrayList<DiscussionMessage>(messages);
       }
       
       public DiscussionMessage getMessageByMessageId(long messageId) throws NoSuchDiscussionMessageException, SystemException {
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
       
       
       
       public List<DiscussionMessage> getThreadMessages(DiscussionMessage dMessage) throws SystemException {
           if (dMessage.getThreadId() <= 0) {
               // threadId is null so we have first message (that represents the thread) 
               // use messageId instead of threadId
               return DiscussionMessageLocalServiceUtil.getThreadMessages(dMessage.getMessageId());
           }
           return DiscussionMessageLocalServiceUtil.getThreadMessages(dMessage.getThreadId());
           
       }
       
       public int getThreadMessagesCount(DiscussionMessage dMessage) throws SystemException {
           if (dMessage.getThreadId() <= 0) {
               return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(dMessage.getMessageId());
           }
           return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(dMessage.getThreadId());
           
       }
       
       public void store(DiscussionMessage dMessage) throws SystemException {
           if (dMessage.isNew()) {
               DiscussionMessageLocalServiceUtil.addDiscussionMessage(dMessage);
           }
           else {
               DiscussionMessageLocalServiceUtil.updateDiscussionMessage(dMessage);
           }
           
           Indexer indexer = IndexerRegistryUtil.getIndexer(DiscussionMessage.class);

           try {
               indexer.reindex(dMessage.getMessageId());
           } catch (Exception e) {
               _log.error("Can't reindex message " + dMessage.getMessageId(), e);
           }
       }
       
       public DiscussionMessage addThreadMessage(DiscussionMessage dMessage, String subject, String body, User author) throws SystemException, NoSuchDiscussionCategoryException {
           long threadId = dMessage.getThreadId();
           if (threadId <= 0L) {
               // threadId is null so we have first message (that represents the thread) 
               // use messageId instead of threadId
               threadId = dMessage.getMessageId();
           }
           DiscussionMessage msg = DiscussionMessageLocalServiceUtil.addMessage(dMessage.getCategoryGroupId(), dMessage.getCategoryId(), threadId, subject, body, author);
           
           dMessage.setResponsesCount(dMessage.getResponsesCount() + 1);
           dMessage.setLastActivityAuthorId(msg.getAuthorId());
           dMessage.setLastActivityDate(msg.getCreateDate());
           store(dMessage);
           
           // set last activity info in category
           if (dMessage.getCategoryId() > 0) {
               DiscussionCategory category = getCategory(dMessage);
           
               category.setLastActivityAuthorId(msg.getAuthorId());
               category.setLastActivityDate(msg.getCreateDate());
               DiscussionCategoryLocalServiceUtil.store(category);
           }
           
           
           return msg;
       }
       
       public User getAuthor(DiscussionMessage dMessage) throws PortalException, SystemException {
           return UserLocalServiceUtil.getUser(dMessage.getAuthorId());
       }
       
       public User getLastActivityAuthor(DiscussionMessage dMessage) throws PortalException, SystemException {
           Long lastActAuthorId = dMessage.getLastActivityAuthorId();
           if (lastActAuthorId == null || lastActAuthorId <= 0) {
               return getAuthor(dMessage);
           }
           return UserLocalServiceUtil.getUser(lastActAuthorId);
       }
       
       public void delete(DiscussionMessage dMessage) throws SystemException, PortalException {
           dMessage.setDeleted(new Date());
           store(dMessage);
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
       
       public void update(DiscussionMessage dMessage, String subject, String body) throws SystemException {
           dMessage.setSubject(subject);
           dMessage.setBody(body);
           store(dMessage);
           /*
           try {
               Indexer.updateEntry(10112L, this);
           } catch (SearchException e) {
               _log.error("Can't update message with id: " + getMessageId() + " in search cache", e);
           }
           */
       }
       
       public DiscussionCategory getCategory(DiscussionMessage dMessage) throws NoSuchDiscussionCategoryException, SystemException {
           return DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(dMessage.getCategoryId());
       }
       
       public DiscussionCategoryGroup getCategoryGroup(DiscussionMessage dMessage) throws PortalException, SystemException {
           return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(dMessage.getCategoryGroupId());
       }
       
       public DiscussionMessage getThread(DiscussionMessage dMessage) throws NoSuchDiscussionMessageException, SystemException {
           if (dMessage.getThreadId() > 0) {
               // this is a comment of a thread
               return DiscussionMessageLocalServiceUtil.getThreadByThreadId(dMessage.getThreadId());
           }
           // this is a thread itself
           return dMessage;
       }
       
       public List<DiscussionMessageFlag> getFlags(DiscussionMessage dMessage) throws SystemException {
           return DiscussionMessageFlagLocalServiceUtil.findMessageFlags(dMessage.getMessageId());
       }
       
       public void addFlag(DiscussionMessage dMessage, String flagType, String data, User user) throws SystemException  {
           DiscussionMessageFlagLocalServiceUtil.createFlag(dMessage.getMessageId(), flagType, data, user.getUserId());
       }
       
       public void removeFlag(DiscussionMessage dMessage, String flagType) throws SystemException {
           DiscussionMessageFlag flag = findFlag(dMessage, flagType);
           if (flag != null) {
               DiscussionMessageFlagLocalServiceUtil.deleteDiscussionMessageFlag(flag);
           }
       }
       
       public boolean hasFlag(long messageId, String flag) throws SystemException {
           try {
               discussionMessageFlagPersistence.findByMessageIdFlagType(messageId, flag);
               return true;
           }
           catch (NoSuchDiscussionMessageFlagException e) {
               return false;
           }
           
       }
       
       private DiscussionMessageFlag findFlag(DiscussionMessage dMessage, String flagType) throws SystemException {
           try {
               return discussionMessageFlagPersistence.findByMessageIdFlagType(dMessage.getMessageId(), flagType);
           }
           catch (NoSuchDiscussionMessageFlagException e) {
               return null;
           }
           
       }
}
