package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageLocalService
 * @generated
 */
public class MessagingMessageLocalServiceWrapper
    implements MessagingMessageLocalService,
        ServiceWrapper<MessagingMessageLocalService> {
    private MessagingMessageLocalService _messagingMessageLocalService;

    public MessagingMessageLocalServiceWrapper(
        MessagingMessageLocalService messagingMessageLocalService) {
        _messagingMessageLocalService = messagingMessageLocalService;
    }

    /**
    * Adds the messaging message to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @return the messaging message that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage addMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.addMessagingMessage(messagingMessage);
    }

    /**
    * Creates a new messaging message with the primary key. Does not add the messaging message to the database.
    *
    * @param messageId the primary key for the new messaging message
    * @return the new messaging message
    */
    public com.ext.portlet.model.MessagingMessage createMessagingMessage(
        long messageId) {
        return _messagingMessageLocalService.createMessagingMessage(messageId);
    }

    /**
    * Deletes the messaging message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the messaging message
    * @throws PortalException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessage(long messageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageLocalService.deleteMessagingMessage(messageId);
    }

    /**
    * Deletes the messaging message from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageLocalService.deleteMessagingMessage(messagingMessage);
    }

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
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.dynamicQuery(dynamicQuery);
    }

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
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

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
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.MessagingMessage fetchMessagingMessage(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.fetchMessagingMessage(messageId);
    }

    /**
    * Returns the messaging message with the primary key.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message
    * @throws PortalException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage getMessagingMessage(
        long messageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.getMessagingMessage(messageId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.getPersistedModel(primaryKeyObj);
    }

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
    public java.util.List<com.ext.portlet.model.MessagingMessage> getMessagingMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.getMessagingMessages(start, end);
    }

    /**
    * Returns the number of messaging messages.
    *
    * @return the number of messaging messages
    * @throws SystemException if a system exception occurred
    */
    public int getMessagingMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.getMessagingMessagesCount();
    }

    /**
    * Updates the messaging message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessage the messaging message
    * @return the messaging message that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage updateMessagingMessage(
        com.ext.portlet.model.MessagingMessage messagingMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.updateMessagingMessage(messagingMessage);
    }

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
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageLocalService.updateMessagingMessage(messagingMessage,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageLocalService getWrappedMessagingMessageLocalService() {
        return _messagingMessageLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageLocalService(
        MessagingMessageLocalService messagingMessageLocalService) {
        _messagingMessageLocalService = messagingMessageLocalService;
    }

    public MessagingMessageLocalService getWrappedService() {
        return _messagingMessageLocalService;
    }

    public void setWrappedService(
        MessagingMessageLocalService messagingMessageLocalService) {
        _messagingMessageLocalService = messagingMessageLocalService;
    }
}
