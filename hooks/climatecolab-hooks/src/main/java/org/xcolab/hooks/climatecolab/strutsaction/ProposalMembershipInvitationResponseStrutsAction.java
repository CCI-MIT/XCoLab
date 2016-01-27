package org.xcolab.hooks.climatecolab.strutsaction;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mente on 30/04/14.
 */
public class ProposalMembershipInvitationResponseStrutsAction extends BaseStrutsAction {

	/**
	 * The friendly URL mapping for the proposal detail page
	 */
	private static final String PROPOSAL_URL = "/web/guest/plans/-/plans/contestId/%d/planId/%d";

	private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT = "Response to your membership invite";

	/**
	 * The response message when the proposal membership invitee accepts the invitation
	 */
	private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED = "Your invitation of %s to join the proposal %s has been accepted.";

	/**
	 * The response message when the proposal membership invitee rejects the invitation
	 */
	private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED = "Your invitation of %s to join the proposal %s has been rejected.";


	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws PortalException, SystemException, IOException {

		long membershipId = GetterUtil.get(request.getParameter("requestId"), 0L);
		long proposalId = GetterUtil.get(request.getParameter("proposalId"), 0L);
		long contestId = GetterUtil.get(request.getParameter("contestId"), 0L);
		String action = request.getParameter("do");

		MembershipRequest membershipRequest = MembershipRequestLocalServiceUtil.getMembershipRequest(membershipId);

		List<Long> recipients = new ArrayList<>();
		List<User> contributors = ProposalLocalServiceUtil.getMembers(proposalId);

		for (User user : contributors) {
			recipients.add(user.getUserId());
		}

		String proposalName = ProposalAttributeLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME,0).getStringValue();
		String proposalLink = String.format("<a href='%s'>%s</a>", String.format(PROPOSAL_URL, contestId, proposalId), proposalName);

		if (membershipRequest != null) {
			User invitee = UserLocalServiceUtil.getUserById(membershipRequest.getUserId());
			if (action.equalsIgnoreCase("ACCEPT")){
				ProposalLocalServiceUtil.approveMembershipRequest(proposalId, membershipRequest.getUserId(), membershipRequest, "The invitation was accepted.", invitee.getUserId());
				sendMessage(invitee.getUserId(),recipients,MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,String.format(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED, invitee.getFullName(), proposalLink));
			} else if (action.equalsIgnoreCase("DECLINE")){
				ProposalLocalServiceUtil.dennyMembershipRequest(proposalId, membershipRequest.getUserId(), membershipId, "The invitation was rejected.", invitee.getUserId());
				sendMessage(invitee.getUserId(),recipients,MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,String.format(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED, invitee.getFullName(), proposalLink));
			}
		}

		response.sendRedirect(String.format(PROPOSAL_URL, contestId, proposalId));
		return StringPool.BLANK;
	}

	public void sendMessage(long sender, List<Long> recipients, String subject, String content) {
		try{
			MessageUtil.sendMessage(subject, content, sender,
					sender, recipients, null);
		} catch (AddressException | SystemException | PortalException | MailEngineException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
