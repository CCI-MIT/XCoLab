package org.xcolab.view.util.entity;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.commons.GroupingHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a utility class to group entities by certain criteria.
 */
public final class EntityGroupingUtil {

    private EntityGroupingUtil() { }

    public static Map<ContestType, Set<ProposalWrapper>> groupByContestType(
            List<ProposalWrapper> proposals) {
        return new GroupingHelper<>(proposals).groupWithDuplicateValues(proposal ->
                StaticProposalContext.getProposalClient()
                .getLatestContestInProposal(proposal.getId()).getContestType());
    }
}
