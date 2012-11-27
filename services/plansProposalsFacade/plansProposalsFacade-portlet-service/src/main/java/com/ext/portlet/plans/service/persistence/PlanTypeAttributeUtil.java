package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanTypeAttribute;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan type attribute service. This utility wraps {@link PlanTypeAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttributePersistence
 * @see PlanTypeAttributePersistenceImpl
 * @generated
 */
public class PlanTypeAttributeUtil {
    private static PlanTypeAttributePersistence _persistence;

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
    public static void clearCache(PlanTypeAttribute planTypeAttribute) {
        getPersistence().clearCache(planTypeAttribute);
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
    public static List<PlanTypeAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanTypeAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanTypeAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanTypeAttribute update(
        PlanTypeAttribute planTypeAttribute, boolean merge)
        throws SystemException {
        return getPersistence().update(planTypeAttribute, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanTypeAttribute update(
        PlanTypeAttribute planTypeAttribute, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planTypeAttribute, merge, serviceContext);
    }

    /**
    * Caches the plan type attribute in the entity cache if it is enabled.
    *
    * @param planTypeAttribute the plan type attribute
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute) {
        getPersistence().cacheResult(planTypeAttribute);
    }

    /**
    * Caches the plan type attributes in the entity cache if it is enabled.
    *
    * @param planTypeAttributes the plan type attributes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> planTypeAttributes) {
        getPersistence().cacheResult(planTypeAttributes);
    }

    /**
    * Creates a new plan type attribute with the primary key. Does not add the plan type attribute to the database.
    *
    * @param planTypeAttributeId the primary key for the new plan type attribute
    * @return the new plan type attribute
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute create(
        java.lang.Long planTypeAttributeId) {
        return getPersistence().create(planTypeAttributeId);
    }

    /**
    * Removes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @return the plan type attribute that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute remove(
        java.lang.Long planTypeAttributeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planTypeAttributeId);
    }

    public static com.ext.portlet.plans.model.PlanTypeAttribute updateImpl(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planTypeAttribute, merge);
    }

    /**
    * Returns the plan type attribute with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeAttributeException} if it could not be found.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @return the plan type attribute
    * @throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute findByPrimaryKey(
        java.lang.Long planTypeAttributeId)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planTypeAttributeId);
    }

    /**
    * Returns the plan type attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @return the plan type attribute, or <code>null</code> if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchByPrimaryKey(
        java.lang.Long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planTypeAttributeId);
    }

    /**
    * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeAttributeException} if it could not be found.
    *
    * @param planTypeId the plan type ID
    * @param attributeName the attribute name
    * @return the matching plan type attribute
    * @throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException if a matching plan type attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute findByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    /**
    * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planTypeId the plan type ID
    * @param attributeName the attribute name
    * @return the matching plan type attribute, or <code>null</code> if a matching plan type attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    /**
    * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planTypeId the plan type ID
    * @param attributeName the attribute name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan type attribute, or <code>null</code> if a matching plan type attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanTypeIdAttributeName(planTypeId, attributeName,
            retrieveFromCache);
    }

    /**
    * Returns all the plan type attributes.
    *
    * @return the plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan type attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type attributes
    * @param end the upper bound of the range of plan type attributes (not inclusive)
    * @return the range of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan type attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type attributes
    * @param end the upper bound of the range of plan type attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan type attribute where planTypeId = &#63; and attributeName = &#63; from the database.
    *
    * @param planTypeId the plan type ID
    * @param attributeName the attribute name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanTypeAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    /**
    * Removes all the plan type attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan type attributes where planTypeId = &#63; and attributeName = &#63;.
    *
    * @param planTypeId the plan type ID
    * @param attributeName the attribute name
    * @return the number of matching plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanTypeIdAttributeName(
        java.lang.Long planTypeId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPlanTypeIdAttributeName(planTypeId, attributeName);
    }

    /**
    * Returns the number of plan type attributes.
    *
    * @return the number of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTypeAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanTypeAttributePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanTypeAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(PlanTypeAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanTypeAttributePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanTypeAttributeUtil.class,
            "_persistence");
    }
}
