package org.xcolab.view.pages.proposals.utils.edit;

import org.apache.commons.lang3.NotImplementedException;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalMoveClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public final class ProposalMoveUtil {

    private ProposalMoveUtil() {
    }

    public static void moveProposal(ProposalContext proposalContext,
            UpdateProposalDetailsBean updateProposalSectionsBean, Proposal proposal,
            ContestPhase contestPhase, Contest targetContest, long memberId) {
        try {
            final ClientHelper clients = proposalContext.getClients();
            final ProposalClient proposalClient = clients.getProposalClient();
            final ProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();
            final ProposalMoveClient proposalMoveClient = clients.getProposalMoveClient();

            final Contest fromContest = proposalClient.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase targetPhase = ContestClientUtil.getActivePhase(targetContest.getContestPK());

            try {
                if (proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(),
                        targetPhase.getContestPhasePK()) != null) {
                    throw new InternalException("The proposal is already associated with the target contest.");
                }
            } catch (Proposal2PhaseNotFoundException ignored) {
                //proposal is not in the target phase -> that's what we want
            }

            switch (updateProposalSectionsBean.getMoveType()) {
                case MOVE_PERMANENTLY:
                    proposalMoveClient.createProposalMoveHistory(proposal.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), targetPhase.getContestPhasePK(),
                            memberId);
                    for (Proposal2Phase p2p : ProposalPhaseClientUtil
                            .getProposal2PhaseByProposalId(proposal.getProposalId())) {
                        if (ContestClientUtil.getContestPhase(p2p.getContestPhaseId()).getContestPK()
                                != updateProposalSectionsBean.getBaseProposalContestId()) {
                            continue;
                        }

                        // Set end version if it was not set already
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposal.getCurrentVersion());
                            proposalPhaseClient.updateProposal2Phase(p2p);
                        }

                        if (updateProposalSectionsBean.getMoveFromContestPhaseId() != null) {
                            if (!ContestClientUtil.getContestPhase(p2p.getContestPhaseId()).getPhaseStartDate()
                                    .before(
                                            ContestClientUtil
                                                    .getContestPhase(updateProposalSectionsBean.getMoveFromContestPhaseId())
                                                    .getPhaseStartDate())) {
                                // remove proposal from this contest in all phases that come after the selected one
                                proposalPhaseClient.deleteProposal2Phase(p2p);
                            }
                        }
                    }
                    break;
                case COPY:
                    proposalMoveClient.createCopyProposalMoveHistory(proposal.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), targetPhase.getContestPhasePK(),
                            memberId);
                    for (Proposal2Phase p2p : ProposalPhaseClientUtil
                            .getProposal2PhaseByProposalId(proposal.getProposalId())) {
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposal.getCurrentVersion());
                            proposalPhaseClient.updateProposal2Phase(p2p);
                        }
                    }
                    break;
                case FORK:
//                ProposalCreationUtil.createProposal(proposal.getauthorUserid(), updateProposalSectionsBean,
//                        contest, themeDisplay, contestPhase);
//                break;
                    //TODO COLAB-2625: verify if old implementation works
                    throw new NotImplementedException("Not supported");
                default:
            }
            Proposal2Phase p2p = new Proposal2Phase();
            p2p.setContestPhaseId(targetPhase.getContestPhasePK());
            p2p.setProposalId(proposal.getProposalId());

            p2p.setVersionFrom( proposal.getCurrentVersion());
            p2p.setVersionTo(-1);

            proposalPhaseClient.createProposal2Phase(p2p);
            ProposalPhaseClientUtil
                    .setProposalContestPhaseAttribute(proposal.getProposalId(), contestPhase
                                    .getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.VISIBLE, 0L, 1L, "");
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST_CLOSED);
            ServiceRequestUtils.clearCache(CacheName.PROPOSAL_PHASE);
        } catch (ContestNotFoundException ignored) {

        }
    }
}
