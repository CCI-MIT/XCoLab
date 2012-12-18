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
    public com.ext.portlet.plans.model.PlanItem createPlanItem(long id) {
        return _planItemLocalService.createPlanItem(id);
    }

    /**
    * Deletes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItem(long id)
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

    public com.ext.portlet.plans.model.PlanItem fetchPlanItem(long id)
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
    public com.ext.portlet.plans.model.PlanItem getPlanItem(long id)
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
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlansInContestPhase(contestPhaseId);
    }

    public com.ext.portlet.plans.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlan(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap,
            planTypeId, contestPhaseId, start, end, sortColumn, sortDirection);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap,
            planTypeId, contestPhaseId, start, end, sortColumn, sortDirection,
            applyFilters);
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
    * methods from PlanItemImpl.java *
    */
    public java.lang.String getDescription(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getDescription(pi);
    }

    public java.lang.String getName(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getName(pi);
    }

    public java.lang.Long getImageId(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getImageId(pi);
    }

    public java.lang.String getPitch(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPitch(pi);
    }

    public com.liferay.portal.model.Image getImage(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getImage(pi);
    }

    public void setDescription(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String description, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setDescription(pi, description, updateAuthorId);
    }

    public void setName(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setName(pi, name, updateAuthorId);
    }

    public void setImage(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setImage(pi, imageId, updateAuthorId);
    }

    public void setPitch(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException {
        _planItemLocalService.setPitch(pi, pitch, updateAuthorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllDescriptionVersions(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllDescriptionVersions(pi);
    }

    /**
    * List of all versions of PlanDescription objects related to given plan
    *
    * @see com.ext.portlet.plans.model.PlanItem#getPlanDescriptions()
    */
    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanDescriptions(pi);
    }

    public java.lang.Long getScenarioId(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getScenarioId(pi);
    }

    public void setScenarioId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setScenarioId(pi, scenarioId, authorId);
    }

    public void setModelId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setModelId(pi, simulationId, authorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> getAllPlanModelRuns(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPlanModelRuns(pi);
    }

    public com.ext.portlet.plans.model.PlanMeta getPlanMeta(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanMeta(pi);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanMeta> getAllPlanMetas(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPlanMetas(pi);
    }

    public java.lang.Long getPlanTypeId(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanTypeId(pi);
    }

    public com.ext.portlet.plans.model.PlanType getPlanType(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanType(pi);
    }

    public com.ext.portlet.contests.model.Contest getContest(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getContest(pi);
    }

    public com.ext.portlet.contests.model.ContestPhase getContestPhase(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getContestPhase(pi);
    }

    public void setContestPhase(com.ext.portlet.plans.model.PlanItem pi,
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setContestPhase(pi, phase, updateAuthorId);
    }

    public void setPlanTypeId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long planTypeId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPlanTypeId(pi, planTypeId, updateAuthorId);
    }

    public java.lang.Long getMBCategoryId(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getMBCategoryId(pi);
    }

    public void setMBCategoryId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long mbCategoryId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setMBCategoryId(pi, mbCategoryId, updateAuthorId);
    }

    public java.lang.Long getCategoryGroupId(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCategoryGroupId(pi);
    }

    public void setCategoryGroupId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long categoryGroupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setCategoryGroupId(pi, categoryGroupId,
            updateAuthorId);
    }

    public java.lang.Long getPlanGroupId(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanGroupId(pi);
    }

    public void setPlanGroupId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long groupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPlanGroupId(pi, groupId, updateAuthorId);
    }

    public java.lang.Long getAuthorId(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAuthorId(pi);
    }

    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAuthor(pi);
    }

    public void setAuthorId(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long authorId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setAuthorId(pi, authorId, updateAuthorId);
    }

    public java.util.Date getCreateDate(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCreateDate(pi);
    }

    public java.util.Date getPublishDate(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPublishDate(pi);
    }

    public java.lang.String getCreator(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCreator(pi);
    }

    public java.lang.Integer getVotes(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getVotes(pi);
    }

    public boolean getOpen(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getOpen(pi);
    }

    public void setOpen(com.ext.portlet.plans.model.PlanItem pi, boolean open,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setOpen(pi, open, updateAuthorId);
    }

    public void setOpen(com.ext.portlet.plans.model.PlanItem pi, boolean open)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setOpen(pi, open);
    }

    public java.lang.String getStatus(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getStatus(pi);
    }

    public void setStatus(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setStatus(pi, status, updateAuthorId);
    }

    public com.ext.portlet.plans.model.PlanPositions getPlanPositions(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanPositions(pi);
    }

    public java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPositionsIds(pi);
    }

    public java.lang.Long[] getPositionsIdsArray(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPositionsIdsArray(pi);
    }

    public void setPositions(com.ext.portlet.plans.model.PlanItem pi,
        java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPositions(pi, positionsIds, updateAuthorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> getAllPositionsVersions(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPositionsVersions(pi);
    }

    public boolean hasUserVoted(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.hasUserVoted(pi, userId);
    }

    public void vote(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.vote(pi, userId);
    }

    public void unvote(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.unvote(pi, userId);
    }

    public void store(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.store(pi);
    }

    /**
    * Updates values of all available attributes.
    *
    * @throws SystemException
    */
    public void updateAllAttributes(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.updateAllAttributes(pi);
    }

    /**
    * Updates value of a given attribute, should be used only for property
    * attributes.
    *
    * @param attributeName
    attribute which value should be updated
    * @throws SystemException
    in case of any error
    */
    public void updateAttribute(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.updateAttribute(pi, attributeName);
    }

    /**
    * Returns list of plan members.
    */
    public java.util.List<com.liferay.portal.model.User> getMembers(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getMembers(pi);
    }

    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getMembershipRequests(pi);
    }

    public void addMembershipRequest(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId, java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.addMembershipRequest(pi, userId, comments);
    }

    public void dennyMembershipRequest(
        com.ext.portlet.plans.model.PlanItem pi, java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.dennyMembershipRequest(pi, userId, request,
            reply, updateAuthorId);
    }

    public void approveMembershipRequest(
        com.ext.portlet.plans.model.PlanItem pi, java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.approveMembershipRequest(pi, userId, request,
            reply, updateAuthorId);
    }

    public void publish(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.publish(pi, updateAuthorId);
    }

    public void delete(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.delete(pi, updateAuthorId);
    }

    public com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getUpdateAuthor(pi);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getFans(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getFans(pi);
    }

    public com.ext.portlet.plans.model.PlanFan addFan(
        com.ext.portlet.plans.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.addFan(pi, userId);
    }

    public void removeFan(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removeFan(pi, userId);
    }

    public boolean isUserAFan(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isUserAFan(pi, userId);
    }

    public boolean isUserAMember(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isUserAMember(pi, userId);
    }

    public boolean hasUserRequestedMembership(
        com.ext.portlet.plans.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.hasUserRequestedMembership(pi, userId);
    }

    public boolean isAdmin(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isAdmin(pi, userId);
    }

    public boolean isOwner(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isOwner(pi, userId);
    }

    public void setUserPermission(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId, java.lang.String userPermission,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setUserPermission(pi, userId, userPermission,
            updateAuthorId);
    }

    public void removeMember(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removeMember(pi, userId, updateAuthorId);
    }

    public void joinIfNotAMember(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.joinIfNotAMember(pi, userId);
    }

    public void setSeekingAssistance(com.ext.portlet.plans.model.PlanItem pi,
        boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setSeekingAssistance(pi, seekingAssistance);
    }

    public boolean isSeekingAssistance(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isSeekingAssistance(pi);
    }

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getDiscussionCategoryGroup(pi);
    }

    public com.ext.portlet.plans.model.PlanItem promote(
        com.ext.portlet.plans.model.PlanItem pi,
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.promote(pi, user);
    }

    public boolean getPromoted(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPromoted(pi);
    }

    public int getCommentsCount(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCommentsCount(pi);
    }

    public void setPlace(com.ext.portlet.plans.model.PlanItem pi, int place)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPlace(pi, place);
    }

    public void removePlace(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removePlace(pi);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanVotes(pi);
    }

    public void setRibbon(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setRibbon(pi, ribbon);
    }

    public void setRibbonText(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setRibbonText(pi, ribbonText);
    }

    public void setAttribute(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String attributeName, java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setAttribute(pi, attributeName, value);
    }

    public void removeAttribute(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removeAttribute(pi, attributeName);
    }

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanTemplate(pi);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getPlanSections(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanSections(pi);
    }

    public void setSectionContent(com.ext.portlet.plans.model.PlanItem pi,
        com.ext.portlet.plans.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setSectionContent(pi, psd, content,
            referencedPlans, updateAuthorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getAllPlanSections(
        com.ext.portlet.plans.model.PlanItem pi,
        com.ext.portlet.plans.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPlanSections(pi, psd);
    }

    public java.lang.Integer getRibbon(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getRibbon(pi);
    }

    public void setTeam(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTeam(pi, team);
    }

    public java.lang.String getTeam(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTeam(pi);
    }

    public void revertTo(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.revertTo(pi, updateAuthorId);
    }

    public java.lang.String getTags(com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTags(pi);
    }

    public void setTags(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTags(pi, tags);
    }

    public java.lang.String getTagsHover(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTagsHover(pi);
    }

    public void setTagsHover(com.ext.portlet.plans.model.PlanItem pi,
        java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTagsHover(pi, tagsHover);
    }

    public java.lang.Integer getTagsOrder(
        com.ext.portlet.plans.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTagsOrder(pi);
    }

    public void setTagsOrder(com.ext.portlet.plans.model.PlanItem pi,
        int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTagsOrder(pi, tagsOrder);
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
