package org.xcolab.view.pages.proposals.view.action;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SubscribeProposalActionController {

    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/subscribeProposal")
    public void handleAction(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member currentMember)
            throws ProposalsAuthorizationException, IOException {
        
        if (proposalContext.getPermissions().getCanSubscribeProposal()) {
            final Proposal proposal = proposalContext.getProposal();
            long proposalId = proposal.getProposalId();
            long memberId = currentMember.getId_();
            final ProposalClient proposalClient =
                    proposalContext.getClients().getProposalClient();
            if (proposalClient
                    .isMemberSubscribedToProposal(proposalId, memberId)) {
                proposalClient
                        .unsubscribeMemberFromProposal(proposalId, memberId);
            }
            else {
                proposalClient
                        .subscribeMemberToProposal(proposalId, memberId);
            }
            response.sendRedirect(proposal.getProposalLinkUrl(proposalContext.getContest()));
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe proposal");
        }
    }

}
