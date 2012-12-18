package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanModelRun;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan model run service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRunPersistenceImpl
 * @see PlanModelRunUtil
 * @generated
 */
public interface PlanModelRunPersistence extends BasePersistence<PlanModelRun> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanModelRunUtil} to access the plan model run persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan model run in the entity cache if it is enabled.
    *
    * @param planModelRun the plan model run
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanModelRun planModelRun);

    /**
    * Caches the plan model runs in the entity cache if it is enabled.
    *
    * @param planModelRuns the plan model runs
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanModelRun> planModelRuns);

    /**
    * Creates a new plan model run with the primary key. Does not add the plan model run to the database.
    *
    * @param id the primary key for the new plan model run
    * @return the new plan model run
    */
    public com.ext.portlet.plans.model.PlanModelRun create(long id);

    /**
    * Removes the plan model run with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun remove(long id)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanModelRun updateImpl(
        com.ext.portlet.plans.model.PlanModelRun planModelRun, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun findByPrimaryKey(long id)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run, or <code>null</code> if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run where planId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun findByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun fetchByCurrentByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun fetchByCurrentByPlanId(
        long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.plans.model.PlanModelRun findByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.plans.model.PlanModelRun findByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.plans.model.PlanModelRun[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run where planId = &#63; and planVersion &le; &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanModelRunException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan model run
    * @throws com.ext.portlet.plans.NoSuchPlanModelRunException if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun findByPlanIdPlanVersion(
        long planId, long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run where planId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun fetchByPlanIdPlanVersion(
        long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan model run where planId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan model run, or <code>null</code> if a matching plan model run could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanModelRun fetchByPlanIdPlanVersion(
        long planId, long planVersion, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan model runs.
    *
    * @return the plan model runs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan model run where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCurrentByPlanId(long planId)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan model runs where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan model run where planId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanIdPlanVersion(long planId, long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanModelRunException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan model runs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public int countByCurrentByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan model runs where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan model runs where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the number of matching plan model runs
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanIdPlanVersion(long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan model runs.
    *
    * @return the number of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
