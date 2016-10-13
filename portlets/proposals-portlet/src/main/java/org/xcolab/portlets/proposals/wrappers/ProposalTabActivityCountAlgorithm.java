package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import javax.portlet.PortletRequest;

public interface ProposalTabActivityCountAlgorithm {
    int getActivityCount(ProposalsContext context, PortletRequest request);
    
    ProposalTabActivityCountAlgorithm alwaysZero = new ProposalTabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(ProposalsContext context, PortletRequest request) {
            return 0;
        }
    };

    ProposalTabActivityCountAlgorithm evaluationCommentsCount = new ProposalTabActivityCountAlgorithm() {

        @Override
        public int getActivityCount(ProposalsContext context, PortletRequest request) {
            return (int) context.getProposalWrapped(request).getEvaluationCommentsCount();
        }
    };

    ProposalTabActivityCountAlgorithm fellowReviewCommentsCount = new ProposalTabActivityCountAlgorithm() {

        @Override
        public int getActivityCount(ProposalsContext context, PortletRequest request) {
            return (int) context.getProposalWrapped(request).getFellowReviewCommentsCount();
        }
    };

    ProposalTabActivityCountAlgorithm commentsCount = new ProposalTabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(ProposalsContext context, PortletRequest request) {
            return (int) context.getProposalWrapped(request).getCommentsCount();
        }
    };
    
    ProposalTabActivityCountAlgorithm membersCount = new ProposalTabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(ProposalsContext context, PortletRequest request) {
            return context.getProposalWrapped(request).getMembers().size();
        }
    };
}
