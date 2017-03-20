package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

import java.util.function.Predicate;

public enum ProposalTabAccess implements ProposalTabCanAccessAlgorithm {

    ALWAYS(contextWrapper -> true),
    NEVER(contextWrapper -> false),
    PROPOSAL_ADMIN(contextWrapper -> contextWrapper.getPermissions().getCanAdminProposal()),
    ADMIN(contextWrapper -> contextWrapper.getPermissions().getCanAdminAll()),
    EDIT(contextWrapper -> contextWrapper.getPermissions().getCanEdit())
    ;

    private final Predicate<ProposalsContextWrapper> predicate;

    ProposalTabAccess(Predicate<ProposalsContextWrapper> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean canAccess(ProposalsContextWrapper contextWrapper) {
        return predicate.test(contextWrapper);
    }
}
