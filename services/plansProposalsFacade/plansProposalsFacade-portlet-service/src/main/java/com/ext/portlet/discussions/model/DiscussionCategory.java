package com.ext.portlet.discussions.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DiscussionCategory service. Represents a row in the &quot;Discussions_DiscussionCategory&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryModelImpl
 * @generated
 */
public interface DiscussionCategory extends DiscussionCategoryModel,
    PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreads()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getLastActivityAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void delete()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void update(java.lang.String name, java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
