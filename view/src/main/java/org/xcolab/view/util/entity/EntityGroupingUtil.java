package org.xcolab.view.util.entity;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.GroupingUtil;

import java.util.List;
import java.util.Map;

/**
 * This is a utility class to group entities by certain criteria.
 */
public final class EntityGroupingUtil {

    private EntityGroupingUtil() { }

    public static Map<ContestType, List<Proposal>> groupByContestType(List<Proposal> proposals) {
        return GroupingUtil.groupByWithDuplicateValues(proposals, proposal -> ProposalClientUtil
                .getLatestContestInProposal(proposal.getProposalId()).getContestType());
    }
}
