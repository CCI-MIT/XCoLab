package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public class CommentAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        return ConfigurationAttributeKey.PROPOSALS_COMMENTS_IN_SEPARATE_TAB.get();
    }
}
