package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanPropertyFilterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilterLocalService
 * @generated
 */
public class PlanPropertyFilterLocalServiceWrapper
    implements PlanPropertyFilterLocalService,
        ServiceWrapper<PlanPropertyFilterLocalService> {
    private PlanPropertyFilterLocalService _planPropertyFilterLocalService;

    public PlanPropertyFilterLocalServiceWrapper(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        _planPropertyFilterLocalService = planPropertyFilterLocalService;
    }

    /**
    * Adds the plan property filter to the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilter the plan property filter
    * @return the plan property filter that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPropertyFilter addPlanPropertyFilter(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.addPlanPropertyFilter(planPropertyFilter);
    }

    /**
    * Creates a new plan property filter with the primary key. Does not add the plan property filter to the database.
    *
    * @param planPropertyFilterId the primary key for the new plan property filter
    * @return the new plan property filter
    */
    @Override
    public com.ext.portlet.model.PlanPropertyFilter createPlanPropertyFilter(
        long planPropertyFilterId) {
        return _planPropertyFilterLocalService.createPlanPropertyFilter(planPropertyFilterId);
    }

    /**
    * Deletes the plan property filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter that was removed
    * @throws PortalException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPropertyFilter deletePlanPropertyFilter(
        long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.deletePlanPropertyFilter(planPropertyFilterId);
    }

    /**
    * Deletes the plan property filter from the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilter the plan property filter
    * @return the plan property filter that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPropertyFilter deletePlanPropertyFilter(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.deletePlanPropertyFilter(planPropertyFilter);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planPropertyFilterLocalService.dynamicQuery();
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
        return _planPropertyFilterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPropertyFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planPropertyFilterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPropertyFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planPropertyFilterLocalService.dynamicQuery(dynamicQuery,
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
        return _planPropertyFilterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planPropertyFilterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlanPropertyFilter fetchPlanPropertyFilter(
        long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.fetchPlanPropertyFilter(planPropertyFilterId);
    }

    /**
    * Returns the plan property filter with the primary key.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter
    * @throws PortalException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPropertyFilter getPlanPropertyFilter(
        long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getPlanPropertyFilter(planPropertyFilterId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan property filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPropertyFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan property filters
    * @param end the upper bound of the range of plan property filters (not inclusive)
    * @return the range of plan property filters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanPropertyFilter> getPlanPropertyFilters(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getPlanPropertyFilters(start, end);
    }

    /**
    * Returns the number of plan property filters.
    *
    * @return the number of plan property filters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlanPropertyFiltersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getPlanPropertyFiltersCount();
    }

    /**
    * Updates the plan property filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilter the plan property filter
    * @return the plan property filter that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPropertyFilter updatePlanPropertyFilter(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.updatePlanPropertyFilter(planPropertyFilter);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planPropertyFilterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPropertyFilterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planPropertyFilterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.PlanPropertyFilter getByPlanPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getByPlanPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanPropertyFilterLocalService getWrappedPlanPropertyFilterLocalService() {
        return _planPropertyFilterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanPropertyFilterLocalService(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        _planPropertyFilterLocalService = planPropertyFilterLocalService;
    }

    @Override
    public PlanPropertyFilterLocalService getWrappedService() {
        return _planPropertyFilterLocalService;
    }

    @Override
    public void setWrappedService(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        _planPropertyFilterLocalService = planPropertyFilterLocalService;
    }
}
