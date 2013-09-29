package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.HtmlUtil;

@Controller
@RequestMapping("view")
public class ProposalRequestMembershipActionController {

    @Autowired
    private Validator validator;

    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_TEAM", "action=requestMembership"})
    public void show(ActionRequest request, Model model,
            ActionResponse response, @Valid RequestMembershipBean requestMembershipBean, BindingResult result) 
            throws PortalException, SystemException {
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "requestMembership");
            return;
        }
        
        SessionMessages.add(request, "membershipRequestSent");
        
    }
    

    @RequestMapping(params = {"pageToDisplay=proposalDetails_TEAM", "action=requestMembership", "error=true"})
    public String registerError(PortletRequest request, Model model,
            @Valid RequestMembershipBean newAccountBean, BindingResult result,
            @RequestParam(required = false) String redirect) {
        return "proposalTeam";
    }
}
