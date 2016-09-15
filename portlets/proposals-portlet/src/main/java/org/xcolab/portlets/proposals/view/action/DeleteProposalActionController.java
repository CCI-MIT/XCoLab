package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.wrappers.BaseContestPhaseWrapper;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class DeleteProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=deleteProposal"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam boolean delete)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        if (proposalsContext.getPermissions(request).getCanDelete()) {
            //TODO: Undelete doesnt work
            ContestPhase contestPhase = proposalsContext.getContestPhase(request);
            Proposal proposal = proposalsContext.getProposal(request);
            Contest contest = proposalsContext.getContest(request);



            proposal.setVisible(!delete);
            ProposalsClient.updateProposal(proposal);

            response.sendRedirect(
                    proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK()) + "/tab/ADMIN");
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to delete proposal ");
        }
    }

}
