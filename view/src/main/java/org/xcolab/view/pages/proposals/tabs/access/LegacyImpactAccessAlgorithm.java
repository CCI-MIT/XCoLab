package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public class LegacyImpactAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        Long contestId = proposalContext.getContest().getId();
        Long contestIdofFirst2015Contest = 1301101L;
        return contestId <= contestIdofFirst2015Contest
                && proposalContext.getProposal().getModelId() > 0;
    }
}
