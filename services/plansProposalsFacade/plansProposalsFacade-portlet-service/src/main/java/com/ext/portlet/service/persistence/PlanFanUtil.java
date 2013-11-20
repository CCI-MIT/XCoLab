package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanFan;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan fan service. This utility wraps {@link PlanFanPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanFanPersistence
 * @see PlanFanPersistenceImpl
 * @generated
 */
public class PlanFanUtil {
    private static PlanFanPersistence _persistence;

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
    public static void clearCache(PlanFan planFan) {
        getPersistence().clearCache(planFan);
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
    public static List<PlanFan> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanFan> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanFan> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanFan update(PlanFan planFan) throws SystemException {
        return getPersistence().update(planFan);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanFan update(PlanFan planFan, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(planFan, serviceContext);
    }

    /**
    * Returns all the plan fans where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(planId);
    }

    /**
    * Returns a range of all the plan fans where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan fans where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan fan in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan findByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the first plan fan in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan fan in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan findByPlanId_Last(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the last plan fan in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan fans before and after the current plan fan in the ordered set where planId = &#63;.
    *
    * @param id the primary key of the current plan fan
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan[] findByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Removes all the plan fans where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    /**
    * Returns the number of plan fans where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    /**
    * Returns all the plan fans where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the plan fans where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the plan fans where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan fan in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the first plan fan in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last plan fan in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the last plan fan in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the plan fans before and after the current plan fan in the ordered set where userId = &#63;.
    *
    * @param id the primary key of the current plan fan
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan[] findByUserId_PrevAndNext(
        long id, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId_PrevAndNext(id, userId, orderByComparator);
    }

    /**
    * Removes all the plan fans where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Returns the number of plan fans where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Returns the plan fan where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanFanException} if it could not be found.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan findByPlanIdUserId(
        long planId, long userId)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanIdUserId(planId, userId);
    }

    /**
    * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByPlanIdUserId(
        long planId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanIdUserId(planId, userId);
    }

    /**
    * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByPlanIdUserId(
        long planId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdUserId(planId, userId, retrieveFromCache);
    }

    /**
    * Removes the plan fan where planId = &#63; and userId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the plan fan that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan removeByPlanIdUserId(
        long planId, long userId)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByPlanIdUserId(planId, userId);
    }

    /**
    * Returns the number of plan fans where planId = &#63; and userId = &#63;.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the number of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanIdUserId(long planId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanIdUserId(planId, userId);
    }

    /**
    * Caches the plan fan in the entity cache if it is enabled.
    *
    * @param planFan the plan fan
    */
    public static void cacheResult(com.ext.portlet.model.PlanFan planFan) {
        getPersistence().cacheResult(planFan);
    }

    /**
    * Caches the plan fans in the entity cache if it is enabled.
    *
    * @param planFans the plan fans
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanFan> planFans) {
        getPersistence().cacheResult(planFans);
    }

    /**
    * Creates a new plan fan with the primary key. Does not add the plan fan to the database.
    *
    * @param id the primary key for the new plan fan
    * @return the new plan fan
    */
    public static com.ext.portlet.model.PlanFan create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan that was removed
    * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan remove(long id)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanFan updateImpl(
        com.ext.portlet.model.PlanFan planFan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planFan);
    }

    /**
    * Returns the plan fan with the primary key or throws a {@link com.ext.portlet.NoSuchPlanFanException} if it could not be found.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan
    * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan fan with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan, or <code>null</code> if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanFan fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan fans.
    *
    * @return the plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan fans.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan fans.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan fans
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanFan> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan fans from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan fans.
    *
    * @return the number of plan fans
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanFanPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanFanPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanFanPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanFanUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanFanPersistence persistence) {
    }
}
