package org.xcolab.view.util.entity;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a utility class to group entities by certain other criteria. It's in a separate class,
 * because the liferay service layer does not support returning maps!
 */

public final class EntityGroupingUtil {

    private EntityGroupingUtil() { }

    public static Map<ContestType, List<Proposal>> groupByContestType(List<Proposal> proposals) {

        Map<ContestType, List<Proposal>> proposalsByContestType = new HashMap<>();
        final List<ContestType> contestTypes = ContestTypeClient.getActiveContestTypes();
        if (contestTypes.size() == 1) {
            proposalsByContestType.put(contestTypes.get(0), proposals);
        } else {
            Map<Long, ContestType> contestIdToContestTypeMap = new HashMap<>();
            for (ContestType contestType : contestTypes) {
                final List<Contest> contests =
                        ContestClientUtil.getContestsByContestType(contestType.getId());
                proposalsByContestType.put(contestType, new ArrayList<>());
                for (Contest contest : contests) {
                    contestIdToContestTypeMap.put(contest.getContestPK(), contestType);
                }
            }
            for (Proposal p : proposals) {
                final long contestPK =
                        ProposalClientUtil.getLatestContestInProposal(p.getProposalId())
                                .getContestPK();
                ContestType contestType = contestIdToContestTypeMap.get(contestPK);
                proposalsByContestType.get(contestType).add(p);
            }
        }
        return proposalsByContestType;
    }
}
