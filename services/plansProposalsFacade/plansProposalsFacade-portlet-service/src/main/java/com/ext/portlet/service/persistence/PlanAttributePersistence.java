package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanAttribute;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributePersistenceImpl
 * @see PlanAttributeUtil
 * @generated
 */
public interface PlanAttributePersistence extends BasePersistence<PlanAttribute> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanAttributeUtil} to access the plan attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the plan attributes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findByplanAttributes(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan attributes where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findByplanAttributes(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan attributes where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findByplanAttributes(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan attribute in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute findByplanAttributes_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan attribute in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByplanAttributes_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan attribute in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute findByplanAttributes_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan attribute in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByplanAttributes_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attributes before and after the current plan attribute in the ordered set where planId = &#63;.
    *
    * @param attributeId the primary key of the current plan attribute
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute[] findByplanAttributes_PrevAndNext(
        long attributeId, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan attributes where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByplanAttributes(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan attributes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByplanAttributes(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute where planId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanAttributeException} if it could not be found.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the matching plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute findByattributeForPlan(
        long planId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute where planId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByattributeForPlan(
        long planId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute where planId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByattributeForPlan(
        long planId, java.lang.String attributeName, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan attribute where planId = &#63; and attributeName = &#63; from the database.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the plan attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute removeByattributeForPlan(
        long planId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan attributes where planId = &#63; and attributeName = &#63;.
    *
    * @param planId the plan ID
    * @param attributeName the attribute name
    * @return the number of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByattributeForPlan(long planId,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @return the matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.PlanAttribute> findByattributeByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute findByattributeByNameValue_First(
        java.lang.String attributeName, java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByattributeByNameValue_First(
        java.lang.String attributeName, java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute findByattributeByNameValue_Last(
        java.lang.String attributeName, java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan attribute, or <code>null</code> if a matching plan attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByattributeByNameValue_Last(
        java.lang.String attributeName, java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attributes before and after the current plan attribute in the ordered set where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeId the primary key of the current plan attribute
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute[] findByattributeByNameValue_PrevAndNext(
        long attributeId, java.lang.String attributeName,
        java.lang.String attributeValue,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan attributes where attributeName = &#63; and attributeValue = &#63; from the database.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @throws SystemException if a system exception occurred
    */
    public void removeByattributeByNameValue(java.lang.String attributeName,
        java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan attributes where attributeName = &#63; and attributeValue = &#63;.
    *
    * @param attributeName the attribute name
    * @param attributeValue the attribute value
    * @return the number of matching plan attributes
    * @throws SystemException if a system exception occurred
    */
    public int countByattributeByNameValue(java.lang.String attributeName,
        java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan attribute in the entity cache if it is enabled.
    *
    * @param planAttribute the plan attribute
    */
    public void cacheResult(com.ext.portlet.model.PlanAttribute planAttribute);

    /**
    * Caches the plan attributes in the entity cache if it is enabled.
    *
    * @param planAttributes the plan attributes
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanAttribute> planAttributes);

    /**
    * Creates a new plan attribute with the primary key. Does not add the plan attribute to the database.
    *
    * @param attributeId the primary key for the new plan attribute
    * @return the new plan attribute
    */
    public com.ext.portlet.model.PlanAttribute create(long attributeId);

    /**
    * Removes the plan attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute that was removed
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute remove(long attributeId)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanAttribute updateImpl(
        com.ext.portlet.model.PlanAttribute planAttribute)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute with the primary key or throws a {@link com.ext.portlet.NoSuchPlanAttributeException} if it could not be found.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute
    * @throws com.ext.portlet.NoSuchPlanAttributeException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute findByPrimaryKey(
        long attributeId)
        throws com.ext.portlet.NoSuchPlanAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute, or <code>null</code> if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanAttribute fetchByPrimaryKey(
        long attributeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan attributes.
    *
    * @return the plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan attributes.
    *
    * @return the number of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
