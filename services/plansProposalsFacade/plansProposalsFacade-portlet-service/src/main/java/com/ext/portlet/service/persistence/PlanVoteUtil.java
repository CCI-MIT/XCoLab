package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanVote;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan vote service. This utility wraps {@link PlanVotePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanVotePersistence
 * @see PlanVotePersistenceImpl
 * @generated
 */
public class PlanVoteUtil {
    private static PlanVotePersistence _persistence;

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
    public static void clearCache(PlanVote planVote) {
        getPersistence().clearCache(planVote);
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
    public static List<PlanVote> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanVote> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanVote> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanVote update(PlanVote planVote) throws SystemException {
        return getPersistence().update(planVote);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanVote update(PlanVote planVote,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planVote, serviceContext);
    }

    /**
    * Returns all the plan votes where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanVote> findBycontestId(
        long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBycontestId(contestId);
    }

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
    public static java.util.List<com.ext.portlet.model.PlanVote> findBycontestId(
        long contestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBycontestId(contestId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PlanVote> findBycontestId(
        long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBycontestId(contestId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote findBycontestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBycontestId_First(contestId, orderByComparator);
    }

    /**
    * Returns the first plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchBycontestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBycontestId_First(contestId, orderByComparator);
    }

    /**
    * Returns the last plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote findBycontestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBycontestId_Last(contestId, orderByComparator);
    }

    /**
    * Returns the last plan vote in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchBycontestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBycontestId_Last(contestId, orderByComparator);
    }

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
    public static com.ext.portlet.model.PlanVote[] findBycontestId_PrevAndNext(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK,
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBycontestId_PrevAndNext(planVotePK, contestId,
            orderByComparator);
    }

    /**
    * Removes all the plan votes where contestId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBycontestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBycontestId(contestId);
    }

    /**
    * Returns the number of plan votes where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the number of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public static int countBycontestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBycontestId(contestId);
    }

    /**
    * Returns all the plan votes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanVote> findByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(planId);
    }

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
    public static java.util.List<com.ext.portlet.model.PlanVote> findByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(planId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PlanVote> findByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote findByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the first plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote findByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the last plan vote in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanId_Last(planId, orderByComparator);
    }

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
    public static com.ext.portlet.model.PlanVote[] findByPlanId_PrevAndNext(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planVotePK, planId,
            orderByComparator);
    }

    /**
    * Removes all the plan votes where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanId(planId);
    }

    /**
    * Returns the number of plan votes where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanId(planId);
    }

    /**
    * Returns the plan vote where contestId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanVoteException} if it could not be found.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the matching plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote findByContestIdUserId(
        long contestId, long userId)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestIdUserId(contestId, userId);
    }

    /**
    * Returns the plan vote where contestId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchByContestIdUserId(
        long contestId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByContestIdUserId(contestId, userId);
    }

    /**
    * Returns the plan vote where contestId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan vote, or <code>null</code> if a matching plan vote could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchByContestIdUserId(
        long contestId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestIdUserId(contestId, userId, retrieveFromCache);
    }

    /**
    * Removes the plan vote where contestId = &#63; and userId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the plan vote that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote removeByContestIdUserId(
        long contestId, long userId)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByContestIdUserId(contestId, userId);
    }

    /**
    * Returns the number of plan votes where contestId = &#63; and userId = &#63;.
    *
    * @param contestId the contest ID
    * @param userId the user ID
    * @return the number of matching plan votes
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestIdUserId(long contestId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestIdUserId(contestId, userId);
    }

    /**
    * Caches the plan vote in the entity cache if it is enabled.
    *
    * @param planVote the plan vote
    */
    public static void cacheResult(com.ext.portlet.model.PlanVote planVote) {
        getPersistence().cacheResult(planVote);
    }

    /**
    * Caches the plan votes in the entity cache if it is enabled.
    *
    * @param planVotes the plan votes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanVote> planVotes) {
        getPersistence().cacheResult(planVotes);
    }

    /**
    * Creates a new plan vote with the primary key. Does not add the plan vote to the database.
    *
    * @param planVotePK the primary key for the new plan vote
    * @return the new plan vote
    */
    public static com.ext.portlet.model.PlanVote create(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK) {
        return getPersistence().create(planVotePK);
    }

    /**
    * Removes the plan vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote that was removed
    * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote remove(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planVotePK);
    }

    public static com.ext.portlet.model.PlanVote updateImpl(
        com.ext.portlet.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planVote);
    }

    /**
    * Returns the plan vote with the primary key or throws a {@link com.ext.portlet.NoSuchPlanVoteException} if it could not be found.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote
    * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote findByPrimaryKey(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK)
        throws com.ext.portlet.NoSuchPlanVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planVotePK);
    }

    /**
    * Returns the plan vote with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote, or <code>null</code> if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanVote fetchByPrimaryKey(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planVotePK);
    }

    /**
    * Returns all the plan votes.
    *
    * @return the plan votes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanVote> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.PlanVote> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PlanVote> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan votes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan votes.
    *
    * @return the number of plan votes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanVotePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanVotePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanVotePersistence.class.getName());

            ReferenceRegistry.registerReference(PlanVoteUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanVotePersistence persistence) {
    }
}
