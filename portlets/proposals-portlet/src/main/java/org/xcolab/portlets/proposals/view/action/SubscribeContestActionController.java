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

import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class SubscribeContestActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=subscribeContest", "pageToDisplay=proposalDetails_TEAM"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, 
            @Valid SupportProposalActionBean supportProposalBean, BindingResult result) 
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        
        if (proposalsContext.getPermissions(request).getCanSubscribeContest()) {
            long contestId = proposalsContext.getContest(request).getContestPK();
            long userId = proposalsContext.getUser(request).getUserId();
            
            //ContestLocalServiceUtil.
            
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe contest");
        }
    }

}
