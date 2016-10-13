package org.xcolab.service.proposal.helper.autopromotion;



import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.phases.ContestPhase;

import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

/**
 * Created by johannes on 9/8/15.
 *
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {

    //private final static Log _log = LogFactoryUtil.getLog(PhasePromotionHelper.class);

    private ContestPhase phase;

    private ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

    private ProposalDao proposalDao;

    public PhasePromotionHelper(ContestPhase phase) {
        this.phase = phase;
    }

    public boolean proposalIsVisible(Proposal p) {
        if(!p.getVisible()) return false;

        try {
            ProposalContestPhaseAttribute attr = proposalContestPhaseAttributeDao.getByProposalIdContestPhaseIdName(
                    p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.VISIBLE);
            if(attr.getNumericValue() == 0) {
                return false;
            }
        } catch (Exception ignored) {
        }

        return true;
    }

    public ProposalContestPhaseAttribute getAttribute(long proposalId, String key) {
        try {
            return proposalContestPhaseAttributeDao.getByProposalIdContestPhaseIdName(proposalId, phase.getContestPhasePK(), key);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean allProposalsReviewed() {
        boolean allProposalsReviewed = true;
        for (Proposal p : proposalDao.findByGiven(new PaginationHelper(0,Integer.MAX_VALUE,""),null,null,phase.getContestPhasePK(),null)) {
            if (!hasProposalAlreadyBeenReviewed(p)) {
                allProposalsReviewed = false;
                break;
            }
        }

        return allProposalsReviewed;
    }

    public boolean hasProposalAlreadyBeenPromoted(Proposal p) {

        try{
            boolean hasProposalAlreadyBeenPromoted = proposalContestPhaseAttributeDao.getByProposalIdContestPhaseIdName(
                    p.getProposalId(),
                    phase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.PROMOTE_DONE).getStringValue()=="true";
            if (hasProposalAlreadyBeenPromoted) {
                //_log.info("proposal "+ p.getProposalId() +" in phase " + phase.getContestPhasePK() + " has already been promoted.");
                return true;
            } else {
                return false;
            }
        }catch (NotFoundException ignored){
            return false;
        }

    }

    public boolean hasProposalAlreadyBeenReviewed(Proposal p) {
        ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        Long judgeDecisionValue = (judgeDecision == null) ? JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue() : judgeDecision.getNumericValue();
        ProposalContestPhaseAttribute fellowAction = getAttribute(p.getProposalId(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        Long fellowActionValue = (fellowAction == null) ? JudgingSystemActions.FellowAction.NO_DECISION.getAttributeValue() : fellowAction.getNumericValue();

        final JudgingSystemActions.AdvanceDecision judgesAdvanceDecision = JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue.intValue());
        final JudgingSystemActions.FellowAction fellowAdvanceDecision = JudgingSystemActions.FellowAction.fromInt(fellowActionValue.intValue());
        return !(judgesAdvanceDecision == JudgingSystemActions.AdvanceDecision.NO_DECISION &&
                !fellowAdvanceDecision.isActionProhibitingAdvancing());
    }

    public boolean didJudgeDecideToPromote(Proposal p) {
        ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(),
                ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        Long judgeDecisionValue;
        if (judgeDecision == null) {
            judgeDecisionValue = (long) JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue();
        } else {
            judgeDecisionValue = judgeDecision.getNumericValue();
        }

        return JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue.intValue())
                == JudgingSystemActions.AdvanceDecision.MOVE_ON;
    }

    public boolean isPhaseContestHasNoValidContest(){
        try{
            ContestClientUtil.getContest(phase.getContestPK());
        } catch(ContestNotFoundException e){
            //_log.warn("promoting phase failed due to invalid contest ", e);
            return true;
        }
        return false;
    }

    public boolean isPhaseContestScheduleTemplatePhase() {
        // Usually we do not have phases with a ContestId key = 0; used as template contest phases for our ContestSchedules
        return phase.getContestPK() == 0;
    }

    public static ProposalContestPhaseAttribute createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            ProposalContestPhaseAttribute proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setContestPhaseId(currentPhaseId);
            proposalContestPhaseAttribute.setName(ProposalContestPhaseAttributeKeys.PROMOTE_DONE);
            proposalContestPhaseAttribute.setAdditionalId(0l);
            proposalContestPhaseAttribute.setStringValue("true");
            return proposalContestPhaseAttribute;

        }
        return null;
    }
}
