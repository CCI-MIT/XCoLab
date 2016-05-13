package com.ext.portlet.service.impl;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.NoSuchDiscussionCategoryException;
import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.NoSuchDiscussionMessageFlagException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.base.DiscussionMessageLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
       
       @Override
       public List<DiscussionMessage> getThreadsByCategory(long categoryId) throws SystemException {
           return discussionMessagePersistence.findByCategoryIdThreadId(categoryId, 0);
       }
       
       @Override
       public List<DiscussionMessage> getThreadMessages(long threadId) throws SystemException {
           return discussionMessagePersistence.findByThreadId(threadId);
       }
       
       @Override
       public int getThreadMessagesCount(long threadId) throws SystemException {
           return discussionMessagePersistence.countByThreadId(threadId);
       }
       
       @Override
       public DiscussionMessage getThreadByThreadId(long threadId) throws NoSuchDiscussionMessageException, SystemException {
           return discussionMessagePersistence.findBySingleThreadId(threadId);
       }
       
       @Override
       public DiscussionMessage addThread(long categoryGroupId, long categoryId, String subject, String body, User author) throws SystemException  {
           return addMessage(categoryGroupId, categoryId, 0, subject, body, author);
       }
       
       @Override
       public DiscussionMessage addMessage(long categoryGroupId, long categoryId, long threadId, String subject, String body, User author) throws SystemException {
           Long id = counterLocalService.increment(DiscussionMessage.class.getName());
           Long messageId = counterLocalService.increment(DiscussionMessage.class.getName() + ".discussion");
           
           DiscussionMessage message = createDiscussionMessage(id);
           
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

           return message;
       }
       
       @Override
       public List<DiscussionMessage> search(String query, long categoryGroupId) throws SystemException {
           // preprocess query
           query = "%" + query.replaceAll("\\s", "%") + "%";
           Set<DiscussionMessage> messages = new HashSet<>();
           messages.addAll(discussionMessagePersistence.findByBodyLike(query, categoryGroupId));
           messages.addAll(discussionMessagePersistence.findBySubjectLike(query, categoryGroupId));
           
           return new ArrayList<>(messages);
       }
       
       @Override
       public DiscussionMessage getMessageByMessageId(long messageId) throws NoSuchDiscussionMessageException, SystemException {
           return discussionMessagePersistence.findByMessageId(messageId);
       }

       @Override
       public void reIndex() throws SystemException {
           // reindex concrete plan
//           for (DiscussionMessage message : DiscussionMessageLocalServiceUtil.getDiscussionMessages(0, Integer.MAX_VALUE)) {
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
//           }
       }

       @Override
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
       
       
       
       @Override
       public List<DiscussionMessage> getThreadMessages(DiscussionMessage dMessage) throws SystemException {
           if (dMessage.getThreadId() <= 0) {
               // threadId is null so we have first message (that represents the thread) 
               // use messageId instead of threadId
               return DiscussionMessageLocalServiceUtil.getThreadMessages(dMessage.getMessageId());
           }
           return DiscussionMessageLocalServiceUtil.getThreadMessages(dMessage.getThreadId());
           
       }
       
       @Override
       public int getThreadMessagesCount(DiscussionMessage dMessage) throws SystemException {
           if (dMessage.getThreadId() <= 0) {
               return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(dMessage.getMessageId());
           }
           return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(dMessage.getThreadId());
           
       }
       
       @Override
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
           } catch (SearchException e) {
               _log.error("Can't reindex message " + dMessage.getMessageId(), e);
           }
       }
       
       @Override
       public DiscussionMessage addThreadMessage(DiscussionMessage thread, String subject, String body, User author) throws SystemException, NoSuchDiscussionCategoryException {
           long threadId = thread.getThreadId();
           if (threadId <= 0L) {
               // threadId is null so we have first message (that represents the thread) 
               // use messageId instead of threadId
               threadId = thread.getMessageId();
           }
           DiscussionMessage msg = addMessage(thread.getCategoryGroupId(), thread.getCategoryId(), threadId, subject, body, author);
           
           thread.setResponsesCount(thread.getResponsesCount() + 1);
           thread.setLastActivityAuthorId(msg.getAuthorId());
           thread.setLastActivityDate(msg.getCreateDate());
           store(thread);
           
           // set last activity info in category
           if (thread.getCategoryId() > 0) {
               DiscussionCategory category = getCategory(thread);
           
               category.setLastActivityAuthorId(msg.getAuthorId());
               category.setLastActivityDate(msg.getCreateDate());
               DiscussionCategoryLocalServiceUtil.store(category);
           }

           return msg;
       }
       
       @Override
       public User getAuthor(DiscussionMessage dMessage) throws PortalException, SystemException {
           return UserLocalServiceUtil.getUser(dMessage.getAuthorId());
       }
       
       @Override
       public User getLastActivityAuthor(DiscussionMessage dMessage) throws PortalException, SystemException {
           Long lastActAuthorId = dMessage.getLastActivityAuthorId();
           if (lastActAuthorId <= 0) {
               return getAuthor(dMessage);
           }
           return UserLocalServiceUtil.getUser(lastActAuthorId);
       }
       
       @Override
       public void delete(DiscussionMessage dMessage) throws SystemException, PortalException {
           dMessage.setDeleted(new Date());
           store(dMessage);
           String activityExtraData =ActivityUtil.getExtraDataForIds(dMessage.getCategoryId(), 
        		   dMessage.getThreadId() > 0 ? dMessage.getThreadId() : dMessage.getMessageId(),
        		   dMessage.getMessageId());
           
           // remove social activity for deleted message
           for (SocialActivity sa: SocialActivityLocalServiceUtil.getActivities(DiscussionCategoryGroup.class.getName(), 0, Integer.MAX_VALUE)) {
        	   if (activityExtraData.equals(sa.getExtraData())) {
        		   SocialActivityLocalServiceUtil.deleteActivity(sa);
        	   }
           }
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
       
       @Override
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
       
       @Override
       public DiscussionCategory getCategory(DiscussionMessage dMessage) throws NoSuchDiscussionCategoryException, SystemException {
           return DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(dMessage.getCategoryId());
       }
       
       @Override
       public DiscussionCategoryGroup getCategoryGroup(DiscussionMessage dMessage) throws PortalException, SystemException {
           return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(dMessage.getCategoryGroupId());
       }
       
       @Override
       public DiscussionMessage getThread(DiscussionMessage dMessage) throws NoSuchDiscussionMessageException, SystemException {
           if (dMessage.getThreadId() > 0) {
               // this is a comment of a thread
               return DiscussionMessageLocalServiceUtil.getThreadByThreadId(dMessage.getThreadId());
           }
           // this is a thread itself
           return dMessage;
       }
       
       @Override
       public List<DiscussionMessageFlag> getFlags(DiscussionMessage dMessage) throws SystemException {
           return DiscussionMessageFlagLocalServiceUtil.findMessageFlags(dMessage.getMessageId());
       }
       
       @Override
       public void addFlag(DiscussionMessage dMessage, String flagType, String data, User user) throws SystemException  {
           DiscussionMessageFlagLocalServiceUtil.createFlag(dMessage.getMessageId(), flagType, data, user.getUserId());
       }
       
       @Override
       public void removeFlag(DiscussionMessage dMessage, String flagType) throws SystemException {
           DiscussionMessageFlag flag = findFlag(dMessage, flagType);
           if (flag != null) {
               DiscussionMessageFlagLocalServiceUtil.deleteDiscussionMessageFlag(flag);
           }
       }
       
       @Override
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

    @Override
    public List<DiscussionMessage> getByAuthorId(long authorId) throws SystemException {
        return discussionMessagePersistence.findByAuthorId(authorId);
    }

    @Override
    public void subscribe(long userId, long discussionCategoryGroupId, long categoryId, long threadId)
            throws SystemException, PortalException {
        String extraData = String.valueOf(categoryId) + "," + threadId;
        ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, discussionCategoryGroupId,
                0, extraData, userId);
    }

    @Override
    public void unsubscribe(long userId, long discussionCategoryGroupId, long categoryId, long threadId)
            throws SystemException {
        String extraData = String.valueOf(categoryId) + "," + threadId;
        ActivitySubscriptionLocalServiceUtil.deleteSubscription(userId,
                DiscussionCategoryGroup.class, discussionCategoryGroupId, 0, "");
    }

    @Override
    public boolean isSubscribed(long userId, long discussionCategoryGroupId, long categoryId, long threadId)
            throws PortalException, SystemException {
        String extraData = String.valueOf(categoryId) + "," + threadId;
        return ActivitySubscriptionLocalServiceUtil.isSubscribed(
                userId, DiscussionCategoryGroup.class, discussionCategoryGroupId, 0, extraData);
    }
}
