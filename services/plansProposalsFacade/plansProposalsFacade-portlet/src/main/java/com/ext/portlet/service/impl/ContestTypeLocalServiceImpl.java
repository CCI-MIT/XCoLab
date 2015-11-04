package com.ext.portlet.service.impl;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.base.ContestTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the contest type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.ContestTypeLocalServiceUtil
 */
public class ContestTypeLocalServiceImpl extends ContestTypeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ContestTypeLocalServiceUtil} to access the contest type local service.
     */

    @Override
    public ContestType getContestTypeFromProposalId(long proposalId) throws SystemException, PortalException {
        final Contest latestProposalContest = proposalLocalService.getLatestProposalContest(proposalId);
        return fetchContestType(latestProposalContest.getContestTypeId());
    }

    @Override
    public ContestType getContestTypeFromContestId(long contestId) throws SystemException {
        return fetchContestType(contestLocalService.fetchContest(contestId).getContestTypeId());
    }

    @Override
    public ContestType getContestType(Contest contest) throws SystemException {
        return fetchContestType(contest.getContestTypeId());
    }

    @Override
    public List<ContestType> getAllContestTypes() throws SystemException {
        return getContestTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    @Override
    public List<ContestType> getActiveContestTypes() throws SystemException {
        final List<ContestType> contestTypes = getAllContestTypes();
        List<ContestType> activeContestTypes = new ArrayList<>();
        for (ContestType contestType : contestTypes) {
            if (contestLocalService.countContestsByContestType(contestType.getId()) > 0) {
                activeContestTypes.add(contestType);
            }
        }
        return activeContestTypes;
    }

    // TODO: COLAB-770 replace local methods in UserProfileWrapper and ProposalSectionsTabController
    @Override
    public Map<ContestType, List<Proposal>> groupProposalsByContestType(List<Proposal> proposals) throws SystemException, PortalException {
        Map<Long, ContestType> contestIdToContestTypeMap = new HashMap<>();
        Map<ContestType, List<Proposal>> proposalsByContestType = new HashMap<>();
        final List<ContestType> contestTypes = getActiveContestTypes();
        if (contestTypes.size()  == 1) {
            proposalsByContestType.put(contestTypes.get(0), proposals);
        } else {
            for (ContestType contestType : contestTypes) {
                final List<Contest> contests = contestLocalService.getContestsByContestType(contestType.getId());
                proposalsByContestType.put(contestType, new ArrayList<Proposal>());
                for (Contest contest : contests) {
                    contestIdToContestTypeMap.put(contest.getContestPK(), contestType);
                }
            }
            for (Proposal p : proposals) {
                final long contestPK = proposalLocalService.getLatestProposalContest(p.getProposalId()).getContestPK();
                ContestType contestType = contestIdToContestTypeMap.get(contestPK);
                proposalsByContestType.get(contestType).add(p);
            }
        }
        return proposalsByContestType;
    }
}
