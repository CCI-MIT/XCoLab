package org.xcolab.utils.judging;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * This is a helper class that interprets the Judging Feedback message made during judging contest phases
 * for email notifications and proposal comment thread messages. In addition it is a utility class to get and set
 * Screening and Advance comments
 *
 * Created by kmang on 27/05/14.
 */
public class ProposalJudgingCommentHelper {

    private final static Log _log = LogFactoryUtil.getLog(ProposalJudgingCommentHelper.class);

    private Proposal proposal;
    private ContestPhase contestPhase;

    public ProposalJudgingCommentHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
    }

    public String getScreeningComment() throws NoSuchProposalContestPhaseAttributeException, SystemException {
        //get fellow decision
        ProposalContestPhaseAttribute fellowActionAttribute = ProposalContestPhaseAttributeLocalServiceUtil.
                getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue());

        if (fellowAction != JudgingSystemActions.FellowAction.NO_DECISION &&
                fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
            String fellowRejectionText = ProposalContestPhaseAttributeLocalServiceUtil.
                    getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, 0).getStringValue();

            return fellowRejectionText;
        }

        return null;
    }

    public void setScreeningComment(String comment) throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute fellowActionAttribute = ProposalContestPhaseAttributeLocalServiceUtil.
                getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue());

        //save comment if the action is "incomplete" or "off-topic"
        if (fellowAction != JudgingSystemActions.FellowAction.NO_DECISION &&
                fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
            persistAttribute(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, comment);
        }
    }

    public String getAdvancingComment() throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute advanceDecisionAttribute = ProposalContestPhaseAttributeLocalServiceUtil.
                getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0);
        JudgingSystemActions.AdvanceDecision advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt((int) advanceDecisionAttribute.getNumericValue());

        if (advanceDecision != JudgingSystemActions.AdvanceDecision.NO_DECISION) {
            String advanceDecisionText = ProposalContestPhaseAttributeLocalServiceUtil.
                    getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, 0).getStringValue();

            return advanceDecisionText;
        }

        return null;
    }

    public void setAdvancingComment(String comment) throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute advanceDecisionAttribute = getProposalContestPhaseAttributeCreateIfNotExists(ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        JudgingSystemActions.AdvanceDecision advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt((int) advanceDecisionAttribute.getNumericValue());

        if (advanceDecision != JudgingSystemActions.AdvanceDecision.NO_DECISION) {

            String advanceMessage = comment;
            persistAttribute(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, advanceMessage);
        }
    }

    /**
     * Based on the fellows' and judges' decisions, this method returns the
     * right comment wrapped in a standard text template, ready to be send per email
     * or posted as a comment to the proposal authors.
     *
     * @return the comment wrapped with the correct template from the preferences
     * @throws NoSuchProposalContestPhaseAttributeException
     * @throws SystemException
     */
    public String getPromotionComment(boolean isWrapWithTemplate) throws NoSuchProposalContestPhaseAttributeException, SystemException, PortalException {
        String proposalName = ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
        String contestName = ContestLocalServiceUtil.getContest(contestPhase.getContestPK()).getContestShortName();

        //get fellow decision
        JudgingSystemActions.FellowAction fellowAction;
        try {
            ProposalContestPhaseAttribute fellowActionAttribute = ProposalContestPhaseAttributeLocalServiceUtil.
                    getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
            fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue());
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            fellowAction = JudgingSystemActions.FellowAction.NO_DECISION;
        }

        //JUDGE DECISION
        if (fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
            try {
                String reviewText = ProposalContestPhaseAttributeLocalServiceUtil.
                        getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, 0).getStringValue();

                ProposalContestPhaseAttribute advanceDecisionAttribute = ProposalContestPhaseAttributeLocalServiceUtil.
                        getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0);
                JudgingSystemActions.AdvanceDecision advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt((int) advanceDecisionAttribute.getNumericValue());

                if (advanceDecision != JudgingSystemActions.AdvanceDecision.NO_DECISION) {
                    String templateToLoad = (advanceDecision == JudgingSystemActions.AdvanceDecision.MOVE_ON) ? "ADVANCING_ADVANCE_TO_SEMIFINALIST" : "ADVANCING_DO_NOT_ADVANCE";

                    ContestEmailTemplateWrapper wrapper = new ContestEmailTemplateWrapper(
                            ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(templateToLoad),
                            proposalName,
                            contestName
                    );
                    return isWrapWithTemplate ? wrapper.getCompleteMessage(reviewText) : reviewText;
                }
            } catch (NoSuchProposalContestPhaseAttributeException e) {
                _log.error("Could not extract contest phase promotion body for proposal " + proposal.getProposalId() + " in contestPhase " + contestPhase.getContestPhasePK(), e);
            }
        //FELLOW DECISION: Incomplete/Off-Topic
        } else if (fellowAction != JudgingSystemActions.FellowAction.NO_DECISION) {
            String fellowReviewText = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT,
                    0
            ).getStringValue();

            String templateToLoad = (fellowAction == JudgingSystemActions.FellowAction.INCOMPLETE) ? "SCREENING_DO_NOT_ADVANCE_INCOMPLETE" : "SCREENING_DO_NOT_ADVANCE_OFF_TOPIC";

            ContestEmailTemplateWrapper wrapper = new ContestEmailTemplateWrapper(
                    ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(templateToLoad),
                    proposalName,
                    contestName
            );
            return isWrapWithTemplate ? wrapper.getCompleteMessage(fellowReviewText) : fellowReviewText;
        }

        return StringPool.BLANK;
    }



    private boolean persistAttribute(String attributeName, long numericValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(attributeName);

        attribute.setNumericValue(numericValue);

        try {
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean persistAttribute(String attributeName, String stringValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(attributeName);

        attribute.setStringValue(stringValue);

        try {
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(String attributeName) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.
                    getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), attributeName, 0);
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            try {
                ProposalContestPhaseAttribute attribute = ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                        CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
                attribute.setProposalId(proposal.getProposalId());
                attribute.setContestPhaseId(contestPhase.getContestPhasePK());
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
