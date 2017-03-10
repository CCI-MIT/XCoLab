package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AssignRibbonToProposalActionController {

    private final ProposalsContext proposalsContext;

    @Autowired
    public AssignRibbonToProposalActionController(ProposalsContext proposalsContext) {
        Assert.notNull(proposalsContext, "ProposalsContext bean is required");
        this.proposalsContext = proposalsContext;
    }


    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/ADMIN/assignRibbon")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam int ribbon)
            throws ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanAssignRibbon()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

            ProposalPhaseClientUtil.setProposalContestPhaseAttribute(proposalId, contestPhaseId,
                    ProposalContestPhaseAttributeKeys.RIBBON, null, (long) ribbon, null);

            AlertMessage.success("The ribbon has been assigned to the proposal at the current contest phase!").flash(request);
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request),
                     proposalsContext.getContestPhase(request).getContestPhasePK()) + "/tab/ADMIN");
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to assign ribbon");
        }
    }
}
