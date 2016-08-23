package org.xcolab.portlets.proposals.utils;


import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import org.apache.commons.lang3.tuple.Pair;
import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;

public class ProposalPickerFilterUtil {

    public static final String CONTEST_FILTER_REASON_FOCUS_AREA = "FOCUS_AREA";
    public static final String CONTEST_FILTER_REASON_TIER = "TIER";

    private static final Log _log = LogFactoryUtil.getLog(ProposalPickerFilterUtil.class);

    /**
     * Parse filter from frontend parameter and filter the contents of the proposals parameter
     * @param filterKey the filter key passed as parameter from the frontend
     * @param proposals A list of Proposals paired with their date
     */
    public static void filterByParameter(String filterKey, List<Pair<Proposal, Date>> proposals) {
        if (filterKey != null && filterKey.equalsIgnoreCase("WINNERSONLY")) {
            ProposalPickerFilter.WINNERS_ONLY.filter(proposals);
        } else {
            ProposalPickerFilter.ACCEPT_ALL.filter(proposals);
        }
    }

    public static List<Pair<ContestWrapper, Date>> getFilteredContests(
            long sectionId, ResourceRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        List<Pair<ContestWrapper, Date>> contests = ProposalPickerFilterUtil.getAllContests();
        ProposalPickerFilterUtil.filterContests(contests, sectionId, request, proposalsContext, false);
        return contests;
    }

    public static List<Pair<ContestWrapper, Date>> getAllContests() throws SystemException, PortalException {
        List<Pair<ContestWrapper, Date>> contests = new ArrayList<>();

        for (Contest c: ContestLocalServiceUtil.getContests(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            try {
                org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(c.getContestPK());
                contests.add(Pair.of(new ContestWrapper(contestMicro),  //c
                        c.getCreated() == null ? new Date(0) : c.getCreated()));
            }catch (ContestNotFoundException ignored){

            }
        }
        return contests;
    }

    public static Map<Long, String> filterContests(List<Pair<ContestWrapper, Date>> contests,
            long sectionId, ResourceRequest request, ProposalsContext proposalsContext, boolean trackRemovedContests)
            throws SystemException, PortalException {
        PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionId);
        ProposalPickerFilter.CONTEST_TYPE_FILTER.filterContests(contests, planSectionDefinition.getAllowedContestTypeIds());

        List<Long> alwaysIncludedContestIds = PlanSectionDefinitionLocalServiceUtil.getAdditionalIds(planSectionDefinition);

        final long sectionFocusAreaId = planSectionDefinition.getFocusAreaId();
        final long contestFocusAreaId;
        if (request != null) {
            Contest contest = proposalsContext.getContest(request);
            contestFocusAreaId = contest.getFocusAreaId();
        } else {
            contestFocusAreaId = 0;
        }

        _log.debug(String.format("%d contests before filtering", contests.size()));
        final SectionDefFocusAreaArgument sectionDefFocusAreaArgument =
                new SectionDefFocusAreaArgument(sectionFocusAreaId,
                        contestFocusAreaId, alwaysIncludedContestIds);
        Set<Long> focusAreaRemovedContests =
                ProposalPickerFilter.SECTION_DEF_FOCUS_AREA_FILTER.filterContests(contests,
                sectionDefFocusAreaArgument);

        _log.debug(String.format("%d contests left after filtering for focus areas %d and %d",
                contests.size(), sectionFocusAreaId, contestFocusAreaId));
        final long filterTier = planSectionDefinition.getTier();
        Set<Long> tierRemovedContests = ProposalPickerFilter.CONTEST_TIER.filterContests(contests, filterTier);
        _log.debug(String.format("%d contests left after filtering for tier %d", contests.size(), filterTier));

        Map<Long, String> removedContests = new HashMap<>();
        if (trackRemovedContests) {
            for (Long contestId : focusAreaRemovedContests) {
                removedContests.put(contestId, CONTEST_FILTER_REASON_FOCUS_AREA);
            }
            for (Long contestId : tierRemovedContests) {
                removedContests.put(contestId, CONTEST_FILTER_REASON_TIER);
            }
        }
        return removedContests;
    }

    public static List<Pair<Proposal, Date>> getFilteredSubscribedSupportingProposalsForUser(
            long userId, String filterKey, long sectionId, PortletRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        List<Pair<Proposal, Date>> proposals = getFilteredSubscribedProposalsForUser(
                userId, filterKey, sectionId, request, proposalsContext);

        Set<Long> includedProposals = new HashSet<>();
        for (Pair<Proposal, Date> entry : proposals) {
            includedProposals.add(entry.getLeft().getProposalId());
        }
        for (Pair<Proposal, Date> entry : getFilteredSupportingProposalsForUser(
                userId, filterKey, sectionId, request, proposalsContext)) {
            if (includedProposals.contains(entry.getLeft().getProposalId())) {
                continue;
            }
            proposals.add(entry);
        }

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredSubscribedProposalsForUser(
            long userId, String filterKey, long sectionId, PortletRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        List<ActivitySubscription> activitySubscriptions = ActivitiesClient.getActivitySubscriptions(null, null, userId);

        for (ActivitySubscription as : activitySubscriptions) {
            if (as.getClassNameId() == ClassNameLocalServiceUtil
                    .getClassNameId(Proposal.class)) {
                proposals.add(Pair.of(
                        ProposalLocalServiceUtil.getProposal(as.getClassPK()),
                       new Date(as.getCreateDate().getTime())
                ));
            }
        }

        filterProposals(proposals, filterKey, sectionId, request, proposalsContext);

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredSupportingProposalsForUser(
            long userId, String filterKey, long sectionId, PortletRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        for (ProposalSupporter ps : ProposalSupporterLocalServiceUtil
                .getProposals(userId)) {
            proposals.add(Pair.of(
                    ProposalLocalServiceUtil.getProposal(ps.getProposalId()),
                    ps.getCreateDate()));
        }

        filterProposals(proposals, filterKey, sectionId, request, proposalsContext);

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredAllProposals(
            String filterKey, long sectionId, Long contestPK, PortletRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        List<Proposal> proposalsRaw;
        if (contestPK > 0) {
            proposalsRaw = ProposalLocalServiceUtil
                    .getProposalsInContest(contestPK);
        } else {
            proposalsRaw = ProposalLocalServiceUtil.getProposals(0,
                    Integer.MAX_VALUE);
        }
        for (Proposal p : proposalsRaw) {
            proposals.add(Pair.of(p, new Date(0)));
        }

        filterProposals(proposals, filterKey, sectionId, request, proposalsContext);

        return proposals;
    }

    private static void filterProposals(List<Pair<Proposal, Date>> proposals,
            String filterKey, long sectionId, PortletRequest request,
            ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        filterByParameter(filterKey, proposals);
        filterByVisibility(proposals);

        PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionId);
        ProposalPickerFilter.CONTEST_TYPE_FILTER.filter(proposals, planSectionDefinition.getAllowedContestTypeIds());

        List<Long> filterExceptionContestIds = PlanSectionDefinitionLocalServiceUtil.getAdditionalIds(planSectionDefinition);

        final long sectionFocusAreaId = planSectionDefinition.getFocusAreaId();
        final long contestFocusAreaId;
        if (request != null) {
            Contest contest = proposalsContext.getContest(request);
            contestFocusAreaId = contest.getFocusAreaId();
        } else {
            contestFocusAreaId = 0;
        }
        ProposalPickerFilter.SECTION_DEF_FOCUS_AREA_FILTER.filter(proposals,
                new SectionDefFocusAreaArgument(sectionFocusAreaId, contestFocusAreaId, filterExceptionContestIds));

        ProposalPickerFilter.CONTEST_TIER.filter(proposals, planSectionDefinition.getTier());
    }

    private static void filterByVisibility(List<Pair<Proposal, Date>> proposals) throws SystemException, PortalException {
        for (Iterator<Pair<Proposal, Date>> iterator = proposals.iterator(); iterator.hasNext(); ) {
            Proposal proposal = iterator.next().getLeft();
            if (ProposalLocalServiceUtil.isDeleted(proposal)) {
                iterator.remove();
            }
        }
    }

    public static class SectionDefFocusAreaArgument {
        private final Long sectionFocusAreaId;
        private final Long contestFocusAreaId;
        private final List<Long> filterExceptionContestIds;


        public SectionDefFocusAreaArgument(Long sectionFocusAreaId, Long contestFocusAreaId, List<Long> filterExceptionContestIds) {
            this.sectionFocusAreaId = sectionFocusAreaId;
            this.contestFocusAreaId = contestFocusAreaId;
            this.filterExceptionContestIds = filterExceptionContestIds;
        }

        public Long getSectionFocusAreaId() {
            return sectionFocusAreaId;
        }

        public Long getContestFocusAreaId() {
            return contestFocusAreaId;
        }

        public List<Long> getFilterExceptionContestIds() {
            return filterExceptionContestIds;
        }
    }
}