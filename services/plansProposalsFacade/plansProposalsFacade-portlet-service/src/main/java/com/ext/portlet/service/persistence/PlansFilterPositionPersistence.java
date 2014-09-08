package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlansFilterPosition;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plans filter position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPositionPersistenceImpl
 * @see PlansFilterPositionUtil
 * @generated
 */
public interface PlansFilterPositionPersistence extends BasePersistence<PlansFilterPosition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlansFilterPositionUtil} to access the plans filter position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the plans filter positions where userId = &#63; and planTypeId = &#63;.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @return the matching plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> findByUserIdPlanTypeId(
        long userId, long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plans filter positions where userId = &#63; and planTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param start the lower bound of the range of plans filter positions
    * @param end the upper bound of the range of plans filter positions (not inclusive)
    * @return the range of matching plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> findByUserIdPlanTypeId(
        long userId, long planTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plans filter positions where userId = &#63; and planTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param start the lower bound of the range of plans filter positions
    * @param end the upper bound of the range of plans filter positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> findByUserIdPlanTypeId(
        long userId, long planTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plans filter position
    * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a matching plans filter position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition findByUserIdPlanTypeId_First(
        long userId, long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlansFilterPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plans filter position, or <code>null</code> if a matching plans filter position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition fetchByUserIdPlanTypeId_First(
        long userId, long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plans filter position
    * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a matching plans filter position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition findByUserIdPlanTypeId_Last(
        long userId, long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlansFilterPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plans filter position, or <code>null</code> if a matching plans filter position could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition fetchByUserIdPlanTypeId_Last(
        long userId, long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plans filter positions before and after the current plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
    *
    * @param plansFilterPositionPK the primary key of the current plans filter position
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plans filter position
    * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition[] findByUserIdPlanTypeId_PrevAndNext(
        PlansFilterPositionPK plansFilterPositionPK, long userId,
        long planTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlansFilterPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plans filter positions where userId = &#63; and planTypeId = &#63; from the database.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserIdPlanTypeId(long userId, long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plans filter positions where userId = &#63; and planTypeId = &#63;.
    *
    * @param userId the user ID
    * @param planTypeId the plan type ID
    * @return the number of matching plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public int countByUserIdPlanTypeId(long userId, long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plans filter position in the entity cache if it is enabled.
    *
    * @param plansFilterPosition the plans filter position
    */
    public void cacheResult(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition);

    /**
    * Caches the plans filter positions in the entity cache if it is enabled.
    *
    * @param plansFilterPositions the plans filter positions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlansFilterPosition> plansFilterPositions);

    /**
    * Creates a new plans filter position with the primary key. Does not add the plans filter position to the database.
    *
    * @param plansFilterPositionPK the primary key for the new plans filter position
    * @return the new plans filter position
    */
    public com.ext.portlet.model.PlansFilterPosition create(
        PlansFilterPositionPK plansFilterPositionPK);

    /**
    * Removes the plans filter position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @return the plans filter position that was removed
    * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition remove(
        PlansFilterPositionPK plansFilterPositionPK)
        throws com.ext.portlet.NoSuchPlansFilterPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlansFilterPosition updateImpl(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plans filter position with the primary key or throws a {@link com.ext.portlet.NoSuchPlansFilterPositionException} if it could not be found.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @return the plans filter position
    * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition findByPrimaryKey(
        PlansFilterPositionPK plansFilterPositionPK)
        throws com.ext.portlet.NoSuchPlansFilterPositionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plans filter position with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @return the plans filter position, or <code>null</code> if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition fetchByPrimaryKey(
        PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plans filter positions.
    *
    * @return the plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plans filter positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plans filter positions
    * @param end the upper bound of the range of plans filter positions (not inclusive)
    * @return the range of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plans filter positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plans filter positions
    * @param end the upper bound of the range of plans filter positions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plans filter positions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plans filter positions.
    *
    * @return the number of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
