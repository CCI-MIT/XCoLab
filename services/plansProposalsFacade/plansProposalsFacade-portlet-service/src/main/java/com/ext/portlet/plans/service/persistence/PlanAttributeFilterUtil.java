package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanAttributeFilter;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan attribute filter service. This utility wraps {@link PlanAttributeFilterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterPersistence
 * @see PlanAttributeFilterPersistenceImpl
 * @generated
 */
public class PlanAttributeFilterUtil {
    private static PlanAttributeFilterPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(PlanAttributeFilter planAttributeFilter) {
        getPersistence().clearCache(planAttributeFilter);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<PlanAttributeFilter> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanAttributeFilter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanAttributeFilter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanAttributeFilter update(
        PlanAttributeFilter planAttributeFilter, boolean merge)
        throws SystemException {
        return getPersistence().update(planAttributeFilter, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanAttributeFilter update(
        PlanAttributeFilter planAttributeFilter, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(planAttributeFilter, merge, serviceContext);
    }

    /**
    * Caches the plan attribute filter in the entity cache if it is enabled.
    *
    * @param planAttributeFilter the plan attribute filter
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter) {
        getPersistence().cacheResult(planAttributeFilter);
    }

    /**
    * Caches the plan attribute filters in the entity cache if it is enabled.
    *
    * @param planAttributeFilters the plan attribute filters
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> planAttributeFilters) {
        getPersistence().cacheResult(planAttributeFilters);
    }

    /**
    * Creates a new plan attribute filter with the primary key. Does not add the plan attribute filter to the database.
    *
    * @param planAttributeFilterId the primary key for the new plan attribute filter
    * @return the new plan attribute filter
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter create(
        java.lang.Long planAttributeFilterId) {
        return getPersistence().create(planAttributeFilterId);
    }

    /**
    * Removes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter remove(
        java.lang.Long planAttributeFilterId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planAttributeFilterId);
    }

    public static com.ext.portlet.plans.model.PlanAttributeFilter updateImpl(
        com.ext.portlet.plans.model.PlanAttributeFilter planAttributeFilter,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planAttributeFilter, merge);
    }

    /**
    * Returns the plan attribute filter with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanAttributeFilterException} if it could not be found.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter findByPrimaryKey(
        java.lang.Long planAttributeFilterId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planAttributeFilterId);
    }

    /**
    * Returns the plan attribute filter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter, or <code>null</code> if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter fetchByPrimaryKey(
        java.lang.Long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planAttributeFilterId);
    }

    /**
    * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanAttributeFilterException} if it could not be found.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the matching plan attribute filter
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException if a matching plan attribute filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter findByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    /**
    * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the matching plan attribute filter, or <code>null</code> if a matching plan attribute filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    /**
    * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan attribute filter, or <code>null</code> if a matching plan attribute filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName, retrieveFromCache);
    }

    /**
    * Returns all the plan attribute filters.
    *
    * @return the plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan attribute filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @return the range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan attribute filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; from the database.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    /**
    * Removes all the plan attribute filters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan attribute filters where planUserSettingsId = &#63; and attributeName = &#63;.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param attributeName the attribute name
    * @return the number of matching plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    /**
    * Returns the number of plan attribute filters.
    *
    * @return the number of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanAttributeFilterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanAttributeFilterPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanAttributeFilterPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanAttributeFilterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanAttributeFilterPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanAttributeFilterUtil.class,
            "_persistence");
    }
}
