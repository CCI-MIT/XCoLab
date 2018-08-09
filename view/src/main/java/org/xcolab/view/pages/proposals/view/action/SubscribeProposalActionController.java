package org.xcolab.view.pages.proposals.view.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
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
            ProposalContext proposalContext, Member currentMember)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanSubscribeProposal()) {
            final Proposal proposal = proposalContext.getProposal();
            long proposalId = proposal.getId();
            long userId = currentMember.getId();
            final ProposalClient proposalClient = proposalContext.getClients().getProposalClient();
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
            Member member) {

        AlertMessage.warning(
                "Could not subscribe to proposal, please make sure to click the button only once.")
                .flash(request);
        final Contest contest = proposalContext.getContest();
        final Proposal proposal = proposalContext.getProposal();
        return "redirect:" + proposal.getProposalLinkUrl(contest);
    }
}
