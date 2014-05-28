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

    private boolean isJudgeFinishedWritingReview() throws SystemException, PortalException {
        if (!isUserAmongJudges(currentUser)) {
            return true;
        }

        long judgeRating = getContestPhaseAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_RATING, currentUser.getUserId(), LONG_DEFAULT_VAL);
        String judgeComment = getContestPhaseAttributeStringValue(ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, currentUser.getUserId(), STRING_DEFAULT_VAL);
        if (Validator.isNotNull(judgeRating) && Validator.isNotNull(judgeComment)) {
            return true;
        } else {
            return false;
        }
    }
}
