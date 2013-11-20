package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanColumnSettingsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsLocalService
 * @generated
 */
public class PlanColumnSettingsLocalServiceWrapper
    implements PlanColumnSettingsLocalService,
        ServiceWrapper<PlanColumnSettingsLocalService> {
    private PlanColumnSettingsLocalService _planColumnSettingsLocalService;

    public PlanColumnSettingsLocalServiceWrapper(
        PlanColumnSettingsLocalService planColumnSettingsLocalService) {
        _planColumnSettingsLocalService = planColumnSettingsLocalService;
    }

    /**
    * Adds the plan column settings to the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @return the plan column settings that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanColumnSettings addPlanColumnSettings(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.addPlanColumnSettings(planColumnSettings);
    }

    /**
    * Creates a new plan column settings with the primary key. Does not add the plan column settings to the database.
    *
    * @param planColumnSettingsId the primary key for the new plan column settings
    * @return the new plan column settings
    */
    @Override
    public com.ext.portlet.model.PlanColumnSettings createPlanColumnSettings(
        long planColumnSettingsId) {
        return _planColumnSettingsLocalService.createPlanColumnSettings(planColumnSettingsId);
    }

    /**
    * Deletes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings that was removed
    * @throws PortalException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanColumnSettings deletePlanColumnSettings(
        long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.deletePlanColumnSettings(planColumnSettingsId);
    }

    /**
    * Deletes the plan column settings from the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @return the plan column settings that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanColumnSettings deletePlanColumnSettings(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.deletePlanColumnSettings(planColumnSettings);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planColumnSettingsLocalService.dynamicQuery();
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
        return _planColumnSettingsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planColumnSettingsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planColumnSettingsLocalService.dynamicQuery(dynamicQuery,
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
        return _planColumnSettingsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planColumnSettingsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlanColumnSettings fetchPlanColumnSettings(
        long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.fetchPlanColumnSettings(planColumnSettingsId);
    }

    /**
    * Returns the plan column settings with the primary key.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings
    * @throws PortalException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanColumnSettings getPlanColumnSettings(
        long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.getPlanColumnSettings(planColumnSettingsId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan column settingses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan column settingses
    * @param end the upper bound of the range of plan column settingses (not inclusive)
    * @return the range of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanColumnSettings> getPlanColumnSettingses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.getPlanColumnSettingses(start,
            end);
    }

    /**
    * Returns the number of plan column settingses.
    *
    * @return the number of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlanColumnSettingsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.getPlanColumnSettingsesCount();
    }

    /**
    * Updates the plan column settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @return the plan column settings that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanColumnSettings updatePlanColumnSettings(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.updatePlanColumnSettings(planColumnSettings);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planColumnSettingsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planColumnSettingsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planColumnSettingsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planColumnSettingsLocalService.findByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanColumnSettingsLocalService getWrappedPlanColumnSettingsLocalService() {
        return _planColumnSettingsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanColumnSettingsLocalService(
        PlanColumnSettingsLocalService planColumnSettingsLocalService) {
        _planColumnSettingsLocalService = planColumnSettingsLocalService;
    }

    @Override
    public PlanColumnSettingsLocalService getWrappedService() {
        return _planColumnSettingsLocalService;
    }

    @Override
    public void setWrappedService(
        PlanColumnSettingsLocalService planColumnSettingsLocalService) {
        _planColumnSettingsLocalService = planColumnSettingsLocalService;
    }
}
