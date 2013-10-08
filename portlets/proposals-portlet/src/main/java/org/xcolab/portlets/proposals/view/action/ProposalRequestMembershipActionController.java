package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

@Controller
@RequestMapping("view")
public class ProposalRequestMembershipActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=requestMembership"})
    public void show(ActionRequest request, Model model,
            ActionResponse response, @Valid RequestMembershipBean requestMembershipBean, BindingResult result, @RequestParam("requestComment") String comment)
            throws PortalException, SystemException {
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "requestMembership");
            return;
        }

        long userId = proposalsContext.getUser(request).getUserId();
        long proposalId = proposalsContext.getProposal(request).getProposalId();

        ProposalLocalServiceUtil.addMembershipRequest(proposalId,userId,comment);

        SessionMessages.add(request, "membershipRequestSent");
        
    }



    @RequestMapping(params = {"action=approveMembershipRequest"})
    public void approveMembership(ActionRequest request, Model model,
                     ActionResponse response, BindingResult result)
            throws PortalException, SystemException {
                 System.out.println("------ TEST ------");

    }
    

    @RequestMapping(params = {"pageToDisplay=proposalDetails_TEAM", "action=requestMembership", "error=true"})
    public String registerError(PortletRequest request, Model model,
            @Valid RequestMembershipBean newAccountBean, BindingResult result,
            @RequestParam(required = false) String redirect) {
        return "proposalTeam";
    }
}
