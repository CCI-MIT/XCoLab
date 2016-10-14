package org.xcolab.portlets.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class ToggleProposalOpenController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=toggleProposalOpen"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam boolean planOpen)
            throws ProposalsAuthorizationException, IOException {

        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if (permissions.getCanDelete()) {
            final long proposalId = proposalsContext.getProposal(request).getProposalId();
            final long userId = proposalsContext.getMember(request).getUserId();
            ProposalAttributeClientUtil.setProposalAttribute(userId, proposalId,
                    ProposalAttributeKeys.OPEN, 0L, planOpen ? 1L : 0L);
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)));
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to change proposal open attribute");
        }
    }

}
