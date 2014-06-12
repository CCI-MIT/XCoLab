package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.model.ContestPhase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

/**
 * Created by kmang on 25/05/14.
 */
public class ProposalAdvancingBean {

    private String[] advanceCommentHeaders = {"", "", ""};
    private String[] advanceCommentFooters = {"", "", ""};

    private JudgingSystemActions.AdvanceDecision advanceDecision;

    @NotBlank
    private String advanceComment;

    public ProposalAdvancingBean() {
    }

    public ProposalAdvancingBean(ProposalWrapper wrapper, ContestPhase contestPhase, ProposalsPreferencesWrapper proposalsPreferencesWrapper) throws PortalException, SystemException {
        advanceDecision = wrapper.getJudgeDecision();
        advanceComment = ProposalJudgingCommentHelper.extractComment(wrapper.getProposalReview());
        advanceCommentHeaders = new String[] {
                "",
                ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceRejectionText(contestPhase.getContestPhaseType()))),
                ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceAcceptanceText(contestPhase.getContestPhaseType())))
        };
        advanceCommentFooters = new String[] {
                "",
                ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceRejectionText(contestPhase.getContestPhaseType()))),
                ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceAcceptanceText(contestPhase.getContestPhaseType())))
        };
    }

    public int getAdvanceDecision() {
        return advanceDecision.getAttributeValue();
    }

    public void setAdvanceDecision(int judgeDecisionValue) {
        this.advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue);
    }

    public String getAdvanceComment() {
        return advanceComment;
    }

    public void setAdvanceComment(String advanceComment) {
        this.advanceComment = advanceComment;
    }

    public String[] getAdvanceCommentHeaders() {
        return advanceCommentHeaders;
    }

    public String[] getAdvanceCommentFooters() {
        return advanceCommentFooters;
    }

    private String removeLineBreaks(String html) {
        return StringUtils.replace(html, "\n", "");
    }
}
