package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public class EvaluationAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        return ConfigurationAttributeKey.PUBLISH_JUDGING_RESULTS.get();
    }
}
