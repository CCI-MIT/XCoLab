package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalVote;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the proposal vote service. This utility wraps {@link ProposalVotePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVotePersistence
 * @see ProposalVotePersistenceImpl
 * @generated
 */
public class ProposalVoteUtil {
    private static ProposalVotePersistence _persistence;

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
    public static void clearCache(ProposalVote proposalVote) {
        getPersistence().clearCache(proposalVote);
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
    public static List<ProposalVote> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProposalVote> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProposalVote> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProposalVote update(ProposalVote proposalVote)
        throws SystemException {
        return getPersistence().update(proposalVote);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProposalVote update(ProposalVote proposalVote,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(proposalVote, serviceContext);
    }

    /**
    * Returns all the proposal votes where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

    /**
    * Returns a range of all the proposal votes where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @return the range of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal votes where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the proposal votes before and after the current proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalVotePK the primary key of the current proposal vote
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote[] findByProposalId_PrevAndNext(
        ProposalVotePK proposalVotePK, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(proposalVotePK, proposalId,
            orderByComparator);
    }

    /**
    * Removes all the proposal votes where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of proposal votes where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Returns all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @return the matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    /**
    * Returns a range of all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @return the range of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            start, end);
    }

    /**
    * Returns an ordered range of all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            start, end, orderByComparator);
    }

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdContestPhaseId_First(proposalId,
            contestPhaseId, orderByComparator);
    }

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalIdContestPhaseId_First(proposalId,
            contestPhaseId, orderByComparator);
    }

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdContestPhaseId_Last(proposalId,
            contestPhaseId, orderByComparator);
    }

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalIdContestPhaseId_Last(proposalId,
            contestPhaseId, orderByComparator);
    }

    /**
    * Returns the proposal votes before and after the current proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalVotePK the primary key of the current proposal vote
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote[] findByProposalIdContestPhaseId_PrevAndNext(
        ProposalVotePK proposalVotePK, long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalIdContestPhaseId_PrevAndNext(proposalVotePK,
            proposalId, contestPhaseId, orderByComparator);
    }

    /**
    * Removes all the proposal votes where proposalId = &#63; and contestPhaseId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    /**
    * Returns the number of proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    /**
    * Returns all the proposal votes where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the proposal votes where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @return the range of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the proposal votes where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the first proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the last proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the proposal votes before and after the current proposal vote in the ordered set where userId = &#63;.
    *
    * @param proposalVotePK the primary key of the current proposal vote
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote[] findByUserId_PrevAndNext(
        ProposalVotePK proposalVotePK, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId_PrevAndNext(proposalVotePK, userId,
            orderByComparator);
    }

    /**
    * Removes all the proposal votes where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Returns the number of proposal votes where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestPhaseIdUserId(contestPhaseId, userId);
    }

    /**
    * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestPhaseIdUserId(contestPhaseId, userId);
    }

    /**
    * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByContestPhaseIdUserId(
        long contestPhaseId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestPhaseIdUserId(contestPhaseId, userId,
            retrieveFromCache);
    }

    /**
    * Removes the proposal vote where contestPhaseId = &#63; and userId = &#63; from the database.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the proposal vote that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote removeByContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByContestPhaseIdUserId(contestPhaseId, userId);
    }

    /**
    * Returns the number of proposal votes where contestPhaseId = &#63; and userId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestPhaseIdUserId(long contestPhaseId,
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByContestPhaseIdUserId(contestPhaseId, userId);
    }

    /**
    * Caches the proposal vote in the entity cache if it is enabled.
    *
    * @param proposalVote the proposal vote
    */
    public static void cacheResult(
        com.ext.portlet.model.ProposalVote proposalVote) {
        getPersistence().cacheResult(proposalVote);
    }

    /**
    * Caches the proposal votes in the entity cache if it is enabled.
    *
    * @param proposalVotes the proposal votes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalVote> proposalVotes) {
        getPersistence().cacheResult(proposalVotes);
    }

    /**
    * Creates a new proposal vote with the primary key. Does not add the proposal vote to the database.
    *
    * @param proposalVotePK the primary key for the new proposal vote
    * @return the new proposal vote
    */
    public static com.ext.portlet.model.ProposalVote create(
        ProposalVotePK proposalVotePK) {
        return getPersistence().create(proposalVotePK);
    }

    /**
    * Removes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote that was removed
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote remove(
        ProposalVotePK proposalVotePK)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(proposalVotePK);
    }

    public static com.ext.portlet.model.ProposalVote updateImpl(
        com.ext.portlet.model.ProposalVote proposalVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(proposalVote);
    }

    /**
    * Returns the proposal vote with the primary key or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote findByPrimaryKey(
        ProposalVotePK proposalVotePK)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(proposalVotePK);
    }

    /**
    * Returns the proposal vote with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote, or <code>null</code> if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalVote fetchByPrimaryKey(
        ProposalVotePK proposalVotePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(proposalVotePK);
    }

    /**
    * Returns all the proposal votes.
    *
    * @return the proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the proposal votes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @return the range of proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the proposal votes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalVote> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the proposal votes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of proposal votes.
    *
    * @return the number of proposal votes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProposalVotePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProposalVotePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ProposalVotePersistence.class.getName());

            ReferenceRegistry.registerReference(ProposalVoteUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProposalVotePersistence persistence) {
    }
}
