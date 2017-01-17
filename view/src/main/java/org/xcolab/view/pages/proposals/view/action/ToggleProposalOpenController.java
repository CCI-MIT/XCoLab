package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//-- @RequestMapping("view")
public class ToggleProposalOpenController {

    @Autowired
    private ProposalsContext proposalsContext;



    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/ADMIN/toggleProposalOpen")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam boolean planOpen)
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
