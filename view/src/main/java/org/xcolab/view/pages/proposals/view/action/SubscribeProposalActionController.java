package org.xcolab.view.pages.proposals.view.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}")
public class SubscribeProposalActionController {

    @PostMapping("/subscribeProposal")
    public void handleAction(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, UserWrapper currentMember)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanSubscribeProposal()) {
            final ProposalWrapper proposal = proposalContext.getProposal();
            long proposalId = proposal.getId();
            long userId = currentMember.getId();
            final IProposalClient proposalClient = proposalContext.getClients().getProposalClient();
            if (proposalClient.isMemberSubscribedToProposal(proposalId, userId)) {
                proposalClient.unsubscribeMemberFromProposal(proposalId, userId);
            } else {
                proposalClient.subscribeMemberToProposal(proposalId, userId);
            }
            response.sendRedirect(proposal.getProposalLinkUrl(proposalContext.getContest()));
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe proposal");
        }
    }

    @GetMapping("/subscribeProposal")
    public String handleInvalidGetRequest(HttpServletRequest request,
            HttpServletResponse response, Model model, ProposalContext proposalContext,
            UserWrapper member) {

        AlertMessage.warning(
                "Could not subscribe to proposal, please make sure to click the button only once.")
                .flash(request);
        final ContestWrapper contest = proposalContext.getContest();
        final ProposalWrapper proposal = proposalContext.getProposal();
        return "redirect:" + proposal.getProposalLinkUrl(contest);
    }
}
