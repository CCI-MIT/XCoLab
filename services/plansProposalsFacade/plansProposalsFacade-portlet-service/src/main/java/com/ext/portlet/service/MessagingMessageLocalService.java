package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the messaging message local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageLocalServiceUtil
 * @see com.ext.portlet.service.base.MessagingMessageLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingMessageLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface MessagingMessageLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessagingMessageLocalServiceUtil} to access the messaging message local service. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingMessageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the messaging message to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @return the messaging message that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage addMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new messaging message with the primary key. Does not add the messaging message to the database.
    *
    * @param messageId the primary key for the new messaging message
    * @return the new messaging message
    */
    public com.ext.portlet.model.MessagingMessage createMessagingMessage(
        long messageId);

    /**
    * Deletes the messaging message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the messaging message
    * @throws PortalException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessage(long messageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the messaging message from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage)
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
    public com.ext.portlet.model.MessagingMessage fetchMessagingMessage(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message with the primary key.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message
    * @throws PortalException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.MessagingMessage getMessagingMessage(
        long messageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the messaging messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging messages
    * @param end the upper bound of the range of messaging messages (not inclusive)
    * @return the range of messaging messages
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.MessagingMessage> getMessagingMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging messages.
    *
    * @return the number of messaging messages
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getMessagingMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the messaging message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @return the messaging message that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage updateMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the messaging message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @param merge whether to merge the messaging message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging message that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage updateMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage, boolean merge)
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
}
