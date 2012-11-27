package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPropertyFilterLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPropertyFilterLocalService
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
    public com.ext.portlet.plans.model.PlanPropertyFilter addPlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.addPlanPropertyFilter(planPropertyFilter);
    }

    /**
    * Creates a new plan property filter with the primary key. Does not add the plan property filter to the database.
    *
    * @param planPropertyFilterId the primary key for the new plan property filter
    * @return the new plan property filter
    */
    public com.ext.portlet.plans.model.PlanPropertyFilter createPlanPropertyFilter(
        java.lang.Long planPropertyFilterId) {
        return _planPropertyFilterLocalService.createPlanPropertyFilter(planPropertyFilterId);
    }

    /**
    * Deletes the plan property filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @throws PortalException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanPropertyFilter(java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planPropertyFilterLocalService.deletePlanPropertyFilter(planPropertyFilterId);
    }

    /**
    * Deletes the plan property filter from the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilter the plan property filter
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPropertyFilterLocalService.deletePlanPropertyFilter(planPropertyFilter);
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
        return _planPropertyFilterLocalService.dynamicQuery(dynamicQuery);
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
        return _planPropertyFilterLocalService.dynamicQuery(dynamicQuery,
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanPropertyFilter fetchPlanPropertyFilter(
        java.lang.Long planPropertyFilterId)
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
    public com.ext.portlet.plans.model.PlanPropertyFilter getPlanPropertyFilter(
        java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getPlanPropertyFilter(planPropertyFilterId);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan property filters
    * @param end the upper bound of the range of plan property filters (not inclusive)
    * @return the range of plan property filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> getPlanPropertyFilters(
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
    public com.ext.portlet.plans.model.PlanPropertyFilter updatePlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.updatePlanPropertyFilter(planPropertyFilter);
    }

    /**
    * Updates the plan property filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilter the plan property filter
    * @param merge whether to merge the plan property filter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan property filter that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPropertyFilter updatePlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.updatePlanPropertyFilter(planPropertyFilter,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planPropertyFilterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPropertyFilterLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.plans.model.PlanPropertyFilter getByPlanPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPropertyFilterLocalService.getByPlanPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPropertyFilterLocalService getWrappedPlanPropertyFilterLocalService() {
        return _planPropertyFilterLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPropertyFilterLocalService(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        _planPropertyFilterLocalService = planPropertyFilterLocalService;
    }

    public PlanPropertyFilterLocalService getWrappedService() {
        return _planPropertyFilterLocalService;
    }

    public void setWrappedService(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        _planPropertyFilterLocalService = planPropertyFilterLocalService;
    }
}
