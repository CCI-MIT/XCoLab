package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

public class LegacyImpactAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalsContextWrapper contextWrapper) {
        Long contestPK = contextWrapper.getContest().getContestPK();
        Long contestPKofFirst2015Contest = 1301101L;
        return contestPK <= contestPKofFirst2015Contest
                && contextWrapper.getProposalWrapped().getModelId() > 0;
    }
}
