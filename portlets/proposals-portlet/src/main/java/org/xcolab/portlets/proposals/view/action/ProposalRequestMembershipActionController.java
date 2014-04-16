package org.xcolab.portlets.proposals.view.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.validation.Valid;

import com.ext.portlet.service.ProposalLocalService;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.MembershipRequestLocalService;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;
import org.xcolab.portlets.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.xcolab.portlets.proposals.wrappers.MembershipRequestWrapper;

@Controller
@RequestMapping("view")
public class ProposalRequestMembershipActionController {

    // Membership request from non-group user
    private static final String MSG_MEMBERSHIP_REQUEST_SUBJECT = "%s wants to join your proposal %s";
    private static final String MSG_MEMBERSHIP_REQUEST_CONTENT = "User %s has requested to join your proposal %s. Click <a href='%s'>here</a> to respond to it.";
    private static final String PROPOSAL_URL_ADMIN = "/web/guest/plans/-/plans/contestId/%d/planId/%d/tab/ADMIN";
    private static final String MSG_MEMBERSHIP_RESPONSE_SUBJECT = "Response to your membership request";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED = "Your request has been accepted <br />Comments: ";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED = "Your request has been rejected <br />Comments: ";

    // Membership invite from group user
    private static final String PROPOSAL_URL = "/web/guest/plans/-/plans/contestId/%d/planId/%d";
    private static final String MSG_MEMBERSHIP_INVITE_SUBJECT = "%s invites you to join the proposal %s";
    private static final String MSG_MEMBERSHIP_INVITE_CONTENT = "User %s invites you to contribute to the proposal %s with the following message: \n\n%s\n\n" +
            "Click <a href='%s' target='_blank'>here</a> to <strong>accept</strong> the invitation.\n" +
            "Click <a href='%s' target='_blank'>here</a> to <strong>decline</strong> the invitation. ";
    private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT = "Response to your membership invite";
    private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED = "Your invitation of %s to join the proposal %s has been accepted.";
    private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED = "Your invitation of %s to join the proposal %s has been rejected.";

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
    	
    	if (proposalsContext.getUser(request).getDefaultUser()) {
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
                String proposalUrl = String.format(PROPOSAL_URL_ADMIN,
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

    @RequestMapping(params = {"action=inviteMember"})
    public void invite(ActionRequest request, Model model,
                     ActionResponse response, @Valid RequestMembershipInviteBean requestMembershipBean, BindingResult result)
            throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
                WebKeys.THEME_DISPLAY);

        String input = requestMembershipBean.getInviteRecipient();
        if (input == null || input.equals("")) {
            return;
        }

        String[] inputParts = input.split(" ");

		try {
			if (inputParts.length > 0) {
				String screenName = inputParts[0];
				User recipient = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), screenName);

				if (recipient != null) {
					long userId = proposalsContext.getUser(request).getUserId();
					long proposalId = proposalsContext.getProposal(request).getProposalId();
					long contestId = proposalsContext.getContest(request).getContestPK();
					long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

					String proposalName = ProposalLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME,0).getStringValue();
					MembershipRequest memberRequest = ProposalLocalServiceUtil.addMembershipRequest(proposalId,recipient.getUserId(),requestMembershipBean.getInviteComment());

					String acceptURL = String.format("/web/guest/plans/-/plans/contestId/%d/phaseId/%d/planId/%d/invitationResponse/%s/requestId/%d", contestId, contestPhaseId, proposalId, "accept", memberRequest.getPrimaryKey());
					String declineURL = String.format("/web/guest/plans/-/plans/contestId/%d/phaseId/%d/planId/%d/invitationResponse/%s/requestId/%d", contestId, contestPhaseId, proposalId, "decline", memberRequest.getPrimaryKey());

					String proposalLink = String.format("<a href='%s'>%s</a>", String.format(PROPOSAL_URL, proposalsContext.getContest(request).getContestPK(), proposalId), proposalName);
					String subject = String.format(MSG_MEMBERSHIP_INVITE_SUBJECT,
							proposalsContext.getUser(request).getFullName(), proposalName);
					String content = String.format(MSG_MEMBERSHIP_INVITE_CONTENT,
							proposalsContext.getUser(request).getFullName(),
							proposalLink, requestMembershipBean.getInviteComment(), acceptURL, declineURL);
					sendMessage(proposalsContext.getUser(request).getUserId(),recipient.getUserId(),subject,content);

					SessionMessages.add(request, "memberInviteSent");
				}
			} else {
				SessionErrors.add(request, "memberInviteRecipientError");
			}

		} catch (NoSuchUserException e) {
			SessionErrors.add(request, "memberInviteRecipientError");
		}


    }

    @RequestMapping(params = {"action=invitationResponse"})
    public void respondToInvitation(ActionRequest request, Model model,
                                    ActionResponse response) throws SystemException, PortalException {
        long requestId = GetterUtil.getLong(request.getParameter("requestId"));
        long proposalId = GetterUtil.getLong(request.getParameter("planId"));
        String action = request.getParameter("do");

        MembershipRequest membershipRequest = null;
        for (MembershipRequest mr : ProposalLocalServiceUtil.getMembershipRequests(proposalId)){
            if (mr.getPrimaryKey() == requestId) {
				membershipRequest = mr;
				break;
			}
        }

		String proposalName = ProposalLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME,0).getStringValue();
		String proposalLink = String.format("<a href='%s'>%s</a>", String.format(PROPOSAL_URL, proposalsContext.getContest(request).getContestPK(), proposalId), proposalName);
        if (membershipRequest == null) return;
		User invitee = UserLocalServiceUtil.getUserById(membershipRequest.getUserId());
        if (action.equalsIgnoreCase("ACCEPT")){
            ProposalLocalServiceUtil.approveMembershipRequest(proposalId, membershipRequest.getUserId(), membershipRequest, "The invitation was accepted.", proposalsContext.getUser(request).getUserId());
            sendMessage(proposalsContext.getUser(request).getUserId(),membershipRequest.getUserId(),MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,String.format(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED, invitee.getFullName(), proposalLink));
        } else if (action.equalsIgnoreCase("DECLINE")){
            ProposalLocalServiceUtil.dennyMembershipRequest(proposalId, membershipRequest.getUserId(), requestId, "The invitation was rejected.", proposalsContext.getUser(request).getUserId());
            sendMessage(proposalsContext.getUser(request).getUserId(),membershipRequest.getUserId(),MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,String.format(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED, invitee.getFullName(), proposalLink));
        }
    }

    @ResourceMapping("inviteMembers-validateRecipient")
    public void validateRecipient(ResourceRequest request, ResourceResponse response)
            throws PortalException, SystemException {
        String input = (String)request.getParameter("term");

        List<User> recipients = getRecipientSuggestions(input, proposalsContext.getProposal(request).getProposalId());
        JSONArray responseJson = JSONFactoryUtil.createJSONArray();
        for (User user : recipients) {
            responseJson.put(String.format("%s (%s %s)", user.getScreenName(), user.getFirstName(), user.getLastName()));
        }
        try {
            response.getPortletOutputStream().write(responseJson.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private List<User> getRecipientSuggestions(String input, long proposalId) throws PortalException, SystemException {
        String[] inputParts = input.split(" ");
        if (inputParts.length == 0) {
            return new ArrayList<>();
        }

		List<Long> contributerIds = new ArrayList<>();
		for (User contributor : ProposalLocalServiceUtil.getMembers(proposalId)) {
			contributerIds.add(contributor.getUserId());
		}

        Criterion criterion = RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.in("userId", contributerIds));
        // For the sake of performance we only search the first word for either screenname, firstname or last name match
        // Search by screen name
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
        query.add(RestrictionsFactoryUtil.ilike("screenName", String.format("%s%%", inputParts[0])));
        query.add(criterion);
        query.setLimit(0, 5);
        List<User> recipients = UserLocalServiceUtil.dynamicQuery(query);

        // Search by last name
        DynamicQuery lnQuery = DynamicQueryFactoryUtil.forClass(User.class);
        query.add(RestrictionsFactoryUtil.ilike("lastName", String.format("%s%%", inputParts[0])));
        query.add(criterion);
        query.setLimit(0, 5);
        List<User> fnRecipients = UserLocalServiceUtil.dynamicQuery(query);

        for (User user : fnRecipients) {
            if (!recipients.contains(user)) {
                recipients.add(user);
            }
        }

        return recipients;
    }

}
