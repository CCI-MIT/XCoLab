package org.xcolab.portlets.proposals.utils.edit;

import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal2Phase;
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
        final Contest fromContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposalWrapper.getProposalId());
        ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(targetContest);

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
                for (Proposal2Phase p2p : Proposal2PhaseLocalServiceUtil
                        .getByProposalId(proposalWrapper.getProposalId())) {
                    if (ContestPhaseLocalServiceUtil.getContestPhase(p2p.getContestPhaseId()).getContestPK()
                            != updateProposalSectionsBean.getBaseProposalContestId()) {
                        continue;
                    }

                    // Set end version if it was not set already
                    if (p2p.getVersionTo() < 0) {
                        p2p.setVersionTo(proposalWrapper.getCurrentVersion());
                        Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
                    }

                    if (updateProposalSectionsBean.getMoveFromContestPhaseId() != null) {
                        if (!ContestPhaseLocalServiceUtil.getContestPhase(p2p.getContestPhaseId()).getPhaseStartDate()
                                .before(
                                        ContestPhaseLocalServiceUtil
                                                .getContestPhase(updateProposalSectionsBean.getMoveFromContestPhaseId())
                                                .getPhaseStartDate())) {
                            // remove proposal from this contest in all phases that come after the selected one
                            Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(p2p);
                        }
                    }
                }
                break;
            case COPY:
                ProposalMoveHistoryLocalServiceUtil.createCopyHistory(proposalWrapper.getProposalId(),
                        fromContest.getContestPK(), targetContest.getContestPK(), 0L, targetPhase.getContestPhasePK(),
                        themeDisplay.getUserId());
                for (Proposal2Phase p2p : Proposal2PhaseLocalServiceUtil
                        .getByProposalId(proposalWrapper.getProposalId())) {
                    if (p2p.getVersionTo() < 0) {
                        p2p.setVersionTo(proposalWrapper.getCurrentVersion());
                        Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
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
    }
}
