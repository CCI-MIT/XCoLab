package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanAttributeFilterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterLocalService
 * @generated
 */
public class PlanAttributeFilterLocalServiceWrapper
    implements PlanAttributeFilterLocalService,
        ServiceWrapper<PlanAttributeFilterLocalService> {
    private PlanAttributeFilterLocalService _planAttributeFilterLocalService;

    public PlanAttributeFilterLocalServiceWrapper(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        _planAttributeFilterLocalService = planAttributeFilterLocalService;
    }

    /**
    * Adds the plan attribute filter to the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @return the plan attribute filter that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanAttributeFilter addPlanAttributeFilter(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.addPlanAttributeFilter(planAttributeFilter);
    }

    /**
    * Creates a new plan attribute filter with the primary key. Does not add the plan attribute filter to the database.
    *
    * @param planAttributeFilterId the primary key for the new plan attribute filter
    * @return the new plan attribute filter
    */
    @Override
    public com.ext.portlet.model.PlanAttributeFilter createPlanAttributeFilter(
        long planAttributeFilterId) {
        return _planAttributeFilterLocalService.createPlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Deletes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter that was removed
    * @throws PortalException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanAttributeFilter deletePlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.deletePlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Deletes the plan attribute filter from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @return the plan attribute filter that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanAttributeFilter deletePlanAttributeFilter(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.deletePlanAttributeFilter(planAttributeFilter);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planAttributeFilterLocalService.dynamicQuery();
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
        return _planAttributeFilterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planAttributeFilterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planAttributeFilterLocalService.dynamicQuery(dynamicQuery,
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
        return _planAttributeFilterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planAttributeFilterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlanAttributeFilter fetchPlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.fetchPlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Returns the plan attribute filter with the primary key.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter
    * @throws PortalException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanAttributeFilter getPlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getPlanAttributeFilter(planAttributeFilterId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan attribute filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @return the range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanAttributeFilter> getPlanAttributeFilters(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getPlanAttributeFilters(start,
            end);
    }

    /**
    * Returns the number of plan attribute filters.
    *
    * @return the number of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlanAttributeFiltersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getPlanAttributeFiltersCount();
    }

    /**
    * Updates the plan attribute filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @return the plan attribute filter that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanAttributeFilter updatePlanAttributeFilter(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.updatePlanAttributeFilter(planAttributeFilter);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planAttributeFilterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planAttributeFilterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planAttributeFilterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.PlanAttributeFilter getByPlansUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getByPlansUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanAttributeFilterLocalService getWrappedPlanAttributeFilterLocalService() {
        return _planAttributeFilterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanAttributeFilterLocalService(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        _planAttributeFilterLocalService = planAttributeFilterLocalService;
    }

    @Override
    public PlanAttributeFilterLocalService getWrappedService() {
        return _planAttributeFilterLocalService;
    }

    @Override
    public void setWrappedService(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        _planAttributeFilterLocalService = planAttributeFilterLocalService;
    }
}
