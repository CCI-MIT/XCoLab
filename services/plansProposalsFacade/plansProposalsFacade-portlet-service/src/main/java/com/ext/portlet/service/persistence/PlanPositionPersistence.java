package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPosition;

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
    * Returns all the plan positions where positionId = &#63;.
    *
    * @param positionId the position ID
    * @return the matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPosition> findByPositionId(
        long positionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan positions where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param positionId the position ID
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @return the range of matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPosition> findByPositionId(
        long positionId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan positions where positionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param positionId the position ID
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPosition> findByPositionId(
        long positionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan position in the ordered set where positionId = &#63;.
    *
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan position
    * @throws com.ext.portlet.NoSuchPlanPositionException if a matching plan position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition findByPositionId_First(
        long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan position in the ordered set where positionId = &#63;.
    *
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan position, or <code>null</code> if a matching plan position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition fetchByPositionId_First(
        long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan position in the ordered set where positionId = &#63;.
    *
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan position
    * @throws com.ext.portlet.NoSuchPlanPositionException if a matching plan position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition findByPositionId_Last(
        long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan position in the ordered set where positionId = &#63;.
    *
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan position, or <code>null</code> if a matching plan position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition fetchByPositionId_Last(
        long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan positions before and after the current plan position in the ordered set where positionId = &#63;.
    *
    * @param planPositionPK the primary key of the current plan position
    * @param positionId the position ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan position
    * @throws com.ext.portlet.NoSuchPlanPositionException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition[] findByPositionId_PrevAndNext(
        com.ext.portlet.service.persistence.PlanPositionPK planPositionPK,
        long positionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan positions where positionId = &#63; from the database.
    *
    * @param positionId the position ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPositionId(long positionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan positions where positionId = &#63;.
    *
    * @param positionId the position ID
    * @return the number of matching plan positions
    * @throws SystemException if a system exception occurred
    */
    public int countByPositionId(long positionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan position in the entity cache if it is enabled.
    *
    * @param planPosition the plan position
    */
    public void cacheResult(com.ext.portlet.model.PlanPosition planPosition);

    /**
    * Caches the plan positions in the entity cache if it is enabled.
    *
    * @param planPositions the plan positions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanPosition> planPositions);

    /**
    * Creates a new plan position with the primary key. Does not add the plan position to the database.
    *
    * @param planPositionPK the primary key for the new plan position
    * @return the new plan position
    */
    public com.ext.portlet.model.PlanPosition create(
        com.ext.portlet.service.persistence.PlanPositionPK planPositionPK);

    /**
    * Removes the plan position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position that was removed
    * @throws com.ext.portlet.NoSuchPlanPositionException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition remove(
        com.ext.portlet.service.persistence.PlanPositionPK planPositionPK)
        throws com.ext.portlet.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanPosition updateImpl(
        com.ext.portlet.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPositionException} if it could not be found.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position
    * @throws com.ext.portlet.NoSuchPlanPositionException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition findByPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionPK planPositionPK)
        throws com.ext.portlet.NoSuchPlanPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan position with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position, or <code>null</code> if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPosition fetchByPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan positions.
    *
    * @return the plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPosition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @return the range of plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPosition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPosition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan positions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
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
