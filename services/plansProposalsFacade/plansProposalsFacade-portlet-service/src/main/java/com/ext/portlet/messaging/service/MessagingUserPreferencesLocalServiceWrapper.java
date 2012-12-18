package com.ext.portlet.messaging.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingUserPreferencesLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingUserPreferencesLocalService
 * @generated
 */
public class MessagingUserPreferencesLocalServiceWrapper
    implements MessagingUserPreferencesLocalService,
        ServiceWrapper<MessagingUserPreferencesLocalService> {
    private MessagingUserPreferencesLocalService _messagingUserPreferencesLocalService;

    public MessagingUserPreferencesLocalServiceWrapper(
        MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        _messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }

    /**
    * Adds the messaging user preferences to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @return the messaging user preferences that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessagingUserPreferences addMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.addMessagingUserPreferences(messagingUserPreferences);
    }

    /**
    * Creates a new messaging user preferences with the primary key. Does not add the messaging user preferences to the database.
    *
    * @param messagingPreferencesId the primary key for the new messaging user preferences
    * @return the new messaging user preferences
    */
    public com.ext.portlet.messaging.model.MessagingUserPreferences createMessagingUserPreferences(
        long messagingPreferencesId) {
        return _messagingUserPreferencesLocalService.createMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Deletes the messaging user preferences with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @throws PortalException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingUserPreferences(long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _messagingUserPreferencesLocalService.deleteMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Deletes the messaging user preferences from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingUserPreferencesLocalService.deleteMessagingUserPreferences(messagingUserPreferences);
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
        return _messagingUserPreferencesLocalService.dynamicQuery(dynamicQuery);
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
        return _messagingUserPreferencesLocalService.dynamicQuery(dynamicQuery,
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
        return _messagingUserPreferencesLocalService.dynamicQuery(dynamicQuery,
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
        return _messagingUserPreferencesLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.messaging.model.MessagingUserPreferences fetchMessagingUserPreferences(
        long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.fetchMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Returns the messaging user preferences with the primary key.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @return the messaging user preferences
    * @throws PortalException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessagingUserPreferences getMessagingUserPreferences(
        long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.getMessagingUserPreferences(messagingPreferencesId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging user preferenceses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging user preferenceses
    * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
    * @return the range of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> getMessagingUserPreferenceses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.getMessagingUserPreferenceses(start,
            end);
    }

    /**
    * Returns the number of messaging user preferenceses.
    *
    * @return the number of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public int getMessagingUserPreferencesesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.getMessagingUserPreferencesesCount();
    }

    /**
    * Updates the messaging user preferences in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @return the messaging user preferences that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.updateMessagingUserPreferences(messagingUserPreferences);
    }

    /**
    * Updates the messaging user preferences in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @param merge whether to merge the messaging user preferences with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging user preferences that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.updateMessagingUserPreferences(messagingUserPreferences,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _messagingUserPreferencesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingUserPreferencesLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.messaging.model.MessagingUserPreferences findByUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.findByUser(userId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingUserPreferencesLocalService getWrappedMessagingUserPreferencesLocalService() {
        return _messagingUserPreferencesLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingUserPreferencesLocalService(
        MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        _messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }

    public MessagingUserPreferencesLocalService getWrappedService() {
        return _messagingUserPreferencesLocalService;
    }

    public void setWrappedService(
        MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        _messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }
}
