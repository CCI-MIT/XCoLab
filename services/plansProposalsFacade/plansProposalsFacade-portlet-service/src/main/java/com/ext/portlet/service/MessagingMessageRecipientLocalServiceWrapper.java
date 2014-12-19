package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingMessageRecipientLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientLocalService
 * @generated
 */
public class MessagingMessageRecipientLocalServiceWrapper
    implements MessagingMessageRecipientLocalService,
        ServiceWrapper<MessagingMessageRecipientLocalService> {
    private MessagingMessageRecipientLocalService _messagingMessageRecipientLocalService;

    public MessagingMessageRecipientLocalServiceWrapper(
        MessagingMessageRecipientLocalService messagingMessageRecipientLocalService) {
        _messagingMessageRecipientLocalService = messagingMessageRecipientLocalService;
    }

    /**
    * Adds the messaging message recipient to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @return the messaging message recipient that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingMessageRecipient addMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.addMessagingMessageRecipient(messagingMessageRecipient);
    }

    /**
    * Creates a new messaging message recipient with the primary key. Does not add the messaging message recipient to the database.
    *
    * @param recipientId the primary key for the new messaging message recipient
    * @return the new messaging message recipient
    */
    @Override
    public com.ext.portlet.model.MessagingMessageRecipient createMessagingMessageRecipient(
        long recipientId) {
        return _messagingMessageRecipientLocalService.createMessagingMessageRecipient(recipientId);
    }

    /**
    * Deletes the messaging message recipient with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient that was removed
    * @throws PortalException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingMessageRecipient deleteMessagingMessageRecipient(
        long recipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.deleteMessagingMessageRecipient(recipientId);
    }

    /**
    * Deletes the messaging message recipient from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @return the messaging message recipient that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingMessageRecipient deleteMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.deleteMessagingMessageRecipient(messagingMessageRecipient);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _messagingMessageRecipientLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.MessagingMessageRecipient fetchMessagingMessageRecipient(
        long recipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.fetchMessagingMessageRecipient(recipientId);
    }

    /**
    * Returns the messaging message recipient with the primary key.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient
    * @throws PortalException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingMessageRecipient getMessagingMessageRecipient(
        long recipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.getMessagingMessageRecipient(recipientId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging message recipients.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging message recipients
    * @param end the upper bound of the range of messaging message recipients (not inclusive)
    * @return the range of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.MessagingMessageRecipient> getMessagingMessageRecipients(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.getMessagingMessageRecipients(start,
            end);
    }

    /**
    * Returns the number of messaging message recipients.
    *
    * @return the number of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMessagingMessageRecipientsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.getMessagingMessageRecipientsCount();
    }

    /**
    * Updates the messaging message recipient in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @return the messaging message recipient that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingMessageRecipient updateMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipientLocalService.updateMessagingMessageRecipient(messagingMessageRecipient);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageRecipientLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageRecipientLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingMessageRecipientLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingMessageRecipientLocalService getWrappedMessagingMessageRecipientLocalService() {
        return _messagingMessageRecipientLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageRecipientLocalService(
        MessagingMessageRecipientLocalService messagingMessageRecipientLocalService) {
        _messagingMessageRecipientLocalService = messagingMessageRecipientLocalService;
    }

    @Override
    public MessagingMessageRecipientLocalService getWrappedService() {
        return _messagingMessageRecipientLocalService;
    }

    @Override
    public void setWrappedService(
        MessagingMessageRecipientLocalService messagingMessageRecipientLocalService) {
        _messagingMessageRecipientLocalService = messagingMessageRecipientLocalService;
    }
}
