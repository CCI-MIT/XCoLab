package com.ext.portlet.discussions.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.base.DiscussionCategoryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * The implementation of the discussion category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.discussions.service.DiscussionCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.discussions.service.base.DiscussionCategoryLocalServiceBaseImpl
 * @see com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil
 */
public class DiscussionCategoryLocalServiceImpl
    extends DiscussionCategoryLocalServiceBaseImpl {
    

    public List<DiscussionCategory> getCategoriesByCategoryGroupId(Long categoryGroupId) throws SystemException {
        return discussionCategoryPersistence.findByCategoryGroupId(categoryGroupId);
    }
    
    public DiscussionCategory getDiscussionCategoryById(Long categoryId) throws NoSuchDiscussionCategoryException, SystemException {
        return discussionCategoryPersistence.findByCategoryId(categoryId);
    }
    
    public DiscussionCategory createDebateCategory(Long categoryGroupId, String name, String description, User author)
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
        
        category.store();
        
        return category;
    }
}
