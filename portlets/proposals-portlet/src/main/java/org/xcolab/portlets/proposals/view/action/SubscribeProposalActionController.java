package org.xcolab.portlets.proposals.view.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class SubscribeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=subscribeProposal"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanSubscribeProposal()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long memberId = proposalsContext.getMember(request).getUserId();
            if (ProposalClientUtil.isMemberSubscribedToProposal(proposalId, memberId)) {
                ProposalClientUtil.unsubscribeMemberFromProposal(proposalId, memberId);
            }
            else {
                ProposalClientUtil.subscribeMemberToProposal(proposalId, memberId);
            }
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)));
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe proposal");
        }
    }

}
