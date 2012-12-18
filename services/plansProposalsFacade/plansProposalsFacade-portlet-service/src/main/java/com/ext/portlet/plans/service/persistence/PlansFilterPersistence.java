package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlansFilter;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plans filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPersistenceImpl
 * @see PlansFilterUtil
 * @generated
 */
public interface PlansFilterPersistence extends BasePersistence<PlansFilter> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlansFilterUtil} to access the plans filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plans filter in the entity cache if it is enabled.
    *
    * @param plansFilter the plans filter
    */
    public void cacheResult(com.ext.portlet.plans.model.PlansFilter plansFilter);

    /**
    * Caches the plans filters in the entity cache if it is enabled.
    *
    * @param plansFilters the plans filters
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlansFilter> plansFilters);

    /**
    * Creates a new plans filter with the primary key. Does not add the plans filter to the database.
    *
    * @param plansFilterPK the primary key for the new plans filter
    * @return the new plans filter
    */
    public com.ext.portlet.plans.model.PlansFilter create(
        PlansFilterPK plansFilterPK);

    /**
    * Removes the plans filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @return the plans filter that was removed
    * @throws com.ext.portlet.plans.NoSuchPlansFilterException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlansFilter remove(
        PlansFilterPK plansFilterPK)
        throws com.ext.portlet.plans.NoSuchPlansFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlansFilter updateImpl(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plans filter with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlansFilterException} if it could not be found.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @return the plans filter
    * @throws com.ext.portlet.plans.NoSuchPlansFilterException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlansFilter findByPrimaryKey(
        PlansFilterPK plansFilterPK)
        throws com.ext.portlet.plans.NoSuchPlansFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plans filter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @return the plans filter, or <code>null</code> if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlansFilter fetchByPrimaryKey(
        PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plans filters.
    *
    * @return the plans filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plans filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans filters
    * @param end the upper bound of the range of plans filters (not inclusive)
    * @return the range of plans filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plans filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans filters
    * @param end the upper bound of the range of plans filters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plans filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlansFilter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plans filters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plans filters.
    *
    * @return the number of plans filters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
