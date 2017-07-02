package org.xcolab.view.pages.proposals.utils.picker;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ProposalPickerFilterUtil {

    private static final SectionFocusAreaFilter sectionFocusAreaFilter =
        new SectionFocusAreaFilter();

    public static List<Contest> getTextFilteredContests(long sectionId, String contestName) {

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);

        List<OntologyTerm> ontologyTerms = OntologyClientUtil.getOntologyTermsForFocusArea(
                OntologyClientUtil.getFocusArea(planSectionDefinition.getFocusAreaId()));
        List<Long> ontologyTermIds = new ArrayList<>();
        for (OntologyTerm term : ontologyTerms) {
            ontologyTermIds.add(term.getId_());
        }

        List<Long> allowedTiers = getAllowedTiers(planSectionDefinition.getTier());

        final List<Long> allowedContestTypeIds =
            IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds());
        return ContestClientUtil.findPublicContests(contestName, ontologyTermIds,
            allowedContestTypeIds, allowedTiers);
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

    public static List<Proposal> getFilteredAllProposals(String filterText, String filterKey,
            long sectionId, Long contestPK, HttpServletRequest request) {

        ClientHelper clients = ProposalsContextUtil.getClients(request);
        ProposalClient proposalClient = clients.getProposalClient();

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);
        List<Long> contestTypes = new ArrayList<>(
            IdListUtil.getIdsFromString(planSectionDefinition.getAllowedContestTypeIds()));

        if (contestTypes.isEmpty()) {
            long defaultTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            contestTypes.add(defaultTypeId);
        }

        List<Proposal> proposals;
        if (contestPK > 0) {
            proposals = proposalClient.getProposalsInContest(contestPK);
        } else {
            final List<Long> allowedTiers = getAllowedTiers(planSectionDefinition.getTier());
            proposals = proposalClient.getProposalsByCurrentContests(contestTypes,
                allowedTiers, filterText.isEmpty() ? null : filterText);
        }

        final ArrayList<Proposal> filteredProposals = new ArrayList<>(proposals);
        filterProposals(filteredProposals, filterKey, sectionId, request);
        return filteredProposals;
    }

    private static List<Long> getAllowedTiers(Long filterTier) {
        //TODO: make this processes clearer by introducing a boolean field to allow lower tiers
        // if filterTier < 0:
        //  allow tier <= (-filterTier)
        // else if filterTier > 0
        //  only allow tier == filterTier
        List<Long> allowedTiers = new ArrayList<>();
        final long positiveFilterTier = Math.abs(filterTier);
        if (positiveFilterTier > 0) {
            allowedTiers.add(positiveFilterTier);
            if (filterTier < 0) {
                for (Long currentTier = positiveFilterTier - 1; currentTier >= 0; currentTier--) {
                    allowedTiers.add(currentTier);
                }
            }
        }
        if (!allowedTiers.isEmpty()) {
            return allowedTiers;
        }
        return null;
    }

    private static void filterProposals(List<Proposal> proposals,
            String filterKey, long sectionId, HttpServletRequest request) {
        if (filterKey != null && "WINNERSONLY".equalsIgnoreCase(filterKey)) {
            proposals.removeIf(proposal -> !proposal.hasRibbon());
        }

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionId);

        List<Long> filterExceptionContestIds = planSectionDefinition.getAdditionalIdsAsList();

        final long sectionFocusAreaId = planSectionDefinition.getFocusAreaId();
        Contest contest = ProposalsContextUtil.getContest(request);
        final long contestFocusAreaId = contest.getFocusAreaId();

        sectionFocusAreaFilter.filterProposals(proposals, sectionFocusAreaId,
            contestFocusAreaId, filterExceptionContestIds);
    }
}
