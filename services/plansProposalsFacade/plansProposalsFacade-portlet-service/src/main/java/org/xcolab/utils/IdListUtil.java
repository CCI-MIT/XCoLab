package org.xcolab.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by johannes on 11/4/15.
 *
 * Utility class for common conversions of ids lists to strings or models.
 */
public class IdListUtil {

    private final static Log _log = LogFactoryUtil.getLog(IdListUtil.class);

    public static List<Long> getIdsFromString(String commaSeparated) {
        String[] stringIds = commaSeparated.split("\\s*,\\s*");
        List<Long> longsIds = new ArrayList<>(stringIds.length);
        for (String stringId : stringIds) {
            try {
                longsIds.add(Long.parseLong(stringId));
            } catch (NumberFormatException e) {
                _log.error(String.format("Could not parse id %s in id list %s", stringId, Arrays.asList(stringIds)), e);
            }
        }
        return longsIds;
    }

    public static String getStringFromIds(List<Long> ids) {
        return StringUtils.join(ids, ',');
    }

    public static List<Long> getIdsFromContests(List<Contest> contests) {
        List<Long> ids = new ArrayList<>(contests.size());
        for (Contest contest : contests) {
            ids.add(contest.getContestPK());
        }
        return ids;
    }

    public static List<Long> getIdsFromProposals(List<Proposal> proposals) {
        List<Long> ids = new ArrayList<>(proposals.size());
        for (Proposal proposal : proposals) {
            ids.add(proposal.getProposalId());
        }
        return ids;
    }

    public static List<Long> getIdsFromContestTypes(List<ContestType> contestTypes) {
        List<Long> ids = new ArrayList<>(contestTypes.size());
        for (ContestType contestType : contestTypes) {
            ids.add(contestType.getId());
        }
        return ids;
    }

    public static List<ContestType> getContestTypesFromIds(List<Long> contestTypeIds) {
        List<ContestType> contestTypes = new ArrayList<>(contestTypeIds.size());
        for (long id : contestTypeIds) {
            try {
                contestTypes.add(ContestTypeLocalServiceUtil.fetchContestType(id));
            } catch (SystemException e) {
                _log.error(String.format("Error converting id list to ContestType list: ContestType %d does not exist",
                        id), e);
            }
        }
        return contestTypes;
    }

    public static List<Contest> getContestsFromIds(List<Long> contestIds) {
        List<Contest> contests = new ArrayList<>(contestIds.size());
        for (long id : contestIds) {
            try {
                contests.add(ContestLocalServiceUtil.fetchContest(id));
            } catch (SystemException e) {
                _log.error(String.format("Error converting id list to Contest list: Contest %d does not exist",
                        id), e);
            }
        }
        return contests;
    }

    public static List<Proposal> getProposalsFromIds(List<Long> proposalIds) {
        List<Proposal> proposals = new ArrayList<>(proposalIds.size());
        for (long id : proposalIds) {
            try {
                proposals.add(ProposalLocalServiceUtil.fetchProposal(id));
            } catch (SystemException e) {
                _log.error(String.format("Error converting id list to Proposal list: Proposal %d does not exist",
                        id), e);
            }
        }
        return proposals;
    }
}
