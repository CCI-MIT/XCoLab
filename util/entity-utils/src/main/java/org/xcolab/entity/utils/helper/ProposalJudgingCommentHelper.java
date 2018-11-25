package org.xcolab.entity.utils.helper;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.entity.utils.notifications.EmailTemplateWrapper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions.AdvanceDecision;
import org.xcolab.util.enums.promotion.JudgingSystemActions.FellowAction;

/**
 * This is a helper class that interprets the Judging Feedback message made during judging contest
 * phases for email notifications and proposal comment thread messages. In addition it is a utility
 * class to get and set Screening and Advance comments
 */
public class ProposalJudgingCommentHelper {

    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final ProposalPhaseClient proposalPhaseClient;
    private String subject;

    public ProposalJudgingCommentHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposal = proposal;
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
        this.contestPhase = contestPhase;
    }

    public String getScreeningComment() {
        //get fellow decision
        ProposalContestPhaseAttribute fellowActionAttribute = proposalPhaseClient.
                getProposalContestPhaseAttribute(proposal.getId(),
                        contestPhase.getId(),
                        ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        FellowAction fellowAction = FellowAction.fromInt(
                fellowActionAttribute.getNumericValue().intValue());

        if (fellowAction != FellowAction.NO_DECISION &&
                fellowAction != FellowAction.PASS_TO_JUDGES) {
            String fellowRejectionText = proposalPhaseClient.
                    getProposalContestPhaseAttribute(proposal.getId(),
                            contestPhase.getId(),
                            ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT)
                    .getStringValue();

            return fellowRejectionText;
        }

        return null;
    }

    public String getSubject(){
        if(this.subject == null) {
            String proposalName = ProposalAttributeClientUtil
                    .getProposalAttribute(proposal.getId(), ProposalAttributeKeys.NAME, 0L)
                    .getStringValue();

            return "Judging Results on your Proposal " + proposalName;
        }else{
            return subject;
        }
    }
    public void setScreeningComment(String comment) {
        ProposalContestPhaseAttribute fellowActionAttribute = proposalPhaseClient.
                getProposalContestPhaseAttribute(proposal.getId(), contestPhase.getId(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
        FellowAction fellowAction = FellowAction.fromInt(fellowActionAttribute.getNumericValue().intValue());

        //save comment if the action is "incomplete" or "off-topic"
        if (fellowAction != FellowAction.NO_DECISION &&
                fellowAction != FellowAction.PASS_TO_JUDGES) {
            persistAttribute(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, comment);
        }
    }

    public String getAdvancingComment() {
            ProposalContestPhaseAttribute advanceDecisionAttribute = proposalPhaseClient.
                    getProposalContestPhaseAttribute(proposal.getId(),
                            contestPhase.getId(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION );
            AdvanceDecision advanceDecision =
                    AdvanceDecision.fromInt(
                            advanceDecisionAttribute.getNumericValue().intValue());

            if (advanceDecision != AdvanceDecision.NO_DECISION) {

                return proposalPhaseClient.
                        getProposalContestPhaseAttribute(proposal.getId(),
                                contestPhase.getId(), ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW).getStringValue();
            }

        return null;
    }

    public void setAdvancingComment(String comment) {
        persistAttribute(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, comment);
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
                    .getProposalAttribute(proposal.getId(), ProposalAttributeKeys.NAME, 0L)
                    .getStringValue();
            String contestName = ContestClientUtil.getContest(contestPhase.getContestId())
                    .getTitle();

            //get fellow decision

            ProposalContestPhaseAttribute fellowActionAttribute = proposalPhaseClient
                    .getProposalContestPhaseAttribute(proposal.getId(),
                                    contestPhase.getId(),
                                    ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
            FellowAction fellowAction = fellowActionAttribute != null
                    ? FellowAction
                            .fromInt(fellowActionAttribute.getNumericValue().intValue())
                    : FellowAction.NO_DECISION;

            //JUDGE DECISION
            if (fellowAction == FellowAction.PASS_TO_JUDGES) {
                final ProposalContestPhaseAttribute reviewTextAttribute =
                        proposalPhaseClient.
                                getProposalContestPhaseAttribute(proposal.getId(),
                                        contestPhase.getId(),
                                        ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW);
                String reviewText = reviewTextAttribute != null
                        ? reviewTextAttribute.getStringValue() : "";

                    ProposalContestPhaseAttribute advanceDecisionAttribute = proposalPhaseClient
                            .getProposalContestPhaseAttribute(proposal.getId(),
                                            contestPhase.getId(),
                                            ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
                AdvanceDecision advanceDecision =
                        advanceDecisionAttribute != null
                        ? AdvanceDecision
                                .fromInt(advanceDecisionAttribute.getNumericValue().intValue())
                        : AdvanceDecision.NO_DECISION;

                if (advanceDecision != AdvanceDecision.NO_DECISION) {
                    String templateToLoad;
                    if (advanceDecision == AdvanceDecision.MOVE_ON) {
                        if (contestPhase.isFinalistPhase()) {
                            templateToLoad = "ADVANCING_ADVANCE_TO_FINALIST";
                        } else {
                            templateToLoad = "ADVANCING_ADVANCE_TO_SEMIFINALIST";
                        }
                    } else {
                        templateToLoad = "ADVANCING_DO_NOT_ADVANCE";
                    }

                    EmailTemplateWrapper wrapper = new EmailTemplateWrapper(
                                EmailTemplateClientUtil.getContestEmailTemplateByType(templateToLoad),
                                proposalName,
                                contestName
                        );
                        subject = wrapper.getSubject();
                        return isWrapWithTemplate ? wrapper.getCompleteMessage(reviewText)
                                : reviewText;
                    }

                //FELLOW DECISION: Incomplete/Off-Topic
            } else if (fellowAction != FellowAction.NO_DECISION) {
                final ProposalContestPhaseAttribute fellowReviewTextAttribute =
                        proposalPhaseClient.getProposalContestPhaseAttribute(
                                        proposal.getId(),
                                        contestPhase.getId(),
                                        ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT
                                );
                String fellowReviewText = fellowReviewTextAttribute != null
                        ? fellowReviewTextAttribute.getStringValue() : "";

                String templateToLoad = "SCREENING_DO_NOT_ADVANCE_OFF_TOPIC";
                if (fellowAction == FellowAction.INCOMPLETE) {
                    templateToLoad = "SCREENING_DO_NOT_ADVANCE_INCOMPLETE";
                } else if (fellowAction == FellowAction.NOT_ADVANCE_OTHER) {
                    templateToLoad = "SCREENING_DO_NOT_ADVANCE_OTHER";
                }

                EmailTemplateWrapper wrapper = new EmailTemplateWrapper(
                        EmailTemplateClientUtil.getContestEmailTemplateByType(templateToLoad),
                        proposalName,
                        contestName
                );
                subject = wrapper.getSubject();
                return isWrapWithTemplate ? wrapper.getCompleteMessage(fellowReviewText)
                        : fellowReviewText;
            }

            return "";
        } catch (ContestNotFoundException e) {
            throw new IllegalStateException("Could not get promotion comment for"
                    + " proposal " + proposal.getId()
                    + " contestPhase " + contestPhase.getId());
        }
    }

    private boolean persistAttribute(String attributeName, String stringValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(attributeName);
        attribute.setStringValue(stringValue);
        proposalPhaseClient.updateProposalContestPhaseAttribute(attribute);

        return true;
    }

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(String attributeName) {
        return proposalPhaseClient.getOrCreateProposalContestPhaseAttribute(proposal.getId(),
                contestPhase.getId(), attributeName, 0L, 0L, "");
    }
}
