package org.xcolab.view.pages.proposals.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ProposalPickerFilterUtil {

    public static final String CONTEST_FILTER_REASON_FOCUS_AREA = "FOCUS_AREA";
    public static final String CONTEST_FILTER_REASON_TIER = "TIER";

    private static final Logger _log = LoggerFactory.getLogger(ProposalPickerFilterUtil.class);

    /**
     * Parse filter from frontend parameter and filter the contents of the proposals parameter
     *
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

    public static List<Pair<Contest, Date>> getFilteredContests(
            long sectionId, HttpServletRequest request, ProposalsContext proposalsContext) {
        List<Pair<Contest, Date>> contests = ProposalPickerFilterUtil.getAllContests();
        ProposalPickerFilterUtil
                .filterContests(contests, sectionId, request, proposalsContext, false);
        return contests;
    }

    public static List<Pair<Contest, Date>> getAllContests() {
        List<Pair<Contest, Date>> contests = new ArrayList<>();

        for (Contest contest : ContestClientUtil.getAllContests()) {
            contests.add(Pair.of(contest,
                    contest.getCreated() == null ? new Date(0) : contest.getCreated()));

        }
        return contests;
    }

    public static Map<Long, String> filterContests(List<Pair<Contest, Date>> contests,
            long sectionId, HttpServletRequest request, ProposalsContext proposalsContext,
            boolean trackRemovedContests) {
        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);
        ProposalPickerFilter.CONTEST_TYPE_FILTER
                .filterContests(contests, planSectionDefinition.getAllowedContestTypeIds());

        List<Long> alwaysIncludedContestIds = planSectionDefinition.getAdditionalIdsAsList();

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
        Set<Long> tierRemovedContests =
                ProposalPickerFilter.CONTEST_TIER.filterContests(contests, filterTier);
        _log.debug(String.format("%d contests left after filtering for tier %d", contests.size(),
                filterTier));

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

    public static List<Pair<Contest, Date>> getTextFilteredContests(long sectionId,
            String contestName) {

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);

        List<OntologyTerm> ontologyTerms = OntologyClientUtil.getOntologyTermsForFocusArea(
                OntologyClientUtil.getFocusArea(planSectionDefinition.getFocusAreaId()));
        List<Long> ontologyTermIds = new ArrayList<>();
        for (OntologyTerm term : ontologyTerms) {
            ontologyTermIds.add(term.getId_());
        }

        List<Pair<Contest, Date>> contests = new ArrayList<>();
        for (Contest contest : ContestClientUtil.findContestsByName(contestName, ontologyTermIds,
                IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds()))) {
            contests.add(Pair.of((contest),
                    contest.getCreated() == null ? new Date(0) : contest.getCreated()));

        }
        return contests;
    }

    public static List<Pair<Proposal, Date>> getFilteredSubscribedSupportingProposalsForUser(
            long userId, String filterKey, long sectionId, HttpServletRequest request) {
        List<Pair<Proposal, Date>> proposals = getFilteredSubscribedProposalsForUser(
                userId, filterKey, sectionId, request);

        Set<Long> includedProposals = new HashSet<>();
        for (Pair<Proposal, Date> entry : proposals) {
            includedProposals.add(entry.getLeft().getProposalId());
        }
        for (Pair<Proposal, Date> entry : getFilteredSupportingProposalsForUser(
                userId, filterKey, sectionId, request)) {
            if (includedProposals.contains(entry.getLeft().getProposalId())) {
                continue;
            }
            proposals.add(entry);
        }

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredSubscribedProposalsForUser(
            long memberId, String filterKey, long sectionId, HttpServletRequest request) {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        List<ActivitySubscription> activitySubscriptions =
                ActivitiesClientUtil.getActivitySubscriptions(null, null, memberId);

        final ClientHelper clients = ProposalsContextUtil.getClients(request);
        final ProposalClient proposalClient = clients.getProposalClient();

        for (ActivitySubscription subscription : activitySubscriptions) {

            try {
                final long proposalSubscriptionType
                        = ActivityEntryType.PROPOSAL.getPrimaryTypeId();
                if (subscription.getClassNameId() == proposalSubscriptionType) {
                    final Proposal proposal = proposalClient.getProposal(subscription.getClassPK());
                    if (proposal.isVisible()) {
                        proposals.add(Pair.of(
                                proposal,
                                new Date(subscription.getCreateDate().getTime())
                        ));
                    }
                }
            } catch (ProposalNotFoundException ignored) {

            }
        }

        filterProposals(proposals, filterKey, sectionId, request);

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredSupportingProposalsForUser(
            long userId, String filterKey, long sectionId, HttpServletRequest request) {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        final ClientHelper clients = ProposalsContextUtil.getClients(request);
        final ProposalClient proposalClient = clients.getProposalClient();
        for (ProposalSupporter ps : ProposalMemberRatingClientUtil
                .getProposalSupportersByUserId(userId)) {
            try {
                proposals.add(Pair.of(proposalClient.getProposal(ps.getProposalId()),
                        new Date(ps.getCreateDate().getTime())));
            } catch (ProposalNotFoundException ignored) {

            }
        }

        filterProposals(proposals, filterKey, sectionId, request);

        return proposals;
    }

    public static List<Pair<Proposal, Date>> getFilteredAllProposals(String filterText,
            String filterKey, long sectionId, Long contestPK) {
        List<Pair<Proposal, Date>> proposals = new ArrayList<>();
        List<Proposal> proposalsRaw;

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);
        List<Long> contestTypes = new ArrayList<>();
        contestTypes.addAll(IdListUtil
                .getIdsFromString(planSectionDefinition.getAllowedContestTypeIds()));
        if (contestTypes.isEmpty()) {
            long defaultTypeId = 0;
            contestTypes.add(defaultTypeId);
        }
        if (contestPK > 0) {
            proposalsRaw = ProposalClientUtil
                    .getProposalsInContest(contestPK);
        } else {
            //proposalsRaw = ProposalsClient.getAllProposals();

            proposalsRaw = ProposalClientUtil.getProposalsByCurrentContests(contestTypes,
                    getAllowedTiers(planSectionDefinition.getTier()),
                    filterText.isEmpty() ? null : filterText);
        }
        for (Proposal p : proposalsRaw) {
            proposals.add(Pair.of(p, new Date(0)));
        }

        // pushed down to Microservices
        //filterProposals(proposals, filterKey, sectionId, request, proposalsContext);

        return proposals;
    }

    //TODO: redundant to ProposalPickerFilter
    public static List<Long> getAllowedTiers(Long filterTier) {

        // if filterTier < 0:
        //  allow tier <= (-filterTier)
        // else if filterTier > 0
        //  only allow tier == filterTier
        List<Long> allowedTiers = new ArrayList<>();
        final long positiveFilterTier = Math.abs(filterTier);
        allowedTiers.add(positiveFilterTier);
        if (filterTier < 0) {
            for (Long currentTier = positiveFilterTier - 1; currentTier >= 0; currentTier--) {
                allowedTiers.add(currentTier);
            }
        }
        return allowedTiers;
    }

    private static void filterProposals(List<Pair<Proposal, Date>> proposals,
            String filterKey, long sectionId, HttpServletRequest request) {
        filterByParameter(filterKey, proposals);

        filterByVisibility(proposals);

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);

        ProposalPickerFilter.CONTEST_TYPE_FILTER
                .filter(proposals, planSectionDefinition.getAllowedContestTypeIds());

        List<Long> filterExceptionContestIds = planSectionDefinition.getAdditionalIdsAsList();

        final long sectionFocusAreaId = planSectionDefinition.getFocusAreaId();
        final long contestFocusAreaId;
        if (request != null) {
            Contest contest = ProposalsContextUtil.getContest(request);
            contestFocusAreaId = contest.getFocusAreaId();
        } else {
            contestFocusAreaId = 0;
        }

        ProposalPickerFilter.SECTION_DEF_FOCUS_AREA_FILTER.filter(proposals,
                new SectionDefFocusAreaArgument(sectionFocusAreaId, contestFocusAreaId,
                        filterExceptionContestIds));

        ProposalPickerFilter.CONTEST_TIER.filter(proposals, planSectionDefinition.getTier());
    }


    private static void filterByVisibility(List<Pair<Proposal, Date>> proposals) {
        for (Iterator<Pair<Proposal, Date>> iterator = proposals.iterator(); iterator.hasNext(); ) {

            Proposal proposal = iterator.next().getLeft();
            if (proposal.isDeleted()) {
                iterator.remove();
            }
        }
    }

    public static class SectionDefFocusAreaArgument {

        private final Long sectionFocusAreaId;
        private final Long contestFocusAreaId;
        private final List<Long> filterExceptionContestIds;


        public SectionDefFocusAreaArgument(Long sectionFocusAreaId, Long contestFocusAreaId,
                List<Long> filterExceptionContestIds) {
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
