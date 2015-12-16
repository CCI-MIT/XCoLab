package org.xcolab.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by johannes on 11/20/15.
 * This is a utility class to group proposals by certain other criteria.
 * It's in a separate class, because the liferay service layer does not support returning maps!
 */
public final class ProposalGroupingUtil {

    private ProposalGroupingUtil() { }

    public static Map<ContestType, List<Proposal>> groupByContestType(List<Proposal> proposals) throws SystemException, PortalException {
        Map<Long, ContestType> contestIdToContestTypeMap = new HashMap<>();
        Map<ContestType, List<Proposal>> proposalsByContestType = new HashMap<>();
        final List<ContestType> contestTypes = ContestTypeLocalServiceUtil.getActiveContestTypes();
        if (contestTypes.size()  == 1) {
            proposalsByContestType.put(contestTypes.get(0), proposals);
        } else {
            for (ContestType contestType : contestTypes) {
                final List<Contest> contests = ContestLocalServiceUtil.getContestsByContestType(contestType.getId());
                proposalsByContestType.put(contestType, new ArrayList<Proposal>());
                for (Contest contest : contests) {
                    contestIdToContestTypeMap.put(contest.getContestPK(), contestType);
                }
            }
            for (Proposal p : proposals) {
                final long contestPK = ProposalLocalServiceUtil.getLatestProposalContest(p.getProposalId()).getContestPK();
                ContestType contestType = contestIdToContestTypeMap.get(contestPK);
                proposalsByContestType.get(contestType).add(p);
            }
        }
        return proposalsByContestType;
    }
}
