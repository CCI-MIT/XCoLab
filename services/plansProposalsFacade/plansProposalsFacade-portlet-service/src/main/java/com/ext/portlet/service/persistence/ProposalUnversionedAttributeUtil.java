package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalUnversionedAttribute;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal unversioned attribute service. This utility wraps {@link ProposalUnversionedAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalUnversionedAttributePersistence
 * @see ProposalUnversionedAttributePersistenceImpl
 * @generated
 */
public class ProposalUnversionedAttributeUtil {
    private static ProposalUnversionedAttributePersistence _persistence;

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
    public static void clearCache(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        getPersistence().clearCache(proposalUnversionedAttribute);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ProposalUnversionedAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalUnversionedAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalUnversionedAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalUnversionedAttribute update(
        ProposalUnversionedAttribute proposalUnversionedAttribute)
        throws SystemException {
        return getPersistence().update(proposalUnversionedAttribute);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalUnversionedAttribute update(
        ProposalUnversionedAttribute proposalUnversionedAttribute,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(proposalUnversionedAttribute, serviceContext);
    }

    /**
    * Returns all the proposal unversioned attributes where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> findByProposalId_ProposalUnversionedAttribute(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttribute(proposalId);
    }

    /**
    * Returns a range of all the proposal unversioned attributes where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal unversioned attributes
    * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
    * @return the range of matching proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> findByProposalId_ProposalUnversionedAttribute(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttribute(proposalId,
            start, end);
    }

    /**
    * Returns an ordered range of all the proposal unversioned attributes where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal unversioned attributes
    * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> findByProposalId_ProposalUnversionedAttribute(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttribute(proposalId,
            start, end, orderByComparator);
    }

    /**
    * Returns the first proposal unversioned attribute in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal unversioned attribute
    * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute findByProposalId_ProposalUnversionedAttribute_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttribute_First(proposalId,
            orderByComparator);
    }

    /**
    * Returns the first proposal unversioned attribute in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttribute_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_ProposalUnversionedAttribute_First(proposalId,
            orderByComparator);
    }

    /**
    * Returns the last proposal unversioned attribute in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal unversioned attribute
    * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute findByProposalId_ProposalUnversionedAttribute_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttribute_Last(proposalId,
            orderByComparator);
    }

    /**
    * Returns the last proposal unversioned attribute in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttribute_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_ProposalUnversionedAttribute_Last(proposalId,
            orderByComparator);
    }

    /**
    * Returns the proposal unversioned attributes before and after the current proposal unversioned attribute in the ordered set where proposalId = &#63;.
    *
    * @param id the primary key of the current proposal unversioned attribute
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal unversioned attribute
    * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute[] findByProposalId_ProposalUnversionedAttribute_PrevAndNext(
        long id, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttribute_PrevAndNext(id,
            proposalId, orderByComparator);
    }

    /**
    * Removes all the proposal unversioned attributes where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId_ProposalUnversionedAttribute(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByProposalId_ProposalUnversionedAttribute(proposalId);
    }

    /**
    * Returns the number of proposal unversioned attributes where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId_ProposalUnversionedAttribute(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByProposalId_ProposalUnversionedAttribute(proposalId);
    }

    /**
    * Returns the proposal unversioned attribute where proposalId = &#63; and name = &#63; or throws a {@link com.ext.portlet.NoSuchProposalUnversionedAttributeException} if it could not be found.
    *
    * @param proposalId the proposal ID
    * @param name the name
    * @return the matching proposal unversioned attribute
    * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute findByProposalId_ProposalUnversionedAttributeName(
        long proposalId, java.lang.String name)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_ProposalUnversionedAttributeName(proposalId,
            name);
    }

    /**
    * Returns the proposal unversioned attribute where proposalId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param proposalId the proposal ID
    * @param name the name
    * @return the matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttributeName(
        long proposalId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_ProposalUnversionedAttributeName(proposalId,
            name);
    }

    /**
    * Returns the proposal unversioned attribute where proposalId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param proposalId the proposal ID
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttributeName(
        long proposalId, java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_ProposalUnversionedAttributeName(proposalId,
            name, retrieveFromCache);
    }

    /**
    * Removes the proposal unversioned attribute where proposalId = &#63; and name = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param name the name
    * @return the proposal unversioned attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute removeByProposalId_ProposalUnversionedAttributeName(
        long proposalId, java.lang.String name)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByProposalId_ProposalUnversionedAttributeName(proposalId,
            name);
    }

    /**
    * Returns the number of proposal unversioned attributes where proposalId = &#63; and name = &#63;.
    *
    * @param proposalId the proposal ID
    * @param name the name
    * @return the number of matching proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId_ProposalUnversionedAttributeName(
        long proposalId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByProposalId_ProposalUnversionedAttributeName(proposalId,
            name);
    }

    /**
    * Caches the proposal unversioned attribute in the entity cache if it is enabled.
    *
    * @param proposalUnversionedAttribute the proposal unversioned attribute
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalUnversionedAttribute proposalUnversionedAttribute) {
        getPersistence().cacheResult(proposalUnversionedAttribute);
    }

    /**
    * Caches the proposal unversioned attributes in the entity cache if it is enabled.
    *
    * @param proposalUnversionedAttributes the proposal unversioned attributes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> proposalUnversionedAttributes) {
        getPersistence().cacheResult(proposalUnversionedAttributes);
    }

    /**
    * Creates a new proposal unversioned attribute with the primary key. Does not add the proposal unversioned attribute to the database.
    *
    * @param id the primary key for the new proposal unversioned attribute
    * @return the new proposal unversioned attribute
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute create(
        long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the proposal unversioned attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal unversioned attribute
    * @return the proposal unversioned attribute that was removed
    * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute remove(
        long id)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ProposalUnversionedAttribute updateImpl(
        com.ext.portlet.model.ProposalUnversionedAttribute proposalUnversionedAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalUnversionedAttribute);
    }

    /**
    * Returns the proposal unversioned attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalUnversionedAttributeException} if it could not be found.
    *
    * @param id the primary key of the proposal unversioned attribute
    * @return the proposal unversioned attribute
    * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchProposalUnversionedAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the proposal unversioned attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal unversioned attribute
    * @return the proposal unversioned attribute, or <code>null</code> if a proposal unversioned attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalUnversionedAttribute fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the proposal unversioned attributes.
    *
    * @return the proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal unversioned attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal unversioned attributes
    * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
    * @return the range of proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal unversioned attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal unversioned attributes
    * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalUnversionedAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal unversioned attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal unversioned attributes.
    *
    * @return the number of proposal unversioned attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalUnversionedAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalUnversionedAttributePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalUnversionedAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalUnversionedAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        ProposalUnversionedAttributePersistence persistence) {
    }
}
