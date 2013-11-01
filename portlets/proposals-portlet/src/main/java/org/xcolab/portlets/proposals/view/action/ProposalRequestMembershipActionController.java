package org.xcolab.portlets.proposals.view.action;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngineException;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("view")
public class ProposalRequestMembershipActionController {


    private static final String MSG_MEMBERSHIP_REQUEST_SUBJECT = "%s wants to join your proposal %s";
    private static final String MSG_MEMBERSHIP_REQUEST_CONTENT = "User %s has requested to join your proposal %s. Click <a href='%s'>here</a> to respond to it.";
    private static final String PROPOSAL_URL = "/web/guest/plans/-/plans/contestId/%d/planId/%d/tab/ADMIN";
    private static final String MSG_MEMBERSHIP_RESPONSE_SUBJECT = "Response to your membership request";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED = "Your request has been accepted <br />Comments: ";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED = "Your request has been rejected <br />Comments: ";

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
        String proposalName = ProposalLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME,0).getStringValue();
        ProposalLocalServiceUtil.addMembershipRequest(proposalId,userId,comment);

        for(User user : ProposalLocalServiceUtil.getMembers(proposalsContext.getProposal(request).getProposalId())){
            if (proposalsContext.getProposal(request).getAuthorId() == user.getUserId()){
                String subject = String.format(MSG_MEMBERSHIP_REQUEST_SUBJECT,
                        proposalsContext.getUser(request).getFullName(),
                        proposalName);
                String proposalUrl = String.format(PROPOSAL_URL,
                        proposalsContext.getContest(request).getContestPK(),
                        proposalId);
                String content = String.format(MSG_MEMBERSHIP_REQUEST_CONTENT,
                        proposalsContext.getUser(request).getFullName(),
                        proposalName, proposalUrl);
                sendMessage(proposalsContext.getUser(request).getUserId(),user.getUserId(),subject,content);
            }
        }



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
            sendMessage(proposalsContext.getUser(request).getUserId(),membershipRequest.getUserId(),MSG_MEMBERSHIP_RESPONSE_SUBJECT,MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED + comment);
        } else if (approve.equalsIgnoreCase("DENY")){
            ProposalLocalServiceUtil.dennyMembershipRequest(proposalId, membershipRequest.getUserId(), requestId, comment, userId);
            sendMessage(proposalsContext.getUser(request).getUserId(),membershipRequest.getUserId(),MSG_MEMBERSHIP_RESPONSE_SUBJECT,MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED + comment);
        }

    }

    public void sendMessage(long sender, long recipient, String subject, String content) {
        List<Long> recipients = new ArrayList<Long>();
        recipients.add(recipient);

        try{
            MessageUtil.sendMessage(subject, content, sender,
                    sender,recipients , null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
