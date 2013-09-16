package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalAttribute;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal attribute service. This utility wraps {@link ProposalAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributePersistence
 * @see ProposalAttributePersistenceImpl
 * @generated
 */
public class ProposalAttributeUtil {
    private static ProposalAttributePersistence _persistence;

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
    public static void clearCache(ProposalAttribute proposalAttribute) {
        getPersistence().clearCache(proposalAttribute);
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
    public static List<ProposalAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ProposalAttribute update(
        ProposalAttribute proposalAttribute, boolean merge)
        throws SystemException {
        return getPersistence().update(proposalAttribute, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ProposalAttribute update(
        ProposalAttribute proposalAttribute, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(proposalAttribute, merge, serviceContext);
    }

    /**
    * Caches the proposal attribute in the entity cache if it is enabled.
    *
    * @param proposalAttribute the proposal attribute
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalAttribute proposalAttribute) {
        getPersistence().cacheResult(proposalAttribute);
    }

    /**
    * Caches the proposal attributes in the entity cache if it is enabled.
    *
    * @param proposalAttributes the proposal attributes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalAttribute> proposalAttributes) {
        getPersistence().cacheResult(proposalAttributes);
    }

    /**
    * Creates a new proposal attribute with the primary key. Does not add the proposal attribute to the database.
    *
    * @param proposalAttributePK the primary key for the new proposal attribute
    * @return the new proposal attribute
    */
    public static com.ext.portlet.model.ProposalAttribute create(
        ProposalAttributePK proposalAttributePK) {
        return getPersistence().create(proposalAttributePK);
    }

    /**
    * Removes the proposal attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalAttributePK the primary key of the proposal attribute
    * @return the proposal attribute that was removed
    * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttribute remove(
        ProposalAttributePK proposalAttributePK)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(proposalAttributePK);
    }

    public static com.ext.portlet.model.ProposalAttribute updateImpl(
        com.ext.portlet.model.ProposalAttribute proposalAttribute, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalAttribute, merge);
    }

    /**
    * Returns the proposal attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalAttributeException} if it could not be found.
    *
    * @param proposalAttributePK the primary key of the proposal attribute
    * @return the proposal attribute
    * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttribute findByPrimaryKey(
        ProposalAttributePK proposalAttributePK)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(proposalAttributePK);
    }

    /**
    * Returns the proposal attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalAttributePK the primary key of the proposal attribute
    * @return the proposal attribute, or <code>null</code> if a proposal attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttribute fetchByPrimaryKey(
        ProposalAttributePK proposalAttributePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(proposalAttributePK);
    }

    /**
    * Returns all the proposal attributes where proposalId = &#63; and version = &#63;.
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @return the matching proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findByProposalIdVersion(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalIdVersion(proposalId, version);
    }

    /**
    * Returns a range of all the proposal attributes where proposalId = &#63; and version = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @param start the lower bound of the range of proposal attributes
    * @param end the upper bound of the range of proposal attributes (not inclusive)
    * @return the range of matching proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findByProposalIdVersion(
        long proposalId, int version, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdVersion(proposalId, version, start, end);
    }

    /**
    * Returns an ordered range of all the proposal attributes where proposalId = &#63; and version = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @param start the lower bound of the range of proposal attributes
    * @param end the upper bound of the range of proposal attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findByProposalIdVersion(
        long proposalId, int version, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdVersion(proposalId, version, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal attribute in the ordered set where proposalId = &#63; and version = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal attribute
    * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttribute findByProposalIdVersion_First(
        long proposalId, int version,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdVersion_First(proposalId, version,
            orderByComparator);
    }

    /**
    * Returns the last proposal attribute in the ordered set where proposalId = &#63; and version = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal attribute
    * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttribute findByProposalIdVersion_Last(
        long proposalId, int version,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdVersion_Last(proposalId, version,
            orderByComparator);
    }

    /**
    * Returns the proposal attributes before and after the current proposal attribute in the ordered set where proposalId = &#63; and version = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param proposalAttributePK the primary key of the current proposal attribute
    * @param proposalId the proposal ID
    * @param version the version
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal attribute
    * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttribute[] findByProposalIdVersion_PrevAndNext(
        ProposalAttributePK proposalAttributePK, long proposalId, int version,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdVersion_PrevAndNext(proposalAttributePK,
            proposalId, version, orderByComparator);
    }

    /**
    * Returns all the proposal attributes.
    *
    * @return the proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal attributes
    * @param end the upper bound of the range of proposal attributes (not inclusive)
    * @return the range of proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal attributes
    * @param end the upper bound of the range of proposal attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal attributes where proposalId = &#63; and version = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalIdVersion(long proposalId, int version)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalIdVersion(proposalId, version);
    }

    /**
    * Removes all the proposal attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal attributes where proposalId = &#63; and version = &#63;.
    *
    * @param proposalId the proposal ID
    * @param version the version
    * @return the number of matching proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalIdVersion(long proposalId, int version)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalIdVersion(proposalId, version);
    }

    /**
    * Returns the number of proposal attributes.
    *
    * @return the number of proposal attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalAttributePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ProposalAttributePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ProposalAttributeUtil.class,
            "_persistence");
    }
}
