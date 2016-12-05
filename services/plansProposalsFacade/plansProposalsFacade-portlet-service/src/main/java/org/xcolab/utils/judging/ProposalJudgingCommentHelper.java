package org.xcolab.utils.judging;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

/**
 * This is a helper class that interprets the Judging Feedback message made during judging contest
 * phases for email notifications and proposal comment thread messages. In addition it is a utility
 * class to get and set Screening and Advance comments
 */
public class ProposalJudgingCommentHelper {

    private final static Log _log = LogFactoryUtil.getLog(ProposalJudgingCommentHelper.class);

    private final Proposal proposal;
    private final ContestPhase contestPhase;

    public ProposalJudgingCommentHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
    }

    public String getScreeningComment() throws NoSuchProposalContestPhaseAttributeException, SystemException {
        //get fellow decision
        ProposalContestPhaseAttribute fellowActionAttribute = ProposalPhaseClientUtil.
                getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue().intValue());

        if (fellowAction != JudgingSystemActions.FellowAction.NO_DECISION &&
                fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
            String fellowRejectionText = ProposalPhaseClientUtil.
                    getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT).getStringValue();

            return fellowRejectionText;
        }

        return null;
    }

    public void setScreeningComment(String comment) {
        ProposalContestPhaseAttribute fellowActionAttribute = ProposalPhaseClientUtil.
                getProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue().intValue());

        //save comment if the action is "incomplete" or "off-topic"
        if (fellowAction != JudgingSystemActions.FellowAction.NO_DECISION &&
                fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
            persistAttribute(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, comment);
        }
    }

    public String getAdvancingComment() {
            ProposalContestPhaseAttribute advanceDecisionAttribute = ProposalPhaseClientUtil.
                    getProposalContestPhaseAttribute(proposal.getProposalId(),
                            contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION );
            JudgingSystemActions.AdvanceDecision advanceDecision =
                    JudgingSystemActions.AdvanceDecision.fromInt(
                            advanceDecisionAttribute.getNumericValue().intValue());

            if (advanceDecision != JudgingSystemActions.AdvanceDecision.NO_DECISION) {

                return ProposalPhaseClientUtil.
                        getProposalContestPhaseAttribute(proposal.getProposalId(),
                                contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW).getStringValue();
            }

        return null;
    }

    public void setAdvancingComment(String comment) {
        ProposalContestPhaseAttribute advanceDecisionAttribute = getProposalContestPhaseAttributeCreateIfNotExists(ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        JudgingSystemActions.AdvanceDecision advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt((int) advanceDecisionAttribute.getNumericValue().intValue());

        if (advanceDecision != JudgingSystemActions.AdvanceDecision.NO_DECISION) {

            String advanceMessage = comment;
            persistAttribute(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, advanceMessage);
        }
    }

    /**
     * Based on the fellows' and judges' decisions, this method returns the right comment wrapped in
     * a standard text template, ready to be send per email or posted as a comment to the proposal
     * authors.
     *
     * @return the comment wrapped with the correct template from the preferences
     */
    public String getPromotionComment(boolean isWrapWithTemplate) {
        try {
            String proposalName = ProposalAttributeClientUtil
                    .getProposalAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0l)
                    .getStringValue();
            String contestName = ContestClientUtil.getContest(contestPhase.getContestPK())
                    .getContestShortName();

            //get fellow decision
            JudgingSystemActions.FellowAction fellowAction;

            ProposalContestPhaseAttribute fellowActionAttribute = ProposalPhaseClientUtil
                    .
                            getProposalContestPhaseAttribute(proposal.getProposalId(),
                                    contestPhase.getContestPhasePK(),
                                    ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
            fellowAction = JudgingSystemActions.FellowAction
                    .fromInt((int) fellowActionAttribute.getNumericValue().intValue());


            //JUDGE DECISION
            if (fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES) {
                    String reviewText = ProposalPhaseClientUtil.
                            getProposalContestPhaseAttribute(proposal.getProposalId(),
                                    contestPhase.getContestPhasePK(),
                                    ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW)
                            .getStringValue();

                    ProposalContestPhaseAttribute advanceDecisionAttribute = ProposalPhaseClientUtil
                            .
                                    getProposalContestPhaseAttribute(proposal.getProposalId(),
                                            contestPhase.getContestPhasePK(),
                                            ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
                    JudgingSystemActions.AdvanceDecision advanceDecision = JudgingSystemActions.AdvanceDecision
                            .fromInt((int) advanceDecisionAttribute.getNumericValue().intValue());

                    if (advanceDecision != JudgingSystemActions.AdvanceDecision.NO_DECISION) {
                        String templateToLoad =
                                (advanceDecision == JudgingSystemActions.AdvanceDecision.MOVE_ON)
                                        ? "ADVANCING_ADVANCE_TO_SEMIFINALIST"
                                        : "ADVANCING_DO_NOT_ADVANCE";

                        EmailTemplateWrapper wrapper = new EmailTemplateWrapper(
                                EmailTemplateClientUtil.getContestEmailTemplateByType(templateToLoad),
                                proposalName,
                                contestName
                        );
                        return isWrapWithTemplate ? wrapper.getCompleteMessage(reviewText)
                                : reviewText;
                    }

                //FELLOW DECISION: Incomplete/Off-Topic
            } else if (fellowAction != JudgingSystemActions.FellowAction.NO_DECISION) {
                String fellowReviewText = ProposalPhaseClientUtil
                        .getProposalContestPhaseAttribute(
                                proposal.getProposalId(),
                                contestPhase.getContestPhasePK(),
                                ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT
                        ).getStringValue();

                String templateToLoad = "SCREENING_DO_NOT_ADVANCE_OFF_TOPIC";
                if (fellowAction == JudgingSystemActions.FellowAction.INCOMPLETE) {
                    templateToLoad = "SCREENING_DO_NOT_ADVANCE_INCOMPLETE";
                } else if (fellowAction == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER) {
                    templateToLoad = "SCREENING_DO_NOT_ADVANCE_OTHER";
                }

                EmailTemplateWrapper wrapper = new EmailTemplateWrapper(
                        EmailTemplateClientUtil.getContestEmailTemplateByType(templateToLoad),
                        proposalName,
                        contestName
                );
                return isWrapWithTemplate ? wrapper.getCompleteMessage(fellowReviewText)
                        : fellowReviewText;
            }

            return StringPool.BLANK;
        } catch (ContestNotFoundException e) {
            throw new IllegalStateException("Could not get promotion comment for"
                    + " proposal " + proposal.getProposalId()
                    + " contestPhase " + contestPhase.getContestPhasePK());
        }
    }

    private boolean persistAttribute(String attributeName, String stringValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(attributeName);
        attribute.setStringValue(stringValue);
        ProposalPhaseClientUtil.updateProposalContestPhaseAttribute(attribute);

        return true;
    }

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(String attributeName) {

        return ProposalPhaseClientUtil.getOrCreateProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), attributeName, 0l, 0l, "");
    }


}
