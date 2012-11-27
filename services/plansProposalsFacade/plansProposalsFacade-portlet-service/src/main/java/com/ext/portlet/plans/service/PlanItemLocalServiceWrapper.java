package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemLocalService
 * @generated
 */
public class PlanItemLocalServiceWrapper implements PlanItemLocalService,
    ServiceWrapper<PlanItemLocalService> {
    private PlanItemLocalService _planItemLocalService;

    public PlanItemLocalServiceWrapper(
        PlanItemLocalService planItemLocalService) {
        _planItemLocalService = planItemLocalService;
    }

    /**
    * Adds the plan item to the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanItem addPlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.addPlanItem(planItem);
    }

    /**
    * Creates a new plan item with the primary key. Does not add the plan item to the database.
    *
    * @param id the primary key for the new plan item
    * @return the new plan item
    */
    public com.ext.portlet.plans.model.PlanItem createPlanItem(
        java.lang.Long id) {
        return _planItemLocalService.createPlanItem(id);
    }

    /**
    * Deletes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItem(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.deletePlanItem(id);
    }

    /**
    * Deletes the plan item from the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItem(com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.deletePlanItem(planItem);
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
        return _planItemLocalService.dynamicQuery(dynamicQuery);
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
        return _planItemLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planItemLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _planItemLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanItem fetchPlanItem(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.fetchPlanItem(id);
    }

    /**
    * Returns the plan item with the primary key.
    *
    * @param id the primary key of the plan item
    * @return the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanItem getPlanItem(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanItem(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan items
    * @param end the upper bound of the range of plan items (not inclusive)
    * @return the range of plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlanItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanItems(start, end);
    }

    /**
    * Returns the number of plan items.
    *
    * @return the number of plan items
    * @throws SystemException if a system exception occurred
    */
    public int getPlanItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanItemsCount();
    }

    /**
    * Updates the plan item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.updatePlanItem(planItem);
    }

    /**
    * Updates the plan item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @param merge whether to merge the plan item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.updatePlanItem(planItem, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planItemLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planItemLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
    * Default forum category description.
    */
    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.createPlan(phase, authorId);
    }

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.createPlan(basePlan, authorId);
    }

    public com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan,
        com.ext.portlet.contests.model.ContestPhase contestPhase,
        java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.createPlan(basePlan, contestPhase, authorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansInContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlansInContestPhase(contestPhase);
    }

    public com.ext.portlet.plans.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlan(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap, planType,
            phase, start, end, sortColumn, sortDirection);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap, planType,
            phase, start, end, sortColumn, sortDirection, applyFilters);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap, planType,
            phases, start, end, sortColumn, sortDirection);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap, planType,
            phases, start, end, sortColumn, sortDirection, applyFilters);
    }

    public boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.contests.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isNameAvailable(planName, c);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.plans.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.applyFilters(sessionMap, requestMap,
            planType, plans);
    }

    public void removePlanWithEntireHistory(java.lang.Long planId) {
        _planItemLocalService.removePlanWithEntireHistory(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllVersions(plan);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanAttributes(plan);
    }

    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanAttribute(plan, name);
    }

    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.reIndex();
    }

    public void reIndex(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.reIndex(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.ontology.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.findPlansForFocusArea(fa);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.ontology.model.OntologyTerm terms)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.findPlansForOntologyTerms(terms);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.ontology.model.OntologyTerm> terms)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.findPlansForOntologyTerms(terms);
    }

    public long countPlansByContest(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.countPlansByContest(contestId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlansByContest(contestId);
    }

    public void planDeleted(com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.planDeleted(plan);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanItemLocalService getWrappedPlanItemLocalService() {
        return _planItemLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanItemLocalService(
        PlanItemLocalService planItemLocalService) {
        _planItemLocalService = planItemLocalService;
    }

    public PlanItemLocalService getWrappedService() {
        return _planItemLocalService;
    }

    public void setWrappedService(PlanItemLocalService planItemLocalService) {
        _planItemLocalService = planItemLocalService;
    }
}
