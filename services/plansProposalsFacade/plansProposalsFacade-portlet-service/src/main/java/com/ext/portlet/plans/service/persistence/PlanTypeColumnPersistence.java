package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanTypeColumn;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan type column service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumnPersistenceImpl
 * @see PlanTypeColumnUtil
 * @generated
 */
public interface PlanTypeColumnPersistence extends BasePersistence<PlanTypeColumn> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanTypeColumnUtil} to access the plan type column persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan type column in the entity cache if it is enabled.
    *
    * @param planTypeColumn the plan type column
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn);

    /**
    * Caches the plan type columns in the entity cache if it is enabled.
    *
    * @param planTypeColumns the plan type columns
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> planTypeColumns);

    /**
    * Creates a new plan type column with the primary key. Does not add the plan type column to the database.
    *
    * @param planTypeColumnId the primary key for the new plan type column
    * @return the new plan type column
    */
    public com.ext.portlet.plans.model.PlanTypeColumn create(
        long planTypeColumnId);

    /**
    * Removes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTypeColumn remove(
        long planTypeColumnId)
        throws com.ext.portlet.plans.NoSuchPlanTypeColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanTypeColumn updateImpl(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan type column with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeColumnException} if it could not be found.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column
    * @throws com.ext.portlet.plans.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTypeColumn findByPrimaryKey(
        long planTypeColumnId)
        throws com.ext.portlet.plans.NoSuchPlanTypeColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan type column with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column, or <code>null</code> if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTypeColumn fetchByPrimaryKey(
        long planTypeColumnId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan type columns.
    *
    * @return the plan type columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan type columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type columns
    * @param end the upper bound of the range of plan type columns (not inclusive)
    * @return the range of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan type columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type columns
    * @param end the upper bound of the range of plan type columns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan type columns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan type columns.
    *
    * @return the number of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
