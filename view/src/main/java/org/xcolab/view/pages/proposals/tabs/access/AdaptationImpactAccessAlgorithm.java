package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.List;

public class AdaptationImpactAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    // this feature was only introduced in the 2017 contest cycle
    private static final int INTRODUCTION_YEAR = 2017;

    public static AdaptationImpactAccessAlgorithm view() {
        return new AdaptationImpactAccessAlgorithm();
    }

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        final ContestWrapper contest = proposalContext.getContest();
        return canAccess(contest);
    }

    public boolean canAccess(ContestWrapper contest) {
        return getCanView(contest);
    }

    private boolean getCanView(ContestWrapper contest) {
        if (!ConfigurationAttributeKey.IMPACT_TAB_IS_ACTIVE.get()) {
            return false;
        }

        if (contest.getContestYear() < INTRODUCTION_YEAR) {
            return false;
        }

        //TODO COLAB-2425: evaluate if this condition still makes sense
        if (contest.getContestTier() == ContestTier.NONE.getTierType()
                || contest.getContestTier() == ContestTier.REGION_SECTOR.getTierType()) {
            return false;
        }

        // adapted from ImpactAccessAlgorithm
        // this was done to hide the impact tab for adaptation proposals, we revert the condition
        // TODO COLAB-2425: this should explicitly look for adaptation
        long focusAreaId = contest.getFocusAreaId();
        return isDescendantOfExcludedOntologyTerm(focusAreaId);
    }

    private boolean isDescendantOfExcludedOntologyTerm(long focusAreaId) {

        final List<Long> excludedOntologyTermIds = ConfigurationAttributeKey
                .IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS.get();
        for (Long excludedOntologyTermId : excludedOntologyTermIds) {
            if (StaticContestContext.getOntologyClient()
                    .isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(
                            focusAreaId, excludedOntologyTermId)) {
                return true;
            }
        }
        return false;
    }
}
