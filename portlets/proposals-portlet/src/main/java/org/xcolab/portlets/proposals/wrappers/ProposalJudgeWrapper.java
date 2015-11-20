package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * Created by patrickhiesel on 19/12/13.
 */
public class ProposalJudgeWrapper extends ProposalWrapper {
    private User currentUser;

    public ProposalJudgeWrapper(ProposalWrapper proposal, User currentUser) throws NoSuchContestException {
        super(proposal);
        this.currentUser = currentUser;

        try {
            //find out contestPhase
            Contest baseContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(baseContest);

            this.setProposalRatings(proposal.getProposalId(), contestPhase);
        } catch (Exception e) {
            this.proposalRatings = null;
        }
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase, User currentUser) throws NoSuchContestException {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentUser = currentUser;

        try {
            this.setProposalRatings(proposal.getProposalId(), contestPhase);
        } catch (Exception e) {
            this.proposalRatings = null;
        }
    }

    private void setProposalRatings(long proposalId, ContestPhase contestPhase) throws SystemException, PortalException {
        List<ProposalRating> list = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposalAndUser(
                currentUser.getUserId(),
                proposalId,
                contestPhase.getContestPhasePK());
        this.proposalRatings = new ProposalRatingsWrapper(currentUser, list);
    }

    /**
     *
     * @throws SystemException
     * @throws PortalException
     */
    public JudgingSystemActions.JudgeReviewStatus getJudgeReviewStatus() throws SystemException, PortalException {
        if (currentUser == null || !isJudgingContestPhase()) return JudgingSystemActions.JudgeReviewStatus.NOT_RESPONSIBLE;

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

    private boolean isJudgeFinishedWritingReview() throws SystemException, PortalException {
        return !isUserAmongJudges(currentUser) || proposalRatings.isReviewComplete();
    }
}
