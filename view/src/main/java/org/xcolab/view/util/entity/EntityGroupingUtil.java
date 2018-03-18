package org.xcolab.view.util.entity;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.GroupingHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a utility class to group entities by certain criteria.
 */
public final class EntityGroupingUtil {

    private EntityGroupingUtil() { }

    public static Map<ContestType, Set<Proposal>> groupByContestType(List<Proposal> proposals) {
        return new GroupingHelper<>(proposals).groupWithDuplicateValues(proposal -> ProposalClientUtil
                .getLatestContestInProposal(proposal.getProposalId()).getContestType());
    }
}
