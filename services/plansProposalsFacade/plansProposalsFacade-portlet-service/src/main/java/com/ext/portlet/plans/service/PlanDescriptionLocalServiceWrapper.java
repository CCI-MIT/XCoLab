package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanDescriptionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanDescriptionLocalService
 * @generated
 */
public class PlanDescriptionLocalServiceWrapper
    implements PlanDescriptionLocalService,
        ServiceWrapper<PlanDescriptionLocalService> {
    private PlanDescriptionLocalService _planDescriptionLocalService;

    public PlanDescriptionLocalServiceWrapper(
        PlanDescriptionLocalService planDescriptionLocalService) {
        _planDescriptionLocalService = planDescriptionLocalService;
    }

    /**
    * Adds the plan description to the database. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @return the plan description that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanDescription addPlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.addPlanDescription(planDescription);
    }

    /**
    * Creates a new plan description with the primary key. Does not add the plan description to the database.
    *
    * @param id the primary key for the new plan description
    * @return the new plan description
    */
    public com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        java.lang.Long id) {
        return _planDescriptionLocalService.createPlanDescription(id);
    }

    /**
    * Deletes the plan description with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan description
    * @throws PortalException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanDescription(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planDescriptionLocalService.deletePlanDescription(id);
    }

    /**
    * Deletes the plan description from the database. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planDescriptionLocalService.deletePlanDescription(planDescription);
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
        return _planDescriptionLocalService.dynamicQuery(dynamicQuery);
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
        return _planDescriptionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _planDescriptionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _planDescriptionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanDescription fetchPlanDescription(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.fetchPlanDescription(id);
    }

    /**
    * Returns the plan description with the primary key.
    *
    * @param id the primary key of the plan description
    * @return the plan description
    * @throws PortalException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanDescription getPlanDescription(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getPlanDescription(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan descriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getPlanDescriptions(start, end);
    }

    /**
    * Returns the number of plan descriptions.
    *
    * @return the number of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public int getPlanDescriptionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getPlanDescriptionsCount();
    }

    /**
    * Updates the plan description in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @return the plan description that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.updatePlanDescription(planDescription);
    }

    /**
    * Updates the plan description in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @param merge whether to merge the plan description with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan description that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.updatePlanDescription(planDescription,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planDescriptionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planDescriptionLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.createPlanDescription(plan, name);
    }

    public com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name,
        boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.createPlanDescription(plan, name,
            store);
    }

    public com.ext.portlet.plans.model.PlanDescription getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getCurrentForPlan(plan);
    }

    public com.ext.portlet.plans.model.PlanDescription getForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getForPlan(plan);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.getAllForPlan(plan);
    }

    public com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.createNewVersionForPlan(plan);
    }

    public com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.createNewVersionForPlan(plan, store);
    }

    public com.ext.portlet.plans.model.PlanDescription createNewVersionForPlanFrom(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanDescription from, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planDescriptionLocalService.createNewVersionForPlanFrom(plan,
            from, store);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanDescriptionLocalService getWrappedPlanDescriptionLocalService() {
        return _planDescriptionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanDescriptionLocalService(
        PlanDescriptionLocalService planDescriptionLocalService) {
        _planDescriptionLocalService = planDescriptionLocalService;
    }

    public PlanDescriptionLocalService getWrappedService() {
        return _planDescriptionLocalService;
    }

    public void setWrappedService(
        PlanDescriptionLocalService planDescriptionLocalService) {
        _planDescriptionLocalService = planDescriptionLocalService;
    }
}
