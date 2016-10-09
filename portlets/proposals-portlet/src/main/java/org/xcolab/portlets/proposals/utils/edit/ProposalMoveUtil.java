package org.xcolab.portlets.proposals.utils.edit;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.Proposal2PhaseClient;
import org.xcolab.client.proposals.ProposalContestPhaseAttributeClient;
import org.xcolab.client.proposals.ProposalMoveHistoryClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import javax.validation.Valid;

public final class ProposalMoveUtil {

    private ProposalMoveUtil() { }

    public static void moveProposal(@Valid UpdateProposalDetailsBean updateProposalSectionsBean,
                                    ProposalWrapper proposalWrapper, ContestPhase contestPhase, Contest targetContest, ThemeDisplay themeDisplay)
            throws SystemException, PortalException {
        try {
            final Contest fromContest = ProposalsClient.getCurrentContestForProposal(proposalWrapper.getProposalId());
            ContestPhase targetPhase = ContestClientUtil.getActivePhase(targetContest.getContestPK());

            try {//Proposal2PhaseLocalServiceUtil
                if (Proposal2PhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposalWrapper.getProposalId(),
                        targetPhase.getContestPhasePK()) != null) {
                    throw new PortalException("The proposal is already associated with the target contest.");
                }
            } catch (Proposal2PhaseNotFoundException ignored) {
                //proposal is not in the target phase -> that's what we want
            }

            switch (updateProposalSectionsBean.getMoveType()) {
                case MOVE_PERMANENTLY:
                    ProposalMoveHistoryClient.createProposalMoveHistory(proposalWrapper.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
                            themeDisplay.getUserId());
                    for (Proposal2Phase p2p : Proposal2PhaseClient
                            .getProposal2PhaseByProposalId(proposalWrapper.getProposalId())) {
                        if (ContestClientUtil.getContestPhase(p2p.getContestPhaseId()).getContestPK()
                                != updateProposalSectionsBean.getBaseProposalContestId()) {
                            continue;
                        }

                        // Set end version if it was not set already
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposalWrapper.getCurrentVersion());
                            Proposal2PhaseClient.updateProposal2Phase(p2p);
                        }

                        if (updateProposalSectionsBean.getMoveFromContestPhaseId() != null) {
                            if (!ContestClientUtil.getContestPhase(p2p.getContestPhaseId()).getPhaseStartDate()
                                    .before(
                                            ContestClientUtil
                                                    .getContestPhase(updateProposalSectionsBean.getMoveFromContestPhaseId())
                                                    .getPhaseStartDate())) {
                                // remove proposal from this contest in all phases that come after the selected one
                                Proposal2PhaseClient.deleteProposal2Phase(p2p);
                            }
                        }
                    }
                    break;
                case COPY:
                    ProposalMoveHistoryClient.createCopyProposalMoveHistory(proposalWrapper.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
                            themeDisplay.getUserId());
                    for (Proposal2Phase p2p : Proposal2PhaseClient
                            .getProposal2PhaseByProposalId(proposalWrapper.getProposalId())) {
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposalWrapper.getCurrentVersion());
                            Proposal2PhaseClient.updateProposal2Phase(p2p);
                        }
                    }
                    break;
                case FORK:
//                ProposalCreationUtil.createProposal(proposalWrapper.getAuthorId(), updateProposalSectionsBean,
//                        contest, themeDisplay, contestPhase);
//                break;
                    //TODO: verify if old implementation works
                    throw new SystemException("Not supported");
                default:
            }
            Proposal2Phase p2p = new Proposal2Phase();
            p2p.setContestPhaseId(targetPhase.getContestPhasePK());
            p2p.setProposalId(proposalWrapper.getProposalId());

            p2p.setVersionFrom( proposalWrapper.getCurrentVersion());
            p2p.setVersionTo(-1);

            Proposal2PhaseClient.createProposal2Phase(p2p);
            ProposalContestPhaseAttributeClient
                    .setProposalContestPhaseAttribute(proposalWrapper.getProposalId(), contestPhase
                                    .getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.VISIBLE,0l, 1l,"");
        }catch (ContestNotFoundException ignored){

        }
    }
}
