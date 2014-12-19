package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalVersion;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal version service. This utility wraps {@link ProposalVersionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersionPersistence
 * @see ProposalVersionPersistenceImpl
 * @generated
 */
public class ProposalVersionUtil {
    private static ProposalVersionPersistence _persistence;

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
    public static void clearCache(ProposalVersion proposalVersion) {
        getPersistence().clearCache(proposalVersion);
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
    public static List<ProposalVersion> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalVersion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalVersion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalVersion update(ProposalVersion proposalVersion)
        throws SystemException {
        return getPersistence().update(proposalVersion);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalVersion update(ProposalVersion proposalVersion,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(proposalVersion, serviceContext);
    }

    /**
    * Returns all the proposal versions where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

    /**
    * Returns a range of all the proposal versions where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @return the range of matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal versions where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal version, or <code>null</code> if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal version, or <code>null</code> if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the proposal versions before and after the current proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalVersionPK the primary key of the current proposal version
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion[] findByProposalId_PrevAndNext(
        ProposalVersionPK proposalVersionPK, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(proposalVersionPK, proposalId,
            orderByComparator);
    }

    /**
    * Removes all the proposal versions where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of proposal versions where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Caches the proposal version in the entity cache if it is enabled.
    *
    * @param proposalVersion the proposal version
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalVersion proposalVersion) {
        getPersistence().cacheResult(proposalVersion);
    }

    /**
    * Caches the proposal versions in the entity cache if it is enabled.
    *
    * @param proposalVersions the proposal versions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalVersion> proposalVersions) {
        getPersistence().cacheResult(proposalVersions);
    }

    /**
    * Creates a new proposal version with the primary key. Does not add the proposal version to the database.
    *
    * @param proposalVersionPK the primary key for the new proposal version
    * @return the new proposal version
    */
    public static com.ext.portlet.model.ProposalVersion create(
        ProposalVersionPK proposalVersionPK) {
        return getPersistence().create(proposalVersionPK);
    }

    /**
    * Removes the proposal version with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version that was removed
    * @throws com.ext.portlet.NoSuchProposalVersionException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion remove(
        ProposalVersionPK proposalVersionPK)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(proposalVersionPK);
    }

    public static com.ext.portlet.model.ProposalVersion updateImpl(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalVersion);
    }

    /**
    * Returns the proposal version with the primary key or throws a {@link com.ext.portlet.NoSuchProposalVersionException} if it could not be found.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion findByPrimaryKey(
        ProposalVersionPK proposalVersionPK)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(proposalVersionPK);
    }

    /**
    * Returns the proposal version with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version, or <code>null</code> if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVersion fetchByPrimaryKey(
        ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(proposalVersionPK);
    }

    /**
    * Returns all the proposal versions.
    *
    * @return the proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal versions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @return the range of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal versions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVersion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal versions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal versions.
    *
    * @return the number of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalVersionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalVersionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalVersionPersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalVersionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalVersionPersistence persistence) {
    }
}
