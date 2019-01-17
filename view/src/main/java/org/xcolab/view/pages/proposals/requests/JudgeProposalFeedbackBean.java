package org.xcolab.view.pages.proposals.requests;

import org.xcolab.client.contest.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

public class JudgeProposalFeedbackBean extends RatingBean {

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper){
        super(wrapper, ProposalJudgeRatingClientUtil.getRatingTypesForJudges());
    }

    public JudgeProposalFeedbackBean() {
        super(ProposalJudgeRatingClientUtil.getRatingTypesForJudges());
    }
}
