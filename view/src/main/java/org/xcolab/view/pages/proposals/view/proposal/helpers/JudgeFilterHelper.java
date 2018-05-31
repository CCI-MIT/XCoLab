package org.xcolab.view.pages.proposals.view.proposal.helpers;

import org.xcolab.client.proposals.pojo.Proposal;

public class JudgeFilterHelper {

    private final Proposal proposal;
    private final long judgeId;

    public JudgeFilterHelper(Proposal proposal, long judgeId) {
        this.proposal = proposal;
        this.judgeId = judgeId;
    }

    public boolean getIsUserAmongSelectedJudges() {
        return proposal.isUserAmongSelectedJudges(judgeId);
    }

    public boolean getIsReviewFinishedForJudge() {
        return proposal.getIsReviewFinishedForJudge(judgeId);
    }
}
