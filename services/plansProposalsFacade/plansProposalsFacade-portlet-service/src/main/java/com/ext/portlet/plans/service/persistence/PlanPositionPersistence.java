package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanPosition;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionPersistenceImpl
 * @see PlanPositionUtil
 * @generated
 */
public interface PlanPositionPersistence extends BasePersistence<PlanPosition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanPositionUtil} to access the plan position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan position in the entity cache if it is enabled.
    *
    * @param planPosition the plan position
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanPosition planPosition);

    /**
    * Caches the plan positions in the entity cache if it is enabled.
    *
    * @param planPositions the plan positions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPosition> planPositions);

    /**
    * Creates a new plan position with the primary key. Does not add the plan position to the database.
    *
    * @param planPositionPK the primary key for the new plan position
    * @return the new plan position
    */
    public com.ext.portlet.plans.model.PlanPosition create(
        PlanPositionPK planPositionPK);

    /**
    * Removes the plan position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanPositionException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition remove(
        PlanPositionPK planPositionPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanPosition updateImpl(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanPositionException} if it could not be found.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position
    * @throws com.ext.portlet.plans.NoSuchPlanPositionException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition findByPrimaryKey(
        PlanPositionPK planPositionPK)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position, or <code>null</code> if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition fetchByPrimaryKey(
        PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan positions where positionId = &#63;.
    *
    * @param positionId the position ID
    * @return the matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan positions where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param positionId the position ID
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @return the range of matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan positions where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param positionId the position ID
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findByPositionId(
        java.lang.Long positionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan position in the ordered set where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan position
    * @throws com.ext.portlet.plans.NoSuchPlanPositionException if a matching plan position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition findByPositionId_First(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan position in the ordered set where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan position
    * @throws com.ext.portlet.plans.NoSuchPlanPositionException if a matching plan position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition findByPositionId_Last(
        java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions before and after the current plan position in the ordered set where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planPositionPK the primary key of the current plan position
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan position
    * @throws com.ext.portlet.plans.NoSuchPlanPositionException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition[] findByPositionId_PrevAndNext(
        PlanPositionPK planPositionPK, java.lang.Long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan positions.
    *
    * @return the plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @return the range of plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan positions where positionId = &#63; from the database.
    *
    * @param positionId the position ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan positions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan positions where positionId = &#63;.
    *
    * @param positionId the position ID
    * @return the number of matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public int countByPositionId(java.lang.Long positionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan positions.
    *
    * @return the number of plan positions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
