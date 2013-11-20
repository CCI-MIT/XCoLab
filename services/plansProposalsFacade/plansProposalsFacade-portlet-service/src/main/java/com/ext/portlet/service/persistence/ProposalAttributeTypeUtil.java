package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalAttributeType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal attribute type service. This utility wraps {@link ProposalAttributeTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeTypePersistence
 * @see ProposalAttributeTypePersistenceImpl
 * @generated
 */
public class ProposalAttributeTypeUtil {
    private static ProposalAttributeTypePersistence _persistence;

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
    public static void clearCache(ProposalAttributeType proposalAttributeType) {
        getPersistence().clearCache(proposalAttributeType);
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
    public static List<ProposalAttributeType> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalAttributeType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalAttributeType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalAttributeType update(
        ProposalAttributeType proposalAttributeType) throws SystemException {
        return getPersistence().update(proposalAttributeType);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalAttributeType update(
        ProposalAttributeType proposalAttributeType,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(proposalAttributeType, serviceContext);
    }

    /**
    * Caches the proposal attribute type in the entity cache if it is enabled.
    *
    * @param proposalAttributeType the proposal attribute type
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalAttributeType proposalAttributeType) {
        getPersistence().cacheResult(proposalAttributeType);
    }

    /**
    * Caches the proposal attribute types in the entity cache if it is enabled.
    *
    * @param proposalAttributeTypes the proposal attribute types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalAttributeType> proposalAttributeTypes) {
        getPersistence().cacheResult(proposalAttributeTypes);
    }

    /**
    * Creates a new proposal attribute type with the primary key. Does not add the proposal attribute type to the database.
    *
    * @param name the primary key for the new proposal attribute type
    * @return the new proposal attribute type
    */
    public static com.ext.portlet.model.ProposalAttributeType create(
        java.lang.String name) {
        return getPersistence().create(name);
    }

    /**
    * Removes the proposal attribute type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param name the primary key of the proposal attribute type
    * @return the proposal attribute type that was removed
    * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttributeType remove(
        java.lang.String name)
        throws com.ext.portlet.NoSuchProposalAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(name);
    }

    public static com.ext.portlet.model.ProposalAttributeType updateImpl(
        com.ext.portlet.model.ProposalAttributeType proposalAttributeType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalAttributeType);
    }

    /**
    * Returns the proposal attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalAttributeTypeException} if it could not be found.
    *
    * @param name the primary key of the proposal attribute type
    * @return the proposal attribute type
    * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttributeType findByPrimaryKey(
        java.lang.String name)
        throws com.ext.portlet.NoSuchProposalAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(name);
    }

    /**
    * Returns the proposal attribute type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param name the primary key of the proposal attribute type
    * @return the proposal attribute type, or <code>null</code> if a proposal attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalAttributeType fetchByPrimaryKey(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(name);
    }

    /**
    * Returns all the proposal attribute types.
    *
    * @return the proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttributeType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal attribute types
    * @param end the upper bound of the range of proposal attribute types (not inclusive)
    * @return the range of proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttributeType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal attribute types
    * @param end the upper bound of the range of proposal attribute types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalAttributeType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal attribute types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal attribute types.
    *
    * @return the number of proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalAttributeTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalAttributeTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalAttributeTypePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalAttributeTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalAttributeTypePersistence persistence) {
    }
}
