package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.List;

public class AdaptationImpactAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    public static AdaptationImpactAccessAlgorithm view() {
        return new AdaptationImpactAccessAlgorithm();
    }
    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        return getCanView(proposalContext);
    }

    private boolean getCanView(ProposalContext proposalContext) {
        if (ConfigurationAttributeKey.IMPACT_TAB_IS_ACTIVE.get()) {
            final Contest contest = proposalContext.getContest();

            // this feature was only introduced in the 2017 contest cycle
            if (contest.getContestYear() < 2017) {
                return false;
            }

            if (contest.getContestTier() != ContestTier.NONE.getTierType()
                    && contest.getContestTier() != ContestTier.REGION_SECTOR.getTierType()) {
                // adapted from ImpactAccessAlgorithm
                // this was done to hide the impact tab for adaptation proposals, we revert the condition
                // TODO: this should explicitly look for adaptation
                long focusAreaId = contest.getFocusAreaId();
                if (isDescendantOfExcludedOntologyTerm(focusAreaId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDescendantOfExcludedOntologyTerm(long focusAreaId) {

        final List<Long> excludedOntologyTermIds = ConfigurationAttributeKey
                .IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS.get();
        for (Long excludedOntologyTermId : excludedOntologyTermIds) {
            if (OntologyClientUtil
                    .isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
                            focusAreaId, excludedOntologyTermId)) {
                return true;
            }
        }
        return false;
    }
}
