package org.xcolab.view.pages.proposals.judging;


import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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
            UserWrapper judge = StaticUserContext.getUserClient().getMember(userId);
            ContestWrapper contest = StaticContestContext.getContestClient().getContest(contestId);
            IProposalClient proposalClient = StaticProposalContext.getProposalClient();
            ProposalWrapper proposal = proposalClient.getProposal(proposalId);
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
