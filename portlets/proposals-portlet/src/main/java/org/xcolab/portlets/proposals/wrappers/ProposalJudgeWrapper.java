package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalRatingClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.client.proposals.pojo.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.ProposalRating;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.List;

public class ProposalJudgeWrapper extends ProposalWrapper {
    private final Member currentUser;

    public ProposalJudgeWrapper(ProposalWrapper proposal, Member currentUser) {
        super(proposal);
        this.currentUser = currentUser;
        setProposalRatings(proposal.getProposalId(), contestPhase);
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, Contest contest,
                                ContestPhase contestPhase, Proposal2Phase proposal2Phase, Member currentUser) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentUser = currentUser;
        setProposalRatings(proposal.getProposalId(), contestPhase);
    }

    private void setProposalRatings(long proposalId, ContestPhase contestPhase) {

            List<ProposalRating> list = ProposalRatingClient
                    .getJudgeRatingsForProposalAndUser(
                            currentUser.getUserId(),
                            proposalId,
                            contestPhase.getContestPhasePK());
            this.proposalRatings = new ProposalRatingsWrapper(currentUser, list);

    }

    public JudgingSystemActions.JudgeReviewStatus getJudgeReviewStatus() {
        if (currentUser == null || !isJudgingContestPhase()) {
            return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;
        }

        // If the phase does not require initial fellow screening all judges should do the review
        if (!getFellowScreeningNecessary() && isUserAmongJudges(currentUser)) {
            if (isJudgeFinishedWritingReview()) {
                return JudgingSystemActions.JudgeReviewStatus.DONE;
            } else {
                return JudgingSystemActions.JudgeReviewStatus.NOT_DONE;
            }
        }

        for (long userId : this.getSelectedJudges()) {
            if (currentUser.getUserId() == userId) {
                if (isJudgeFinishedWritingReview()) {
                    return JudgingSystemActions.JudgeReviewStatus.DONE;
                } else {
                    return JudgingSystemActions.JudgeReviewStatus.NOT_DONE;
                }
            }
        }
        return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;
    }

    public boolean shouldShowJudgingTab() {
        ProposalContestPhaseAttribute a = proposalContestPhaseAttributeHelper.getAttributeOrCreate(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) a.getNumericValue().intValue());
        return fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES;
    }

    private boolean isJudgeFinishedWritingReview() {
        return !isUserAmongJudges(currentUser) || proposalRatings.isReviewComplete();
    }
}
