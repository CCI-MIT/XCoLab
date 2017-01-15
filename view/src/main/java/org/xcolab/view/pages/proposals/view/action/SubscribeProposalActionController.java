package org.xcolab.view.pages.proposals.view.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SubscribeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;


    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/subscribeProposal")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response)
            throws ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanSubscribeProposal()) {
            final Proposal proposal = proposalsContext.getProposal(request);
            long proposalId = proposal.getProposalId();
            long memberId = proposalsContext.getMember(request).getUserId();
            final ProposalClient proposalClient =
                    ProposalsContextUtil.getClients(request).getProposalClient();
            if (proposalClient
                    .isMemberSubscribedToProposal(proposalId, memberId)) {
                proposalClient
                        .unsubscribeMemberFromProposal(proposalId, memberId);
            }
            else {
                proposalClient
                        .subscribeMemberToProposal(proposalId, memberId);
            }
            response.sendRedirect(proposal.getProposalLinkUrl(proposalsContext.getContest(request)));
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe proposal");
        }
    }

}
