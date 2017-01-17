package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import javax.servlet.http.HttpServletRequest;

public interface ProposalTabActivityCountAlgorithm {
    int getActivityCount(ProposalsContext context, HttpServletRequest request);
    
    ProposalTabActivityCountAlgorithm alwaysZero = new ProposalTabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(ProposalsContext context, HttpServletRequest request) {
            return 0;
        }
    };

    ProposalTabActivityCountAlgorithm evaluationCommentsCount = new ProposalTabActivityCountAlgorithm() {

        @Override
        public int getActivityCount(ProposalsContext context, HttpServletRequest request) {
            return (int) context.getProposalWrapped(request).getEvaluationCommentsCount();
        }
    };

    ProposalTabActivityCountAlgorithm fellowReviewCommentsCount = new ProposalTabActivityCountAlgorithm() {

        @Override
        public int getActivityCount(ProposalsContext context, HttpServletRequest request) {
            return (int) context.getProposalWrapped(request).getFellowReviewCommentsCount();
        }
    };

    ProposalTabActivityCountAlgorithm commentsCount = new ProposalTabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(ProposalsContext context, HttpServletRequest request) {
            return (int) context.getProposalWrapped(request).getCommentsCount();
        }
    };
    
    ProposalTabActivityCountAlgorithm membersCount = new ProposalTabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(ProposalsContext context, HttpServletRequest request) {
            return context.getProposalWrapped(request).getMembers().size();
        }
    };
}
