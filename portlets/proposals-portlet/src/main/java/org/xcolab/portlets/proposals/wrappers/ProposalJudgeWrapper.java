package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * Created by patrickhiesel on 19/12/13.
 */
public class ProposalJudgeWrapper extends ProposalWrapper {
    private User currentUser;
    private ProposalRating proposalRating;


    public ProposalJudgeWrapper(ProposalWrapper proposal, User currentUser) {
        super(proposal);
        this.currentUser = currentUser;

        try {
            //find out contestPhase
            Contest baseContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(baseContest);

            this.proposalRating = ProposalRatingLocalServiceUtil.getJudgeRatingForProposal(
                    currentUser.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
        } catch (Exception e) {
            this.proposalRating = null;
        }
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase, User currentUser) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentUser = currentUser;

        try {
            this.proposalRating = ProposalRatingLocalServiceUtil.getJudgeRatingForProposal(
                    currentUser.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
        } catch (Exception e) {
            this.proposalRating = null;
        }

    }

    /**
     *
     * @throws SystemException
     * @throws PortalException
     */
    public JudgingSystemActions.JudgeReviewStatus getJudgeReviewStatus() throws SystemException, PortalException {
        if (currentUser == null || !isJudgingContestPhase()) return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;

        // If the phase does not require initial fellow screening all judges should do the review
        if (!getFellowScreeningNeccessary() && isUserAmongJudges(currentUser)) {
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

    public Long getJudgeRating() throws SystemException, PortalException {
        if (this.proposalRating != null) {
            return this.proposalRating.getRating();
        } else {
            return 0L;
        }

    }

    public String getJudgeComment() throws SystemException, PortalException {
        if (this.proposalRating != null) {
            return this.proposalRating.getComment();
        } else {
            return "";
        }

    }

    public boolean shouldShowJudgingTab(long contestPhaseId) {
        ProposalContestPhaseAttribute a = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) a.getNumericValue());
        return fellowAction == JudgingSystemActions.FellowAction.PASS_TO_JUDGES;
    }

    private boolean isJudgeFinishedWritingReview() throws SystemException, PortalException {
        if (!isUserAmongJudges(currentUser)) {
            return true;
        }
        if (this.proposalRating != null) {
            return this.proposalRating.isRatingComplete();
        } else {
            return false;
        }
    }
}
