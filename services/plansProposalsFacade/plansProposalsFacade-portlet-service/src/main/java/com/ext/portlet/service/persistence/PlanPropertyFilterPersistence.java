package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPropertyFilter;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan property filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilterPersistenceImpl
 * @see PlanPropertyFilterUtil
 * @generated
 */
public interface PlanPropertyFilterPersistence extends BasePersistence<PlanPropertyFilter> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanPropertyFilterUtil} to access the plan property filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan property filter in the entity cache if it is enabled.
    *
    * @param planPropertyFilter the plan property filter
    */
    public void cacheResult(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter);

    /**
    * Caches the plan property filters in the entity cache if it is enabled.
    *
    * @param planPropertyFilters the plan property filters
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanPropertyFilter> planPropertyFilters);

    /**
    * Creates a new plan property filter with the primary key. Does not add the plan property filter to the database.
    *
    * @param planPropertyFilterId the primary key for the new plan property filter
    * @return the new plan property filter
    */
    public com.ext.portlet.model.PlanPropertyFilter create(
        long planPropertyFilterId);

    /**
    * Removes the plan property filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter that was removed
    * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPropertyFilter remove(
        long planPropertyFilterId)
        throws com.ext.portlet.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanPropertyFilter updateImpl(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan property filter with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPropertyFilterException} if it could not be found.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter
    * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPropertyFilter findByPrimaryKey(
        long planPropertyFilterId)
        throws com.ext.portlet.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan property filter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter, or <code>null</code> if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPropertyFilter fetchByPrimaryKey(
        long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanPropertyFilterException} if it could not be found.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @return the matching plan property filter
    * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a matching plan property filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPropertyFilter findByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @return the matching plan property filter, or <code>null</code> if a matching plan property filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan property filter, or <code>null</code> if a matching plan property filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, java.lang.String propertyName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan property filters.
    *
    * @return the plan property filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPropertyFilter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan property filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan property filters
    * @param end the upper bound of the range of plan property filters (not inclusive)
    * @return the range of plan property filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPropertyFilter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan property filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan property filters
    * @param end the upper bound of the range of plan property filters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan property filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPropertyFilter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; from the database.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan property filters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan property filters where planUserSettingsId = &#63; and propertyName = &#63;.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @return the number of matching plan property filters
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanUserSettingsIdPropertyName(long planUserSettingsId,
        java.lang.String propertyName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan property filters.
    *
    * @return the number of plan property filters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
