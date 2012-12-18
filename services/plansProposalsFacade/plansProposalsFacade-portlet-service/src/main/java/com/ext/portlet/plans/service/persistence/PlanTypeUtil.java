package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan type service. This utility wraps {@link PlanTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypePersistence
 * @see PlanTypePersistenceImpl
 * @generated
 */
public class PlanTypeUtil {
    private static PlanTypePersistence _persistence;

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
    public static void clearCache(PlanType planType) {
        getPersistence().clearCache(planType);
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
    public static List<PlanType> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanType update(PlanType planType, boolean merge)
        throws SystemException {
        return getPersistence().update(planType, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanType update(PlanType planType, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planType, merge, serviceContext);
    }

    /**
    * Caches the plan type in the entity cache if it is enabled.
    *
    * @param planType the plan type
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanType planType) {
        getPersistence().cacheResult(planType);
    }

    /**
    * Caches the plan types in the entity cache if it is enabled.
    *
    * @param planTypes the plan types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanType> planTypes) {
        getPersistence().cacheResult(planTypes);
    }

    /**
    * Creates a new plan type with the primary key. Does not add the plan type to the database.
    *
    * @param planTypeId the primary key for the new plan type
    * @return the new plan type
    */
    public static com.ext.portlet.plans.model.PlanType create(long planTypeId) {
        return getPersistence().create(planTypeId);
    }

    /**
    * Removes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanType remove(long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planTypeId);
    }

    public static com.ext.portlet.plans.model.PlanType updateImpl(
        com.ext.portlet.plans.model.PlanType planType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planType, merge);
    }

    /**
    * Returns the plan type with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeException} if it could not be found.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type
    * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanType findByPrimaryKey(
        long planTypeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planTypeId);
    }

    /**
    * Returns the plan type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type, or <code>null</code> if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanType fetchByPrimaryKey(
        long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planTypeId);
    }

    /**
    * Returns the plan type where isDefault = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeException} if it could not be found.
    *
    * @param isDefault the is default
    * @return the matching plan type
    * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a matching plan type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanType findBydefault(
        boolean isDefault)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBydefault(isDefault);
    }

    /**
    * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param isDefault the is default
    * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanType fetchBydefault(
        boolean isDefault)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBydefault(isDefault);
    }

    /**
    * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param isDefault the is default
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanType fetchBydefault(
        boolean isDefault, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBydefault(isDefault, retrieveFromCache);
    }

    /**
    * Returns all the plan types.
    *
    * @return the plan types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @return the range of plan types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan type where isDefault = &#63; from the database.
    *
    * @param isDefault the is default
    * @throws SystemException if a system exception occurred
    */
    public static void removeBydefault(boolean isDefault)
        throws com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBydefault(isDefault);
    }

    /**
    * Removes all the plan types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan types where isDefault = &#63;.
    *
    * @param isDefault the is default
    * @return the number of matching plan types
    * @throws SystemException if a system exception occurred
    */
    public static int countBydefault(boolean isDefault)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBydefault(isDefault);
    }

    /**
    * Returns the number of plan types.
    *
    * @return the number of plan types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the plan type attributes associated with the plan type.
    *
    * @param pk the primary key of the plan type
    * @return the plan type attributes associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getPlanTypeAttributes(pk);
    }

    /**
    * Returns a range of all the plan type attributes associated with the plan type.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the plan type
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @return the range of plan type attributes associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getPlanTypeAttributes(pk, start, end);
    }

    /**
    * Returns an ordered range of all the plan type attributes associated with the plan type.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the plan type
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan type attributes associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getPlanTypeAttributes(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of plan type attributes associated with the plan type.
    *
    * @param pk the primary key of the plan type
    * @return the number of plan type attributes associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanTypeAttributesSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getPlanTypeAttributesSize(pk);
    }

    /**
    * Returns <code>true</code> if the plan type attribute is associated with the plan type.
    *
    * @param pk the primary key of the plan type
    * @param planTypeAttributePK the primary key of the plan type attribute
    * @return <code>true</code> if the plan type attribute is associated with the plan type; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsPlanTypeAttribute(long pk,
        long planTypeAttributePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .containsPlanTypeAttribute(pk, planTypeAttributePK);
    }

    /**
    * Returns <code>true</code> if the plan type has any plan type attributes associated with it.
    *
    * @param pk the primary key of the plan type to check for associations with plan type attributes
    * @return <code>true</code> if the plan type has any plan type attributes associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsPlanTypeAttributes(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsPlanTypeAttributes(pk);
    }

    /**
    * Returns all the plan type columns associated with the plan type.
    *
    * @param pk the primary key of the plan type
    * @return the plan type columns associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getPlanTypeColumns(pk);
    }

    /**
    * Returns a range of all the plan type columns associated with the plan type.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the plan type
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @return the range of plan type columns associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getPlanTypeColumns(pk, start, end);
    }

    /**
    * Returns an ordered range of all the plan type columns associated with the plan type.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the plan type
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan type columns associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .getPlanTypeColumns(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of plan type columns associated with the plan type.
    *
    * @param pk the primary key of the plan type
    * @return the number of plan type columns associated with the plan type
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanTypeColumnsSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getPlanTypeColumnsSize(pk);
    }

    /**
    * Returns <code>true</code> if the plan type column is associated with the plan type.
    *
    * @param pk the primary key of the plan type
    * @param planTypeColumnPK the primary key of the plan type column
    * @return <code>true</code> if the plan type column is associated with the plan type; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsPlanTypeColumn(long pk, long planTypeColumnPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsPlanTypeColumn(pk, planTypeColumnPK);
    }

    /**
    * Returns <code>true</code> if the plan type has any plan type columns associated with it.
    *
    * @param pk the primary key of the plan type to check for associations with plan type columns
    * @return <code>true</code> if the plan type has any plan type columns associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsPlanTypeColumns(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsPlanTypeColumns(pk);
    }

    public static PlanTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanTypePersistence.class.getName());

            ReferenceRegistry.registerReference(PlanTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanTypePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanTypeUtil.class, "_persistence");
    }
}
