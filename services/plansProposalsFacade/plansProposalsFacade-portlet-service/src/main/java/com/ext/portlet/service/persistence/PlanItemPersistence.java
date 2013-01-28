package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanItem;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPersistenceImpl
 * @see PlanItemUtil
 * @generated
 */
public interface PlanItemPersistence extends BasePersistence<PlanItem> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanItemUtil} to access the plan item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan item in the entity cache if it is enabled.
    *
    * @param planItem the plan item
    */
    public void cacheResult(com.ext.portlet.model.PlanItem planItem);

    /**
    * Caches the plan items in the entity cache if it is enabled.
    *
    * @param planItems the plan items
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanItem> planItems);

    /**
    * Creates a new plan item with the primary key. Does not add the plan item to the database.
    *
    * @param id the primary key for the new plan item
    * @return the new plan item
    */
    public com.ext.portlet.model.PlanItem create(long id);

    /**
    * Removes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item
    * @return the plan item that was removed
    * @throws com.ext.portlet.NoSuchPlanItemException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem remove(long id)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanItem updateImpl(
        com.ext.portlet.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item with the primary key or throws a {@link com.ext.portlet.NoSuchPlanItemException} if it could not be found.
    *
    * @param id the primary key of the plan item
    * @return the plan item
    * @throws com.ext.portlet.NoSuchPlanItemException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan item
    * @return the plan item, or <code>null</code> if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan items where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItem> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan items where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan items
    * @param end the upper bound of the range of plan items (not inclusive)
    * @return the range of matching plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItem> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan items where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan items
    * @param end the upper bound of the range of plan items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItem> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan item in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan item
    * @throws com.ext.portlet.NoSuchPlanItemException if a matching plan item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem findByAllByPlanId_First(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan item in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan item
    * @throws com.ext.portlet.NoSuchPlanItemException if a matching plan item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem findByAllByPlanId_Last(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan items before and after the current plan item in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan item
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan item
    * @throws com.ext.portlet.NoSuchPlanItemException if a plan item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanItemException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan item
    * @throws com.ext.portlet.NoSuchPlanItemException if a matching plan item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem findByPlanId(long planId)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan item, or <code>null</code> if a matching plan item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem fetchByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan item, or <code>null</code> if a matching plan item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItem fetchByPlanId(long planId,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan items.
    *
    * @return the plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan items
    * @param end the upper bound of the range of plan items (not inclusive)
    * @return the range of plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItem> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan items
    * @param end the upper bound of the range of plan items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItem> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan items where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan item where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanId(long planId)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan items where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan items
    * @throws SystemException if a system exception occurred
    */
    public int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan items where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan items
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan items.
    *
    * @return the number of plan items
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
