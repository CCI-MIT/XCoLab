package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalSupporter;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal supporter service. This utility wraps {@link ProposalSupporterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalSupporterPersistence
 * @see ProposalSupporterPersistenceImpl
 * @generated
 */
public class ProposalSupporterUtil {
    private static ProposalSupporterPersistence _persistence;

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
    public static void clearCache(ProposalSupporter proposalSupporter) {
        getPersistence().clearCache(proposalSupporter);
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
    public static List<ProposalSupporter> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalSupporter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalSupporter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalSupporter update(ProposalSupporter proposalSupporter)
        throws SystemException {
        return getPersistence().update(proposalSupporter);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalSupporter update(
        ProposalSupporter proposalSupporter, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(proposalSupporter, serviceContext);
    }

    /**
    * Returns all the proposal supporters where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

    /**
    * Returns a range of all the proposal supporters where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal supporters where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the proposal supporters before and after the current proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalSupporterPK the primary key of the current proposal supporter
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter[] findByProposalId_PrevAndNext(
        ProposalSupporterPK proposalSupporterPK, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(proposalSupporterPK,
            proposalId, orderByComparator);
    }

    /**
    * Removes all the proposal supporters where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of proposal supporters where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Returns all the proposal supporters where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the proposal supporters where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal supporters where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the first proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the last proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter fetchByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the proposal supporters before and after the current proposal supporter in the ordered set where userId = &#63;.
    *
    * @param proposalSupporterPK the primary key of the current proposal supporter
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter[] findByUserId_PrevAndNext(
        ProposalSupporterPK proposalSupporterPK, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId_PrevAndNext(proposalSupporterPK, userId,
            orderByComparator);
    }

    /**
    * Removes all the proposal supporters where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Returns the number of proposal supporters where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Caches the proposal supporter in the entity cache if it is enabled.
    *
    * @param proposalSupporter the proposal supporter
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalSupporter proposalSupporter) {
        getPersistence().cacheResult(proposalSupporter);
    }

    /**
    * Caches the proposal supporters in the entity cache if it is enabled.
    *
    * @param proposalSupporters the proposal supporters
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalSupporter> proposalSupporters) {
        getPersistence().cacheResult(proposalSupporters);
    }

    /**
    * Creates a new proposal supporter with the primary key. Does not add the proposal supporter to the database.
    *
    * @param proposalSupporterPK the primary key for the new proposal supporter
    * @return the new proposal supporter
    */
    public static com.ext.portlet.model.ProposalSupporter create(
        ProposalSupporterPK proposalSupporterPK) {
        return getPersistence().create(proposalSupporterPK);
    }

    /**
    * Removes the proposal supporter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter that was removed
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter remove(
        ProposalSupporterPK proposalSupporterPK)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(proposalSupporterPK);
    }

    public static com.ext.portlet.model.ProposalSupporter updateImpl(
        com.ext.portlet.model.ProposalSupporter proposalSupporter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalSupporter);
    }

    /**
    * Returns the proposal supporter with the primary key or throws a {@link com.ext.portlet.NoSuchProposalSupporterException} if it could not be found.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter findByPrimaryKey(
        ProposalSupporterPK proposalSupporterPK)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(proposalSupporterPK);
    }

    /**
    * Returns the proposal supporter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter, or <code>null</code> if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalSupporter fetchByPrimaryKey(
        ProposalSupporterPK proposalSupporterPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(proposalSupporterPK);
    }

    /**
    * Returns all the proposal supporters.
    *
    * @return the proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal supporters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal supporters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalSupporter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal supporters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal supporters.
    *
    * @return the number of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalSupporterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalSupporterPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalSupporterPersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalSupporterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalSupporterPersistence persistence) {
    }
}
