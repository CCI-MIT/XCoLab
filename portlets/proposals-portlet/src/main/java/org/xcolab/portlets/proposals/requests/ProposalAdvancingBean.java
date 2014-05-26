package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

/**
 * Created by kmang on 25/05/14.
 */
public class ProposalAdvancingBean {
    // TODO: change strings here
    private static final String[] advanceCommentHeaders = {
            "", // no decision
            "<THIS IS A DUMMY STRING>Your proposal will not make it to the next phase. Comment: ", // don't move on
            "<THIS IS A DUMMY STRING>Congratulations, your proposal gets into the next phase. Additional comment: " // move on
    };

    private JudgingSystemActions.AdvanceDecision advanceDecision;

    private String advanceComment;

    public ProposalAdvancingBean() {
    }

    public ProposalAdvancingBean(ProposalWrapper wrapper) throws PortalException, SystemException {
        advanceDecision = wrapper.getJudgeDecision();
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
}
