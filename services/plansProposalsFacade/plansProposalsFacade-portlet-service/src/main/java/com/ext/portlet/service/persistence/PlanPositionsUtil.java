package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPositions;

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
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanPositions update(PlanPositions planPositions)
        throws SystemException {
        return getPersistence().update(planPositions);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanPositions update(PlanPositions planPositions,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planPositions, serviceContext);
    }

    /**
    * Returns the plan positions where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanPositionsException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan positions
    * @throws com.ext.portlet.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions findByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanPositionsException,
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
    public static com.ext.portlet.model.PlanPositions fetchByCurrentByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
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
    public static com.ext.portlet.model.PlanPositions fetchByCurrentByPlanId(
        long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    /**
    * Removes the plan positions where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @return the plan positions that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions removeByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByCurrentByPlanId(planId);
    }

    /**
    * Returns the number of plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    /**
    * Returns all the plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    /**
    * Returns a range of all the plan positionses where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @return the range of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan positionses where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan positions in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan positions
    * @throws com.ext.portlet.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions findByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the first plan positions in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan positions, or <code>null</code> if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions fetchByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan positions in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan positions
    * @throws com.ext.portlet.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions findByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the last plan positions in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan positions, or <code>null</code> if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions fetchByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan positionses before and after the current plan positions in the ordered set where planId = &#63;.
    *
    * @param id the primary key of the current plan positions
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan positions
    * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Removes all the plan positionses where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    /**
    * Returns the number of plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    /**
    * Caches the plan positions in the entity cache if it is enabled.
    *
    * @param planPositions the plan positions
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanPositions planPositions) {
        getPersistence().cacheResult(planPositions);
    }

    /**
    * Caches the plan positionses in the entity cache if it is enabled.
    *
    * @param planPositionses the plan positionses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanPositions> planPositionses) {
        getPersistence().cacheResult(planPositionses);
    }

    /**
    * Creates a new plan positions with the primary key. Does not add the plan positions to the database.
    *
    * @param id the primary key for the new plan positions
    * @return the new plan positions
    */
    public static com.ext.portlet.model.PlanPositions create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions that was removed
    * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions remove(long id)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanPositions updateImpl(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planPositions);
    }

    /**
    * Returns the plan positions with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPositionsException} if it could not be found.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions
    * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanPositionsException,
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
    public static com.ext.portlet.model.PlanPositions fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan positionses.
    *
    * @return the plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan positionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @return the range of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan positionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
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
            _persistence = (PlanPositionsPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanPositionsPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanPositionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanPositionsPersistence persistence) {
    }
}
