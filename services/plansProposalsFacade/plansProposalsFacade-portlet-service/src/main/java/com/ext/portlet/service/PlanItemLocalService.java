package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the plan item local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemLocalServiceUtil
 * @see com.ext.portlet.service.base.PlanItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface PlanItemLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanItemLocalServiceUtil} to access the plan item local service. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the plan item to the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem addPlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new plan item with the primary key. Does not add the plan item to the database.
    *
    * @param id the primary key for the new plan item
    * @return the new plan item
    */
    public com.ext.portlet.model.PlanItem createPlanItem(long id);

    /**
    * Deletes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the plan item from the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItem(com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanItem fetchPlanItem(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item with the primary key.
    *
    * @param id the primary key of the plan item
    * @return the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanItem getPlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlanItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan items.
    *
    * @return the number of plan items
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getPlanItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the plan item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @param merge whether to merge the plan item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    /**
    * Default forum category description.
    */
    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.ContestPhase phase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan,
        com.ext.portlet.model.ContestPhase contestPhase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlansInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void removePlanWithEntireHistory(java.lang.Long planId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getAllVersions(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void reIndex(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.model.OntologyTerm terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.model.OntologyTerm> terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    public long countPlansByContest(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void planDeleted(com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * methods from PlanItemImpl.java *
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getDescription(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getName(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getImageId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getPitch(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.Image getImage(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setDescription(com.ext.portlet.model.PlanItem pi,
        java.lang.String description, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setName(com.ext.portlet.model.PlanItem pi,
        java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setImage(com.ext.portlet.model.PlanItem pi,
        java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setPitch(com.ext.portlet.model.PlanItem pi,
        java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanDescription> getAllDescriptionVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * List of all versions of PlanDescription objects related to given plan
    *
    * @see com.ext.portlet.model.PlanItem#getPlanDescriptions()
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanDescription> getPlanDescriptions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getScenarioId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setScenarioId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setModelId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanModelRun> getAllPlanModelRuns(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanMeta getPlanMeta(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanMeta> getAllPlanMetas(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getPlanTypeId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanType getPlanType(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ContestPhase getContestPhase(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setContestPhase(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.ContestPhase phase, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setPlanTypeId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long planTypeId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getMBCategoryId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setMBCategoryId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long mbCategoryId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getCategoryGroupId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setCategoryGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long categoryGroupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getPlanGroupId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setPlanGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long groupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getAuthorId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setAuthorId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long authorId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.Date getCreateDate(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.Date getPublishDate(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getCreator(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Integer getVotes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean getOpen(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setOpen(com.ext.portlet.model.PlanItem pi, boolean open,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setOpen(com.ext.portlet.model.PlanItem pi, boolean open)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getStatus(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setStatus(com.ext.portlet.model.PlanItem pi,
        java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanPositions getPlanPositions(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long[] getPositionsIdsArray(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setPositions(com.ext.portlet.model.PlanItem pi,
        java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanPositions> getAllPositionsVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserVoted(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void vote(com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void unvote(com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void store(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates values of all available attributes.
    *
    * @throws SystemException
    */
    public void updateAllAttributes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates value of a given attribute, should be used only for property
    * attributes.
    *
    * @param attributeName
    attribute which value should be updated
    * @throws SystemException
    in case of any error
    */
    public void updateAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns list of plan members.
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getMembers(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void addMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void dennyMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void approveMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void publish(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void delete(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanFan> getFans(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanFan addFan(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isUserAFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isUserAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserRequestedMembership(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isAdmin(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isOwner(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setUserPermission(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String userPermission,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void removeMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void joinIfNotAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setSeekingAssistance(com.ext.portlet.model.PlanItem pi,
        boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isSeekingAssistance(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanItem promote(
        com.ext.portlet.model.PlanItem pi, com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean getPromoted(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCommentsCount(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setPlace(com.ext.portlet.model.PlanItem pi, int place)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removePlace(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanVote> getPlanVotes(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setRibbon(com.ext.portlet.model.PlanItem pi,
        java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setRibbonText(com.ext.portlet.model.PlanItem pi,
        java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName, java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setSectionContent(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.PlanSection> getAllPlanSections(
        com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Integer getRibbon(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTeam(com.ext.portlet.model.PlanItem pi, java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getTeam(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void revertTo(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getTags(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTags(com.ext.portlet.model.PlanItem pi, java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getTagsHover(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTagsHover(com.ext.portlet.model.PlanItem pi,
        java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Integer getTagsOrder(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTagsOrder(com.ext.portlet.model.PlanItem pi, int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException;
}
