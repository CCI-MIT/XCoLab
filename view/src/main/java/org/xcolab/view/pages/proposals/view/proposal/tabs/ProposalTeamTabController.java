package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.RequestMembershipBean;
import org.xcolab.view.pages.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalTeamTabController extends BaseProposalTabController {

    private static final Logger _log = LoggerFactory.getLogger(ProposalTeamTabController.class);

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=TEAM")
    public String show(HttpServletRequest request, Model model, ProposalContext proposalContext) {
        final ProposalClient proposalClient = getProposalClient(proposalContext);
        final long proposalId = getProposalId(proposalContext);

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.TEAM);

        model.addAttribute("requestMembershipBean", new RequestMembershipBean());
        model.addAttribute("requestMembershipInviteBean", new RequestMembershipInviteBean());

        List<Proposal> listOfLinkedProposals = proposalClient.getLinkingProposalsForProposalID(proposalId);
        model.addAttribute("listOfLinkedProposals", listOfLinkedProposals);

        Map<Proposal, List<Member>> mapOfContributingProposals = new HashMap<>();
        for (Proposal temp : listOfLinkedProposals) {
            List<Member> contributors = proposalClient.getProposalMembers(temp.getProposalId());
            mapOfContributingProposals.put(temp, contributors);
        }
        model.addAttribute("mapOfContributingProposals", mapOfContributingProposals);

        return "/proposals/proposalTeam";
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/removeMemberFromTeam")
    public void handleAction(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member actingMember, @RequestParam long memberId)
            throws ProposalsAuthorizationException, IOException {
        checkHasManagePermissions(proposalContext, actingMember);
        checkIsRemovingOwner(proposalContext.getProposal(), memberId);

        final ProposalClient proposalClient = getProposalClient(proposalContext);
        final long proposalId = getProposalId(proposalContext);

        proposalClient.removeMemberFromProposalTeam(proposalId, memberId);

        AlertMessage.success("The member was removed from the proposal's team!").flash(request);
        sendRedirect(proposalContext, response);
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/promoteMemberToOwner")
    public void promoteMemberToOwner(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member actingMember,
            @RequestParam long memberId) throws ProposalsAuthorizationException, IOException {
        checkHasManagePermissions(proposalContext, actingMember);

        final ProposalClient proposalClient = getProposalClient(proposalContext);
        final long proposalId = getProposalId(proposalContext);

        proposalClient.promoteMemberToProposalOwner(proposalId, memberId);

        AlertMessage.success("The member was promoted to the new team owner.").flash(request);
        sendRedirect(proposalContext, response);
    }

    private ProposalClient getProposalClient(ProposalContext proposalContext) {
        return proposalContext.getClients().getProposalClient();
    }

    private long getProposalId(ProposalContext proposalContext) {
        return proposalContext.getProposal().getProposalId();
    }

    private void sendRedirect(ProposalContext proposalContext, HttpServletResponse response)
            throws IOException {
        final Proposal proposal = proposalContext.getProposal();
        response.sendRedirect(
                proposal.getProposalLinkUrl(proposalContext.getContest()) + "/tab/TEAM");
    }

    private void checkHasManagePermissions(ProposalContext proposalContext, Member actingMember)
            throws ProposalsAuthorizationException {
        final Proposal proposal = proposalContext.getProposal();
        final long proposalId = proposal.getProposalId();
        final long actingUserId = actingMember.getUserId();

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanManageUsers()) {
            generateAuthorizationError(String.format(
                    "User %d does not have the necessary permissions to manage the team of "
                            + "proposal %d", actingUserId, proposalId));
        }
    }

    private void checkIsRemovingOwner(Proposal proposal, long removedMemberId)
            throws ProposalsAuthorizationException {
        if (removedMemberId == proposal.getAuthorId()) {
            generateAuthorizationError(
                    String.format("The owner %d of proposal %d can not be removed from the team.",
                            removedMemberId, proposal.getProposalId()));
        }
    }

    private void generateAuthorizationError(String errorMessage)
            throws ProposalsAuthorizationException {
        _log.error(errorMessage);
        throw new ProposalsAuthorizationException(errorMessage);
    }
}
