package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalVote;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVotePersistenceImpl
 * @see ProposalVoteUtil
 * @generated
 */
public interface ProposalVotePersistence extends BasePersistence<ProposalVote> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalVoteUtil} to access the proposal vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the proposal votes where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVote> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalVote[] findByProposalId_PrevAndNext(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK,
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal votes where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal votes where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @return the matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVote> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalVote findByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalVote findByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalVote[] findByProposalIdContestPhaseId_PrevAndNext(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK,
        long proposalId, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal votes where proposalId = &#63; and contestPhaseId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param contestPhaseId the contest phase ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal votes where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVote> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote findByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal vote in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalVote[] findByUserId_PrevAndNext(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK,
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal votes where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal votes where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the matching proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote findByContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByContestPhaseIdUserId(
        long contestPhaseId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the proposal vote where contestPhaseId = &#63; and userId = &#63; from the database.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the proposal vote that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote removeByContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal votes where contestPhaseId = &#63; and userId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param userId the user ID
    * @return the number of matching proposal votes
    * @throws SystemException if a system exception occurred
    */
    public int countByContestPhaseIdUserId(long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal vote in the entity cache if it is enabled.
    *
    * @param proposalVote the proposal vote
    */
    public void cacheResult(com.ext.portlet.model.ProposalVote proposalVote);

    /**
    * Caches the proposal votes in the entity cache if it is enabled.
    *
    * @param proposalVotes the proposal votes
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalVote> proposalVotes);

    /**
    * Creates a new proposal vote with the primary key. Does not add the proposal vote to the database.
    *
    * @param proposalVotePK the primary key for the new proposal vote
    * @return the new proposal vote
    */
    public com.ext.portlet.model.ProposalVote create(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK);

    /**
    * Removes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote that was removed
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote remove(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalVote updateImpl(
        com.ext.portlet.model.ProposalVote proposalVote)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal vote with the primary key or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote
    * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote findByPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal vote with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote, or <code>null</code> if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVote fetchByPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal votes.
    *
    * @return the proposal votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVote> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalVote> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal votes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal votes.
    *
    * @return the number of proposal votes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
