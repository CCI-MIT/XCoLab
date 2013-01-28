package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTeamHistory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan team history service. This utility wraps {@link PlanTeamHistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryPersistence
 * @see PlanTeamHistoryPersistenceImpl
 * @generated
 */
public class PlanTeamHistoryUtil {
    private static PlanTeamHistoryPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(PlanTeamHistory planTeamHistory) {
        getPersistence().clearCache(planTeamHistory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<PlanTeamHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanTeamHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanTeamHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanTeamHistory update(PlanTeamHistory planTeamHistory,
        boolean merge) throws SystemException {
        return getPersistence().update(planTeamHistory, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanTeamHistory update(PlanTeamHistory planTeamHistory,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planTeamHistory, merge, serviceContext);
    }

    /**
    * Caches the plan team history in the entity cache if it is enabled.
    *
    * @param planTeamHistory the plan team history
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanTeamHistory planTeamHistory) {
        getPersistence().cacheResult(planTeamHistory);
    }

    /**
    * Caches the plan team histories in the entity cache if it is enabled.
    *
    * @param planTeamHistories the plan team histories
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanTeamHistory> planTeamHistories) {
        getPersistence().cacheResult(planTeamHistories);
    }

    /**
    * Creates a new plan team history with the primary key. Does not add the plan team history to the database.
    *
    * @param id the primary key for the new plan team history
    * @return the new plan team history
    */
    public static com.ext.portlet.model.PlanTeamHistory create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan team history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history that was removed
    * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory remove(long id)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanTeamHistory updateImpl(
        com.ext.portlet.model.PlanTeamHistory planTeamHistory, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planTeamHistory, merge);
    }

    /**
    * Returns the plan team history with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTeamHistoryException} if it could not be found.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history
    * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan team history with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history, or <code>null</code> if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan team histories where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTeamHistory> findByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(planId);
    }

    /**
    * Returns a range of all the plan team histories where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan team histories
    * @param end the upper bound of the range of plan team histories (not inclusive)
    * @return the range of matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTeamHistory> findByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan team histories where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan team histories
    * @param end the upper bound of the range of plan team histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTeamHistory> findByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan team history in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan team history
    * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory findByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan team history in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan team history
    * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory findByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan team histories before and after the current plan team history in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan team history
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan team history
    * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory[] findByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Returns the plan team history where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanTeamHistoryException} if it could not be found.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan team history
    * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory findByLastUserActionInPlan(
        long planId, long userId)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByLastUserActionInPlan(planId, userId);
    }

    /**
    * Returns the plan team history where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan team history, or <code>null</code> if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory fetchByLastUserActionInPlan(
        long planId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByLastUserActionInPlan(planId, userId);
    }

    /**
    * Returns the plan team history where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan team history, or <code>null</code> if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTeamHistory fetchByLastUserActionInPlan(
        long planId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLastUserActionInPlan(planId, userId,
            retrieveFromCache);
    }

    /**
    * Returns all the plan team histories.
    *
    * @return the plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTeamHistory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan team histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan team histories
    * @param end the upper bound of the range of plan team histories (not inclusive)
    * @return the range of plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTeamHistory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan team histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan team histories
    * @param end the upper bound of the range of plan team histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTeamHistory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan team histories where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    /**
    * Removes the plan team history where planId = &#63; and userId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByLastUserActionInPlan(long planId, long userId)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByLastUserActionInPlan(planId, userId);
    }

    /**
    * Removes all the plan team histories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan team histories where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    /**
    * Returns the number of plan team histories where planId = &#63; and userId = &#63;.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the number of matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByLastUserActionInPlan(long planId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByLastUserActionInPlan(planId, userId);
    }

    /**
    * Returns the number of plan team histories.
    *
    * @return the number of plan team histories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTeamHistoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanTeamHistoryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanTeamHistoryPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanTeamHistoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanTeamHistoryPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanTeamHistoryUtil.class,
            "_persistence");
    }
}
