package org.xcolab.view.pages.proposals.utils.picker;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProposalPickerFilterUtil {

    private static final SectionFocusAreaFilter sectionFocusAreaFilter =
            new SectionFocusAreaFilter();

    public static List<Contest> getTextFilteredContests(long sectionId, String contestName) {

        ProposalTemplateSectionDefinition proposalTemplateSectionDefinition =
                ProposalTemplateClientUtil.getProposalTemplateSectionDefinition(sectionId);

        final Long focusAreaId = proposalTemplateSectionDefinition.getFocusAreaId();
        List<Long> ontologyTermIds = null;
        if (focusAreaId != null) {
            ontologyTermIds = OntologyClientUtil
                    .getOntologyTermsForFocusArea(OntologyClientUtil.getFocusArea(focusAreaId))
                    .stream().map(OntologyTerm::getId).collect(Collectors.toList());
        }

        List<Long> allowedTiers = getAllowedTiers(proposalTemplateSectionDefinition.getTier());

        final List<Long> allowedContestTypeIds =
                IdListUtil.getIdsFromString(proposalTemplateSectionDefinition.getAllowedContestTypeIds());
        return ContestClientUtil
                .findPublicContests(contestName, ontologyTermIds, allowedContestTypeIds,
                        allowedTiers);
    }

    public static List<Proposal> getFilteredSubscribedSupportingProposalsForUser(
            ProposalContext proposalContext, long userId, String filterKey, long sectionId) {
        List<Proposal> proposals =
                getFilteredSubscribedProposalsForUser(proposalContext, userId, filterKey,
                        sectionId);

        Set<Long> includedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            includedProposals.add(proposal.getId());
        }
        for (Proposal proposal : getFilteredSupportingProposalsForUser(proposalContext, userId,
                filterKey, sectionId)) {
            if (includedProposals.contains(proposal.getId())) {
                continue;
            }
            proposals.add(proposal);
        }

        return proposals;
    }

    public static List<Proposal> getFilteredSubscribedProposalsForUser(
            ProposalContext proposalContext, long userId, String filterKey, long sectionId) {
        List<Proposal> proposals = new ArrayList<>();
        List<ActivitySubscription> activitySubscriptions =
                ActivitiesClientUtil.getActivitySubscriptions(null, null, userId);

        final ClientHelper clients = proposalContext.getClients();
        final ProposalClient proposalClient = clients.getProposalClient();

        for (ActivitySubscription subscription : activitySubscriptions) {

            try {
                if (subscription.getActivityCategoryEnum() == ActivityCategory.PROPOSAL) {
                    final Proposal proposal = proposalClient.getProposal(subscription.getCategoryId());
                    if (proposal.isVisible()) {
                        proposals.add(proposal);
                    }
                }
            } catch (ProposalNotFoundException ignored) {

            }
        }

        filterProposals(proposalContext, proposals, filterKey, sectionId);

        return proposals;
    }

    public static List<Proposal> getFilteredSupportingProposalsForUser(
            ProposalContext proposalContext, long userId, String filterKey, long sectionId) {
        List<Proposal> proposals = new ArrayList<>();
        final ClientHelper clients = proposalContext.getClients();
        final ProposalClient proposalClient = clients.getProposalClient();
        for (ProposalSupporter ps : ProposalMemberRatingClientUtil
                .getProposalSupportersByUserId(userId)) {
            try {
                proposals.add(proposalClient.getProposal(ps.getProposalId()));
            } catch (ProposalNotFoundException ignored) {

            }
        }

        filterProposals(proposalContext, proposals, filterKey, sectionId);

        return proposals;
    }

    public static List<Proposal> getFilteredAllProposals(ProposalContext proposalContext,
            String filterText, String filterKey, long sectionId, Long contestId) {

        ClientHelper clients = proposalContext.getClients();
        ProposalClient proposalClient = clients.getProposalClient();

        ProposalTemplateSectionDefinition proposalTemplateSectionDefinition =
                ProposalTemplateClientUtil.getProposalTemplateSectionDefinition(sectionId);
        List<Long> contestTypes = new ArrayList<>(
                IdListUtil.getIdsFromString(proposalTemplateSectionDefinition.getAllowedContestTypeIds()));

        if (contestTypes.isEmpty()) {
            long defaultTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            contestTypes.add(defaultTypeId);
        }

        List<Proposal> proposals;
        if (contestId > 0) {
            proposals = proposalClient.getProposalsInContest(contestId);
        } else {
            final List<Long> allowedTiers = getAllowedTiers(proposalTemplateSectionDefinition.getTier());
            proposals = proposalClient.getProposalsInPublicContests(contestTypes, allowedTiers,
                    filterText.isEmpty() ? null : filterText);
        }

        final ArrayList<Proposal> filteredProposals = new ArrayList<>(proposals);
        filterProposals(proposalContext, filteredProposals, filterKey, sectionId);
        return filteredProposals;
    }

    private static List<Long> getAllowedTiers(Long filterTier) {
        //TODO COLAB-641: make this processes clearer by introducing a boolean field to allow lower tiers
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

    private static void filterProposals(ProposalContext proposalContext, List<Proposal> proposals,
            String filterKey, long sectionId) {
        if (filterKey != null && "WINNERSONLY".equalsIgnoreCase(filterKey)) {
            proposals.removeIf(proposal -> !proposal.hasRibbon());
        }

        ProposalTemplateSectionDefinition proposalTemplateSectionDefinition =
                ProposalTemplateClientUtil.getProposalTemplateSectionDefinition(sectionId);

        List<Long> filterExceptionContestIds = proposalTemplateSectionDefinition.getAdditionalIdsAsList();

        final Long sectionFocusAreaId = proposalTemplateSectionDefinition.getFocusAreaId();
        Contest contest = proposalContext.getContest();
        final Long contestFocusAreaId = contest.getFocusAreaId();

        sectionFocusAreaFilter.filterProposals(proposals, sectionFocusAreaId, contestFocusAreaId,
                filterExceptionContestIds);
    }
}
