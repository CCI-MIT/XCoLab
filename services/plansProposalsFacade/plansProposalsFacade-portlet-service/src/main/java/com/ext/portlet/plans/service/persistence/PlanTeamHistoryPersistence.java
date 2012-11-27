package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanTeamHistory;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan team history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryPersistenceImpl
 * @see PlanTeamHistoryUtil
 * @generated
 */
public interface PlanTeamHistoryPersistence extends BasePersistence<PlanTeamHistory> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanTeamHistoryUtil} to access the plan team history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan team history in the entity cache if it is enabled.
    *
    * @param planTeamHistory the plan team history
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory);

    /**
    * Caches the plan team histories in the entity cache if it is enabled.
    *
    * @param planTeamHistories the plan team histories
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> planTeamHistories);

    /**
    * Creates a new plan team history with the primary key. Does not add the plan team history to the database.
    *
    * @param id the primary key for the new plan team history
    * @return the new plan team history
    */
    public com.ext.portlet.plans.model.PlanTeamHistory create(java.lang.Long id);

    /**
    * Removes the plan team history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanTeamHistory updateImpl(
        com.ext.portlet.plans.model.PlanTeamHistory planTeamHistory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan team history with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTeamHistoryException} if it could not be found.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history
    * @throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan team history with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history, or <code>null</code> if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan team histories where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findByPlanId(
        java.lang.Long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    * @throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory findByPlanId_First(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

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
    * @throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory findByPlanId_Last(
        java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

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
    * @throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory[] findByPlanId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan team history where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanTeamHistoryException} if it could not be found.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan team history
    * @throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory findByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan team history where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the matching plan team history, or <code>null</code> if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory fetchByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan team history where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan team history, or <code>null</code> if a matching plan team history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTeamHistory fetchByLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan team histories.
    *
    * @return the plan team histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanTeamHistory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan team histories where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan team history where planId = &#63; and userId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByLastUserActionInPlan(java.lang.Long planId,
        java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan team histories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan team histories where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan team histories where planId = &#63; and userId = &#63;.
    *
    * @param planId the plan ID
    * @param userId the user ID
    * @return the number of matching plan team histories
    * @throws SystemException if a system exception occurred
    */
    public int countByLastUserActionInPlan(java.lang.Long planId,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan team histories.
    *
    * @return the number of plan team histories
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
