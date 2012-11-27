package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanPropertyFilter;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan property filter service. This utility wraps {@link PlanPropertyFilterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilterPersistence
 * @see PlanPropertyFilterPersistenceImpl
 * @generated
 */
public class PlanPropertyFilterUtil {
    private static PlanPropertyFilterPersistence _persistence;

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
    public static void clearCache(PlanPropertyFilter planPropertyFilter) {
        getPersistence().clearCache(planPropertyFilter);
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
    public static List<PlanPropertyFilter> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanPropertyFilter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanPropertyFilter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanPropertyFilter update(
        PlanPropertyFilter planPropertyFilter, boolean merge)
        throws SystemException {
        return getPersistence().update(planPropertyFilter, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanPropertyFilter update(
        PlanPropertyFilter planPropertyFilter, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planPropertyFilter, merge, serviceContext);
    }

    /**
    * Caches the plan property filter in the entity cache if it is enabled.
    *
    * @param planPropertyFilter the plan property filter
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter) {
        getPersistence().cacheResult(planPropertyFilter);
    }

    /**
    * Caches the plan property filters in the entity cache if it is enabled.
    *
    * @param planPropertyFilters the plan property filters
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> planPropertyFilters) {
        getPersistence().cacheResult(planPropertyFilters);
    }

    /**
    * Creates a new plan property filter with the primary key. Does not add the plan property filter to the database.
    *
    * @param planPropertyFilterId the primary key for the new plan property filter
    * @return the new plan property filter
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter create(
        java.lang.Long planPropertyFilterId) {
        return getPersistence().create(planPropertyFilterId);
    }

    /**
    * Removes the plan property filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter remove(
        java.lang.Long planPropertyFilterId)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planPropertyFilterId);
    }

    public static com.ext.portlet.plans.model.PlanPropertyFilter updateImpl(
        com.ext.portlet.plans.model.PlanPropertyFilter planPropertyFilter,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planPropertyFilter, merge);
    }

    /**
    * Returns the plan property filter with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanPropertyFilterException} if it could not be found.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter
    * @throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter findByPrimaryKey(
        java.lang.Long planPropertyFilterId)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planPropertyFilterId);
    }

    /**
    * Returns the plan property filter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planPropertyFilterId the primary key of the plan property filter
    * @return the plan property filter, or <code>null</code> if a plan property filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter fetchByPrimaryKey(
        java.lang.Long planPropertyFilterId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planPropertyFilterId);
    }

    /**
    * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanPropertyFilterException} if it could not be found.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @return the matching plan property filter
    * @throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException if a matching plan property filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter findByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    /**
    * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @return the matching plan property filter, or <code>null</code> if a matching plan property filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    /**
    * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan property filter, or <code>null</code> if a matching plan property filter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName, retrieveFromCache);
    }

    /**
    * Returns all the plan property filters.
    *
    * @return the plan property filters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; from the database.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    /**
    * Removes all the plan property filters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan property filters where planUserSettingsId = &#63; and propertyName = &#63;.
    *
    * @param planUserSettingsId the plan user settings ID
    * @param propertyName the property name
    * @return the number of matching plan property filters
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanUserSettingsIdPropertyName(
        java.lang.Long planUserSettingsId, java.lang.String propertyName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName);
    }

    /**
    * Returns the number of plan property filters.
    *
    * @return the number of plan property filters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanPropertyFilterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanPropertyFilterPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanPropertyFilterPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanPropertyFilterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanPropertyFilterPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanPropertyFilterUtil.class,
            "_persistence");
    }
}
