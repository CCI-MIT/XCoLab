package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;


    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/ADMIN/deleteProposal")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam boolean delete)
            throws ProposalsAuthorizationException, IOException {

        if (proposalsContext.getPermissions(request).getCanDelete()) {
            //TODO: Undelete doesnt work
            ContestPhase contestPhase = proposalsContext.getContestPhase(request);
            Proposal proposal = proposalsContext.getProposal(request);
            Contest contest = proposalsContext.getContest(request);



            proposal.setVisible(!delete);
            ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);

            AlertMessage.success("Proposal was deleted successfully!").flash(request);
            response.sendRedirect(
                    proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK()) + "/tab/ADMIN");
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to delete proposal ");
        }
    }

}
