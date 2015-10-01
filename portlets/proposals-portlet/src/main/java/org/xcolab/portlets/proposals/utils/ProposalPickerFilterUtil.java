package org.xcolab.portlets.proposals.utils;

import java.util.*;

import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.ProposalContestPhaseAttributeType;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import org.apache.commons.lang3.tuple.Pair;

import com.ext.portlet.model.Proposal;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 04/12/13
 * Time: 10:25
 */
public class ProposalPickerFilterUtil {

    private static Log _log = LogFactoryUtil.getLog(ProposalPickerFilterUtil.class);

    /**
     * Parse filter from frontend parameter and filter the contents of the proposals parameter
     * @param filterKey the filter key passed as parameter from the frontend
     * @param proposals A list of Proposals paired with their date
     */
    public static void filterByParameter(String filterKey, List<Pair<Proposal, Date>> proposals) {
        if (filterKey != null && filterKey.equalsIgnoreCase("WINNERSONLY")) {
            ProposalPickerFilter.WINNERSONLY.filter(proposals);
        } else {
            ProposalPickerFilter.ACCEPTALL.filter(proposals);
        }
    }

    public static List<Pair<ContestWrapper, Date>> getFilteredContests(
            long sectionId, ResourceRequest request, ProposalsContext proposalsContext) throws SystemException,
            PortalException {

        List<Pair<ContestWrapper, Date>> contests = new ArrayList<>();

        for (Contest c: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            contests.add(Pair.of(new ContestWrapper(c),
                    c.getCreated() == null ? new Date(0) : c.getCreated()));
        }

        PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionId);

        final long sectionFocusAreaId = planSectionDefinition.getFocusAreaId();
        final long contestFocusAreaId;
        if (request != null) {
            Contest contest = proposalsContext.getContest(request);
            contestFocusAreaId = contest.getFocusAreaId();
        } else {
            contestFocusAreaId = 0;
        }
        _log.debug(String.format("%d contests before filtering", contests.size()));
        ProposalPickerFilter.SECTION_DEF_FOCUS_AREA_FILTER.filterContests(contests,
                Pair.of(sectionFocusAreaId, contestFocusAreaId));
        _log.debug(String.format("%d contests left after filtering for focus areas %d and %d",
                contests.size(), sectionFocusAreaId, contestFocusAreaId));
        final long filterTier = planSectionDefinition.getTier();
        ProposalPickerFilter.CONTEST_TIER.filterContests(contests, filterTier);
        _log.debug(String.format("%d contests left after filtering for tier %d", contests.size(), filterTier));

        return contests;
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
            if (includedProposals.contains(entry.getLeft().getProposalId()))
                continue;
            proposals.add(entry);
        }

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredSubscribedProposalsForUser(
            long userId, String filterKey, long sectionId, PortletRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        List<ActivitySubscription> activitySubscriptions = ActivitySubscriptionLocalServiceUtil
                .findByUser(userId);
        for (ActivitySubscription as : activitySubscriptions) {
            if (as.getClassNameId() == ClassNameLocalServiceUtil
                    .getClassNameId(Proposal.class)) {
                proposals.add(Pair.of(
                        ProposalLocalServiceUtil.getProposal(as.getClassPK()),
                        as.getCreateDate()));
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

    public static void filterProposals(List<Pair<Proposal, Date>> proposals,
                                       String filterKey, long sectionId, PortletRequest request, ProposalsContext proposalsContext)
            throws SystemException, PortalException {
        filterByParameter(filterKey, proposals);
        filterByVisibility(proposals);

        PlanSectionDefinition planSectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionId);

        final long sectionFocusAreaId = planSectionDefinition.getFocusAreaId();
        final long contestFocusAreaId;
        if (request != null) {
            Contest contest = proposalsContext.getContest(request);
            contestFocusAreaId = contest.getFocusAreaId();
        } else {
            contestFocusAreaId = 0;
        }
        ProposalPickerFilter.SECTION_DEF_FOCUS_AREA_FILTER.filter(proposals,
                Pair.of(sectionFocusAreaId, contestFocusAreaId));

        ProposalPickerFilter.CONTEST_TIER.filter(proposals, planSectionDefinition.getTier());
    }

    public static void filterByVisibility(List<Pair<Proposal, Date>> proposals) {
        for (Iterator<Pair<Proposal, Date>> iterator = proposals.iterator(); iterator.hasNext(); ) {
            Proposal proposal = iterator.next().getLeft();
            if (proposalIsHiddenInAllPhases(proposal)) {
                iterator.remove();
            }
        }
    }

    private static boolean proposalIsHiddenInAllPhases(Proposal proposal) {
        try {
            final List<Long> contestPhases = Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(proposal.getProposalId());
            for (Long phasePK : contestPhases) {
                ContestPhase contestPhase = ContestPhaseLocalServiceUtil.fetchContestPhase(phasePK);
                final ProposalContestPhaseAttributeHelper attributeHelper = new ProposalContestPhaseAttributeHelper(proposal, contestPhase);
                if (attributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.VISIBLE, 0, 1) != 0) {
                    return false;
                }
            }
        } catch (PortalException | SystemException e) {
            _log.warn(String.format("Exception while determining visibility of proposal %d", proposal.getProposalId()),e);
            //default to not hidden on errors - we don't want to accidentally hide proposals
            return false;
        }
        return true;
    }
}