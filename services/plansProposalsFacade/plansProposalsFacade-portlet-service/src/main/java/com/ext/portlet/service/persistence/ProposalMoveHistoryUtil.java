package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalMoveHistory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal move history service. This utility wraps {@link ProposalMoveHistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistoryPersistence
 * @see ProposalMoveHistoryPersistenceImpl
 * @generated
 */
public class ProposalMoveHistoryUtil {
    private static ProposalMoveHistoryPersistence _persistence;

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
    public static void clearCache(ProposalMoveHistory proposalMoveHistory) {
        getPersistence().clearCache(proposalMoveHistory);
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
    public static List<ProposalMoveHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalMoveHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalMoveHistory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalMoveHistory update(
        ProposalMoveHistory proposalMoveHistory) throws SystemException {
        return getPersistence().update(proposalMoveHistory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalMoveHistory update(
        ProposalMoveHistory proposalMoveHistory, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(proposalMoveHistory, serviceContext);
    }

    /**
    * Returns all the proposal move histories where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySourceProposalId(sourceProposalId);
    }

    /**
    * Returns a range of all the proposal move histories where sourceProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalId(sourceProposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where sourceProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalId(sourceProposalId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourceProposalId_First(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalId_First(sourceProposalId,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalId_First(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourceProposalId_First(sourceProposalId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourceProposalId_Last(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalId_Last(sourceProposalId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalId_Last(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourceProposalId_Last(sourceProposalId,
            orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findBySourceProposalId_PrevAndNext(
        long id_, long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalId_PrevAndNext(id_, sourceProposalId,
            orderByComparator);
    }

    /**
    * Removes all the proposal move histories where sourceProposalId = &#63; from the database.
    *
    * @param sourceProposalId the source proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySourceProposalId(long sourceProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySourceProposalId(sourceProposalId);
    }

    /**
    * Returns the number of proposal move histories where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countBySourceProposalId(long sourceProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySourceProposalId(sourceProposalId);
    }

    /**
    * Returns all the proposal move histories where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceContestId(
        long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySourceContestId(sourceContestId);
    }

    /**
    * Returns a range of all the proposal move histories where sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceContestId(
        long sourceContestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceContestId(sourceContestId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceContestId(
        long sourceContestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceContestId(sourceContestId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourceContestId_First(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceContestId_First(sourceContestId,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourceContestId_First(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourceContestId_First(sourceContestId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourceContestId_Last(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceContestId_Last(sourceContestId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourceContestId_Last(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourceContestId_Last(sourceContestId,
            orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findBySourceContestId_PrevAndNext(
        long id_, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceContestId_PrevAndNext(id_, sourceContestId,
            orderByComparator);
    }

    /**
    * Removes all the proposal move histories where sourceContestId = &#63; from the database.
    *
    * @param sourceContestId the source contest ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySourceContestId(long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySourceContestId(sourceContestId);
    }

    /**
    * Returns the number of proposal move histories where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countBySourceContestId(long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySourceContestId(sourceContestId);
    }

    /**
    * Returns all the proposal move histories where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourcePhaseId(
        long sourcePhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySourcePhaseId(sourcePhaseId);
    }

    /**
    * Returns a range of all the proposal move histories where sourcePhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourcePhaseId the source phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourcePhaseId(
        long sourcePhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySourcePhaseId(sourcePhaseId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where sourcePhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourcePhaseId the source phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourcePhaseId(
        long sourcePhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourcePhaseId(sourcePhaseId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourcePhaseId_First(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourcePhaseId_First(sourcePhaseId, orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourcePhaseId_First(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourcePhaseId_First(sourcePhaseId, orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourcePhaseId_Last(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourcePhaseId_Last(sourcePhaseId, orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourcePhaseId_Last(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourcePhaseId_Last(sourcePhaseId, orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findBySourcePhaseId_PrevAndNext(
        long id_, long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourcePhaseId_PrevAndNext(id_, sourcePhaseId,
            orderByComparator);
    }

    /**
    * Removes all the proposal move histories where sourcePhaseId = &#63; from the database.
    *
    * @param sourcePhaseId the source phase ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySourcePhaseId(long sourcePhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySourcePhaseId(sourcePhaseId);
    }

    /**
    * Returns the number of proposal move histories where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countBySourcePhaseId(long sourcePhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySourcePhaseId(sourcePhaseId);
    }

    /**
    * Returns all the proposal move histories where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetProposalId(targetProposalId);
    }

    /**
    * Returns a range of all the proposal move histories where targetProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetProposalId the target proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetProposalId(targetProposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where targetProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetProposalId the target proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetProposalId(targetProposalId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetProposalId_First(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetProposalId_First(targetProposalId,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalId_First(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetProposalId_First(targetProposalId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetProposalId_Last(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetProposalId_Last(targetProposalId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalId_Last(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetProposalId_Last(targetProposalId,
            orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findByTargetProposalId_PrevAndNext(
        long id_, long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetProposalId_PrevAndNext(id_, targetProposalId,
            orderByComparator);
    }

    /**
    * Removes all the proposal move histories where targetProposalId = &#63; from the database.
    *
    * @param targetProposalId the target proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTargetProposalId(long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTargetProposalId(targetProposalId);
    }

    /**
    * Returns the number of proposal move histories where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByTargetProposalId(long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTargetProposalId(targetProposalId);
    }

    /**
    * Returns all the proposal move histories where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetContestId(
        long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetContestId(targetContestId);
    }

    /**
    * Returns a range of all the proposal move histories where targetContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetContestId the target contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetContestId(
        long targetContestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetContestId(targetContestId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where targetContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetContestId the target contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetContestId(
        long targetContestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetContestId(targetContestId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetContestId_First(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetContestId_First(targetContestId,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetContestId_First(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetContestId_First(targetContestId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetContestId_Last(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetContestId_Last(targetContestId,
            orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetContestId_Last(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetContestId_Last(targetContestId,
            orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findByTargetContestId_PrevAndNext(
        long id_, long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetContestId_PrevAndNext(id_, targetContestId,
            orderByComparator);
    }

    /**
    * Removes all the proposal move histories where targetContestId = &#63; from the database.
    *
    * @param targetContestId the target contest ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTargetContestId(long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTargetContestId(targetContestId);
    }

    /**
    * Returns the number of proposal move histories where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByTargetContestId(long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTargetContestId(targetContestId);
    }

    /**
    * Returns all the proposal move histories where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetPhaseId(
        long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetPhaseId(targetPhaseId);
    }

    /**
    * Returns a range of all the proposal move histories where targetPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetPhaseId the target phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetPhaseId(
        long targetPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTargetPhaseId(targetPhaseId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where targetPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetPhaseId the target phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetPhaseId(
        long targetPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetPhaseId(targetPhaseId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetPhaseId_First(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetPhaseId_First(targetPhaseId, orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetPhaseId_First(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetPhaseId_First(targetPhaseId, orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetPhaseId_Last(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetPhaseId_Last(targetPhaseId, orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetPhaseId_Last(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetPhaseId_Last(targetPhaseId, orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findByTargetPhaseId_PrevAndNext(
        long id_, long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetPhaseId_PrevAndNext(id_, targetPhaseId,
            orderByComparator);
    }

    /**
    * Removes all the proposal move histories where targetPhaseId = &#63; from the database.
    *
    * @param targetPhaseId the target phase ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTargetPhaseId(long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTargetPhaseId(targetPhaseId);
    }

    /**
    * Returns the number of proposal move histories where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByTargetPhaseId(long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTargetPhaseId(targetPhaseId);
    }

    /**
    * Returns all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalIdContestId(sourceProposalId,
            sourceContestId);
    }

    /**
    * Returns a range of all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalIdContestId(sourceProposalId,
            sourceContestId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalIdContestId(sourceProposalId,
            sourceContestId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourceProposalIdContestId_First(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalIdContestId_First(sourceProposalId,
            sourceContestId, orderByComparator);
    }

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalIdContestId_First(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourceProposalIdContestId_First(sourceProposalId,
            sourceContestId, orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findBySourceProposalIdContestId_Last(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalIdContestId_Last(sourceProposalId,
            sourceContestId, orderByComparator);
    }

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalIdContestId_Last(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySourceProposalIdContestId_Last(sourceProposalId,
            sourceContestId, orderByComparator);
    }

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory[] findBySourceProposalIdContestId_PrevAndNext(
        long id_, long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySourceProposalIdContestId_PrevAndNext(id_,
            sourceProposalId, sourceContestId, orderByComparator);
    }

    /**
    * Removes all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63; from the database.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeBySourceProposalIdContestId(sourceProposalId, sourceContestId);
    }

    /**
    * Returns the number of proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countBySourceProposalIdContestId(long sourceProposalId,
        long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countBySourceProposalIdContestId(sourceProposalId,
            sourceContestId);
    }

    /**
    * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalMoveHistoryException} if it could not be found.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTargetProposalIdContestId(targetProposalId,
            targetContestId);
    }

    /**
    * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetProposalIdContestId(targetProposalId,
            targetContestId);
    }

    /**
    * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalIdContestId(
        long targetProposalId, long targetContestId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTargetProposalIdContestId(targetProposalId,
            targetContestId, retrieveFromCache);
    }

    /**
    * Removes the proposal move history where targetProposalId = &#63; and targetContestId = &#63; from the database.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the proposal move history that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory removeByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByTargetProposalIdContestId(targetProposalId,
            targetContestId);
    }

    /**
    * Returns the number of proposal move histories where targetProposalId = &#63; and targetContestId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countByTargetProposalIdContestId(long targetProposalId,
        long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByTargetProposalIdContestId(targetProposalId,
            targetContestId);
    }

    /**
    * Caches the proposal move history in the entity cache if it is enabled.
    *
    * @param proposalMoveHistory the proposal move history
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory) {
        getPersistence().cacheResult(proposalMoveHistory);
    }

    /**
    * Caches the proposal move histories in the entity cache if it is enabled.
    *
    * @param proposalMoveHistories the proposal move histories
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalMoveHistory> proposalMoveHistories) {
        getPersistence().cacheResult(proposalMoveHistories);
    }

    /**
    * Creates a new proposal move history with the primary key. Does not add the proposal move history to the database.
    *
    * @param id_ the primary key for the new proposal move history
    * @return the new proposal move history
    */
    public static com.ext.portlet.model.ProposalMoveHistory create(long id_) {
        return getPersistence().create(id_);
    }

    /**
    * Removes the proposal move history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history that was removed
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory remove(long id_)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id_);
    }

    public static com.ext.portlet.model.ProposalMoveHistory updateImpl(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalMoveHistory);
    }

    /**
    * Returns the proposal move history with the primary key or throws a {@link com.ext.portlet.NoSuchProposalMoveHistoryException} if it could not be found.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory findByPrimaryKey(
        long id_)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id_);
    }

    /**
    * Returns the proposal move history with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history, or <code>null</code> if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalMoveHistory fetchByPrimaryKey(
        long id_) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id_);
    }

    /**
    * Returns all the proposal move histories.
    *
    * @return the proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal move histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal move histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalMoveHistory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal move histories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal move histories.
    *
    * @return the number of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalMoveHistoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalMoveHistoryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalMoveHistoryPersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalMoveHistoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalMoveHistoryPersistence persistence) {
    }
}
