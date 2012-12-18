package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttributeFilterLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanAttributeFilterLocalService
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
    public com.ext.portlet.plans.model.PlanAttributeFilter addPlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.addPlanAttributeFilter(planAttributeFilter);
    }

    /**
    * Creates a new plan attribute filter with the primary key. Does not add the plan attribute filter to the database.
    *
    * @param planAttributeFilterId the primary key for the new plan attribute filter
    * @return the new plan attribute filter
    */
    public com.ext.portlet.plans.model.PlanAttributeFilter createPlanAttributeFilter(
        long planAttributeFilterId) {
        return _planAttributeFilterLocalService.createPlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Deletes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @throws PortalException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanAttributeFilter(long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planAttributeFilterLocalService.deletePlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Deletes the plan attribute filter from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planAttributeFilterLocalService.deletePlanAttributeFilter(planAttributeFilter);
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
        return _planAttributeFilterLocalService.dynamicQuery(dynamicQuery);
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
        return _planAttributeFilterLocalService.dynamicQuery(dynamicQuery,
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanAttributeFilter fetchPlanAttributeFilter(
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
    public com.ext.portlet.plans.model.PlanAttributeFilter getPlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getPlanAttributeFilter(planAttributeFilterId);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @return the range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
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
    public com.ext.portlet.plans.model.PlanAttributeFilter updatePlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.updatePlanAttributeFilter(planAttributeFilter);
    }

    /**
    * Updates the plan attribute filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @param merge whether to merge the plan attribute filter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan attribute filter that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanAttributeFilter updatePlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.updatePlanAttributeFilter(planAttributeFilter,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planAttributeFilterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planAttributeFilterLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.plans.model.PlanAttributeFilter getByPlansUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planAttributeFilterLocalService.getByPlansUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanAttributeFilterLocalService getWrappedPlanAttributeFilterLocalService() {
        return _planAttributeFilterLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanAttributeFilterLocalService(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        _planAttributeFilterLocalService = planAttributeFilterLocalService;
    }

    public PlanAttributeFilterLocalService getWrappedService() {
        return _planAttributeFilterLocalService;
    }

    public void setWrappedService(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        _planAttributeFilterLocalService = planAttributeFilterLocalService;
    }
}
