package org.xcolab.jsp.tags.judging;

import org.xcolab.client.proposals.ProposalClientUtil;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
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

        PortletRequest portletRequest = (PortletRequest) pageContext.getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
        if (portletRequest == null) {
            throw new JspException("Can't find portlet request");
        }
        pageContext.setAttribute("proposalCount", judgeAssignedProposalCount);

        return EVAL_BODY_INCLUDE; 
    }
}
