package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.NoSuchPlanVoteException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.PlanVote;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.base.PlanVoteLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.PlanItemFinderUtil;
import com.ext.portlet.service.persistence.PlanVotePK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan vote local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanVoteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanVoteLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanVoteLocalServiceUtil
 */
public class PlanVoteLocalServiceImpl extends PlanVoteLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanVoteLocalServiceUtil} to access the plan vote local service.
     */
    public boolean voteForPlan(Long planId, Long userId) throws SystemException, PortalException {
        PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
        PlanVotePK votePk = new PlanVotePK(userId, PlanItemLocalServiceUtil.getContest(plan).getContestPK());
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(votePk);
            PlanVotePK oldVotePk = new PlanVotePK();
            oldVotePk.userId = userId;
            if (vote.getPlanId() == planId) {
                return false;
            }
            try {
                PlanItem planOldVote = PlanItemLocalServiceUtil.getPlan(vote.getPlanId());
                oldVotePk.setContestId(PlanItemLocalServiceUtil.getContest(planOldVote).getContestPK());
                PlanItemLocalServiceUtil.unvote(planOldVote, userId);
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
    

    public void store(PlanVote planVote) throws SystemException {
        if (planVote.isNew()) {
            PlanVoteLocalServiceUtil.addPlanVote(planVote);
        }
        else {
            PlanVoteLocalServiceUtil.updatePlanVote(planVote);
        }
    }
}
