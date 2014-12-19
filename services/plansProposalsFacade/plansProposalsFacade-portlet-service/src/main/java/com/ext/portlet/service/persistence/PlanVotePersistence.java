package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanVote;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanVotePersistenceImpl
 * @see PlanVoteUtil
 * @generated
 */
public interface PlanVotePersistence extends BasePersistence<PlanVote> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanVoteUtil} to access the plan vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the plan votes where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findBycontestId(
        long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan votes where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestId the contest ID
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @return the range of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findBycontestId(
        long contestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan votes where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestId the contest ID
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findBycontestId(
        long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote findBycontestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchBycontestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote findBycontestId_Last(long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchBycontestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan votes before and after the current plan vote in the ordered set where contestId = &#63;.
    *
    * @param planVotePK the primary key of the current plan vote
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote[] findBycontestId_PrevAndNext(
        PlanVotePK planVotePK, long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan votes where contestId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBycontestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan votes where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the number of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public int countBycontestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan votes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan votes where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @return the range of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan votes where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote findByPlanId_First(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchByPlanId_First(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote findByPlanId_Last(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchByPlanId_Last(long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan votes before and after the current plan vote in the ordered set where planId = &#63;.
    *
    * @param planVotePK the primary key of the current plan vote
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote[] findByPlanId_PrevAndNext(
        PlanVotePK planVotePK, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan votes where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan votes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public int countByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan vote where contestId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanVoteException} if it could not be found.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote findByContestIdUserId(
        long contestId, long userId)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan vote where contestId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchByContestIdUserId(
        long contestId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan vote where contestId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchByContestIdUserId(
        long contestId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the plan vote where contestId = &#63; and userId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the plan vote that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote removeByContestIdUserId(
        long contestId, long userId)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan votes where contestId = &#63; and userId = &#63;.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the number of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public int countByContestIdUserId(long contestId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan vote in the entity cache if it is enabled.
    *
    * @param planVote the plan vote
    */
    public void cacheResult(com.ext.portlet.model.PlanVote planVote);

    /**
    * Caches the plan votes in the entity cache if it is enabled.
    *
    * @param planVotes the plan votes
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PlanVote> planVotes);

    /**
    * Creates a new plan vote with the primary key. Does not add the plan vote to the database.
    *
    * @param planVotePK the primary key for the new plan vote
    * @return the new plan vote
    */
    public com.ext.portlet.model.PlanVote create(PlanVotePK planVotePK);

    /**
    * Removes the plan vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote that was removed
    * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote remove(PlanVotePK planVotePK)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PlanVote updateImpl(
        com.ext.portlet.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan vote with the primary key or throws a {@link com.ext.portlet.NoSuchPlanVoteException} if it could not be found.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote findByPrimaryKey(
        PlanVotePK planVotePK)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan vote with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote, or <code>null</code> if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanVote fetchByPrimaryKey(
        PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan votes.
    *
    * @return the plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan votes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @return the range of plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan votes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanVote> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan votes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan votes.
    *
    * @return the number of plan votes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
