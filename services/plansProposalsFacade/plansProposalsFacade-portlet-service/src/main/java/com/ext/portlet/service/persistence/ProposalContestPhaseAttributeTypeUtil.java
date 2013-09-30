package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalContestPhaseAttributeType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal contest phase attribute type service. This utility wraps {@link ProposalContestPhaseAttributeTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypePersistence
 * @see ProposalContestPhaseAttributeTypePersistenceImpl
 * @generated
 */
public class ProposalContestPhaseAttributeTypeUtil {
    private static ProposalContestPhaseAttributeTypePersistence _persistence;

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
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        getPersistence().clearCache(proposalContestPhaseAttributeType);
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
    public static List<ProposalContestPhaseAttributeType> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalContestPhaseAttributeType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalContestPhaseAttributeType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ProposalContestPhaseAttributeType update(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType,
        boolean merge) throws SystemException {
        return getPersistence().update(proposalContestPhaseAttributeType, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ProposalContestPhaseAttributeType update(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(proposalContestPhaseAttributeType, merge,
            serviceContext);
    }

    /**
    * Caches the proposal contest phase attribute type in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        getPersistence().cacheResult(proposalContestPhaseAttributeType);
    }

    /**
    * Caches the proposal contest phase attribute types in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttributeTypes the proposal contest phase attribute types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> proposalContestPhaseAttributeTypes) {
        getPersistence().cacheResult(proposalContestPhaseAttributeTypes);
    }

    /**
    * Creates a new proposal contest phase attribute type with the primary key. Does not add the proposal contest phase attribute type to the database.
    *
    * @param id the primary key for the new proposal contest phase attribute type
    * @return the new proposal contest phase attribute type
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType create(
        long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type that was removed
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType remove(
        long id)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ProposalContestPhaseAttributeType updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .updateImpl(proposalContestPhaseAttributeType, merge);
    }

    /**
    * Returns the proposal contest phase attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException} if it could not be found.
    *
    * @param id the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the proposal contest phase attribute type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type, or <code>null</code> if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the proposal contest phase attribute types.
    *
    * @return the proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal contest phase attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attribute types
    * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
    * @return the range of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal contest phase attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attribute types
    * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal contest phase attribute types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal contest phase attribute types.
    *
    * @return the number of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalContestPhaseAttributeTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalContestPhaseAttributeTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalContestPhaseAttributeTypePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalContestPhaseAttributeTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(
        ProposalContestPhaseAttributeTypePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ProposalContestPhaseAttributeTypeUtil.class,
            "_persistence");
    }
}
