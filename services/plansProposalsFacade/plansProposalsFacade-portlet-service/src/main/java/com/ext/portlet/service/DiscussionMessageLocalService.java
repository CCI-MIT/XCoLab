package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the discussion message local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageLocalServiceUtil
 * @see com.ext.portlet.service.base.DiscussionMessageLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.DiscussionMessageLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DiscussionMessageLocalService
    extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DiscussionMessageLocalServiceUtil} to access the discussion message local service. Add custom service methods to {@link com.ext.portlet.service.impl.DiscussionMessageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the discussion message to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
    *
    * @param pk the primary key for the new discussion message
    * @return the new discussion message
    */
    public com.ext.portlet.model.DiscussionMessage createDiscussionMessage(
        long pk);

    /**
    * Deletes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message
    * @throws PortalException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteDiscussionMessage(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the discussion message from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @throws SystemException if a system exception occurred
    */
    public void deleteDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionMessage fetchDiscussionMessage(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message with the primary key.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message
    * @throws PortalException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionMessage getDiscussionMessage(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getDiscussionMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages.
    *
    * @return the number of discussion messages
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDiscussionMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the discussion message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the discussion message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @param merge whether to merge the discussion message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the discussion message that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadsByCategory(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadMessages(
        long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getThreadMessagesCount(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionMessage getThreadByThreadId(
        long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.DiscussionMessage addThread(
        long categoryGroupId, long categoryId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.DiscussionMessage addMessage(
        long categoryGroupId, long categoryId, long threadId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.DiscussionMessage> search(
        java.lang.String query, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionMessage getMessageByMessageId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void reIndex(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadMessages(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getThreadMessagesCount(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store(com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.DiscussionMessage addThreadMessage(
        com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getLastActivityAuthor(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void delete(com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void update(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String subject, java.lang.String body)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionCategory getCategory(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionCategoryGroup getCategoryGroup(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionMessage getThread(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> getFlags(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void addFlag(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String flagType, java.lang.String data,
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeFlag(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException;
}
