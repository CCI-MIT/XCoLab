package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanAttribute;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan attribute service. This utility wraps {@link PlanAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributePersistence
 * @see PlanAttributePersistenceImpl
 * @generated
 */
public class PlanAttributeUtil {
    private static PlanAttributePersistence _persistence;

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
    public static void clearCache(PlanAttribute planAttribute) {
        getPersistence().clearCache(planAttribute);
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
    public static List<PlanAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanAttribute update(PlanAttribute planAttribute,
        boolean merge) throws SystemException {
        return getPersistence().update(planAttribute, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanAttribute update(PlanAttribute planAttribute,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planAttribute, merge, serviceContext);
    }

    /**
    * Caches the plan attribute in the entity cache if it is enabled.
    *
    * @param planAttribute the plan attribute
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanAttribute planAttribute) {
        getPersistence().cacheResult(planAttribute);
    }

    /**
    * Caches the plan attributes in the entity cache if it is enabled.
    *
    * @param planAttributes the plan attributes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanAttribute> planAttributes) {
        getPersistence().cacheResult(planAttributes);
    }

    /**
    * Creates a new plan attribute with the primary key. Does not add the plan attribute to the database.
    *
    * @param attributeId the primary key for the new plan attribute
    * @return the new plan attribute
    */
    public static com.ext.portlet.plans.model.PlanAttribute create(
        long attributeId) {
        return getPersistence().create(attributeId);
    }

    /**
    * Removes the plan attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute remove(
        long attributeId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(attributeId);
    }

    public static com.ext.portlet.plans.model.PlanAttribute updateImpl(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planAttribute, merge);
    }

    /**
    * Returns the plan attribute with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanAttributeException} if it could not be found.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute findByPrimaryKey(
        long attributeId)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(attributeId);
    }

    /**
    * Returns the plan attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute, or <code>null</code> if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute fetchByPrimaryKey(
        long attributeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(attributeId);
    }

    /**
    * Returns all the plan attributes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByplanAttributes(planId);
    }

    /**
    * Returns a range of all the plan attributes where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByplanAttributes(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan attributes where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByplanAttributes(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByplanAttributes(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan attribute in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute findByplanAttributes_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByplanAttributes_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan attribute in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute findByplanAttributes_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByplanAttributes_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan attributes before and after the current plan attribute in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param attributeId the primary key of the current plan attribute
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute[] findByplanAttributes_PrevAndNext(
        long attributeId, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByplanAttributes_PrevAndNext(attributeId, planId,
            orderByComparator);
    }

    /**
    * Returns the plan attribute where planId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanAttributeException} if it could not be found.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the matching plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute findByattributeForPlan(
        long planId, java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByattributeForPlan(planId, attributeName);
    }

    /**
    * Returns the plan attribute where planId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute fetchByattributeForPlan(
        long planId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByattributeForPlan(planId, attributeName);
    }

    /**
    * Returns the plan attribute where planId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute fetchByattributeForPlan(
        long planId, java.lang.String attributeName, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByattributeForPlan(planId, attributeName,
            retrieveFromCache);
    }

    /**
    * Returns all the plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @return the matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByattributeByNameValue(attributeName, attributeValue);
    }

    /**
    * Returns a range of all the plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByattributeByNameValue(attributeName, attributeValue,
            start, end);
    }

    /**
    * Returns an ordered range of all the plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByattributeByNameValue(attributeName, attributeValue,
            start, end, orderByComparator);
    }

    /**
    * Returns the first plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute findByattributeByNameValue_First(
        java.lang.String attributeName, java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByattributeByNameValue_First(attributeName,
            attributeValue, orderByComparator);
    }

    /**
    * Returns the last plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute findByattributeByNameValue_Last(
        java.lang.String attributeName, java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByattributeByNameValue_Last(attributeName,
            attributeValue, orderByComparator);
    }

    /**
    * Returns the plan attributes before and after the current plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param attributeId the primary key of the current plan attribute
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan attribute
    * @throws com.ext.portlet.plans.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute[] findByattributeByNameValue_PrevAndNext(
        long attributeId, java.lang.String attributeName,
        java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByattributeByNameValue_PrevAndNext(attributeId,
            attributeName, attributeValue, orderByComparator);
    }

    /**
    * Returns all the plan attributes.
    *
    * @return the plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan attributes where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByplanAttributes(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByplanAttributes(planId);
    }

    /**
    * Removes the plan attribute where planId = &#63; and attributeName = &#63; from the database.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByattributeForPlan(long planId,
        java.lang.String attributeName)
        throws com.ext.portlet.plans.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByattributeForPlan(planId, attributeName);
    }

    /**
    * Removes all the plan attributes where attributeName = &#63; and attributeValue = &#63; from the database.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @throws SystemException if a system exception occurred
    */
    public static void removeByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByattributeByNameValue(attributeName, attributeValue);
    }

    /**
    * Removes all the plan attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan attributes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByplanAttributes(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByplanAttributes(planId);
    }

    /**
    * Returns the number of plan attributes where planId = &#63; and attributeName = &#63;.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the number of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByattributeForPlan(long planId,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByattributeForPlan(planId, attributeName);
    }

    /**
    * Returns the number of plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @return the number of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByattributeByNameValue(attributeName, attributeValue);
    }

    /**
    * Returns the number of plan attributes.
    *
    * @return the number of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanAttributePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(PlanAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanAttributePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanAttributeUtil.class,
            "_persistence");
    }
}
