package com.ext.portlet.service.impl;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.base.ContestTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public String getLabelName(ContestType contestType) {
        return String.format("%d - %s with %s",
                contestType.getId(), contestType.getContestName(), contestType.getProposalNamePlural());
    }

    @Override
    public String getProposalNames(List<Long> contestTypeIds, boolean isSingular, String conjunction) {
        return getJoinedNameString(contestTypeIds, true, isSingular, conjunction);
    }

    @Override
    public String getContestNames(List<Long> contestTypeIds, boolean isSingular, String conjunction) {
        return getJoinedNameString(contestTypeIds, false, isSingular, conjunction);
    }

    private String getJoinedNameString(List<Long> contestTypeIds, boolean isProposal, boolean isSingular, String conjuction) {
        String proposalsString;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Long> iterator = contestTypeIds.iterator();
            int currentWord = 1, totalWords = contestTypeIds.size();
            while (iterator.hasNext()) {
                ContestType contestType = ContestTypeLocalServiceUtil.fetchContestType(iterator.next());
                if (currentWord > 1) {
                    if (currentWord == totalWords) {
                        stringBuilder.append(String.format(" %s ", conjuction));
                    } else {
                        stringBuilder.append(", ");
                    }
                }
                if (isProposal) {
                    stringBuilder.append(isSingular ? contestType.getProposalName() : contestType.getProposalNamePlural());
                } else {
                    stringBuilder.append(isSingular ? contestType.getContestName() : contestType.getContestNamePlural());
                }

                currentWord++;
            }
            proposalsString = stringBuilder.toString();
        } catch (SystemException e) {
            proposalsString = "Proposals";
        }
        return proposalsString;
    }
}
