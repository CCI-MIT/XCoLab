package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanItemLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemLocalService
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
    @Override
    public com.ext.portlet.model.PlanItem addPlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.addPlanItem(planItem);
    }

    /**
    * Creates a new plan item with the primary key. Does not add the plan item to the database.
    *
    * @param id the primary key for the new plan item
    * @return the new plan item
    */
    @Override
    public com.ext.portlet.model.PlanItem createPlanItem(long id) {
        return _planItemLocalService.createPlanItem(id);
    }

    /**
    * Deletes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @return the plan item that was removed
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanItem deletePlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.deletePlanItem(id);
    }

    /**
    * Deletes the plan item from the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanItem deletePlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.deletePlanItem(planItem);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planItemLocalService.dynamicQuery();
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
        return _planItemLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planItemLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planItemLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.ext.portlet.model.PlanItem fetchPlanItem(long id)
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
    @Override
    public com.ext.portlet.model.PlanItem getPlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanItem(id);
    }

    @Override
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan items
    * @param end the upper bound of the range of plan items (not inclusive)
    * @return the range of plan items
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlanItems(
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
    @Override
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
    @Override
    public com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.updatePlanItem(planItem);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planItemLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planItemLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planItemLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
    * Default forum category description.
    */
    @Override
    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.ContestPhase phase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.createPlan(phase, authorId);
    }

    @Override
    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.createPlan(basePlan, authorId);
    }

    @Override
    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan,
        com.ext.portlet.model.ContestPhase contestPhase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.createPlan(basePlan, contestPhase, authorId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlansForUser(
        long userId) {
        return _planItemLocalService.getPlansForUser(userId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans();
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlansInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlansInContestPhase(contestPhaseId);
    }

    @Override
    public com.ext.portlet.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlan(planId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap,
            planTypeId, contestPhaseId, start, end, sortColumn, sortDirection);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap,
            planTypeId, contestPhaseId, start, end, sortColumn, sortDirection,
            applyFilters);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap, planType,
            phases, start, end, sortColumn, sortDirection);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlans(sessionMap, requestMap, planType,
            phases, start, end, sortColumn, sortDirection, applyFilters);
    }

    @Override
    public boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isNameAvailable(planName, c);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.applyFilters(sessionMap, requestMap,
            planType, plans);
    }

    @Override
    public void removePlanWithEntireHistory(java.lang.Long planId) {
        _planItemLocalService.removePlanWithEntireHistory(planId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getAllVersions(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllVersions(plan);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanAttributes(plan);
    }

    @Override
    public com.ext.portlet.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanAttribute(plan, name);
    }

    @Override
    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.reIndex();
    }

    @Override
    public void reIndex(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.reIndex(planId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.findPlansForFocusArea(fa);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.model.OntologyTerm terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.findPlansForOntologyTerms(terms);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.model.OntologyTerm> terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.findPlansForOntologyTerms(terms);
    }

    @Override
    public long countPlansByContestPhase(
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.countPlansByContestPhase(phase);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlansByContest(contestId);
    }

    @Override
    public void planDeleted(com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.planDeleted(plan);
    }

    /**
    * methods from PlanItemImpl.java *
    */
    @Override
    public java.lang.String getDescription(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getDescription(pi);
    }

    @Override
    public java.lang.String getName(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getName(pi);
    }

    @Override
    public java.lang.Long getImageId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getImageId(pi);
    }

    @Override
    public java.lang.String getPitch(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPitch(pi);
    }

    @Override
    public com.liferay.portal.model.Image getImage(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getImage(pi);
    }

    @Override
    public void setDescription(com.ext.portlet.model.PlanItem pi,
        java.lang.String description, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setDescription(pi, description, updateAuthorId);
    }

    @Override
    public void setName(com.ext.portlet.model.PlanItem pi,
        java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setName(pi, name, updateAuthorId);
    }

    @Override
    public void setImage(com.ext.portlet.model.PlanItem pi,
        java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setImage(pi, imageId, updateAuthorId);
    }

    @Override
    public void setPitch(com.ext.portlet.model.PlanItem pi,
        java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException {
        _planItemLocalService.setPitch(pi, pitch, updateAuthorId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanDescription> getAllDescriptionVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllDescriptionVersions(pi);
    }

    /**
    * List of all versions of PlanDescription objects related to given plan
    *
    * @see com.ext.portlet.model.PlanItem#getPlanDescriptions()
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanDescription> getPlanDescriptions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanDescriptions(pi);
    }

    @Override
    public java.lang.Long getScenarioId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getScenarioId(pi);
    }

    @Override
    public void setScenarioId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setScenarioId(pi, scenarioId, authorId);
    }

    @Override
    public void setModelId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setModelId(pi, simulationId, authorId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanModelRun> getAllPlanModelRuns(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPlanModelRuns(pi);
    }

    @Override
    public com.ext.portlet.model.PlanMeta getPlanMeta(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanMeta(pi);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanMeta> getAllPlanMetas(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPlanMetas(pi);
    }

    @Override
    public java.lang.Long getPlanTypeId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanTypeId(pi);
    }

    @Override
    public com.ext.portlet.model.PlanType getPlanType(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanType(pi);
    }

    @Override
    public com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getContest(pi);
    }

    @Override
    public com.ext.portlet.model.ContestPhase getContestPhase(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getContestPhase(pi);
    }

    @Override
    public void setContestPhase(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.ContestPhase phase, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setContestPhase(pi, phase, updateAuthorId);
    }

    @Override
    public void setPlanTypeId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long planTypeId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPlanTypeId(pi, planTypeId, updateAuthorId);
    }

    @Override
    public java.lang.Long getMBCategoryId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getMBCategoryId(pi);
    }

    @Override
    public void setMBCategoryId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long mbCategoryId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setMBCategoryId(pi, mbCategoryId, updateAuthorId);
    }

    @Override
    public java.lang.Long getCategoryGroupId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCategoryGroupId(pi);
    }

    @Override
    public void setCategoryGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long categoryGroupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setCategoryGroupId(pi, categoryGroupId,
            updateAuthorId);
    }

    @Override
    public java.lang.Long getPlanGroupId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanGroupId(pi);
    }

    @Override
    public void setPlanGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long groupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPlanGroupId(pi, groupId, updateAuthorId);
    }

    @Override
    public java.lang.Long getAuthorId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAuthorId(pi);
    }

    @Override
    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAuthor(pi);
    }

    @Override
    public void setAuthorId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long authorId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setAuthorId(pi, authorId, updateAuthorId);
    }

    @Override
    public java.util.Date getCreateDate(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCreateDate(pi);
    }

    @Override
    public java.util.Date getPublishDate(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPublishDate(pi);
    }

    @Override
    public java.lang.String getCreator(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCreator(pi);
    }

    @Override
    public java.lang.Integer getVotes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getVotes(pi);
    }

    @Override
    public boolean getOpen(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getOpen(pi);
    }

    @Override
    public void setOpen(com.ext.portlet.model.PlanItem pi, boolean open,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setOpen(pi, open, updateAuthorId);
    }

    @Override
    public void setOpen(com.ext.portlet.model.PlanItem pi, boolean open)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setOpen(pi, open);
    }

    @Override
    public java.lang.String getStatus(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getStatus(pi);
    }

    @Override
    public void setStatus(com.ext.portlet.model.PlanItem pi,
        java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setStatus(pi, status, updateAuthorId);
    }

    @Override
    public com.ext.portlet.model.PlanPositions getPlanPositions(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanPositions(pi);
    }

    @Override
    public java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPositionsIds(pi);
    }

    @Override
    public java.lang.Long[] getPositionsIdsArray(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPositionsIdsArray(pi);
    }

    @Override
    public void setPositions(com.ext.portlet.model.PlanItem pi,
        java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPositions(pi, positionsIds, updateAuthorId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanPositions> getAllPositionsVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPositionsVersions(pi);
    }

    @Override
    public boolean hasUserVoted(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.hasUserVoted(pi, userId);
    }

    @Override
    public void vote(com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.vote(pi, userId);
    }

    @Override
    public void unvote(com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.unvote(pi, userId);
    }

    @Override
    public void store(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.store(pi);
    }

    /**
    * Updates values of all available attributes.
    *
    * @throws SystemException
    */
    @Override
    public void updateAllAttributes(com.ext.portlet.model.PlanItem pi)
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
    @Override
    public void updateAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.updateAttribute(pi, attributeName);
    }

    /**
    * Returns list of plan members.
    */
    @Override
    public java.util.List<com.liferay.portal.model.User> getMembers(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getMembers(pi);
    }

    @Override
    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getMembershipRequests(pi);
    }

    @Override
    public void addMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.addMembershipRequest(pi, userId, comments);
    }

    @Override
    public void dennyMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.dennyMembershipRequest(pi, userId, request,
            reply, updateAuthorId);
    }

    @Override
    public void approveMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.approveMembershipRequest(pi, userId, request,
            reply, updateAuthorId);
    }

    @Override
    public void publish(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.publish(pi, updateAuthorId);
    }

    @Override
    public void delete(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.delete(pi, updateAuthorId);
    }

    @Override
    public com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getUpdateAuthor(pi);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanFan> getFans(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getFans(pi);
    }

    @Override
    public com.ext.portlet.model.PlanFan addFan(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.addFan(pi, userId);
    }

    @Override
    public void removeFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removeFan(pi, userId);
    }

    @Override
    public boolean isUserAFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isUserAFan(pi, userId);
    }

    @Override
    public boolean isUserAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isUserAMember(pi, userId);
    }

    @Override
    public boolean hasUserRequestedMembership(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.hasUserRequestedMembership(pi, userId);
    }

    @Override
    public boolean isAdmin(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isAdmin(pi, userId);
    }

    @Override
    public boolean isOwner(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isOwner(pi, userId);
    }

    @Override
    public void setUserPermission(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String userPermission,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setUserPermission(pi, userId, userPermission,
            updateAuthorId);
    }

    @Override
    public void removeMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removeMember(pi, userId, updateAuthorId);
    }

    @Override
    public void joinIfNotAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.joinIfNotAMember(pi, userId);
    }

    @Override
    public void setSeekingAssistance(com.ext.portlet.model.PlanItem pi,
        boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setSeekingAssistance(pi, seekingAssistance);
    }

    @Override
    public boolean isSeekingAssistance(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.isSeekingAssistance(pi);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getDiscussionCategoryGroup(pi);
    }

    @Override
    public com.ext.portlet.model.PlanItem promote(
        com.ext.portlet.model.PlanItem pi, com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.promote(pi, user);
    }

    @Override
    public boolean getPromoted(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPromoted(pi);
    }

    @Override
    public int getCommentsCount(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getCommentsCount(pi);
    }

    @Override
    public void setPlace(com.ext.portlet.model.PlanItem pi, int place)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setPlace(pi, place);
    }

    @Override
    public void removePlace(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removePlace(pi);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanVote> getPlanVotes(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanVotes(pi);
    }

    @Override
    public void setRibbon(com.ext.portlet.model.PlanItem pi,
        java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setRibbon(pi, ribbon);
    }

    @Override
    public void setRibbonText(com.ext.portlet.model.PlanItem pi,
        java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setRibbonText(pi, ribbonText);
    }

    @Override
    public void setAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName, java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setAttribute(pi, attributeName, value);
    }

    @Override
    public void removeAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.removeAttribute(pi, attributeName);
    }

    @Override
    public com.ext.portlet.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanTemplate(pi);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getPlanSections(pi);
    }

    @Override
    public void setSectionContent(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setSectionContent(pi, psd, content,
            referencedPlans, updateAuthorId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanSection> getAllPlanSections(
        com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getAllPlanSections(pi, psd);
    }

    @Override
    public java.lang.Integer getRibbon(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getRibbon(pi);
    }

    @Override
    public void setTeam(com.ext.portlet.model.PlanItem pi, java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTeam(pi, team);
    }

    @Override
    public java.lang.String getTeam(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTeam(pi);
    }

    @Override
    public void revertTo(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.revertTo(pi, updateAuthorId);
    }

    @Override
    public java.lang.String getTags(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTags(pi);
    }

    @Override
    public void setTags(com.ext.portlet.model.PlanItem pi, java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTags(pi, tags);
    }

    @Override
    public java.lang.String getTagsHover(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTagsHover(pi);
    }

    @Override
    public void setTagsHover(com.ext.portlet.model.PlanItem pi,
        java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTagsHover(pi, tagsHover);
    }

    @Override
    public java.lang.Integer getTagsOrder(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemLocalService.getTagsOrder(pi);
    }

    @Override
    public void setTagsOrder(com.ext.portlet.model.PlanItem pi, int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.setTagsOrder(pi, tagsOrder);
    }

    @Override
    public void promotePlans(long sourcePhasePk, long destPhasePk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.promotePlans(sourcePhasePk, destPhasePk);
    }

    @Override
    public void promotePlans(
        java.util.List<com.ext.portlet.model.PlanItem> plansToBeCopied,
        long destPhasePk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemLocalService.promotePlans(plansToBeCopied, destPhasePk);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanItemLocalService getWrappedPlanItemLocalService() {
        return _planItemLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanItemLocalService(
        PlanItemLocalService planItemLocalService) {
        _planItemLocalService = planItemLocalService;
    }

    @Override
    public PlanItemLocalService getWrappedService() {
        return _planItemLocalService;
    }

    @Override
    public void setWrappedService(PlanItemLocalService planItemLocalService) {
        _planItemLocalService = planItemLocalService;
    }
}
