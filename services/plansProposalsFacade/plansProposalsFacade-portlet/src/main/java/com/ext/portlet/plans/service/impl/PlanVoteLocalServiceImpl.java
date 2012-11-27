package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanVoteLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlanItemFinderUtil;
import com.ext.portlet.plans.service.persistence.PlanVotePK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan vote local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanVoteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanVoteLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanVoteLocalServiceUtil
 */
public class PlanVoteLocalServiceImpl extends PlanVoteLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanVoteLocalServiceUtil} to access the plan vote local service.
     */
    /**
     * Votes for a plan, if user has already voted for given plan returns false, true otherwise.
     */
    public boolean voteForPlan(Long planId, Long userId) throws SystemException, PortalException {
        PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
        PlanVotePK votePk = new PlanVotePK(userId, plan.getContest().getContestPK());
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(votePk);
            PlanVotePK oldVotePk = new PlanVotePK();
            oldVotePk.userId = userId;
            if (vote.getPlanId() == planId) {
                return false;
            }
            try {
                PlanItem planOldVote = PlanItemLocalServiceUtil.getPlan(vote.getPlanId());
                oldVotePk.setContestId(planOldVote.getContest().getContestPK());
                planOldVote.unvote(userId);
            }
            catch (NoSuchPlanItemException e) {
                // ignore
            }
            deletePlanVote(oldVotePk);
        }
        catch (NoSuchPlanVoteException e) {
            // ignore
        }
        PlanVote vote = createPlanVote(votePk);
        vote.setPlanId(planId);
        vote.setCreateDate(new Date());
        addPlanVote(vote);
        
        return true;
    }
    
    public boolean unvote(Long userId, Long contestId) throws SystemException, PortalException {
        try {
            PlanVotePK votePk = new PlanVotePK(userId, contestId);
            PlanVoteLocalServiceUtil.deletePlanVote(votePk);
            return true;
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        return false;
    }
    
    public PlanVote getPlanVote(Long userId, Long contestId) throws SystemException, PortalException {
        PlanVotePK votePk = new PlanVotePK(userId, contestId);
        return getPlanVote(votePk);
    }
    
    public int coutPlanVotes(Long planId) throws SystemException {
        return planVotePersistence.countByPlanId(planId);   
    }
    
    public List<PlanVote> getPlanVotes(Long planId) throws SystemException {
        return planVotePersistence.findByPlanId(planId);
    }

    public int countPlanVotes(PlanType type) throws SystemException {
        return PlanItemFinderUtil.countVotesForPlanType(type);
    }
    

    public int countPlanVotes(Contest contest) throws SystemException {
        return planVotePersistence.countBycontestId(contest.getContestPK());
    }
    
    public int countPlanVotesByPlanId(Long planId) throws SystemException {
        return planVotePersistence.countByPlanId(planId);
    }

}
