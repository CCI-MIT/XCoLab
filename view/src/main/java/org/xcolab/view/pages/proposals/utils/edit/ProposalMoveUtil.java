package org.xcolab.view.pages.proposals.utils.edit;

import org.apache.commons.lang3.NotImplementedException;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.tables.pojos.Proposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalMoveClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public final class ProposalMoveUtil {

    private ProposalMoveUtil() {
    }

    public static void moveProposal(ProposalContext proposalContext,
            UpdateProposalDetailsBean updateProposalSectionsBean, ProposalWrapper proposal,
            ContestPhaseWrapper contestPhase, ContestWrapper targetContest, long userId) {
        try {
            final ClientHelper clients = proposalContext.getClients();
            final IProposalClient proposalClient = clients.getProposalClient();
            final IProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();
            final IProposalMoveClient proposalMoveClient = clients.getProposalMoveClient();

            final ContestWrapper fromContest = proposalClient.getCurrentContestForProposal(proposal.getId());
            ContestPhaseWrapper targetPhase = StaticContestContext.getContestClient()
                    .getActivePhase(targetContest.getId());

            try {
                if (proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposal.getId(),
                        targetPhase.getId()) != null) {
                    throw new InternalException("The proposal is already associated with the target contest.");
                }
            } catch (Proposal2PhaseNotFoundException ignored) {
                //proposal is not in the target phase -> that's what we want
            }

            switch (updateProposalSectionsBean.getMoveType()) {
                case MOVE_PERMANENTLY:
                    proposalMoveClient.createProposalMoveHistory(proposal.getId(),
                            fromContest.getId(), targetContest.getId(), targetPhase.getId(),
                            userId);
                    for (IProposal2Phase p2p : StaticProposalContext.getProposalPhaseClient()
                            .getProposal2PhaseByProposalId(proposal.getId())) {
                        if (StaticContestContext.getContestClient()
                                .getContestPhase(p2p.getContestPhaseId()).getContestId()
                                != updateProposalSectionsBean.getBaseProposalContestId()) {
                            continue;
                        }

                        // Set end version if it was not set already
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposal.getCurrentVersion());
                            proposalPhaseClient.updateProposal2Phase(p2p);
                        }

                        if (updateProposalSectionsBean.getMoveFromContestPhaseId() != null) {
                            if (!StaticContestContext.getContestClient()
                                    .getContestPhase(p2p.getContestPhaseId()).getPhaseStartDate()
                                    .before(StaticContestContext.getContestClient()
                                                    .getContestPhase(updateProposalSectionsBean.getMoveFromContestPhaseId())
                                                    .getPhaseStartDate())) {
                                // remove proposal from this contest in all phases that come after the selected one
                                proposalPhaseClient.deleteProposal2Phase(p2p);
                            }
                        }
                    }
                    break;
                case COPY:
                    proposalMoveClient.createCopyProposalMoveHistory(proposal.getId(),
                            fromContest.getId(), targetContest.getId(), targetPhase.getId(),
                            userId);
                    for (IProposal2Phase p2p : StaticProposalContext.getProposalPhaseClient()
                            .getProposal2PhaseByProposalId(proposal.getId())) {
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposal.getCurrentVersion());
                            proposalPhaseClient.updateProposal2Phase(p2p);
                        }
                    }
                    break;
                case FORK:
//                ProposalCreationUtil.createProposal(proposal.getAuthorUserId(), updateProposalSectionsBean,
//                        contest, themeDisplay, contestPhase);
//                break;
                    //TODO COLAB-2625: verify if old implementation works
                    throw new NotImplementedException("Not supported");
                default:
            }
            IProposal2Phase p2p = new Proposal2Phase();
            p2p.setContestPhaseId(targetPhase.getId());
            p2p.setProposalId(proposal.getId());

            p2p.setVersionFrom( proposal.getCurrentVersion());
            p2p.setVersionTo(-1);

            proposalPhaseClient.createProposal2Phase(p2p);
            StaticProposalContext.getProposalPhaseClient()
                    .setProposalContestPhaseAttribute(proposal.getId(), contestPhase.getId(),
                            ProposalContestPhaseAttributeKeys.VISIBLE, 0L, 1L, "");
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST_CLOSED);
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_PHASE);
        } catch (ContestNotFoundException ignored) {

        }
    }
}
