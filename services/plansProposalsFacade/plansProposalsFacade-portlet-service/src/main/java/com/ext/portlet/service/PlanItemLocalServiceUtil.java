package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan item local service. This utility wraps {@link com.ext.portlet.service.impl.PlanItemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemLocalService
 * @see com.ext.portlet.service.base.PlanItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemLocalServiceImpl
 * @generated
 */
public class PlanItemLocalServiceUtil {
    private static PlanItemLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan item to the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItem addPlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanItem(planItem);
    }

    /**
    * Creates a new plan item with the primary key. Does not add the plan item to the database.
    *
    * @param id the primary key for the new plan item
    * @return the new plan item
    */
    public static com.ext.portlet.model.PlanItem createPlanItem(long id) {
        return getService().createPlanItem(id);
    }

    /**
    * Deletes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanItem(id);
    }

    /**
    * Deletes the plan item from the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanItem(com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanItem(planItem);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static com.ext.portlet.model.PlanItem fetchPlanItem(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanItem(id);
    }

    /**
    * Returns the plan item with the primary key.
    *
    * @param id the primary key of the plan item
    * @return the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItem getPlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanItem(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.ext.portlet.model.PlanItem> getPlanItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanItems(start, end);
    }

    /**
    * Returns the number of plan items.
    *
    * @return the number of plan items
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanItemsCount();
    }

    /**
    * Updates the plan item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanItem(planItem);
    }

    /**
    * Updates the plan item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @param merge whether to merge the plan item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanItem(planItem, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    /**
    * Default forum category description.
    */
    public static com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.ContestPhase phase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlan(phase, authorId);
    }

    public static com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlan(basePlan, authorId);
    }

    public static com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan,
        com.ext.portlet.model.ContestPhase contestPhase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlan(basePlan, contestPhase, authorId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlans();
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlansInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansInContestPhase(contestPhaseId);
    }

    public static com.ext.portlet.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlan(planId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planTypeId,
            contestPhaseId, start, end, sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planTypeId,
            contestPhaseId, start, end, sortColumn, sortDirection, applyFilters);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phases, start,
            end, sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phases, start,
            end, sortColumn, sortDirection, applyFilters);
    }

    public static boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isNameAvailable(planName, c);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().applyFilters(sessionMap, requestMap, planType, plans);
    }

    public static void removePlanWithEntireHistory(java.lang.Long planId) {
        getService().removePlanWithEntireHistory(planId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getAllVersions(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllVersions(plan);
    }

    public static java.util.List<com.ext.portlet.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributes(plan);
    }

    public static com.ext.portlet.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttribute(plan, name);
    }

    public static void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().reIndex();
    }

    public static void reIndex(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().reIndex(planId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlansForFocusArea(fa);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.model.OntologyTerm terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlansForOntologyTerms(terms);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.model.OntologyTerm> terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlansForOntologyTerms(terms);
    }

    public static long countPlansByContestPhase(
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().countPlansByContestPhase(phase);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansByContest(contestId);
    }

    public static void planDeleted(com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().planDeleted(plan);
    }

    /**
    * methods from PlanItemImpl.java *
    */
    public static java.lang.String getDescription(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDescription(pi);
    }

    public static java.lang.String getName(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getName(pi);
    }

    public static java.lang.Long getImageId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImageId(pi);
    }

    public static java.lang.String getPitch(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPitch(pi);
    }

    public static com.liferay.portal.model.Image getImage(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getImage(pi);
    }

    public static void setDescription(com.ext.portlet.model.PlanItem pi,
        java.lang.String description, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setDescription(pi, description, updateAuthorId);
    }

    public static void setName(com.ext.portlet.model.PlanItem pi,
        java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setName(pi, name, updateAuthorId);
    }

    public static void setImage(com.ext.portlet.model.PlanItem pi,
        java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setImage(pi, imageId, updateAuthorId);
    }

    public static void setPitch(com.ext.portlet.model.PlanItem pi,
        java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException {
        getService().setPitch(pi, pitch, updateAuthorId);
    }

    public static java.util.List<com.ext.portlet.model.PlanDescription> getAllDescriptionVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllDescriptionVersions(pi);
    }

    /**
    * List of all versions of PlanDescription objects related to given plan
    *
    * @see com.ext.portlet.model.PlanItem#getPlanDescriptions()
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> getPlanDescriptions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanDescriptions(pi);
    }

    public static java.lang.Long getScenarioId(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getScenarioId(pi);
    }

    public static void setScenarioId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setScenarioId(pi, scenarioId, authorId);
    }

    public static void setModelId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setModelId(pi, simulationId, authorId);
    }

    public static java.util.List<com.ext.portlet.model.PlanModelRun> getAllPlanModelRuns(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllPlanModelRuns(pi);
    }

    public static com.ext.portlet.model.PlanMeta getPlanMeta(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanMeta(pi);
    }

    public static java.util.List<com.ext.portlet.model.PlanMeta> getAllPlanMetas(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllPlanMetas(pi);
    }

    public static java.lang.Long getPlanTypeId(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTypeId(pi);
    }

    public static com.ext.portlet.model.PlanType getPlanType(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanType(pi);
    }

    public static com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContest(pi);
    }

    public static com.ext.portlet.model.ContestPhase getContestPhase(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhase(pi);
    }

    public static void setContestPhase(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.ContestPhase phase, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setContestPhase(pi, phase, updateAuthorId);
    }

    public static void setPlanTypeId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long planTypeId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setPlanTypeId(pi, planTypeId, updateAuthorId);
    }

    public static java.lang.Long getMBCategoryId(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMBCategoryId(pi);
    }

    public static void setMBCategoryId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long mbCategoryId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setMBCategoryId(pi, mbCategoryId, updateAuthorId);
    }

    public static java.lang.Long getCategoryGroupId(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCategoryGroupId(pi);
    }

    public static void setCategoryGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long categoryGroupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setCategoryGroupId(pi, categoryGroupId, updateAuthorId);
    }

    public static java.lang.Long getPlanGroupId(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanGroupId(pi);
    }

    public static void setPlanGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long groupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setPlanGroupId(pi, groupId, updateAuthorId);
    }

    public static java.lang.Long getAuthorId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAuthorId(pi);
    }

    public static com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAuthor(pi);
    }

    public static void setAuthorId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long authorId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setAuthorId(pi, authorId, updateAuthorId);
    }

    public static java.util.Date getCreateDate(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCreateDate(pi);
    }

    public static java.util.Date getPublishDate(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPublishDate(pi);
    }

    public static java.lang.String getCreator(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCreator(pi);
    }

    public static java.lang.Integer getVotes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getVotes(pi);
    }

    public static boolean getOpen(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getOpen(pi);
    }

    public static void setOpen(com.ext.portlet.model.PlanItem pi, boolean open,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setOpen(pi, open, updateAuthorId);
    }

    public static void setOpen(com.ext.portlet.model.PlanItem pi, boolean open)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setOpen(pi, open);
    }

    public static java.lang.String getStatus(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getStatus(pi);
    }

    public static void setStatus(com.ext.portlet.model.PlanItem pi,
        java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setStatus(pi, status, updateAuthorId);
    }

    public static com.ext.portlet.model.PlanPositions getPlanPositions(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositions(pi);
    }

    public static java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPositionsIds(pi);
    }

    public static java.lang.Long[] getPositionsIdsArray(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPositionsIdsArray(pi);
    }

    public static void setPositions(com.ext.portlet.model.PlanItem pi,
        java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setPositions(pi, positionsIds, updateAuthorId);
    }

    public static java.util.List<com.ext.portlet.model.PlanPositions> getAllPositionsVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllPositionsVersions(pi);
    }

    public static boolean hasUserVoted(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().hasUserVoted(pi, userId);
    }

    public static void vote(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().vote(pi, userId);
    }

    public static void unvote(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().unvote(pi, userId);
    }

    public static void store(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(pi);
    }

    /**
    * Updates values of all available attributes.
    *
    * @throws SystemException
    */
    public static void updateAllAttributes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().updateAllAttributes(pi);
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
    public static void updateAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().updateAttribute(pi, attributeName);
    }

    /**
    * Returns list of plan members.
    */
    public static java.util.List<com.liferay.portal.model.User> getMembers(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMembers(pi);
    }

    public static java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMembershipRequests(pi);
    }

    public static void addMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addMembershipRequest(pi, userId, comments);
    }

    public static void dennyMembershipRequest(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .dennyMembershipRequest(pi, userId, request, reply, updateAuthorId);
    }

    public static void approveMembershipRequest(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .approveMembershipRequest(pi, userId, request, reply, updateAuthorId);
    }

    public static void publish(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().publish(pi, updateAuthorId);
    }

    public static void delete(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().delete(pi, updateAuthorId);
    }

    public static com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUpdateAuthor(pi);
    }

    public static java.util.List<com.ext.portlet.model.PlanFan> getFans(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFans(pi);
    }

    public static com.ext.portlet.model.PlanFan addFan(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addFan(pi, userId);
    }

    public static void removeFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().removeFan(pi, userId);
    }

    public static boolean isUserAFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().isUserAFan(pi, userId);
    }

    public static boolean isUserAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().isUserAMember(pi, userId);
    }

    public static boolean hasUserRequestedMembership(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().hasUserRequestedMembership(pi, userId);
    }

    public static boolean isAdmin(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isAdmin(pi, userId);
    }

    public static boolean isOwner(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isOwner(pi, userId);
    }

    public static void setUserPermission(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String userPermission,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .setUserPermission(pi, userId, userPermission, updateAuthorId);
    }

    public static void removeMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().removeMember(pi, userId, updateAuthorId);
    }

    public static void joinIfNotAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().joinIfNotAMember(pi, userId);
    }

    public static void setSeekingAssistance(com.ext.portlet.model.PlanItem pi,
        boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setSeekingAssistance(pi, seekingAssistance);
    }

    public static boolean isSeekingAssistance(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().isSeekingAssistance(pi);
    }

    public static com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategoryGroup(pi);
    }

    public static com.ext.portlet.model.PlanItem promote(
        com.ext.portlet.model.PlanItem pi, com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().promote(pi, user);
    }

    public static boolean getPromoted(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPromoted(pi);
    }

    public static int getCommentsCount(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCommentsCount(pi);
    }

    public static void setPlace(com.ext.portlet.model.PlanItem pi, int place)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setPlace(pi, place);
    }

    public static void removePlace(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().removePlace(pi);
    }

    public static java.util.List<com.ext.portlet.model.PlanVote> getPlanVotes(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanVotes(pi);
    }

    public static void setRibbon(com.ext.portlet.model.PlanItem pi,
        java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setRibbon(pi, ribbon);
    }

    public static void setRibbonText(com.ext.portlet.model.PlanItem pi,
        java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setRibbonText(pi, ribbonText);
    }

    public static void setAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName, java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setAttribute(pi, attributeName, value);
    }

    public static void removeAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().removeAttribute(pi, attributeName);
    }

    public static com.ext.portlet.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTemplate(pi);
    }

    public static java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSections(pi);
    }

    public static void setSectionContent(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .setSectionContent(pi, psd, content, referencedPlans, updateAuthorId);
    }

    public static java.util.List<com.ext.portlet.model.PlanSection> getAllPlanSections(
        com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllPlanSections(pi, psd);
    }

    public static java.lang.Integer getRibbon(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getRibbon(pi);
    }

    public static void setTeam(com.ext.portlet.model.PlanItem pi,
        java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setTeam(pi, team);
    }

    public static java.lang.String getTeam(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getTeam(pi);
    }

    public static void revertTo(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().revertTo(pi, updateAuthorId);
    }

    public static java.lang.String getTags(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getTags(pi);
    }

    public static void setTags(com.ext.portlet.model.PlanItem pi,
        java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setTags(pi, tags);
    }

    public static java.lang.String getTagsHover(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getTagsHover(pi);
    }

    public static void setTagsHover(com.ext.portlet.model.PlanItem pi,
        java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setTagsHover(pi, tagsHover);
    }

    public static java.lang.Integer getTagsOrder(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getTagsOrder(pi);
    }

    public static void setTagsOrder(com.ext.portlet.model.PlanItem pi,
        int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setTagsOrder(pi, tagsOrder);
    }

    public static void promotePlans(long sourcePhasePk, long destPhasePk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().promotePlans(sourcePhasePk, destPhasePk);
    }

    public static void promotePlans(
        java.util.List<com.ext.portlet.model.PlanItem> plansToBeCopied,
        long destPhasePk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().promotePlans(plansToBeCopied, destPhasePk);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanItemLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanItemLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanItemLocalService.class.getName(), portletClassLoader);

            _service = new PlanItemLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanItemLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanItemLocalService.class);
        }

        return _service;
    }

    public void setService(PlanItemLocalService service) {
        MethodCache.remove(PlanItemLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanItemLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanItemLocalService.class);
    }
}
