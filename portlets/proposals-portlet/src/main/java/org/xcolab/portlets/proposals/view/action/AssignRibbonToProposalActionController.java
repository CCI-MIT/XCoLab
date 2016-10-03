package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.NoSuchContestPhaseRibbonTypeException;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class AssignRibbonToProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=assignRibbon"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam int ribbon)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanAssignRibbon()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long contestId = proposalsContext.getContest(request).getContestPK();


                ProposalsClient.setProposalContestPhaseAttribute(proposalId, contestPhaseId,
                        ProposalContestPhaseAttributeKeys.RIBBON,null,new Long(ribbon),null);

            //    ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(proposalId, contestPhaseId,
            //        ProposalContestPhaseAttributeKeys.RIBBON);


            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request),
                     proposalsContext.getContestPhase(request).getContestPhasePK()) + "/tab/ADMIN");
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to assign ribbon");
        }

    }

}
