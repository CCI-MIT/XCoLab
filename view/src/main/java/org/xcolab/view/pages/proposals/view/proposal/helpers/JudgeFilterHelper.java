package org.xcolab.view.pages.proposals.view.proposal.helpers;

import org.ocpsoft.common.util.Assert;

import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;

public class JudgeFilterHelper {

    private final ProposalWrapper proposal;
    private final long judgeId;

    public JudgeFilterHelper(ProposalWrapper proposal, long judgeId) {
        Assert.notNull(proposal, "Proposal is required");
        this.proposal = proposal;
        this.judgeId = judgeId;
    }

    public boolean getIsUserAmongSelectedJudges() {
        return proposal.getIsUserAmongSelectedJudges(judgeId);
    }

    public boolean getIsReviewFinishedForJudge() {
        return proposal.getIsReviewFinishedForJudge(judgeId);
    }
}
