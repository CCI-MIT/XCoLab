package org.xcolab.service.contest.utils.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import static org.xcolab.util.enums.promotion.JudgingSystemActions.AdvanceDecision;
import static org.xcolab.util.enums.promotion.JudgingSystemActions.FellowAction;

/**
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {

    private static final Logger _log = LoggerFactory.getLogger(PhasePromotionHelper.class);

    private final ContestPhaseWrapper phase;

    public PhasePromotionHelper(ContestPhaseWrapper phase) {
        this.phase = phase;
    }

    public boolean isProposalVisible(ProposalWrapper p) {
        if (!p.isVisible()) {
            return false;
        }

        IProposalContestPhaseAttribute attr = StaticProposalContext.getProposalPhaseClient()
                .getProposalContestPhaseAttribute(p.getId(),
                        phase.getId(), ProposalContestPhaseAttributeKeys.VISIBLE);

        return attr == null || attr.getNumericValue() != 0;
    }

    private IProposalContestPhaseAttribute getAttribute(long proposalId, String key) {
        return StaticProposalContext.getProposalPhaseClient()
                .getProposalContestPhaseAttribute(proposalId, phase.getId(), key);
    }

    public boolean isAllProposalsReviewed() {
        boolean allProposalsReviewed = true;
        for (ProposalWrapper p : StaticProposalContext.getProposalClient()
                .getProposalsInContestPhase(phase.getId())) {
            if (!isProposalReviewed(p)) {
                allProposalsReviewed = false;
                break;
            }
        }

        return allProposalsReviewed;
    }

    public boolean isProposalPromoted(ProposalWrapper p) {
        boolean hasProposalAlreadyBeenPromoted = StaticProposalContext.getProposalPhaseClient()
                .isProposalContestPhaseAttributeSetAndTrue(p.getId(), phase.getId(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE);

        if (hasProposalAlreadyBeenPromoted) {
            _log.info("proposal {} in phase {} has already been promoted.", p.getId(),
                    phase.getId());
            return true;
        } else {
            return false;
        }
    }

    public boolean isProposalReviewed(ProposalWrapper p) {
        final AdvanceDecision judgesAdvanceDecision = getJudgeAdvancingDecision(p);
        final FellowAction fellowAdvanceDecision = getFellowAdvancingDecision(p);

        return judgesAdvanceDecision != AdvanceDecision.NO_DECISION
                || fellowAdvanceDecision.isActionProhibitingAdvancing();
    }

    private FellowAction getFellowAdvancingDecision(ProposalWrapper p) {
        IProposalContestPhaseAttribute fellowAction = getAttribute(p.getId(),
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

    private AdvanceDecision getJudgeAdvancingDecision(ProposalWrapper p) {
        IProposalContestPhaseAttribute judgeDecision = getAttribute(p.getId(),
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

    public boolean didJudgeDecideToPromote(ProposalWrapper p) {
        final AdvanceDecision judgesAdvanceDecision = getJudgeAdvancingDecision(p);
        return judgesAdvanceDecision == AdvanceDecision.MOVE_ON;
    }

    public boolean isPhaseContestScheduleTemplatePhase() {
        // template contest phases have no contest assigned
        return phase.getContestId() == 0;
    }

    public static void createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            StaticProposalContext.getProposalPhaseClient().persistProposalContestPhaseAttribute(
                    proposalId, currentPhaseId, ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                    0L, 0L, "true");
        }
    }
}
