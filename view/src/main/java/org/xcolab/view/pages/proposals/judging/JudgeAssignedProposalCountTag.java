package org.xcolab.view.pages.proposals.judging;

import org.xcolab.client.proposals.ProposalClientUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class JudgeAssignedProposalCountTag extends BodyTagSupport {
    
    private long userId;

    private long contestPhaseId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getContestPhaseId() {
        return contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        this.contestPhaseId = contestPhaseId;
    }

    @Override
    public int doStartTag() throws JspException {

        //TODO: get right client? Or not, because judging is probably only done in the host colab
        int judgeAssignedProposalCount = ProposalClientUtil
                .getNumberOfProposalsForJudge(userId, contestPhaseId);

        int judgeJudgedProposalCount = ProposalClientUtil
                .getNumberOfProposalsAlreadyJudgedForJudge(userId, contestPhaseId);

        pageContext.setAttribute("proposalCount", judgeAssignedProposalCount);
        pageContext.setAttribute("proposalAlreadyJudgedCount", judgeJudgedProposalCount);

        return EVAL_BODY_INCLUDE; 
    }
}
