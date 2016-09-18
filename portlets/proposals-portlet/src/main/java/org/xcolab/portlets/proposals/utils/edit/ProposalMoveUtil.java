package org.xcolab.portlets.proposals.utils.edit;

import com.ext.portlet.NoSuchProposal2PhaseException;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.validation.Valid;

public final class ProposalMoveUtil {

    private ProposalMoveUtil() { }

    public static void moveProposal(@Valid UpdateProposalDetailsBean updateProposalSectionsBean,
                                    ProposalWrapper proposalWrapper, ContestPhase contestPhase, Contest targetContest, ThemeDisplay themeDisplay)
            throws SystemException, PortalException {
        try {
            final Contest fromContest = ProposalsClient.getCurrentContestForProposal(proposalWrapper.getProposalId());
            ContestPhase targetPhase = ContestClient.getActivePhase(targetContest.getContestPK());

            try {
                if (Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalWrapper.getProposalId(),
                        targetPhase.getContestPhasePK()) != null) {
                    throw new PortalException("The proposal is already associated with the target contest.");
                }
            } catch (NoSuchProposal2PhaseException ignored) {
                //proposal is not in the target phase -> that's what we want
            }

            switch (updateProposalSectionsBean.getMoveType()) {
                case MOVE_PERMANENTLY:
                    ProposalMoveHistoryLocalServiceUtil.createMoveHistory(proposalWrapper.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
                            themeDisplay.getUserId());
                    for (Proposal2Phase p2p : ProposalsClient
                            .getProposal2PhaseByProposalId(proposalWrapper.getProposalId())) {
                        if (ContestClient.getContestPhase(p2p.getContestPhaseId()).getContestPK()
                                != updateProposalSectionsBean.getBaseProposalContestId()) {
                            continue;
                        }

                        // Set end version if it was not set already
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposalWrapper.getCurrentVersion());
                            ProposalsClient.updateProposal2Phase(p2p);
                        }

                        if (updateProposalSectionsBean.getMoveFromContestPhaseId() != null) {
                            if (!ContestClient.getContestPhase(p2p.getContestPhaseId()).getPhaseStartDate()
                                    .before(
                                            ContestPhaseLocalServiceUtil
                                                    .getContestPhase(updateProposalSectionsBean.getMoveFromContestPhaseId())
                                                    .getPhaseStartDate())) {
                                // remove proposal from this contest in all phases that come after the selected one
                                ProposalsClient.deleteProposal2Phase(p2p);
                            }
                        }
                    }
                    break;
                case COPY:
                    ProposalMoveHistoryLocalServiceUtil.createCopyHistory(proposalWrapper.getProposalId(),
                            fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
                            themeDisplay.getUserId());
                    for (Proposal2Phase p2p : ProposalsClient
                            .getProposal2PhaseByProposalId(proposalWrapper.getProposalId())) {
                        if (p2p.getVersionTo() < 0) {
                            p2p.setVersionTo(proposalWrapper.getCurrentVersion());
                            ProposalsClient.updateProposal2Phase(p2p);
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

            Proposal2PhaseLocalServiceUtil.create(proposalWrapper.getProposalId(), targetPhase.getContestPhasePK(),
                    proposalWrapper.getCurrentVersion(), -1);
            ProposalContestPhaseAttributeLocalServiceUtil
                    .setProposalContestPhaseAttribute(proposalWrapper.getProposalId(), contestPhase
                                    .getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.VISIBLE, 1);
        }catch (ContestNotFoundException ignored){

        }
    }
}
