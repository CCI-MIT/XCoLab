package org.xcolab.view.pages.proposals.utils.edit;


import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
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

    public static Proposal createProposal(long memberId,
            @Valid UpdateProposalDetailsBean updateProposalSectionsBean,
            Contest contest, ContestPhase contestPhase) {
        final ClientHelper clientHelper = new ClientHelper(contest);
        try {
            Proposal newProposal = clientHelper.getProposalClient()
                    .createProposal(memberId, contestPhase.getContestPhasePK(), true);
            Proposal2Phase newProposal2Phase = clientHelper.getProposalPhaseClient().getProposal2PhaseByProposalIdContestPhaseId(
                    newProposal.getProposalId(), contestPhase.getContestPhasePK());

            Proposal proposalWrapper = new Proposal(newProposal, 0, contest, contestPhase, newProposal2Phase);

            final long baseProposalId = updateProposalSectionsBean.getBaseProposalId();

            if (baseProposalId > 0) {
                final ProposalAttributeClient proposalAttributeClient =
                        clientHelper.getProposalAttributeClient();
                Integer proposalAttributeVersion = proposalAttributeClient.setProposalAttribute(
                        memberId, proposalWrapper.getProposalId(), ProposalAttributeKeys.BASE_PROPOSAL_ID,
                        0L, baseProposalId, null).getVersion();
                final long baseContestId = updateProposalSectionsBean.getBaseProposalContestId();
                proposalAttributeVersion = proposalAttributeClient
                        .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                                ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0L, baseContestId,
                                proposalAttributeVersion).getVersion();
                clientHelper.getProposalMoveClient()
                        .createForkProposalMoveHistory(baseProposalId, proposalWrapper.getProposalId(),
                        baseContestId, contest.getContestPK(), contestPhase.getContestPhasePK(), memberId);

                for (ProposalAttribute attribute : proposalAttributeClient
                        .getAllProposalAttributes(baseProposalId)) {
                    if (attributesNotToBeCopiedFromBaseProposal.contains(attribute.getName())) {
                        continue;
                    }

                    proposalAttributeVersion = proposalAttributeClient
                            .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
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
            ContestPhase contestPhase) {
        try {
            ContestClient contestClient = proposalContext.getClients().getContestClient();
            Contest contest = contestClient
                    .getContest(contestClient.getContestPhase(contestPhase.getContestPhasePK()).getContestPK());

            final ProposalClient proposalClient =
                    proposalContext.getClients().getProposalClient();
            Proposal updatedProposal = proposalClient.getProposal(proposalWrapper.getProposalId());
            Contest contestMicro = contestClient.getContest(contest.getContestPK());
            new ProposalCreationNotification(updatedProposal, contestMicro).sendMessage();
        } catch (ContestNotFoundException | ProposalNotFoundException ignored) {

        }
    }
}
