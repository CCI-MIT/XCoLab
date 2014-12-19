package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanModelRun;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan model run service. This utility wraps {@link PlanModelRunPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRunPersistence
 * @see PlanModelRunPersistenceImpl
 * @generated
 */
public class PlanModelRunUtil {
    private static PlanModelRunPersistence _persistence;

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
    public static void clearCache(PlanModelRun planModelRun) {
        getPersistence().clearCache(planModelRun);
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
    public static List<PlanModelRun> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanModelRun> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanModelRun> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanModelRun update(PlanModelRun planModelRun)
        throws SystemException {
        return getPersistence().update(planModelRun);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanModelRun update(PlanModelRun planModelRun,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planModelRun, serviceContext);
    }

    /**
    * Returns the plan model run where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun findByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan model run where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByCurrentByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan model run where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByCurrentByPlanId(
        long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    /**
    * Removes the plan model run where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @return the plan model run that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun removeByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByCurrentByPlanId(planId);
    }

    /**
    * Returns the number of plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    /**
    * Returns all the plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    /**
    * Returns a range of all the plan model runs where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @return the range of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan model runs where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan model run in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun findByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the first plan model run in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan model run in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun findByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the last plan model run in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan model runs before and after the current plan model run in the ordered set where planId = &#63;.
    *
    * @param id the primary key of the current plan model run
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Removes all the plan model runs where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    /**
    * Returns the number of plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    /**
    * Returns all the plan model runs where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findByPlanIdPlanVersion(
        long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns a range of all the plan model runs where planId = &#63; and planVersion &le; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @return the range of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findByPlanIdPlanVersion(
        long planId, long planVersion, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion(planId, planVersion, start, end);
    }

    /**
    * Returns an ordered range of all the plan model runs where planId = &#63; and planVersion &le; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findByPlanIdPlanVersion(
        long planId, long planVersion, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion(planId, planVersion, start, end,
            orderByComparator);
    }

    /**
    * Returns the first plan model run in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun findByPlanIdPlanVersion_First(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion_First(planId, planVersion,
            orderByComparator);
    }

    /**
    * Returns the first plan model run in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByPlanIdPlanVersion_First(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion_First(planId, planVersion,
            orderByComparator);
    }

    /**
    * Returns the last plan model run in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun findByPlanIdPlanVersion_Last(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion_Last(planId, planVersion,
            orderByComparator);
    }

    /**
    * Returns the last plan model run in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByPlanIdPlanVersion_Last(
        long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion_Last(planId, planVersion,
            orderByComparator);
    }

    /**
    * Returns the plan model runs before and after the current plan model run in the ordered set where planId = &#63; and planVersion &le; &#63;.
    *
    * @param id the primary key of the current plan model run
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun[] findByPlanIdPlanVersion_PrevAndNext(
        long id, long planId, long planVersion,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion_PrevAndNext(id, planId,
            planVersion, orderByComparator);
    }

    /**
    * Removes all the plan model runs where planId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanIdPlanVersion(long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns the number of plan model runs where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanIdPlanVersion(long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Caches the plan model run in the entity cache if it is enabled.
    *
    * @param planModelRun the plan model run
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanModelRun planModelRun) {
        getPersistence().cacheResult(planModelRun);
    }

    /**
    * Caches the plan model runs in the entity cache if it is enabled.
    *
    * @param planModelRuns the plan model runs
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanModelRun> planModelRuns) {
        getPersistence().cacheResult(planModelRuns);
    }

    /**
    * Creates a new plan model run with the primary key. Does not add the plan model run to the database.
    *
    * @param id the primary key for the new plan model run
    * @return the new plan model run
    */
    public static com.ext.portlet.model.PlanModelRun create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan model run with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run that was removed
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun remove(long id)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanModelRun updateImpl(
        com.ext.portlet.model.PlanModelRun planModelRun)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planModelRun);
    }

    /**
    * Returns the plan model run with the primary key or throws a {@link com.ext.portlet.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run
    * @throws com.ext.portlet.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan model run with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run, or <code>null</code> if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan model runs.
    *
    * @return the plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan model runs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @return the range of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan model runs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan model runs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan model runs.
    *
    * @return the number of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanModelRunPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanModelRunPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanModelRunPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanModelRunUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanModelRunPersistence persistence) {
    }
}
