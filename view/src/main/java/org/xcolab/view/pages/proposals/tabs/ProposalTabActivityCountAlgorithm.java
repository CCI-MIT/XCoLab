package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import javax.servlet.http.HttpServletRequest;

public interface ProposalTabActivityCountAlgorithm {
    int getActivityCount(ProposalContext proposalContext, HttpServletRequest request);
    
    ProposalTabActivityCountAlgorithm alwaysZero = (proposalContext, request) -> 0;

    ProposalTabActivityCountAlgorithm evaluationCommentsCount =
            (proposalContext, request) -> (int) proposalContext.getProposal().getEvaluationCommentsCount();

    ProposalTabActivityCountAlgorithm commentsCount =
            (proposalContext, request) -> (int) proposalContext.getProposal().getCommentsCount();
    
    ProposalTabActivityCountAlgorithm membersCount =
            (proposalContext, request) -> proposalContext.getProposal().getMembers().size();
}
