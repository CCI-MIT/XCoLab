package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.UserProposalRatings;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;

import java.util.List;

public class ProposalJudgeWrapper extends ProposalWrapper {
    private final UserWrapper currentMember;

    public ProposalJudgeWrapper(ProposalWrapper proposal, UserWrapper currentMember) {
        super(proposal, proposal.getContestPhase());
        this.currentMember = currentMember;
        setProposalRatings(proposal.getId(), contestPhase);
    }

    public ProposalJudgeWrapper(ProposalWrapper proposal, int version, ContestWrapper contest,
            ContestPhaseWrapper contestPhase, IProposal2Phase proposal2Phase, UserWrapper currentMember) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentMember = currentMember;
        setProposalRatings(proposal.getId(), contestPhase);
    }

    private void setProposalRatings(long proposalId, ContestPhaseWrapper contestPhase) {

        if(contestPhase!=null) {
            List<ProposalRatingWrapper> list = StaticProposalContext.getProposalJudgeRatingClient()
                    .getJudgeRatingsForProposalAndUser(currentMember.getId(), proposalId,
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
        IProposalContestPhaseAttribute a = proposalContestPhaseAttributeHelper
                .getAttributeOrCreate(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt(
                a.getNumericValue().intValue());
        return fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES;
    }

    private boolean isJudgeFinishedWritingReview() {
        return !isUserAmongJudges(currentMember.getId()) || proposalRatings.isReviewComplete();
    }
}
