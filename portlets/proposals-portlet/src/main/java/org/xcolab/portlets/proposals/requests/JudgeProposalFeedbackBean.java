package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;

import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;

public class JudgeProposalFeedbackBean extends RatingBean {

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper){
        super(wrapper, ProposalRatingTypeLocalServiceUtil.getRatingTypesForJudges());
    }

    public JudgeProposalFeedbackBean() {
    }

}
