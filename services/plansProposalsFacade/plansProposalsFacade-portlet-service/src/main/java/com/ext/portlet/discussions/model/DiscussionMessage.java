package com.ext.portlet.discussions.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DiscussionMessage service. Represents a row in the &quot;Discussions_DiscussionMessage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageModelImpl
 * @generated
 */
public interface DiscussionMessage extends DiscussionMessageModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.discussions.model.impl.DiscussionMessageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages()
        throws com.liferay.portal.kernel.exception.SystemException;

    public int getThreadMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addThreadMessage(
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getLastActivityAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void delete()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void update(java.lang.String subject, java.lang.String body)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory getCategory()
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage getThread()
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> getFlags()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void addFlag(java.lang.String flagType, java.lang.String data,
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeFlag(java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException;
}
