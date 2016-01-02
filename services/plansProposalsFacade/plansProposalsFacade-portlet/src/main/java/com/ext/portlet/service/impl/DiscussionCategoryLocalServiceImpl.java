package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchDiscussionCategoryException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.base.DiscussionCategoryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the discussion category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.DiscussionCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.DiscussionCategoryLocalServiceBaseImpl
 * @see com.ext.portlet.service.DiscussionCategoryLocalServiceUtil
 */
public class DiscussionCategoryLocalServiceImpl extends DiscussionCategoryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.DiscussionCategoryLocalServiceUtil} to access the discussion category local service.
     */


    @Override
    public List<DiscussionCategory> getCategoriesByCategoryGroupId(long categoryGroupId) throws SystemException {
        return discussionCategoryPersistence.findByCategoryGroupId(categoryGroupId);
    }
    
    @Override
    public DiscussionCategory getDiscussionCategoryById(long categoryId) throws NoSuchDiscussionCategoryException, SystemException {
        return discussionCategoryPersistence.findByCategoryId(categoryId);
    }
    
    @Override
    public DiscussionCategory createDiscussionCategory(long categoryGroupId, String name, String description, User author)
    throws SystemException {
        Long id = CounterLocalServiceUtil.increment(DiscussionCategory.class.getName());
        Long categoryId = CounterLocalServiceUtil.increment(DiscussionCategory.class.getName() + ".category");
        DiscussionCategory category = DiscussionCategoryLocalServiceUtil.createDiscussionCategory(id);
        
        category.setName(name);
        category.setDescription(description);
        category.setCreateDate(new Date());
        category.setAuthorId(author.getUserId());
        category.setCategoryGroupId(categoryGroupId);
        category.setCategoryId(categoryId);
        category.setThreadsCount(0);
        
        store(category);
        
        return category;
    }
    
    
    @Override
    public List<DiscussionMessage> getThreads(DiscussionCategory dCategory) throws SystemException {
        return DiscussionMessageLocalServiceUtil.getThreadsByCategory(dCategory.getCategoryId());
    }
    
    @Override
    public DiscussionMessage addThread(DiscussionCategory dCategory, String subject, String body, User author) throws SystemException {
        DiscussionMessage thread = DiscussionMessageLocalServiceUtil.addThread(dCategory.getCategoryGroupId(), dCategory.getCategoryId(), subject, body, author);
        
        dCategory.setThreadsCount(dCategory.getThreadsCount() + 1);
        dCategory.setLastActivityAuthorId(thread.getAuthorId());
        dCategory.setLastActivityDate(thread.getCreateDate());
        store(dCategory);
        
        return thread;
    }
    
    @Override
    public void store(DiscussionCategory dCategory) throws SystemException {
        if (dCategory.isNew()) {
            DiscussionCategoryLocalServiceUtil.addDiscussionCategory(dCategory);
        }
        else {
            DiscussionCategoryLocalServiceUtil.updateDiscussionCategory(dCategory);
        }
    }
    
    @Override
    public User getAuthor(DiscussionCategory dCategory) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(dCategory.getAuthorId());
    }
    
    @Override
    public User getLastActivityAuthor(DiscussionCategory dCategory) throws PortalException, SystemException {
        long lastActivityAuthor = dCategory.getLastActivityAuthorId();
        if (lastActivityAuthor > 0) {
            return UserLocalServiceUtil.getUser(dCategory.getLastActivityAuthorId());
        }
        return getAuthor(dCategory);
    }
    
    @Override
    public void delete(DiscussionCategory dCategory) throws SystemException {
        dCategory.setDeleted(new Date());
        store(dCategory);
        /*
        try {
            // remove from index all messages from deleted category
            for (DiscussionMessage thread: DiscussionMessageLocalServiceUtil.getThreadsByCategory(getCategoryId())) {
                Indexer.deleteEntry(10112L, thread.getMessageId());
                for (DiscussionMessage msg: DiscussionMessageLocalServiceUtil.getThreadMessages(thread.getMessageId())) {
                    Indexer.deleteEntry(10112L, msg.getMessageId());
                }
            }
        } catch (SearchException e) {
            _log.error("Can't remove category messages from search index.", e);
        }*/
    }
    
    @Override
    public void update(DiscussionCategory dCategory, String name, String description) throws SystemException {
        dCategory.setName(name);
        dCategory.setDescription(description);
        store(dCategory);
    }
    
    @Override
    public DiscussionCategoryGroup getCategoryGroup(DiscussionCategory dCategory) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(dCategory.getCategoryGroupId());
    }
}
