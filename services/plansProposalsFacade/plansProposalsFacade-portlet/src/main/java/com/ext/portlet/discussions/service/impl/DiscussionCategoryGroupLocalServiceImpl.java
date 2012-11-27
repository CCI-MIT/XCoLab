package com.ext.portlet.discussions.service.impl;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.base.DiscussionCategoryGroupLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the discussion category group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.discussions.service.base.DiscussionCategoryGroupLocalServiceBaseImpl
 * @see com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil
 */
public class DiscussionCategoryGroupLocalServiceImpl
    extends DiscussionCategoryGroupLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil} to access the discussion category group local service.
     */
    
    public DiscussionCategoryGroup createDiscussionCategoryGroup(String description) throws SystemException {
        Long id = CounterLocalServiceUtil.increment(DiscussionCategoryGroup.class.getName());
        DiscussionCategoryGroup discussionCategoryGroup = createDiscussionCategoryGroup(id);
        discussionCategoryGroup.setDescription(description);
        discussionCategoryGroup.store();
        
        return discussionCategoryGroup;
    }
}
