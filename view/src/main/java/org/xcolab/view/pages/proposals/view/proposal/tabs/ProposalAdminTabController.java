package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalAdminTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADMIN")
    public String showProposalDetails(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember) {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanAdminProposal()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        IContestClient contestClient = proposalContext.getClients().getContestClient();
        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.ADMIN);
        model.addAttribute("availableRibbons", contestClient.getAllContestPhaseRibbonType());

        return "proposals/proposalAdmin";
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/deleteProposal")
    public void deleteProposal(HttpServletRequest request, Model model, HttpServletResponse response,
            ProposalContext proposalContext, @RequestParam boolean delete)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanDelete()) {
            ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
            ProposalWrapper proposal = proposalContext.getProposal();
            ContestWrapper contest = proposalContext.getContest();


            proposal.setVisible(!delete);
            proposalContext.getClients().getProposalClient().updateProposal(proposal);

            AlertMessage.success("Proposal was deleted successfully!").flash(request);

            response.sendRedirect(contest.getContestLinkUrl());
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
        final IContestClient contestClient = clients.getContestClient();
        ContestPhaseWrapper contestPhase = contestClient.getContestPhase(contestPhaseId);
        final ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();
        if (proposalsPermissions.getCanPromoteProposalToNextPhase(contestPhase)) {
            try {
                final IProposalClient proposalClient = clients.getProposalClient();
                final IProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();

                ContestWrapper latestProposalContest =
                        proposalClient.getLatestContestInProposal(proposalId);
                ContestPhaseWrapper currentProposalContestPhase =
                        contestClient.getContestPhase(contestPhaseId);
                ContestPhaseWrapper activePhaseForContest =
                        contestClient.getActivePhase(latestProposalContest.getId());

                proposalPhaseClient.promoteProposal(proposalId,
                        activePhaseForContest.getId(),
                        currentProposalContestPhase.getId());

                response.sendRedirect(proposal.getProposalLinkUrl(contest,
                        contestPhase.getId()));
            } catch (ContestNotFoundException ignored) {

            }
        } else {
            response.sendRedirect(proposal.getProposalLinkUrl(contest,
                    contestPhase.getId()) + "/tab/ADMIN");
        }
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/toggleProposalOpen")
    public void toggleOpen(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper currentMember, ProposalContext proposalContext, @RequestParam boolean planOpen)
            throws IOException {

        final ProposalsPermissions permissions = proposalContext.getPermissions();

        if (permissions.getCanToggleOpen()) {
            final long proposalId = proposalContext.getProposal().getId();
            final long userId = currentMember.getId();
            proposalContext.getClients().getProposalAttributeClient().setProposalAttribute(userId, proposalId,
                    ProposalAttributeKeys.OPEN, 0L, planOpen ? 1L : 0L, null);
            response.sendRedirect(proposalContext.getProposal().getProposalLinkUrl(proposalContext.getContest()));
        } else {
            AlertMessage.danger("You are not allowed to perform this action.").flash(request);
            response.sendRedirect(proposalContext.getProposal().getProposalUrl());
        }
    }
}
