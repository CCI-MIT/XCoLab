package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
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

    private String advanceComment;

    public ProposalAdvancingBean() {
    }

    public ProposalAdvancingBean(ProposalWrapper wrapper, ProposalsPreferencesWrapper proposalsPreferencesWrapper) throws PortalException, SystemException {
        advanceDecision = wrapper.getJudgeDecision();
        advanceComment = ProposalJudgingCommentHelper.extractComment(wrapper.getProposalReview());
        advanceCommentHeaders = new String[] {
                "",
                ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceRejectionText())),
                ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceAcceptanceText()))
        };
        advanceCommentFooters = new String[] {
                "",
                ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceRejectionText())),
                ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(proposalsPreferencesWrapper.getAdvanceAcceptanceText()))
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
