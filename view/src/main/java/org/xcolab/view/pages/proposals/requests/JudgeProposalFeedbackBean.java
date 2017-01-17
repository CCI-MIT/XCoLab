package org.xcolab.portlets.proposals.requests;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.view.pages.proposals.requests.RatingBean;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

public class JudgeProposalFeedbackBean extends RatingBean {

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper){
        super(wrapper, ProposalJudgeRatingClientUtil.getRatingTypesForJudges());
    }

    public JudgeProposalFeedbackBean() {
    }
}
