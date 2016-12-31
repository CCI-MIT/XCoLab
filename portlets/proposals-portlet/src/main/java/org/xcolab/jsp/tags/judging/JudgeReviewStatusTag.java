package org.xcolab.jsp.tags.judging;


import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class JudgeReviewStatusTag extends BodyTagSupport {

    private long userId;

    private long proposalId;

    private long contestPhaseId;

    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    private long contestId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public long getContestPhaseId() {
        return contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        this.contestPhaseId = contestPhaseId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            Member judge = MembersClient.getMember(userId);
            Contest contest = ContestClientUtil.getContest(contestId);
            ProposalClient proposalClient;

            if (contest.getIsSharedContestInForeignColab()) {
                RestService proposalsService = new RefreshingRestService(CoLabService.PROPOSAL,
                        ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                        ConfigurationAttributeKey.PARTNER_COLAB_PORT);
                proposalClient = ProposalClient.fromService(proposalsService);
            } else {
                proposalClient = ProposalClientUtil.getClient();
            }
            Proposal proposal = proposalClient.getProposal(proposalId);
            //ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
            ProposalJudgeWrapper judgeWrapper = new ProposalJudgeWrapper(proposal, judge);

            PortletRequest portletRequest = (PortletRequest) pageContext
                    .getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
            if (portletRequest == null) {
                throw new JspException("Can't find portlet request");
            }
            pageContext.setAttribute("judgeReviewStatus", judgeWrapper.getJudgeReviewStatus());
        } catch (ProposalNotFoundException e) {
            throw new IllegalArgumentException("Could not load proposal " + proposalId
                    + " and contest phase " + contestPhaseId);

        } catch (MemberNotFoundException e) {
            throw new IllegalArgumentException("User does not exist: " + id);
        }
        return EVAL_BODY_INCLUDE;
    }

}
