package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlansUserSettingsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsLocalService
 * @generated
 */
public class PlansUserSettingsLocalServiceWrapper
    implements PlansUserSettingsLocalService,
        ServiceWrapper<PlansUserSettingsLocalService> {
    private PlansUserSettingsLocalService _plansUserSettingsLocalService;

    public PlansUserSettingsLocalServiceWrapper(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        _plansUserSettingsLocalService = plansUserSettingsLocalService;
    }

    /**
    * Adds the plans user settings to the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansUserSettings addPlansUserSettings(
        com.ext.portlet.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.addPlansUserSettings(plansUserSettings);
    }

    /**
    * Creates a new plans user settings with the primary key. Does not add the plans user settings to the database.
    *
    * @param planUserSettingsId the primary key for the new plans user settings
    * @return the new plans user settings
    */
    @Override
    public com.ext.portlet.model.PlansUserSettings createPlansUserSettings(
        long planUserSettingsId) {
        return _plansUserSettingsLocalService.createPlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planUserSettingsId the primary key of the plans user settings
    * @return the plans user settings that was removed
    * @throws PortalException if a plans user settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansUserSettings deletePlansUserSettings(
        long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.deletePlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings from the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansUserSettings deletePlansUserSettings(
        com.ext.portlet.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.deletePlansUserSettings(plansUserSettings);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _plansUserSettingsLocalService.dynamicQuery();
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
        return _plansUserSettingsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansUserSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _plansUserSettingsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansUserSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _plansUserSettingsLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _plansUserSettingsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _plansUserSettingsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlansUserSettings fetchPlansUserSettings(
        long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.fetchPlansUserSettings(planUserSettingsId);
    }

    /**
    * Returns the plans user settings with the primary key.
    *
    * @param planUserSettingsId the primary key of the plans user settings
    * @return the plans user settings
    * @throws PortalException if a plans user settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansUserSettings getPlansUserSettings(
        long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPlansUserSettings(planUserSettingsId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plans user settingses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansUserSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plans user settingses
    * @param end the upper bound of the range of plans user settingses (not inclusive)
    * @return the range of plans user settingses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlansUserSettings> getPlansUserSettingses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPlansUserSettingses(start, end);
    }

    /**
    * Returns the number of plans user settingses.
    *
    * @return the number of plans user settingses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlansUserSettingsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPlansUserSettingsesCount();
    }

    /**
    * Updates the plans user settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.updatePlansUserSettings(plansUserSettings);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _plansUserSettingsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansUserSettingsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _plansUserSettingsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlansUserSettingsLocalService getWrappedPlansUserSettingsLocalService() {
        return _plansUserSettingsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlansUserSettingsLocalService(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        _plansUserSettingsLocalService = plansUserSettingsLocalService;
    }

    @Override
    public PlansUserSettingsLocalService getWrappedService() {
        return _plansUserSettingsLocalService;
    }

    @Override
    public void setWrappedService(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        _plansUserSettingsLocalService = plansUserSettingsLocalService;
    }
}
