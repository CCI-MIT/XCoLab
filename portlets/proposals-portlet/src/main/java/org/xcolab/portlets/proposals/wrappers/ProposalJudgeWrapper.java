package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

/**
 * Created by patrickhiesel on 19/12/13.
 */
public class ProposalJudgeWrapper extends ProposalWrapper {

    private User currentUser;

    public ProposalJudgeWrapper(ProposalWrapper proposal, User currentUser) {
        super(proposal);
        this.currentUser = currentUser;
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase, User currentUser) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentUser = currentUser;
    }

    /**
     *
     * @throws SystemException
     * @throws PortalException
     */
    public JudgingSystemActions.JudgeReviewStatus getJudgeReviewStatus() throws SystemException, PortalException {
        if (currentUser == null) return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;
        for (long userId : this.getSelectedJudges()) {
            if (currentUser.getUserId() == userId) {
                long judgeRating = getContestPhaseAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_RATING, currentUser.getUserId(), LONG_DEFAULT_VAL);
                String judgeComment = getContestPhaseAttributeStringValue(ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, currentUser.getUserId(), STRING_DEFAULT_VAL);

                if (Validator.isNotNull(judgeRating) && Validator.isNotNull(judgeComment)) {
                    return JudgingSystemActions.JudgeReviewStatus.DONE;
                } else {
                    return JudgingSystemActions.JudgeReviewStatus.NOT_DONE;
                }
            }
        }
        return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;
    }

    /**
     * get email message that is supposed that is supposed to be sent out to users based on the decisions either the fellow or the judge took
     *
     * @param contestPhaseId
     * @param prefs
     * @return
     */
    public String getEmailMessage(long contestPhaseId, ProposalsPreferencesWrapper prefs) {
        //get fellow decision
        ProposalContestPhaseAttribute p = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) p.getNumericValue());
        if (fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
            //judge decided
            String judgeText = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, 0).getStringValue();
            ProposalContestPhaseAttribute pa = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0);
            JudgingSystemActions.AdvanceDecision advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt((int) pa.getNumericValue());
            if (advanceDecision == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingRejectionText(), judgeText);
            } else if (advanceDecision == JudgingSystemActions.AdvanceDecision.MOVE_ON) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingAcceptanceText(), judgeText);
            }
        } else {
            //fellow decided
            String fellowText = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_COMMENT, 0).getStringValue();
            if (fellowAction == JudgingSystemActions.FellowAction.INCOMPLETE) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingIncompleteText(), fellowText);
            } else if (fellowAction == JudgingSystemActions.FellowAction.OFFTOPIC) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingOfftopicText(), fellowText);
            }
        }
        return null;
    }

    public Long getJudgeRating() throws SystemException, PortalException {
        return getContestPhaseAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_RATING, currentUser.getUserId(), 0);
    }

    public String getJudgeComment() throws SystemException, PortalException {
        return getContestPhaseAttributeStringValue(ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, currentUser.getUserId(), "");
    }

    public boolean shouldShowJudgingTab(long contestPhaseId) {
        ProposalContestPhaseAttribute a = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) a.getNumericValue());
        return fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES;
    }

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(long proposalId, long contestPhaseId, String attributeName, long additionalId) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            try {
                ProposalContestPhaseAttribute attribute = ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                        CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
                attribute.setProposalId(proposalId);
                attribute.setContestPhaseId(contestPhaseId);
                attribute.setName(attributeName);
                ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute(attribute);
                return attribute;
            } catch (Exception e2) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
