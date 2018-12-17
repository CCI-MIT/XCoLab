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
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.ProposalTeamMembershipRequest;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.enums.membershiprequest.MembershipRequestStatus;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.RequestMembershipBean;
import org.xcolab.view.pages.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

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
    public String show(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member loggedInMember) {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanView()) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        final ProposalClient proposalClient = getProposalClient(proposalContext);
        final long proposalId = getProposalId(proposalContext);

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.TEAM);

        model.addAttribute("requestMembershipBean", new RequestMembershipBean());
        model.addAttribute("requestMembershipInviteBean", new RequestMembershipInviteBean());

        List<Proposal> listOfSubProposals = proposalClient
                .getLinkingProposalsForProposalId(proposalId);
        model.addAttribute("listOfSubProposals", listOfSubProposals);

        Map<Proposal, List<Member>> mapOfSubProposalContributors = new HashMap<>();
        for (Proposal temp : listOfSubProposals) {
            List<Member> contributors = proposalClient.getProposalMembers(temp.getId());
            mapOfSubProposalContributors.put(temp, contributors);
        }
        model.addAttribute("mapOfSubProposalContributors", mapOfSubProposalContributors);

        long membershipRequestId = -1;
        if (loggedInMember != null) {
            MembershipClient membershipClient = MembershipClientUtil.getClient();
            ProposalTeamMembershipRequest membershipRequest = membershipClient
                    .getActiveMembershipRequestByUser(proposalContext.getProposal(), loggedInMember.getId());
            if (membershipRequest != null && membershipRequest.getStatusId() == MembershipRequestStatus.STATUS_PENDING_INVITED) {
                membershipRequestId = membershipRequest.getId();
            }
        }
        model.addAttribute("membershipRequestId", membershipRequestId);

        return "/proposals/proposalTeam";
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/removeMemberFromTeam")
    public void handleAction(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member actingMember, @RequestParam long userId)
            throws ProposalsAuthorizationException, IOException {
        checkHasManagePermissions(proposalContext, actingMember);
        checkIsRemovingOwner(proposalContext.getProposal(), userId);

        final ProposalClient proposalClient = getProposalClient(proposalContext);
        final long proposalId = getProposalId(proposalContext);

        proposalClient.removeMemberFromProposalTeam(proposalId, userId);

        AlertMessage.success("The member was removed from the proposal's team!").flash(request);
        sendRedirect(proposalContext, response);
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/promoteMemberToOwner")
    public void promoteMemberToOwner(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member actingMember,
            @RequestParam long userId) throws ProposalsAuthorizationException, IOException {
        checkHasManagePermissions(proposalContext, actingMember);

        final ProposalClient proposalClient = getProposalClient(proposalContext);
        final long proposalId = getProposalId(proposalContext);

        proposalClient.promoteMemberToProposalOwner(proposalId, userId);

        AlertMessage.success("The member was promoted to the new team owner.").flash(request);
        sendRedirect(proposalContext, response);
    }

    private ProposalClient getProposalClient(ProposalContext proposalContext) {
        return proposalContext.getClients().getProposalClient();
    }

    private long getProposalId(ProposalContext proposalContext) {
        return proposalContext.getProposal().getId();
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
        final long proposalId = proposal.getId();
        final long actingUserId = actingMember.getId();

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanManageUsers()) {
            generateAuthorizationError(String.format(
                    "User %d does not have the necessary permissions to manage the team of "
                            + "proposal %d", actingUserId, proposalId));
        }
    }

    private void checkIsRemovingOwner(Proposal proposal, long removeduserId)
            throws ProposalsAuthorizationException {
        if (removeduserId == proposal.getAuthorUserId()) {
            generateAuthorizationError(
                    String.format("The owner %d of proposal %d can not be removed from the team.",
                            removeduserId, proposal.getId()));
        }
    }

    private void generateAuthorizationError(String errorMessage)
            throws ProposalsAuthorizationException {
        _log.error(errorMessage);
        throw new ProposalsAuthorizationException(errorMessage);
    }
}
