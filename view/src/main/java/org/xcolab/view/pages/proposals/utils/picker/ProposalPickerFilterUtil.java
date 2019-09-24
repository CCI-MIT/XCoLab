package org.xcolab.view.pages.proposals.utils.picker;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
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

    public static List<ContestWrapper> getTextFilteredContests(long sectionId, String contestName) {
        ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition =
                StaticContestContext.getProposalTemplateClient()
                        .getProposalTemplateSectionDefinition(sectionId);

        final Long focusAreaId = proposalTemplateSectionDefinition.getFocusAreaId();
        List<Long> ontologyTermIds = null;
        if (focusAreaId != null) {
            ontologyTermIds = StaticContestContext.getOntologyClient()
                    .getOntologyTermsForFocusArea(StaticContestContext.getOntologyClient()
                            .getFocusArea(focusAreaId))
                    .stream().map(OntologyTermWrapper::getId).collect(Collectors.toList());
        }

        List<Long> allowedTiers = getAllowedTiers(proposalTemplateSectionDefinition.getTier());

        final List<Long> allowedContestTypeIds =
                IdListUtil.getIdsFromString(proposalTemplateSectionDefinition.getAllowedContestTypeIds());
        return StaticContestContext.getContestClient()
                .findPublicContests(contestName, ontologyTermIds, allowedContestTypeIds,
                        allowedTiers);
    }

    public static List<ProposalWrapper> getFilteredSubscribedSupportingProposalsForUser(
            ProposalContext proposalContext, long userId, String filterKey, long sectionId) {
        List<ProposalWrapper> proposals =
                getFilteredSubscribedProposalsForUser(proposalContext, userId, filterKey,
                        sectionId);

        Set<Long> includedProposals = new HashSet<>();
        for (ProposalWrapper proposal : proposals) {
            includedProposals.add(proposal.getId());
        }
        for (ProposalWrapper proposal : getFilteredSupportingProposalsForUser(proposalContext, userId,
                filterKey, sectionId)) {
            if (includedProposals.contains(proposal.getId())) {
                continue;
            }
            proposals.add(proposal);
        }

        return proposals;
    }

    public static List<ProposalWrapper> getFilteredSubscribedProposalsForUser(
            ProposalContext proposalContext, long userId, String filterKey, long sectionId) {
        List<ProposalWrapper> proposals = new ArrayList<>();
        List<IActivitySubscription> activitySubscriptions = StaticActivityContext
                .getActivityClient().getActivitySubscriptions(null, null, userId);

        final ClientHelper clients = proposalContext.getClients();
        final IProposalClient proposalClient = clients.getProposalClient();

        for (IActivitySubscription subscription : activitySubscriptions) {

            try {
                if (subscription.getActivityCategoryEnum() == ActivityCategory.PROPOSAL) {
                    final ProposalWrapper proposal = proposalClient.getProposal(subscription.getCategoryId());
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

    public static List<ProposalWrapper> getFilteredSupportingProposalsForUser(
            ProposalContext proposalContext, long userId, String filterKey, long sectionId) {
        List<ProposalWrapper> proposals = new ArrayList<>();
        final ClientHelper clients = proposalContext.getClients();
        final IProposalClient proposalClient = clients.getProposalClient();
        for (IProposalSupporter ps : StaticProposalContext.getProposalMemberRatingClient()
                .getProposalSupportersByUserId(userId)) {
            try {
                proposals.add(proposalClient.getProposal(ps.getProposalId()));
            } catch (ProposalNotFoundException ignored) {
            }
        }

        filterProposals(proposalContext, proposals, filterKey, sectionId);

        return proposals;
    }

    public static List<ProposalWrapper> getFilteredAllProposals(ProposalContext proposalContext,
            String filterText, String filterKey, long sectionId, Long contestId) {

        ClientHelper clients = proposalContext.getClients();
        IProposalClient proposalClient = clients.getProposalClient();

        ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition =
                StaticContestContext.getProposalTemplateClient()
                        .getProposalTemplateSectionDefinition(sectionId);
        List<Long> contestTypes = new ArrayList<>(
                IdListUtil.getIdsFromString(proposalTemplateSectionDefinition.getAllowedContestTypeIds()));

        if (contestTypes.isEmpty()) {
            long defaultTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            contestTypes.add(defaultTypeId);
        }

        List<ProposalWrapper> proposals;
        if (contestId > 0) {
            proposals = proposalClient.getProposalsInContest(contestId);
        } else {
            final List<Long> allowedTiers = getAllowedTiers(proposalTemplateSectionDefinition.getTier());
            proposals = proposalClient.getProposalsInPublicContests(contestTypes, allowedTiers,
                    filterText.isEmpty() ? null : filterText);
        }

        final ArrayList<ProposalWrapper> filteredProposals = new ArrayList<>(proposals);
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

    private static void filterProposals(ProposalContext proposalContext, List<ProposalWrapper> proposals,
            String filterKey, long sectionId) {
        if (filterKey != null && "WINNERSONLY".equalsIgnoreCase(filterKey)) {
            proposals.removeIf(proposal -> !proposal.hasRibbon());
        }

        ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition =
                StaticContestContext.getProposalTemplateClient()
                        .getProposalTemplateSectionDefinition(sectionId);

        List<Long> filterExceptionContestIds = proposalTemplateSectionDefinition.getAdditionalIdsAsList();

        final Long sectionFocusAreaId = proposalTemplateSectionDefinition.getFocusAreaId();
        ContestWrapper contest = proposalContext.getContest();
        final Long contestFocusAreaId = contest.getFocusAreaId();

        sectionFocusAreaFilter.filterProposals(proposals, sectionFocusAreaId, contestFocusAreaId,
                filterExceptionContestIds);
    }
}
