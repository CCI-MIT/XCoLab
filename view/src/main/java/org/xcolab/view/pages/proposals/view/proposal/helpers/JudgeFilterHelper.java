package org.xcolab.view.pages.proposals.view.proposal.helpers;

import org.ocpsoft.common.util.Assert;

import org.xcolab.client.contest.pojo.Proposal;

public class JudgeFilterHelper {

    private final Proposal proposal;
    private final long judgeId;

    public JudgeFilterHelper(Proposal proposal, long judgeId) {
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
