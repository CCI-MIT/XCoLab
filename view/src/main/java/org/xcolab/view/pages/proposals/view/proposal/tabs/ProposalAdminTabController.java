package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalAdminTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADMIN")
    public String showProposalDetails(Model model, HttpServletRequest request) {

        ContestClient contestClient = proposalsContext.getClients(request).getContestClient();
        setCommonModelAndPageAttributes(request, model, ProposalTab.ADMIN);
        model.addAttribute("availableRibbons", contestClient.getAllContestPhaseRibbonType());

        return "proposals/proposalAdmin";
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/deleteProposal")
    public void deleteProposal(HttpServletRequest request, Model model, HttpServletResponse response,
            @RequestParam boolean delete)
            throws ProposalsAuthorizationException, IOException {

        if (proposalsContext.getPermissions(request).getCanDelete()) {
            //TODO: Undelete doesn't work
            ContestPhase contestPhase = proposalsContext.getContestPhase(request);
            Proposal proposal = proposalsContext.getProposal(request);
            Contest contest = proposalsContext.getContest(request);


            proposal.setVisible(!delete);
            ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);

            AlertMessage.success("Proposal was deleted successfully!").flash(request);
            response.sendRedirect(
                    proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK())
                            + "/tab/ADMIN");
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to delete proposal ");
        }
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/promoteProposal")
    public void promoteProposal(HttpServletRequest request, Model model, HttpServletResponse response,
            @RequestParam Long contestId,
            @RequestParam Long contestPhaseId,
            @RequestParam Long proposalId) throws IOException {

        ProposalsPermissions proposalsPermissions = proposalsContext.getPermissions(request);
        final ClientHelper clients = proposalsContext.getClients(request);
        final ContestClient contestClient = clients.getContestClient();
        ContestPhase contestPhase = contestClient.getContestPhase(contestPhaseId);
        final Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
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
    public void toogleOpen(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam boolean planOpen)
            throws ProposalsAuthorizationException, IOException {

        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if (permissions.getCanDelete()) {
            final long proposalId = proposalsContext.getProposal(request).getProposalId();
            final long userId = proposalsContext.getMember(request).getUserId();
            ProposalsContextUtil.getClients(request).getProposalAttributeClient().setProposalAttribute(userId, proposalId,
                    ProposalAttributeKeys.OPEN, 0L, planOpen ? 1L : 0L);
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)));
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to change proposal open attribute");
        }
    }
}
