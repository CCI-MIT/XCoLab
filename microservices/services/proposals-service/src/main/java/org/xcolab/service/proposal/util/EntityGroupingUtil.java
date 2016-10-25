package org.xcolab.service.proposal.util;



import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a utility class to group entities by certain other criteria.
 * It's in a separate class, because the liferay service layer does not support returning maps!
 */

public final class EntityGroupingUtil {

    private EntityGroupingUtil() { }

    public static Map<ContestType, List<Proposal>> groupByContestType(List<Proposal> proposals) {
        Map<Long, ContestType> contestIdToContestTypeMap = new HashMap<>();
        Map<ContestType, List<Proposal>> proposalsByContestType = new HashMap<>();
        final List<ContestType> contestTypes = ContestClientUtil.getActiveContestTypes();
        if (contestTypes.size()  == 1) {
            proposalsByContestType.put(contestTypes.get(0), proposals);
        } else {
            for (ContestType contestType : contestTypes) {
                final List<Contest> contests = ContestClientUtil.getContestsByContestTypeId(contestType.getId_());
                proposalsByContestType.put(contestType, new ArrayList<Proposal>());
                for (Contest contest : contests) {
                    contestIdToContestTypeMap.put(contest.getContestPK(), contestType);
                }
            }
            for (Proposal p : proposals) {
                try {
                    final long contestPK = ProposalClientUtil.getLatestContestInProposal(p.getProposalId()).getContestPK();
                    ContestType contestType = contestIdToContestTypeMap.get(contestPK);
                    proposalsByContestType.get(contestType).add(p);
                }catch (ContestNotFoundException ignored){

                }
            }
        }
        return proposalsByContestType;
    }

    public static <SearchKey, MapKey, MapVal> Map<MapKey, MapVal> getInnerMapOrCreate(
            SearchKey searchKey, Map<SearchKey, Map<MapKey, MapVal>> searchMap) {
        Map<MapKey, MapVal> innerMap = searchMap.get(searchKey);
        if (innerMap == null) {
            innerMap = new HashMap<>();
            searchMap.put(searchKey, innerMap);
        }
        return innerMap;
    }

    public static <SearchKey, ListVal> List<ListVal> getInnerListOrCreate(
            SearchKey searchKey, Map<SearchKey, List<ListVal>> searchMap) {
        List<ListVal> innerList = searchMap.get(searchKey);
        if (innerList == null) {
            innerList = new ArrayList<>();
            searchMap.put(searchKey, innerList);
        }
        return innerList;
    }
}
