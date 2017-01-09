package org.xcolab.portlets.proposals.requests;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;

public class JudgeProposalFeedbackBean extends RatingBean {

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper){
        super(wrapper, ProposalJudgeRatingClientUtil.getRatingTypesForJudges());
    }

    public JudgeProposalFeedbackBean() {
    }
}
