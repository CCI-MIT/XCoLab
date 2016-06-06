package org.xcolab.portlets.proposals.utils.edit;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.emailnotification.proposal.ProposalCreationNotification;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

public final class ProposalCreationUtil {

    private final static Set<String> attributesNotToBeCopiedFromBaseProposal = new HashSet<>();

    static {
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.SECTION);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.DESCRIPTION);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.NAME);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.PITCH);
        attributesNotToBeCopiedFromBaseProposal.add(ProposalAttributeKeys.TEAM);
    }

    private ProposalCreationUtil() {
    }

    public static ProposalWrapper createProposal(long userId,
            @Valid UpdateProposalDetailsBean updateProposalSectionsBean, Contest contest, ThemeDisplay themeDisplay,
            ContestPhase contestPhase) throws PortalException,
            SystemException {
        Proposal newProposal = ProposalLocalServiceUtil.create(userId, contestPhase.getContestPhasePK());
        Proposal2Phase newProposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(
                newProposal.getProposalId(), contestPhase.getContestPhasePK());

        ProposalWrapper proposalWrapper = new ProposalWrapper(newProposal, 0, contest, contestPhase, newProposal2Phase);

        final long baseProposalId = updateProposalSectionsBean.getBaseProposalId();
        if (baseProposalId > 0) {
            ProposalAttributeLocalServiceUtil.setAttribute(
                    themeDisplay.getUserId(), proposalWrapper.getProposalId(), ProposalAttributeKeys.BASE_PROPOSAL_ID,
                    baseProposalId);
            final long baseContestId = updateProposalSectionsBean.getBaseProposalContestId();
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID,
                    baseContestId);
            ProposalMoveHistoryLocalServiceUtil.createForkHistory(baseProposalId, proposalWrapper.getProposalId(),
                    baseContestId, contest.getContestPK(), 0L, contestPhase.getContestPhasePK(), userId);

            for (ProposalAttribute attribute : ProposalAttributeLocalServiceUtil
                    .getAttributes(baseProposalId)) {
                if (attributesNotToBeCopiedFromBaseProposal.contains(attribute.getName())) {
                    continue;
                }
                ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(),
                        proposalWrapper.getProposalId(), attribute.getName(), attribute.getAdditionalId(),
                        attribute.getStringValue(), attribute.getNumericValue(), attribute.getRealValue());
            }
        }
        return proposalWrapper;
    }

    public static void sendAuthorNotification(ThemeDisplay themeDisplay,
            ProposalWrapper proposalWrapper, ContestPhase contestPhase) throws PortalException,
            SystemException {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());
        Contest contest = ContestPhaseLocalServiceUtil
                .getContest(ContestPhaseLocalServiceUtil.getContestPhase(contestPhase.getContestPhasePK()));
        final Proposal updatedProposal = ProposalLocalServiceUtil.fetchProposal(proposalWrapper.getProposalId());
        new ProposalCreationNotification(updatedProposal, contest, serviceContext).sendMessage();
    }
}
