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

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ToggleProposalOpenController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=toggleProposalOpen"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam boolean planOpen) 
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        
        if (proposalsContext.getPermissions(request).getCanDelete()) {
            ProposalLocalServiceUtil.setAttribute(proposalsContext.getUser(request).getUserId(), 
                    proposalsContext.getProposal(request).getProposalId(), ProposalAttributeKeys.OPEN, planOpen ? 1 : 0);
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to change proposal open attribute");
        }
    }

}
