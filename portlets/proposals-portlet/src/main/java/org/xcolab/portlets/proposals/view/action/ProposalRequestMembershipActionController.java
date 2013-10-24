package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.util.PortalUtil;
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



    @RequestMapping(params = {"action=replyToMembershipRequest"})
    public void respond(ActionRequest request, Model model,
                        ActionResponse response,
                        @RequestParam("approve") String approve,
                        @RequestParam("comment") String comment,
                        @RequestParam("requestId") long requestId)
            throws PortalException, SystemException {
        if (PortalUtil.getUser(request) == null) return;

        long userId = PortalUtil.getUser(request).getUserId();
        long proposalId = proposalsContext.getProposal(request).getProposalId();

        MembershipRequest membershipRequest = null;
        for (MembershipRequest mr : ProposalLocalServiceUtil.getMembershipRequests(proposalId)){
            if (mr.getPrimaryKey() == requestId) membershipRequest = mr;
        }

        if (membershipRequest == null) return;
        if (comment == null || comment.equalsIgnoreCase("Optional response")) comment = "no comments";
        if (approve.equalsIgnoreCase("APPROVE")){
            ProposalLocalServiceUtil.approveMembershipRequest(proposalId, membershipRequest.getUserId(), membershipRequest, comment, userId);
        } else if (approve.equalsIgnoreCase("DENY")){
            ProposalLocalServiceUtil.dennyMembershipRequest(proposalId, membershipRequest.getUserId(), requestId, comment, userId);
        }

    }
}
