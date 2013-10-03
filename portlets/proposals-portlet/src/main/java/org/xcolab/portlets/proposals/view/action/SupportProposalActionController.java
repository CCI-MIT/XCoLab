package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.SupportProposalActionBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class SupportProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    
    @RequestMapping(params = {"action=supportProposalAction", "pageToDisplay=proposalDetails_TEAM"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, 
            @Valid SupportProposalActionBean supportProposalBean, BindingResult result)
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        
        if (proposalsContext.getPermissions(request).getCanSupportProposal()) {
            long userId = proposalsContext.getUser(request).getUserId();
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            
            if (ProposalLocalServiceUtil.isSupporter(proposalId, userId)) {
                ProposalLocalServiceUtil.removeSupporter(proposalId, userId);   
            }
            else {
                ProposalLocalServiceUtil.addSupporter(proposalId, userId);
            }
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to toggle support for proposal");
        }
    }
    
}
