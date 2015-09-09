package com.ext.utils.promotion;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Created by johannes on 9/8/15.
 *
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {
    private final static Log _log = LogFactoryUtil.getLog(PhasePromotionHelper.class);

    private ContestPhase phase;

    public PhasePromotionHelper(ContestPhase phase) {
        this.phase = phase;
    }

    public boolean proposalIsVisible(Proposal p) {
        if(!p.getVisible()) return false;

        try {
            ProposalContestPhaseAttribute attr = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(
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
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, phase.getContestPhasePK(), key);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean allProposalsReviewed() throws SystemException, PortalException {
        boolean allProposalsReviewed = true;
        for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
            if (!hasProposalAlreadyBeenReviewed(p)) {
                allProposalsReviewed = false;
                break;
            }
        }

        return allProposalsReviewed;
    }

    public boolean hasProposalAlreadyBeenPromoted(Proposal p) throws SystemException {
        boolean hasProposalAlreadyBeenPromoted = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                p.getProposalId(),
                phase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                0
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

    public boolean isPhaseContestHasNoValidContest() throws PortalException, SystemException{
        try{
            ContestPhaseLocalServiceUtil.getContest(phase);
        } catch(NoSuchContestException e){
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
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    currentPhaseId,
                    ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                    0,
                    "true"
            );
        }
    }
}
