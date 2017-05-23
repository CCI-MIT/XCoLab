package org.xcolab.view.pages.proposals.judging;


import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

import javax.servlet.jsp.JspException;
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
                RestService proposalsService = new RefreshingRestService(CoLabService.CONTEST,
                        ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
                );
                proposalClient = ProposalClient.fromService(proposalsService);
            } else {
                proposalClient = ProposalClientUtil.getClient();
            }
            Proposal proposal = proposalClient.getProposal(proposalId);
            //ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
            ProposalJudgeWrapper judgeWrapper = new ProposalJudgeWrapper(proposal, judge);

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
