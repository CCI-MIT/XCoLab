package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.DatabaseAccessException;

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
        try {
            List<ProposalRating> list = ProposalRatingLocalServiceUtil
                    .getJudgeRatingsForProposalAndUser(
                            currentUser.getUserId(),
                            proposalId,
                            contestPhase.getContestPhasePK());
            this.proposalRatings = new ProposalRatingsWrapper(currentUser, list);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
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
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) a.getNumericValue());
        return fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES;
    }

    private boolean isJudgeFinishedWritingReview() {
        return !isUserAmongJudges(currentUser) || proposalRatings.isReviewComplete();
    }
}
