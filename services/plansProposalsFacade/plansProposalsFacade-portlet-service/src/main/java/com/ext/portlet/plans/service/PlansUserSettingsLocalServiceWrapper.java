package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansUserSettingsLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansUserSettingsLocalService
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
    public com.ext.portlet.plans.model.PlansUserSettings addPlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.addPlansUserSettings(plansUserSettings);
    }

    /**
    * Creates a new plans user settings with the primary key. Does not add the plans user settings to the database.
    *
    * @param planUserSettingsId the primary key for the new plans user settings
    * @return the new plans user settings
    */
    public com.ext.portlet.plans.model.PlansUserSettings createPlansUserSettings(
        long planUserSettingsId) {
        return _plansUserSettingsLocalService.createPlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planUserSettingsId the primary key of the plans user settings
    * @throws PortalException if a plans user settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlansUserSettings(long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _plansUserSettingsLocalService.deletePlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings from the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @throws SystemException if a system exception occurred
    */
    public void deletePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansUserSettingsLocalService.deletePlansUserSettings(plansUserSettings);
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
        return _plansUserSettingsLocalService.dynamicQuery(dynamicQuery);
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
        return _plansUserSettingsLocalService.dynamicQuery(dynamicQuery, start,
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlansUserSettings fetchPlansUserSettings(
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
    public com.ext.portlet.plans.model.PlansUserSettings getPlansUserSettings(
        long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPlansUserSettings(planUserSettingsId);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans user settingses
    * @param end the upper bound of the range of plans user settingses (not inclusive)
    * @return the range of plans user settingses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlansUserSettings> getPlansUserSettingses(
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
    public com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.updatePlansUserSettings(plansUserSettings);
    }

    /**
    * Updates the plans user settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @param merge whether to merge the plans user settings with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plans user settings that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.updatePlansUserSettings(plansUserSettings,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _plansUserSettingsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansUserSettingsLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.plans.model.PlansUserSettings getByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlansUserSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getByUserIdPlanTypeId(userId,
            planTypeId);
    }

    public com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPlanUserSettings(sessionMap,
            requestMap, planType);
    }

    /**
    * Method responsible for storing columns configuration. Configuration is
    * stored in session also, if user is authorized then configuration is
    * persisted.
    *
    * @param requestMap           render request
    * @param plansUserSettings settings that should be stored
    * @throws PortalException passed up in case of framework error
    * @throws SystemException passed up in case of framework error
    */
    public void saveUserSettings(java.util.Map sessionMap,
        java.util.Map requestMap,
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _plansUserSettingsLocalService.saveUserSettings(sessionMap, requestMap,
            plansUserSettings);
    }

    public com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettingsLocalService.getPlanUserSettings(sessionMap,
            requestMap, planTypeId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlansUserSettingsLocalService getWrappedPlansUserSettingsLocalService() {
        return _plansUserSettingsLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlansUserSettingsLocalService(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        _plansUserSettingsLocalService = plansUserSettingsLocalService;
    }

    public PlansUserSettingsLocalService getWrappedService() {
        return _plansUserSettingsLocalService;
    }

    public void setWrappedService(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        _plansUserSettingsLocalService = plansUserSettingsLocalService;
    }
}
