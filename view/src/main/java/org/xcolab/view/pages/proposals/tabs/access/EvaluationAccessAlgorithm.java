package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

public class EvaluationAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalsContextWrapper contextWrapper) {
        return ConfigurationAttributeKey.PUBLISH_JUDGING_RESULTS.get();
    }
}
