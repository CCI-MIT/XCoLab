package org.xcolab.service.contest.utils.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import static org.xcolab.util.enums.promotion.JudgingSystemActions.AdvanceDecision;
import static org.xcolab.util.enums.promotion.JudgingSystemActions.FellowAction;

/**
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {

    private static final Logger _log = LoggerFactory.getLogger(PhasePromotionHelper.class);

    private final ContestPhase phase;

    public PhasePromotionHelper(ContestPhase phase) {
        this.phase = phase;
    }

    public boolean isProposalVisible(Proposal p) {
        if (!p.getVisible()) {
            return false;
        }

        ProposalContestPhaseAttribute attr = ProposalPhaseClientUtil
                .getProposalContestPhaseAttribute(p.getProposalId(),
                        phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.VISIBLE);

        return attr == null || attr.getNumericValue() != 0;
    }

    private ProposalContestPhaseAttribute getAttribute(long proposalId, String key) {
        return ProposalPhaseClientUtil.getProposalContestPhaseAttribute(proposalId, phase.getContestPhasePK(), key);
    }

    public boolean isAllProposalsReviewed() {
        boolean allProposalsReviewed = true;
        for (Proposal p : ProposalClientUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
            if (!isProposalReviewed(p)) {
                allProposalsReviewed = false;
                break;
            }
        }

        return allProposalsReviewed;
    }

    public boolean isProposalPromoted(Proposal p) {
        boolean hasProposalAlreadyBeenPromoted = ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                p.getProposalId(),
                phase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE);

        if (hasProposalAlreadyBeenPromoted) {
            _log.info("proposal {} in phase {} has already been promoted.", p.getProposalId(),
                    phase.getContestPhasePK());
            return true;
        } else {
            return false;
        }
    }

    public boolean isProposalReviewed(Proposal p) {
        final AdvanceDecision judgesAdvanceDecision = getJudgeAdvancingDecision(p);
        final FellowAction fellowAdvanceDecision = getFellowAdvancingDecision(p);

        return judgesAdvanceDecision != AdvanceDecision.NO_DECISION
                || fellowAdvanceDecision.isActionProhibitingAdvancing();
    }

    private FellowAction getFellowAdvancingDecision(Proposal p) {
        ProposalContestPhaseAttribute fellowAction = getAttribute(p.getProposalId(),
                ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        final FellowAction fellowAdvanceDecision;
        if (fellowAction == null) {
            fellowAdvanceDecision = FellowAction.NO_DECISION;
        } else {
            final int fellowActionValue = fellowAction.getNumericValue().intValue();
            fellowAdvanceDecision = FellowAction.fromInt(fellowActionValue);
        }
        return fellowAdvanceDecision;
    }

    private AdvanceDecision getJudgeAdvancingDecision(Proposal p) {
        ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(),
                ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        final AdvanceDecision judgesAdvanceDecision;
        if (judgeDecision == null) {
            judgesAdvanceDecision = AdvanceDecision.NO_DECISION;
        } else {
            final int judgeDecisionValue = judgeDecision.getNumericValue().intValue();
            judgesAdvanceDecision = AdvanceDecision.fromInt(judgeDecisionValue);
        }
        return judgesAdvanceDecision;
    }

    public boolean didJudgeDecideToPromote(Proposal p) {
        final AdvanceDecision judgesAdvanceDecision = getJudgeAdvancingDecision(p);
        return judgesAdvanceDecision == AdvanceDecision.MOVE_ON;
    }

    public boolean isPhaseContestScheduleTemplatePhase() {
        // template contest phases have no contest assigned
        return phase.getContestPK() == 0;
    }

    public static void createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            ProposalPhaseClientUtil.persistProposalContestPhaseAttribute(
                    proposalId,
                    currentPhaseId,
                    ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                    0L,
                    0L,
                    "true"
            );
        }
    }
}
