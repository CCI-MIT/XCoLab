package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import javax.servlet.http.HttpServletRequest;

public interface ProposalTabActivityCountAlgorithm {
    int getActivityCount(ProposalsContext context, HttpServletRequest request);
    
    ProposalTabActivityCountAlgorithm alwaysZero = (context, request) -> 0;

    ProposalTabActivityCountAlgorithm evaluationCommentsCount =
            (context, request) -> (int) context.getProposalWrapped(request).getEvaluationCommentsCount();

    ProposalTabActivityCountAlgorithm commentsCount =
            (context, request) -> (int) context.getProposalWrapped(request).getCommentsCount();
    
    ProposalTabActivityCountAlgorithm membersCount =
            (context, request) -> context.getProposalWrapped(request).getMembers().size();
}
