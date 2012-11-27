package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanFan;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan fan service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanFanPersistenceImpl
 * @see PlanFanUtil
 * @generated
 */
public interface PlanFanPersistence extends BasePersistence<PlanFan> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanFanUtil} to access the plan fan persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan fan in the entity cache if it is enabled.
    *
    * @param planFan the plan fan
    */
    public void cacheResult(com.ext.portlet.plans.model.PlanFan planFan);

    /**
    * Caches the plan fans in the entity cache if it is enabled.
    *
    * @param planFans the plan fans
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanFan> planFans);

    /**
    * Creates a new plan fan with the primary key. Does not add the plan fan to the database.
    *
    * @param id the primary key for the new plan fan
    * @return the new plan fan
    */
    public com.ext.portlet.plans.model.PlanFan create(java.lang.Long id);

    /**
    * Removes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanFan updateImpl(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fan with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanFanException} if it could not be found.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fan with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan, or <code>null</code> if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan fans where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan fans where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan fans where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan fan in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan fan in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fans before and after the current plan fan in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan fan
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan[] findByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan fans where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan fans where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan fans where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan fan in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan fan in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fans before and after the current plan fan in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan fan
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan[] findByUserId_PrevAndNext(
        java.lang.Long id, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fan where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanFanException} if it could not be found.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan fan
    * @throws com.ext.portlet.plans.NoSuchPlanFanException if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan findByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan fetchByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan fetchByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan fans.
    *
    * @return the plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan fans.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan fans.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan fans where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan fans where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan fan where planId = &#63; and userId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanIdUserId(java.lang.Long planId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan fans from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan fans where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan fans where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan fans where planId = &#63; and userId = &#63;.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the number of matching plan fans
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanIdUserId(java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan fans.
    *
    * @return the number of plan fans
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
