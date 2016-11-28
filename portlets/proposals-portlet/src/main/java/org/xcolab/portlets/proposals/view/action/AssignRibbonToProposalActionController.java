package org.xcolab.portlets.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class AssignRibbonToProposalActionController {

    private final ProposalsContext proposalsContext;

    @Autowired
    public AssignRibbonToProposalActionController(ProposalsContext proposalsContext) {
        Assert.notNull(proposalsContext, "ProposalsContext bean is required");
        this.proposalsContext = proposalsContext;
    }

    @RequestMapping(params = {"action=assignRibbon"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam int ribbon)
            throws ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanAssignRibbon()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

            ProposalPhaseClientUtil.setProposalContestPhaseAttribute(proposalId, contestPhaseId,
                    ProposalContestPhaseAttributeKeys.RIBBON, null, (long) ribbon, null);

            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request),
                     proposalsContext.getContestPhase(request).getContestPhasePK()) + "/tab/ADMIN");
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to assign ribbon");
        }
    }
}
