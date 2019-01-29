package org.xcolab.view.pages.proposals.requests;

import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

public class JudgeProposalFeedbackBean extends RatingBean {

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper){
        super(wrapper, StaticProposalContext.getProposalJudgeRatingClient()
                .getRatingTypesForJudges());
    }

    public JudgeProposalFeedbackBean() {
        super(StaticProposalContext.getProposalJudgeRatingClient().getRatingTypesForJudges());
    }
}
