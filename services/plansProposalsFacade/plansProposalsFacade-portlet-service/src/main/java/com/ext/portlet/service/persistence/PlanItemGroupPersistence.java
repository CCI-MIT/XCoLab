package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanItemGroup;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroupPersistenceImpl
 * @see PlanItemGroupUtil
 * @generated
 */
public interface PlanItemGroupPersistence extends BasePersistence<PlanItemGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanItemGroupUtil} to access the plan item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan item group in the entity cache if it is enabled.
    *
    * @param planItemGroup the plan item group
    */
    public void cacheResult(com.ext.portlet.model.PlanItemGroup planItemGroup);

    /**
    * Caches the plan item groups in the entity cache if it is enabled.
    *
    * @param planItemGroups the plan item groups
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanItemGroup> planItemGroups);

    /**
    * Creates a new plan item group with the primary key. Does not add the plan item group to the database.
    *
    * @param planId the primary key for the new plan item group
    * @return the new plan item group
    */
    public com.ext.portlet.model.PlanItemGroup create(long planId);

    /**
    * Removes the plan item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planId the primary key of the plan item group
    * @return the plan item group that was removed
    * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup remove(long planId)
        throws com.ext.portlet.NoSuchPlanItemGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanItemGroup updateImpl(
        com.ext.portlet.model.PlanItemGroup planItemGroup, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item group with the primary key or throws a {@link com.ext.portlet.NoSuchPlanItemGroupException} if it could not be found.
    *
    * @param planId the primary key of the plan item group
    * @return the plan item group
    * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup findByPrimaryKey(long planId)
        throws com.ext.portlet.NoSuchPlanItemGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planId the primary key of the plan item group
    * @return the plan item group, or <code>null</code> if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup fetchByPrimaryKey(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan item groups where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching plan item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemGroup> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan item groups where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of plan item groups
    * @param end the upper bound of the range of plan item groups (not inclusive)
    * @return the range of matching plan item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemGroup> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan item groups where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of plan item groups
    * @param end the upper bound of the range of plan item groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemGroup> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan item group in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan item group
    * @throws com.ext.portlet.NoSuchPlanItemGroupException if a matching plan item group could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanItemGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan item group in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan item group
    * @throws com.ext.portlet.NoSuchPlanItemGroupException if a matching plan item group could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanItemGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan item groups before and after the current plan item group in the ordered set where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the primary key of the current plan item group
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan item group
    * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemGroup[] findByGroupId_PrevAndNext(
        long planId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanItemGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan item groups.
    *
    * @return the plan item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item groups
    * @param end the upper bound of the range of plan item groups (not inclusive)
    * @return the range of plan item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemGroup> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item groups
    * @param end the upper bound of the range of plan item groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemGroup> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan item groups where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan item groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan item groups where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching plan item groups
    * @throws SystemException if a system exception occurred
    */
    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan item groups.
    *
    * @return the number of plan item groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
