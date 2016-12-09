package org.xcolab.portlets.proposals.utils.edit;

import org.apache.commons.lang.NotImplementedException;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalMoveClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.InternalException;

import javax.portlet.PortletRequest;
import javax.validation.Valid;

public final class ProposalMoveUtil {

    private ProposalMoveUtil() {
    }

    public static void moveProposal(@Valid UpdateProposalDetailsBean updateProposalSectionsBean,
            Proposal proposal, ContestPhase contestPhase, Contest targetContest,
            long memberId, PortletRequest request) {
        try {
            final Contest fromContest = ProposalsContextUtil.getClients(request).getProposalClient().getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase targetPhase = ContestClientUtil.getActivePhase(targetContest.getContestPK());

            final ProposalPhaseClient proposalPhaseClient =
                    ProposalsContextUtil.getClients(request).getProposalPhaseClient();
            try {
                if (proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(),
                        targetPhase.getContestPhasePK()) != null) {
                    throw new InternalException("The proposal is already associated with the target contest.");
                }
            } catch (Proposal2PhaseNotFoundException ignored) {
                //proposal is not in the target phase -> that's what we want
            }

            final ProposalMoveClient proposalMoveClient =
                    ProposalsContextUtil.getClients(request).getProposalMoveClient();
            switch (updateProposalSectionsBean.getMoveType()) {
                case MOVE_PERMANENTLY:
                    proposalMoveClient.createProposalMoveHistory(proposal.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
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
                            fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
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
//                ProposalCreationUtil.createProposal(proposal.getAuthorId(), updateProposalSectionsBean,
//                        contest, themeDisplay, contestPhase);
//                break;
                    //TODO: verify if old implementation works
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
        } catch (ContestNotFoundException ignored) {

        }
    }
}
