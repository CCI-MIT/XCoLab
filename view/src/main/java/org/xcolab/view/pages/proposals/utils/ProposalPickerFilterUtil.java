package org.xcolab.view.pages.proposals.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ProposalPickerFilterUtil {

    private static final Logger _log = LoggerFactory.getLogger(ProposalPickerFilterUtil.class);

    /**
     * Parse filter from frontend parameter and filter the contents of the proposals parameter
     *
     * @param filterKey the filter key passed as parameter from the frontend
     * @param proposals A list of Proposals paired with their date
     */
    private static void filterByParameter(String filterKey, List<Proposal> proposals) {
        if (filterKey != null && filterKey.equalsIgnoreCase("WINNERSONLY")) {
            ProposalPickerFilter.WINNERS_ONLY.filter(proposals);
        } else {
            ProposalPickerFilter.ACCEPT_ALL.filter(proposals);
        }
    }

    public static List<Contest> getFilteredContests(
            long sectionId, HttpServletRequest request, ProposalsContext proposalsContext) {
        List<Contest> contests = ProposalPickerFilterUtil.getAllContests();
        ProposalPickerFilterUtil
                .filterContests(contests, sectionId, request, proposalsContext);
        return contests;
    }

    private static List<Contest> getAllContests() {
        return new ArrayList<>(ContestClientUtil.getAllContests());
    }

    public static void filterContests(List<Contest> contests, long sectionId,
        HttpServletRequest request, ProposalsContext proposalsContext) {
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

        ProposalPickerFilter.SECTION_DEF_FOCUS_AREA_FILTER.filterContests(contests,
                sectionDefFocusAreaArgument);
        _log.debug(String.format("%d contests left after filtering for focus areas %d and %d",
                contests.size(), sectionFocusAreaId, contestFocusAreaId));

        final long filterTier = planSectionDefinition.getTier();
        ProposalPickerFilter.CONTEST_TIER.filterContests(contests, filterTier);
        _log.debug(String.format("%d contests left after filtering for tier %d", contests.size(),
                filterTier));
    }

    public static List<Contest> getTextFilteredContests(long sectionId,
            String contestName) {

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);

        List<OntologyTerm> ontologyTerms = OntologyClientUtil.getOntologyTermsForFocusArea(
                OntologyClientUtil.getFocusArea(planSectionDefinition.getFocusAreaId()));
        List<Long> ontologyTermIds = new ArrayList<>();
        for (OntologyTerm term : ontologyTerms) {
            ontologyTermIds.add(term.getId_());
        }

        final List<Long> allowedContestTypeIds =
            IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds());
        return new ArrayList<>(ContestClientUtil.findContestsByName(contestName, ontologyTermIds,
            allowedContestTypeIds));
    }

    public static List<Proposal> getFilteredSubscribedSupportingProposalsForUser(
            long userId, String filterKey, long sectionId, HttpServletRequest request) {
        List<Proposal> proposals = getFilteredSubscribedProposalsForUser(
                userId, filterKey, sectionId, request);

        Set<Long> includedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            includedProposals.add(proposal.getProposalId());
        }
        for (Proposal proposal : getFilteredSupportingProposalsForUser(
                userId, filterKey, sectionId, request)) {
            if (includedProposals.contains(proposal.getProposalId())) {
                continue;
            }
            proposals.add(proposal);
        }

        return proposals;
    }

    public static List<Proposal> getFilteredSubscribedProposalsForUser(
            long memberId, String filterKey, long sectionId, HttpServletRequest request) {
        List<Proposal> proposals = new ArrayList<>();
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
                        proposals.add(proposal);
                    }
                }
            } catch (ProposalNotFoundException ignored) {

            }
        }

        filterProposals(proposals, filterKey, sectionId, request);

        return proposals;
    }

    public static List<Proposal> getFilteredSupportingProposalsForUser(
            long userId, String filterKey, long sectionId, HttpServletRequest request) {
        List<Proposal> proposals = new ArrayList<>();
        final ClientHelper clients = ProposalsContextUtil.getClients(request);
        final ProposalClient proposalClient = clients.getProposalClient();
        for (ProposalSupporter ps : ProposalMemberRatingClientUtil
                .getProposalSupportersByUserId(userId)) {
            try {
                proposals.add(proposalClient.getProposal(ps.getProposalId()));
            } catch (ProposalNotFoundException ignored) {

            }
        }

        filterProposals(proposals, filterKey, sectionId, request);

        return proposals;
    }

    public static List<Proposal> getFilteredAllProposals(String filterText,
            long sectionId, Long contestPK) {

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);
        List<Long> contestTypes = new ArrayList<>();
        final List<Long> allowedContestTypeIds =
            IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds());
        contestTypes.addAll(
            allowedContestTypeIds);

        if (contestTypes.isEmpty()) {
            long defaultTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            contestTypes.add(defaultTypeId);
        }

        List<Proposal> proposals;
        if (contestPK > 0) {
            proposals = ProposalClientUtil.getProposalsInContest(contestPK);
        } else {
            proposals = ProposalClientUtil.getProposalsByCurrentContests(contestTypes,
                    getAllowedTiers(planSectionDefinition.getTier()),
                    filterText.isEmpty() ? null : filterText);
        }
        return new ArrayList<>(proposals);
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

    private static void filterProposals(List<Proposal> proposals,
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


    private static void filterByVisibility(List<Proposal> proposals) {
        proposals.removeIf(Proposal::isDeleted);
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
