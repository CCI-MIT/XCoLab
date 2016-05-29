package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.NoSuchContestPhaseRibbonTypeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
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
            
            try {
                ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(ribbon);
                ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(proposalId, contestPhaseId, 
                        ProposalContestPhaseAttributeKeys.RIBBON, ribbon);   
            }
            catch (NoSuchContestPhaseRibbonTypeException e) {
                ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(proposalId, contestPhaseId, 
                        ProposalContestPhaseAttributeKeys.RIBBON);   
            }

            response.sendRedirect(ProposalLocalServiceUtil.getProposalLinkUrl(proposalsContext.getContest(request),
                    proposalsContext.getProposal(request), proposalsContext.getContestPhase(request)) + "/tab/ADMIN");
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to assign ribbon");
        }

    }

}
