package com.ext.portlet.discussions.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DiscussionCategoryGroup service. Represents a row in the &quot;Discussions_DiscussionCategoryGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupModelImpl
 * @generated
 */
public interface DiscussionCategoryGroup extends DiscussionCategoryGroupModel,
    PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public com.ext.portlet.discussions.model.DiscussionCategory getCategoryById(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage getThreadById(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategories()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory addCategory(
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage getCommentThread()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addComment(
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public int getCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void copyEverything(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup source)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
