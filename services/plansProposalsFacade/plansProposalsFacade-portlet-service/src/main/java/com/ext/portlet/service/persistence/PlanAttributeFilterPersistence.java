package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanAttributeFilter;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan attribute filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterPersistenceImpl
 * @see PlanAttributeFilterUtil
 * @generated
 */
public interface PlanAttributeFilterPersistence extends BasePersistence<PlanAttributeFilter> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanAttributeFilterUtil} to access the plan attribute filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanAttributeFilterException} if it could not be found.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the matching plan attribute filter
    * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a matching plan attribute filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter findByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the matching plan attribute filter, or <code>null</code> if a matching plan attribute filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan attribute filter, or <code>null</code> if a matching plan attribute filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, java.lang.String attributeName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; from the database.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the plan attribute filter that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter removeByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan attribute filters where planUserSettingsId = &#63; and attributeName = &#63;.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the number of matching plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanUserSettingsIdAttributeName(long planUserSettingsId,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan attribute filter in the entity cache if it is enabled.
    *
    * @param planAttributeFilter the plan attribute filter
    */
    public void cacheResult(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter);

    /**
    * Caches the plan attribute filters in the entity cache if it is enabled.
    *
    * @param planAttributeFilters the plan attribute filters
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanAttributeFilter> planAttributeFilters);

    /**
    * Creates a new plan attribute filter with the primary key. Does not add the plan attribute filter to the database.
    *
    * @param planAttributeFilterId the primary key for the new plan attribute filter
    * @return the new plan attribute filter
    */
    public com.ext.portlet.model.PlanAttributeFilter create(
        long planAttributeFilterId);

    /**
    * Removes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter that was removed
    * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter remove(
        long planAttributeFilterId)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanAttributeFilter updateImpl(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute filter with the primary key or throws a {@link com.ext.portlet.NoSuchPlanAttributeFilterException} if it could not be found.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter
    * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter findByPrimaryKey(
        long planAttributeFilterId)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute filter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter, or <code>null</code> if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttributeFilter fetchByPrimaryKey(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan attribute filters.
    *
    * @return the plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttributeFilter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan attribute filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @return the range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttributeFilter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan attribute filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttributeFilter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan attribute filters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan attribute filters.
    *
    * @return the number of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
