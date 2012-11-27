package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanPositions;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan positions service. This utility wraps {@link PlanPositionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsPersistence
 * @see PlanPositionsPersistenceImpl
 * @generated
 */
public class PlanPositionsUtil {
    private static PlanPositionsPersistence _persistence;

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
    public static void clearCache(PlanPositions planPositions) {
        getPersistence().clearCache(planPositions);
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
    public static List<PlanPositions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanPositions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanPositions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanPositions update(PlanPositions planPositions,
        boolean merge) throws SystemException {
        return getPersistence().update(planPositions, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanPositions update(PlanPositions planPositions,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planPositions, merge, serviceContext);
    }

    /**
    * Caches the plan positions in the entity cache if it is enabled.
    *
    * @param planPositions the plan positions
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanPositions planPositions) {
        getPersistence().cacheResult(planPositions);
    }

    /**
    * Caches the plan positionses in the entity cache if it is enabled.
    *
    * @param planPositionses the plan positionses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPositions> planPositionses) {
        getPersistence().cacheResult(planPositionses);
    }

    /**
    * Creates a new plan positions with the primary key. Does not add the plan positions to the database.
    *
    * @param id the primary key for the new plan positions
    * @return the new plan positions
    */
    public static com.ext.portlet.plans.model.PlanPositions create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanPositions updateImpl(
        com.ext.portlet.plans.model.PlanPositions planPositions, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planPositions, merge);
    }

    /**
    * Returns the plan positions with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanPositionsException} if it could not be found.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan positions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions, or <code>null</code> if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns the plan positions where planId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanPositionsException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions findByCurrentByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan positions where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan positions, or <code>null</code> if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions fetchByCurrentByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan positions where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan positions, or <code>null</code> if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions fetchByCurrentByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    /**
    * Returns all the plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    /**
    * Returns a range of all the plan positionses where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @return the range of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan positionses where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan positions in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan positions in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan positionses before and after the current plan positions in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan positions
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPositions[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Returns all the plan positionses.
    *
    * @return the plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan positionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @return the range of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan positionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan positions where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCurrentByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCurrentByPlanId(planId);
    }

    /**
    * Removes all the plan positionses where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    /**
    * Removes all the plan positionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    /**
    * Returns the number of plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    /**
    * Returns the number of plan positionses.
    *
    * @return the number of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanPositionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanPositionsPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanPositionsPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanPositionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanPositionsPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanPositionsUtil.class,
            "_persistence");
    }
}
