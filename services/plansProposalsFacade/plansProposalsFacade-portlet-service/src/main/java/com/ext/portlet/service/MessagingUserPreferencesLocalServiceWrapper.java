package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingUserPreferencesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferencesLocalService
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
    @Override
    public com.ext.portlet.model.MessagingUserPreferences addMessagingUserPreferences(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.addMessagingUserPreferences(messagingUserPreferences);
    }

    /**
    * Creates a new messaging user preferences with the primary key. Does not add the messaging user preferences to the database.
    *
    * @param messagingPreferencesId the primary key for the new messaging user preferences
    * @return the new messaging user preferences
    */
    @Override
    public com.ext.portlet.model.MessagingUserPreferences createMessagingUserPreferences(
        long messagingPreferencesId) {
        return _messagingUserPreferencesLocalService.createMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Deletes the messaging user preferences with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @return the messaging user preferences that was removed
    * @throws PortalException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingUserPreferences deleteMessagingUserPreferences(
        long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.deleteMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Deletes the messaging user preferences from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @return the messaging user preferences that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MessagingUserPreferences deleteMessagingUserPreferences(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.deleteMessagingUserPreferences(messagingUserPreferences);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _messagingUserPreferencesLocalService.dynamicQuery();
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
        return _messagingUserPreferencesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingUserPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _messagingUserPreferencesLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingUserPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.dynamicQueryCount(dynamicQuery);
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
        return _messagingUserPreferencesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.MessagingUserPreferences fetchMessagingUserPreferences(
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
    @Override
    public com.ext.portlet.model.MessagingUserPreferences getMessagingUserPreferences(
        long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.getMessagingUserPreferences(messagingPreferencesId);
    }

    @Override
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingUserPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging user preferenceses
    * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
    * @return the range of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.MessagingUserPreferences> getMessagingUserPreferenceses(
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
    @Override
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
    @Override
    public com.ext.portlet.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.updateMessagingUserPreferences(messagingUserPreferences);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingUserPreferencesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingUserPreferencesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingUserPreferencesLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.MessagingUserPreferences findByUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferencesLocalService.findByUser(userId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingUserPreferencesLocalService getWrappedMessagingUserPreferencesLocalService() {
        return _messagingUserPreferencesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingUserPreferencesLocalService(
        MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        _messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }

    @Override
    public MessagingUserPreferencesLocalService getWrappedService() {
        return _messagingUserPreferencesLocalService;
    }

    @Override
    public void setWrappedService(
        MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        _messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }
}
