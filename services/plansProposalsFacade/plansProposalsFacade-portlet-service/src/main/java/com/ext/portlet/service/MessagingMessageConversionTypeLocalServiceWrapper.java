package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversionTypeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageConversionTypeLocalService
 * @generated
 */
public class MessagingMessageConversionTypeLocalServiceWrapper
    implements MessagingMessageConversionTypeLocalService,
        ServiceWrapper<MessagingMessageConversionTypeLocalService> {
    private MessagingMessageConversionTypeLocalService _messagingMessageConversionTypeLocalService;

    public MessagingMessageConversionTypeLocalServiceWrapper(
        MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService) {
        _messagingMessageConversionTypeLocalService = messagingMessageConversionTypeLocalService;
    }

    /**
    * Adds the messaging message conversion type to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversionType the messaging message conversion type
    * @return the messaging message conversion type that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType addMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.addMessagingMessageConversionType(messagingMessageConversionType);
    }

    /**
    * Creates a new messaging message conversion type with the primary key. Does not add the messaging message conversion type to the database.
    *
    * @param typeId the primary key for the new messaging message conversion type
    * @return the new messaging message conversion type
    */
    public com.ext.portlet.model.MessagingMessageConversionType createMessagingMessageConversionType(
        long typeId) {
        return _messagingMessageConversionTypeLocalService.createMessagingMessageConversionType(typeId);
    }

    /**
    * Deletes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @throws PortalException if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessageConversionType(long typeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionTypeLocalService.deleteMessagingMessageConversionType(typeId);
    }

    /**
    * Deletes the messaging message conversion type from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversionType the messaging message conversion type
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionTypeLocalService.deleteMessagingMessageConversionType(messagingMessageConversionType);
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
        return _messagingMessageConversionTypeLocalService.dynamicQuery(dynamicQuery);
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
        return _messagingMessageConversionTypeLocalService.dynamicQuery(dynamicQuery,
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
        return _messagingMessageConversionTypeLocalService.dynamicQuery(dynamicQuery,
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
        return _messagingMessageConversionTypeLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.MessagingMessageConversionType fetchMessagingMessageConversionType(
        long typeId) throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.fetchMessagingMessageConversionType(typeId);
    }

    /**
    * Returns the messaging message conversion type with the primary key.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type
    * @throws PortalException if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType getMessagingMessageConversionType(
        long typeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.getMessagingMessageConversionType(typeId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging message conversion types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversion types
    * @param end the upper bound of the range of messaging message conversion types (not inclusive)
    * @return the range of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageConversionType> getMessagingMessageConversionTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.getMessagingMessageConversionTypes(start,
            end);
    }

    /**
    * Returns the number of messaging message conversion types.
    *
    * @return the number of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public int getMessagingMessageConversionTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.getMessagingMessageConversionTypesCount();
    }

    /**
    * Updates the messaging message conversion type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversionType the messaging message conversion type
    * @return the messaging message conversion type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType updateMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.updateMessagingMessageConversionType(messagingMessageConversionType);
    }

    /**
    * Updates the messaging message conversion type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversionType the messaging message conversion type
    * @param merge whether to merge the messaging message conversion type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging message conversion type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageConversionType updateMessagingMessageConversionType(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.updateMessagingMessageConversionType(messagingMessageConversionType,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageConversionTypeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageConversionTypeLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.MessagingMessageConversionType getByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageConversionTypeLocalService.getByName(name);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageConversionTypeLocalService getWrappedMessagingMessageConversionTypeLocalService() {
        return _messagingMessageConversionTypeLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageConversionTypeLocalService(
        MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService) {
        _messagingMessageConversionTypeLocalService = messagingMessageConversionTypeLocalService;
    }

    public MessagingMessageConversionTypeLocalService getWrappedService() {
        return _messagingMessageConversionTypeLocalService;
    }

    public void setWrappedService(
        MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService) {
        _messagingMessageConversionTypeLocalService = messagingMessageConversionTypeLocalService;
    }
}
