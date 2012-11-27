package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan item local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanItemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemLocalService
 * @see com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanItemLocalServiceImpl
 * @generated
 */
public class PlanItemLocalServiceUtil {
    private static PlanItemLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan item to the database. Also notifies the appropriate model listeners.
    *
    * @param planItem the plan item
    * @return the plan item that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanItem addPlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanItem(planItem);
    }

    /**
    * Creates a new plan item with the primary key. Does not add the plan item to the database.
    *
    * @param id the primary key for the new plan item
    * @return the new plan item
    */
    public static com.ext.portlet.plans.model.PlanItem createPlanItem(
        java.lang.Long id) {
        return getService().createPlanItem(id);
    }

    /**
    * Deletes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @throws PortalException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanItem(java.lang.Long id)
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
    public static void deletePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
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

    public static com.ext.portlet.plans.model.PlanItem fetchPlanItem(
        java.lang.Long id)
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
    public static com.ext.portlet.plans.model.PlanItem getPlanItem(
        java.lang.Long id)
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
    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlanItems(
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
    public static com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem)
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
    public static com.ext.portlet.plans.model.PlanItem updatePlanItem(
        com.ext.portlet.plans.model.PlanItem planItem, boolean merge)
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
    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlan(phase, authorId);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlan(basePlan, authorId);
    }

    public static com.ext.portlet.plans.model.PlanItem createPlan(
        com.ext.portlet.plans.model.PlanItem basePlan,
        com.ext.portlet.contests.model.ContestPhase contestPhase,
        java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlan(basePlan, contestPhase, authorId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlans();
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansInContestPhase(
        com.ext.portlet.contests.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansInContestPhase(contestPhase);
    }

    public static com.ext.portlet.plans.model.PlanItem getPlan(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlan(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phase, start,
            end, sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        com.ext.portlet.contests.model.ContestPhase phase, int start, int end,
        java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phase, start,
            end, sortColumn, sortDirection, applyFilters);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phases, start,
            end, sortColumn, sortDirection);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.contests.model.ContestPhase> phases,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlans(sessionMap, requestMap, planType, phases, start,
            end, sortColumn, sortDirection, applyFilters);
    }

    public static boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.contests.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isNameAvailable(planName, c);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType,
        java.util.List<com.ext.portlet.plans.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().applyFilters(sessionMap, requestMap, planType, plans);
    }

    public static void removePlanWithEntireHistory(java.lang.Long planId) {
        getService().removePlanWithEntireHistory(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllVersions(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributes(plan);
    }

    public static com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
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

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.ontology.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlansForFocusArea(fa);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.ontology.model.OntologyTerm terms)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlansForOntologyTerms(terms);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.ontology.model.OntologyTerm> terms)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlansForOntologyTerms(terms);
    }

    public static long countPlansByContest(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().countPlansByContest(contestId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansByContest(contestId);
    }

    public static void planDeleted(com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().planDeleted(plan);
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
