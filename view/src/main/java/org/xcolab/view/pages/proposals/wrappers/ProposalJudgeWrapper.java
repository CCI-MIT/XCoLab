package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalRating;
import org.xcolab.client.contest.pojo.Proposal2Phase;
import org.xcolab.client.contest.pojo.ProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.UserProposalRatings;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;

import java.util.List;

public class ProposalJudgeWrapper extends Proposal {
    private final Member currentMember;

    public ProposalJudgeWrapper(Proposal proposal, Member currentMember) {
        super(proposal, proposal.getContestPhase());
        this.currentMember = currentMember;
        setProposalRatings(proposal.getId(), contestPhase);
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, ContestWrapper contest,
            ContestPhaseWrapper contestPhase, Proposal2Phase proposal2Phase, Member currentMember) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentMember = currentMember;
        setProposalRatings(proposal.getId(), contestPhase);
    }

    private void setProposalRatings(long proposalId, ContestPhaseWrapper contestPhase) {

        if(contestPhase!=null) {
            List<ProposalRating> list = ProposalJudgeRatingClientUtil
                    .getJudgeRatingsForProposalAndUser(
                            currentMember.getId(),
                            proposalId,
                            contestPhase.getId());
            this.proposalRatings = new UserProposalRatings(currentMember, list);
        }

    }

    public JudgingSystemActions.JudgeReviewStatus getJudgeReviewStatus() {
        if (currentMember == null || !isJudgingContestPhase()) {
            return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;
        }

        // If the phase does not require initial fellow screening all judges should do the review
        if (!getFellowScreeningNecessary() && isUserAmongJudges(currentMember.getId())) {
            if (isJudgeFinishedWritingReview()) {
                return JudgingSystemActions.JudgeReviewStatus.DONE;
            } else {
                return JudgingSystemActions.JudgeReviewStatus.NOT_DONE;
            }
        }

        for (long userId : this.getSelectedJudges()) {
            if (currentMember.getId() == userId) {
                if (isJudgeFinishedWritingReview()) {
                    return JudgingSystemActions.JudgeReviewStatus.DONE;
                } else {
                    return JudgingSystemActions.JudgeReviewStatus.NOT_DONE;
                }
            }
        }
        return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;
    }

    public boolean isPassedToJudges() {
        ProposalContestPhaseAttribute a = proposalContestPhaseAttributeHelper
                .getAttributeOrCreate(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt(
                a.getNumericValue().intValue());
        return fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES;
    }

    private boolean isJudgeFinishedWritingReview() {
        return !isUserAmongJudges(currentMember.getId()) || proposalRatings.isReviewComplete();
    }
}
