package org.xcolab.view.pages.proposals.utils.edit;


import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.contest.proposals.ProposalAttributeClient;
import org.xcolab.client.contest.proposals.ProposalClient;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalAttribute;
import org.xcolab.client.contest.pojo.Proposal2Phase;
import org.xcolab.entity.utils.notifications.proposal.ProposalCreationNotification;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

public final class ProposalCreationUtil {

    private static final Set<String> attributesNotToBeCopiedFromBaseProposal = new HashSet<>();

    static {
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.SECTION);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.DESCRIPTION);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.NAME);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.PITCH);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.TEAM);
    }

    private ProposalCreationUtil() {
    }

    public static Proposal createProposal(long userId,
            @Valid UpdateProposalDetailsBean updateProposalSectionsBean,
            ContestWrapper contest, ContestPhaseWrapper contestPhase) {
        final ClientHelper clientHelper = new ClientHelper();
        try {
            Proposal newProposal = clientHelper.getProposalClient()
                    .createProposal(userId, contestPhase.getId(), true);
            Proposal2Phase newProposal2Phase = clientHelper.getProposalPhaseClient().getProposal2PhaseByProposalIdContestPhaseId(
                    newProposal.getId(), contestPhase.getId());

            Proposal proposalWrapper = new Proposal(newProposal, 0, contest, contestPhase, newProposal2Phase);

            final long baseProposalId = updateProposalSectionsBean.getBaseProposalId();

            if (baseProposalId > 0) {
                final ProposalAttributeClient proposalAttributeClient =
                        clientHelper.getProposalAttributeClient();
                Integer proposalAttributeVersion = proposalAttributeClient.setProposalAttribute(
                        userId, proposalWrapper.getId(), ProposalAttributeKeys.BASE_PROPOSAL_ID,
                        0L, baseProposalId, null).getVersion();
                final long baseContestId = updateProposalSectionsBean.getBaseProposalContestId();
                proposalAttributeVersion = proposalAttributeClient
                        .setProposalAttribute(userId, proposalWrapper.getId(),
                                ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0L, baseContestId,
                                proposalAttributeVersion).getVersion();
                clientHelper.getProposalMoveClient()
                        .createForkProposalMoveHistory(baseProposalId, proposalWrapper.getId(),
                        baseContestId, contest.getId(), contestPhase.getId(), userId);

                for (ProposalAttribute attribute : proposalAttributeClient
                        .getAllProposalAttributes(baseProposalId)) {
                    if (attributesNotToBeCopiedFromBaseProposal.contains(attribute.getName())) {
                        continue;
                    }

                    proposalAttributeVersion = proposalAttributeClient
                            .setProposalAttribute(userId, proposalWrapper.getId(),
                                    attribute.getName(), attribute.getAdditionalId(),
                                    attribute.getStringValue(), attribute.getNumericValue(),
                                    attribute.getRealValue(), proposalAttributeVersion)
                            .getVersion();
                }
            }
            return proposalWrapper;
        } catch (Proposal2PhaseNotFoundException ignored) {
            return null;
        }
    }

    public static void sendAuthorNotification(ProposalContext proposalContext, String baseUrl,
            Proposal proposalWrapper,
            ContestPhaseWrapper contestPhase) {
        try {
            ContestClient contestClient = proposalContext.getClients().getContestClient();
            ContestWrapper contest = contestClient
                    .getContest(contestClient.getContestPhase(contestPhase.getId()).getContestId());

            final ProposalClient proposalClient =
                    proposalContext.getClients().getProposalClient();
            Proposal updatedProposal = proposalClient.getProposal(proposalWrapper.getId());
            ContestWrapper contestMicro = contestClient.getContest(contest.getId());
            new ProposalCreationNotification(updatedProposal, contestMicro).sendMessage();
        } catch (ContestNotFoundException | ProposalNotFoundException ignored) {

        }
    }
}
