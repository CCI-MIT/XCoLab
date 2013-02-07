package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageConversionLocalService
 * @generated
 */
public class MessagingMessageConversionLocalServiceWrapper
    implements MessagingMessageConversionLocalService,
        ServiceWrapper<MessagingMessageConversionLocalService> {
    private MessagingMessageConversionLocalService _messagingMessageConversionLocalService;

    public MessagingMessageConversionLocalServiceWrapper(
        MessagingMessageConversionLocalService messagingMessageConversionLocalService) {
        _messagingMessageConversionLocalService = messagingMessageConversionLocalService;
    }

    /**
    * Adds the messaging message conversion to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @return the messaging message conversion that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion addMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.addMessagingMessageConversion(messagingMessageConversion);
    }

    /**
    * Creates a new messaging message conversion with the primary key. Does not add the messaging message conversion to the database.
    *
    * @param conversionId the primary key for the new messaging message conversion
    * @return the new messaging message conversion
    */
    public com.ext.portlet.model.MessagingMessageConversion createMessagingMessageConversion(
        long conversionId) {
        return _messagingMessageConversionLocalService.createMessagingMessageConversion(conversionId);
    }

    /**
    * Deletes the messaging message conversion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @throws PortalException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessageConversion(long conversionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionLocalService.deleteMessagingMessageConversion(conversionId);
    }

    /**
    * Deletes the messaging message conversion from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionLocalService.deleteMessagingMessageConversion(messagingMessageConversion);
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
        return _messagingMessageConversionLocalService.dynamicQuery(dynamicQuery);
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
        return _messagingMessageConversionLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
        return _messagingMessageConversionLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _messagingMessageConversionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.MessagingMessageConversion fetchMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.fetchMessagingMessageConversion(conversionId);
    }

    /**
    * Returns the messaging message conversion with the primary key.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion
    * @throws PortalException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion getMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.getMessagingMessageConversion(conversionId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging message conversions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @return the range of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversion> getMessagingMessageConversions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.getMessagingMessageConversions(start,
            end);
    }

    /**
    * Returns the number of messaging message conversions.
    *
    * @return the number of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public int getMessagingMessageConversionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.getMessagingMessageConversionsCount();
    }

    /**
    * Updates the messaging message conversion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @return the messaging message conversion that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.updateMessagingMessageConversion(messagingMessageConversion);
    }

    /**
    * Updates the messaging message conversion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @param merge whether to merge the messaging message conversion with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging message conversion that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.updateMessagingMessageConversion(messagingMessageConversion,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageConversionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageConversionLocalService.setBeanIdentifier(beanIdentifier);
    }

    public int countByType(java.lang.Long messageId,
        com.ext.portlet.model.MessagingMessageConversionType type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.countByType(messageId,
            type);
    }

    public int countByType(java.lang.Long messageId, java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.countByType(messageId,
            typeName);
    }

    public void addConversion(java.lang.Long messageId,
        java.lang.String typeName, java.lang.String clientIP,
        java.lang.Object extraData, java.lang.Object extraData2)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionLocalService.addConversion(messageId,
            typeName, clientIP, extraData, extraData2);
    }

    public int countConversionsByIP(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.countConversionsByIP(messageId,
            typeName);
    }

    public int countConversionsByRecipient(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionLocalService.countConversionsByRecipient(messageId,
            typeName);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageConversionLocalService getWrappedMessagingMessageConversionLocalService() {
        return _messagingMessageConversionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageConversionLocalService(
        MessagingMessageConversionLocalService messagingMessageConversionLocalService) {
        _messagingMessageConversionLocalService = messagingMessageConversionLocalService;
    }

    public MessagingMessageConversionLocalService getWrappedService() {
        return _messagingMessageConversionLocalService;
    }

    public void setWrappedService(
        MessagingMessageConversionLocalService messagingMessageConversionLocalService) {
        _messagingMessageConversionLocalService = messagingMessageConversionLocalService;
    }
}
