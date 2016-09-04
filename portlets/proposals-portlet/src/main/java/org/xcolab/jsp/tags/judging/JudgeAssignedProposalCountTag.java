package org.xcolab.jsp.tags.judging;




import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

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

            int judgeAssignedProposalCount = ProposalsClient.getNumberOfProposalsForJudge(userId, contestPhaseId);

            PortletRequest portletRequest = (PortletRequest) pageContext.getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
            if (portletRequest == null) {
                throw new JspException("Can't find portlet request");
            }
            pageContext.setAttribute("proposalCount", judgeAssignedProposalCount);

        return EVAL_BODY_INCLUDE; 
    }
}
