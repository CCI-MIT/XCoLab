package org.xcolab.service.contest.utils.promotion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.utils.promotion.enums.JudgingSystemActions;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

/**
 * Created by johannes on 9/8/15.
 *
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {


    private static final Logger _log = LoggerFactory.getLogger(PhasePromotionHelper.class);
    private ContestPhase phase;

    private ContestDao contestDao;

    public PhasePromotionHelper(ContestPhase phase, ContestDao contestDao) {
        this.phase = phase;
        this.contestDao = contestDao;
    }

    public boolean proposalIsVisible(Proposal p) {
        if(!p.getVisible()) return false;

        try {
            ProposalContestPhaseAttribute attr = ProposalPhaseClientUtil.getProposalContestPhaseAttribute(
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
            return ProposalPhaseClientUtil.getProposalContestPhaseAttribute(proposalId, phase.getContestPhasePK(), key);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean allProposalsReviewed() {
        boolean allProposalsReviewed = true;
        for (Proposal p : ProposalClientUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
            if (!hasProposalAlreadyBeenReviewed(p)) {
                allProposalsReviewed = false;
                break;
            }
        }

        return allProposalsReviewed;
    }

    public boolean hasProposalAlreadyBeenPromoted(Proposal p) {
        boolean hasProposalAlreadyBeenPromoted = ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                p.getProposalId(),
                phase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE
        );

        if (hasProposalAlreadyBeenPromoted) {
            _log.info("proposal "+ p.getProposalId() +" in phase " + phase.getContestPhasePK() + " has already been promoted.");
            return true;
        } else {
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

    public boolean isPhaseContestHasNoValidContest() {
        try{
            contestDao.get(phase.getContestPK());
        } catch(NotFoundException e){
            _log.warn("promoting phase failed due to invalid contest ", e);
            return true;
        }
        return false;
    }

    public boolean isPhaseContestScheduleTemplatePhase() {
        // Usually we do not have phases with a ContestId key = 0; used as template contest phases for our ContestSchedules
        return phase.getContestPK() == 0;
    }

    public static void createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            ProposalPhaseClientUtil.persistProposalContestPhaseAttribute(
                    proposalId,
                    currentPhaseId,
                    ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                    0l,
                    0l,
                    "true"
            );
        }
    }
}
