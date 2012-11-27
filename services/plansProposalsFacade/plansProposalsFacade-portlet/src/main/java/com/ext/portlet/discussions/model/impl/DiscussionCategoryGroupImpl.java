package com.ext.portlet.discussions.model.impl;

import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.NoSuchDiscussionMessageException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * The extended model implementation for the DiscussionCategoryGroup service. Represents a row in the &quot;Discussions_DiscussionCategoryGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.discussions.model.DiscussionCategoryGroup} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class DiscussionCategoryGroupImpl extends DiscussionCategoryGroupBaseImpl {

    public DiscussionCategoryGroupImpl() {
    }
    
    public DiscussionCategory getCategoryById(Long categoryId) throws NoSuchDiscussionCategoryException, SystemException {
        return DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(categoryId);
        
    }
    
    public DiscussionMessage getThreadById(Long threadId) throws NoSuchDiscussionMessageException, SystemException {
        return DiscussionMessageLocalServiceUtil.getThreadByThreadId(threadId);   
    }
    
    public List<DiscussionCategory> getCategories() throws SystemException {
        return DiscussionCategoryLocalServiceUtil.getCategoriesByCategoryGroupId(this.getId());
    }
    
    public DiscussionCategory addCategory(String name, String description, User creator) throws SystemException  {
        return DiscussionCategoryLocalServiceUtil.createDebateCategory(this.getId(), name, description, creator);
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            DiscussionCategoryGroupLocalServiceUtil.addDiscussionCategoryGroup(this);
        }
        else {
            DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(this);
        }
    }
    
    public DiscussionMessage getCommentThread() throws SystemException, PortalException {
        DiscussionMessage thread = null;
        if (getCommentsThread() == null || getCommentsThread() <= 0L) {
            thread = null;
        }
        else {
            try {
                thread = DiscussionMessageLocalServiceUtil.getThreadByThreadId(getCommentsThread());
            }
            catch (NoSuchDiscussionMessageException e) {
                thread = null;
            }
        }
        
        return thread;
        
    }
    
    public DiscussionMessage addComment(String title, String description, User author) throws SystemException, PortalException {
        DiscussionMessage comment = null;
        if (getCommentThread() == null) {
            // create new thread
            comment = DiscussionMessageLocalServiceUtil.addThread(getId(), 0L, title, description, author);
            this.setCommentsThread(comment.getMessageId());
            
            this.store();
        }
        else {
            DiscussionMessage thread = getCommentThread();
            comment = thread.addThreadMessage(title, description, author);
        }
        return comment;
    }
    
    public int getCommentsCount() throws SystemException, PortalException {
        if (getCommentThread() == null) {
            return 0;
        }
        else {
            return getCommentThread().getThreadMessagesCount() + 1;
        }
    }
    
    public void copyEverything(DiscussionCategoryGroup source) throws SystemException, PortalException {
        // copy categories
        for (DiscussionCategory category: source.getCategories()) {
            DiscussionCategory newCategory = DiscussionCategoryLocalServiceUtil.createDebateCategory(
                    this.getId(), 
                    category.getName(), 
                    category.getDescription(), 
                    category.getAuthor());
            newCategory.setCreateDate(category.getCreateDate());
            
            newCategory.setLastActivityAuthorId(category.getLastActivityAuthorId());
            newCategory.setLastActivityDate(category.getLastActivityDate());
            
            newCategory.store();
            
            for (DiscussionMessage thread: category.getThreads()) {
                DiscussionMessage newThread = newCategory.addThread(thread.getSubject(), thread.getBody(), thread.getAuthor());
                newThread.setCreateDate(thread.getCreateDate());
                newThread.setLastActivityAuthorId(thread.getLastActivityAuthorId());
                newThread.setLastActivityDate(thread.getLastActivityDate());

                newThread.store();
                
                for (DiscussionMessage msg: thread.getThreadMessages()) {
                    DiscussionMessage newMsg = newThread.addThreadMessage(msg.getSubject(), msg.getBody(), msg.getAuthor());
                    newMsg.setCreateDate(msg.getCreateDate());
                    
                    newMsg.store();
                }
            }
        }
        
        if (source.getCommentsThread() != null) {
            DiscussionMessage commentsThread = source.getCommentThread();
            DiscussionMessage newCommentsThread = addComment(commentsThread.getSubject(), commentsThread.getBody(), commentsThread.getAuthor());
            newCommentsThread.setLastActivityAuthorId(commentsThread.getLastActivityAuthorId());
            newCommentsThread.setLastActivityDate(commentsThread.getLastActivityDate());
            newCommentsThread.setCreateDate(commentsThread.getCreateDate());
            newCommentsThread.store();
            
            for (DiscussionMessage msg: commentsThread.getThreadMessages()) {
                DiscussionMessage newMsg = newCommentsThread.addThreadMessage(msg.getSubject(), msg.getBody(), msg.getAuthor());
                newMsg.setCreateDate(msg.getCreateDate());
                
                newMsg.store();
            }
        }
        
    }
}
