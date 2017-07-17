package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public class LegacyImpactAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        Long contestPK = proposalContext.getContest().getContestPK();
        Long contestPKofFirst2015Contest = 1301101L;
        return contestPK <= contestPKofFirst2015Contest
                && proposalContext.getProposal().getModelId() > 0;
    }
}
