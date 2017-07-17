package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalAdminTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADMIN")
    public String showProposalDetails(HttpServletRequest request, Model model,
            ProposalContext proposalContext) {

        ContestClient contestClient = proposalContext.getClients().getContestClient();
        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.ADMIN);
        model.addAttribute("availableRibbons", contestClient.getAllContestPhaseRibbonType());
        model.addAttribute("allowOpenProposals",
            ConfigurationAttributeKey.CONTESTS_ALLOW_OPEN_PROPOSALS.get());

        return "proposals/proposalAdmin";
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/deleteProposal")
    public void deleteProposal(HttpServletRequest request, Model model, HttpServletResponse response,
            ProposalContext proposalContext, @RequestParam boolean delete)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanDelete()) {
            //TODO: Undelete doesn't work
            ContestPhase contestPhase = proposalContext.getContestPhase();
            Proposal proposal = proposalContext.getProposal();
            Contest contest = proposalContext.getContest();


            proposal.setVisible(!delete);
            proposalContext.getClients().getProposalClient().updateProposal(proposal);

            AlertMessage.success("Proposal was deleted successfully!").flash(request);
            response.sendRedirect(
                    proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK())
                            + "/tab/ADMIN");
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to delete proposal ");
        }
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/promoteProposal")
    public void promoteProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext,
            @RequestParam Long contestId,
            @RequestParam Long contestPhaseId,
            @RequestParam Long proposalId) throws IOException {

        ProposalsPermissions proposalsPermissions = proposalContext.getPermissions();
        final ClientHelper clients = proposalContext.getClients();
        final ContestClient contestClient = clients.getContestClient();
        ContestPhase contestPhase = contestClient.getContestPhase(contestPhaseId);
        final Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();
        if (proposalsPermissions.getCanPromoteProposalToNextPhase(contestPhase)) {
            try {
                final ProposalClient proposalClient = clients.getProposalClient();
                final ProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();

                Contest latestProposalContest =
                        proposalClient.getLatestContestInProposal(proposalId);
                ContestPhase currentProposalContestPhase =
                        contestClient.getContestPhase(contestPhaseId);
                ContestPhase activePhaseForContest =
                        contestClient.getActivePhase(latestProposalContest.getContestPK());

                proposalPhaseClient.promoteProposal(proposalId,
                        activePhaseForContest.getContestPhasePK(),
                        currentProposalContestPhase.getContestPhasePK());

                response.sendRedirect(proposal.getProposalLinkUrl(contest,
                        contestPhase.getContestPhasePK()));
            } catch (ContestNotFoundException ignored) {

            }
        } else {
            response.sendRedirect(proposal.getProposalLinkUrl(contest,
                    contestPhase.getContestPhasePK()) + "/tab/ADMIN");
        }
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/toggleProposalOpen")
    public void toogleOpen(HttpServletRequest request, Model model, HttpServletResponse response,
            Member currentMember, ProposalContext proposalContext, @RequestParam boolean planOpen)
            throws ProposalsAuthorizationException, IOException {

        final ProposalsPermissions permissions = proposalContext.getPermissions();

        if (permissions.getCanDelete()) {
            final long proposalId = proposalContext.getProposal().getProposalId();
            final long userId = currentMember.getUserId();
            proposalContext.getClients().getProposalAttributeClient().setProposalAttribute(userId, proposalId,
                    ProposalAttributeKeys.OPEN, 0L, planOpen ? 1L : 0L);
            response.sendRedirect(proposalContext.getProposal().getProposalLinkUrl(proposalContext.getContest()));
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to change proposal open attribute");
        }
    }
}
