package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.function.Predicate;

public enum ProposalTabAccess implements ProposalTabCanAccessAlgorithm {

    ALWAYS(proposalContext -> true),
    NEVER(proposalContext -> false),
    PROPOSAL_ADMIN(proposalContext -> proposalContext.getPermissions().getCanAdminProposal()),
    ADMIN(proposalContext -> proposalContext.getPermissions().getCanAdminAll()),
    EDIT(proposalContext -> proposalContext.getPermissions().getCanEdit())
    ;

    private final Predicate<ProposalContext> predicate;

    ProposalTabAccess(Predicate<ProposalContext> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        return predicate.test(proposalContext);
    }
}
