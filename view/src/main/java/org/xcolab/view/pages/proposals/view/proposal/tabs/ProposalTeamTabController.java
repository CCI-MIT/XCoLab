package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.RequestMembershipBean;
import org.xcolab.view.pages.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalTeamTabController extends BaseProposalTabController {

    private static final Logger _log = LoggerFactory.getLogger(ProposalTeamTabController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=TEAM")
    public String show(Model model, HttpServletRequest request) {

        setCommonModelAndPageAttributes(request, model, ProposalTab.TEAM);

        model.addAttribute("requestMembershipBean", new RequestMembershipBean());
        model.addAttribute("requestMembershipInviteBean", new RequestMembershipInviteBean());

        return "/proposals/proposalTeam";
    }

    @GetMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/removeUserFromTeam")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam("member") long memberId)
            throws ProposalsAuthorizationException, IOException {

        final Proposal proposal = proposalsContext.getProposal(request);
        final Member actingMember = proposalsContext.getMember(request);
        final long proposalId = proposal.getProposalId();
        final long actingUserId = actingMember.getUserId();

        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanManageUsers()) {
            final String errorMessage = String.format(
                    "User %d does not have the necessary permissions to remove a user from the team of proposal %d",
                    actingUserId, proposalId);
            _log.error(errorMessage);
            throw new ProposalsAuthorizationException(errorMessage);
        }
        if (memberId == proposal.getAuthorId()) {
            final String errorMessage = String.format("User %d is trying to remove the owner %d of proposal %d",
                    actingUserId, memberId, proposalId);
            _log.error(errorMessage);
            throw new ProposalsAuthorizationException(errorMessage);
        }

        final ClientHelper clients = ProposalsContextUtil.getClients(request);
        final ProposalClient proposalClient = clients.getProposalClient();

        proposalClient.removeUserFromProposalTeam(proposalId, memberId);

        AlertMessage.success("The member was removed from the proposal's team!").flash(request);
        response.sendRedirect(proposal.getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/TEAM");
    }
}