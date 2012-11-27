package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanModelRun;

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
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanModelRun update(PlanModelRun planModelRun, boolean merge)
        throws SystemException {
        return getPersistence().update(planModelRun, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanModelRun update(PlanModelRun planModelRun, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planModelRun, merge, serviceContext);
    }

    /**
    * Caches the plan model run in the entity cache if it is enabled.
    *
    * @param planModelRun the plan model run
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanModelRun planModelRun) {
        getPersistence().cacheResult(planModelRun);
    }

    /**
    * Caches the plan model runs in the entity cache if it is enabled.
    *
    * @param planModelRuns the plan model runs
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanModelRun> planModelRuns) {
        getPersistence().cacheResult(planModelRuns);
    }

    /**
    * Creates a new plan model run with the primary key. Does not add the plan model run to the database.
    *
    * @param id the primary key for the new plan model run
    * @return the new plan model run
    */
    public static com.ext.portlet.plans.model.PlanModelRun create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan model run with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanModelRun updateImpl(
        com.ext.portlet.plans.model.PlanModelRun planModelRun, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planModelRun, merge);
    }

    /**
    * Returns the plan model run with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
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
    public static com.ext.portlet.plans.model.PlanModelRun fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns the plan model run where planId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun findByCurrentByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
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
    public static com.ext.portlet.plans.model.PlanModelRun fetchByCurrentByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
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
    public static com.ext.portlet.plans.model.PlanModelRun fetchByCurrentByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    /**
    * Returns all the plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    /**
    * Returns a range of all the plan model runs where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @return the range of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan model runs where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan model run in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan model run in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan model runs before and after the current plan model run in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan model run
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Returns the plan model run where planId = &#63; and planVersion &le; &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun findByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns the plan model run where planId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun fetchByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns the plan model run where planId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanModelRun fetchByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planVersion,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion(planId, planVersion,
            retrieveFromCache);
    }

    /**
    * Returns all the plan model runs.
    *
    * @return the plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan model runs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @return the range of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan model runs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan model run where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCurrentByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCurrentByPlanId(planId);
    }

    /**
    * Removes all the plan model runs where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    /**
    * Removes the plan model run where planId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanIdPlanVersion(java.lang.Long planId,
        java.lang.Long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanIdPlanVersion(planId, planVersion);
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
    * Returns the number of plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    /**
    * Returns the number of plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    /**
    * Returns the number of plan model runs where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanIdPlanVersion(java.lang.Long planId,
        java.lang.Long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanIdPlanVersion(planId, planVersion);
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
            _persistence = (PlanModelRunPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanModelRunPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanModelRunUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanModelRunPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanModelRunUtil.class,
            "_persistence");
    }
}
