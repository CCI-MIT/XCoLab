package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanPositions;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan positions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsPersistenceImpl
 * @see PlanPositionsUtil
 * @generated
 */
public interface PlanPositionsPersistence extends BasePersistence<PlanPositions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanPositionsUtil} to access the plan positions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan positions in the entity cache if it is enabled.
    *
    * @param planPositions the plan positions
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanPositions planPositions);

    /**
    * Caches the plan positionses in the entity cache if it is enabled.
    *
    * @param planPositionses the plan positionses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPositions> planPositionses);

    /**
    * Creates a new plan positions with the primary key. Does not add the plan positions to the database.
    *
    * @param id the primary key for the new plan positions
    * @return the new plan positions
    */
    public com.ext.portlet.plans.model.PlanPositions create(java.lang.Long id);

    /**
    * Removes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPositions remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanPositions updateImpl(
        com.ext.portlet.plans.model.PlanPositions planPositions, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanPositionsException} if it could not be found.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPositions findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions, or <code>null</code> if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPositions fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions where planId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanPositionsException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan positions
    * @throws com.ext.portlet.plans.NoSuchPlanPositionsException if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPositions findByCurrentByPlanId(
        java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan positions, or <code>null</code> if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPositions fetchByCurrentByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan positions, or <code>null</code> if a matching plan positions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPositions fetchByCurrentByPlanId(
        java.lang.Long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findByAllByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.plans.model.PlanPositions findByAllByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.plans.model.PlanPositions findByAllByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.plans.model.PlanPositions[] findByAllByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan positionses.
    *
    * @return the plan positionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanPositions> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan positions where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCurrentByPlanId(java.lang.Long planId)
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan positionses where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan positionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public int countByCurrentByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan positionses where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan positionses
    * @throws SystemException if a system exception occurred
    */
    public int countByAllByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan positionses.
    *
    * @return the number of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
