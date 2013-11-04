package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class DeleteProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=deleteProposal"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam boolean delete) 
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        
        if (proposalsContext.getPermissions(request).getCanDelete()) {
            //TODO: Undelete doesnt work

            Proposal proposal = proposalsContext.getProposal(request);

            ProposalLocalServiceUtil.setVisibility(proposal.getProposalId(), !delete,
                    proposalsContext.getUser(request).getUserId());
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to delete proposal ");
        }
    }

}
