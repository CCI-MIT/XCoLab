package org.xcolab.portlets.proposals.view.action;


import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.MembershipRequestClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.MembershipRequest;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;
import org.xcolab.portlets.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.utils.emailnotification.proposal.ProposalMembershipInviteNotification;
import org.xcolab.utils.emailnotification.proposal.ProposalUserActionNotification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class ProposalRequestMembershipActionController {

    private static Log _log = LogFactoryUtil.getLog(ProposalRequestMembershipActionController.class);

    private static final String MEMBERSHIP_REQUEST_TEMPLATE = "PROPOSAL_MEMBERSHIP_REQUEST_DEFAULT";

    private static final String MSG_MEMBERSHIP_RESPONSE_SUBJECT = "Response to your membership request";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED = "Your request has been accepted <br />Comments: ";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED = "Your request has been rejected <br />Comments: ";

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=requestMembership"})
    public void show(ActionRequest request, Model model,
                     ActionResponse response, @Valid RequestMembershipBean requestMembershipBean, BindingResult result, @RequestParam("requestComment") String comment)
            throws PortalException, SystemException, IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "requestMembership");
            return;
        }

        final User liferaySender = proposalsContext.getUser(request);
        final Member sender = proposalsContext.getMember(request);
        if (liferaySender.getDefaultUser()) {
            return;
        }

        final Proposal proposal = proposalsContext.getProposal(request);
        final long proposalId = proposal.getProposalId();
        final Member proposalAuthor = MembersClient.getMemberUnchecked(proposal.getAuthorId());
        final Contest contest = proposalsContext.getContest(request);

        MembershipRequestClient.addRequestedMembershipRequest(proposalId, liferaySender.getUserId(), comment);

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());


        new ProposalUserActionNotification(proposal, contest, sender, proposalAuthor, MEMBERSHIP_REQUEST_TEMPLATE,
                serviceContext).sendMessage();


        SessionMessages.add(request, "membershipRequestSent");
        response.sendRedirect(proposal.getProposalLinkUrl(contest) + "/tab/TEAM");
    }

    @RequestMapping(params = {"action=inviteMember"})
    public void invite(ActionRequest request, Model model,
                       ActionResponse response, @Valid RequestMembershipInviteBean requestMembershipInviteBean, BindingResult result)
            throws PortalException, SystemException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
                WebKeys.THEME_DISPLAY);

        String input = requestMembershipInviteBean.getInviteRecipient();
        if (input == null || input.equals("")) {
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/TEAM");
            return;
        }

        String[] inputParts = input.split(" ");

        if (inputParts.length > 0) {
            String screenName = inputParts[0];
            try {
                Member recipient = MembersClient.findMemberByScreenName(screenName);

                if (recipient != null) {
                    final Proposal proposal = proposalsContext.getProposal(request);
                    final long proposalId = proposal.getProposalId();
                    final Contest contest = proposalsContext.getContest(request);

                    String comment = HtmlUtil
                            .cleanAll(requestMembershipInviteBean.getInviteComment());

                    if (Validator.isNull(comment)) {
                        comment = "No message specified";
                    }
                    MembershipRequest memberRequest = MembershipRequestClient
                            .addInvitedMembershipRequest(proposalId, recipient.getUserId(),
                                    comment);

                    ServiceContext serviceContext = new ServiceContext();
                    serviceContext.setPortalURL(themeDisplay.getPortalURL());
                    final Member sender = proposalsContext.getMember(request);


                    new ProposalMembershipInviteNotification(proposal, contest, sender,
                            recipient,
                            memberRequest, comment, serviceContext).sendMessage();


                    SessionMessages.add(request, "memberInviteSent");
                }
            } catch (MemberNotFoundException e) {
                throw new IllegalArgumentException("Member with screen name not found: " + screenName);
            }
        } else {
            SessionErrors.add(request, "memberInviteRecipientError");
        }


        response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/TEAM");
    }

    @ResourceMapping("inviteMembers-validateRecipient")
    public void validateRecipient(ResourceRequest request, ResourceResponse response)
            throws PortalException, SystemException {
        String input = request.getParameter("term");

        List<User> recipients = getRecipientSuggestions(input, proposalsContext.getProposal(request).getProposalId());
        JSONArray responseJson = JSONFactoryUtil.createJSONArray();
        for (User user : recipients) {
            responseJson.put(String.format("%s (%s %s)", user.getScreenName(), user.getFirstName(), user.getLastName()));
        }
        try {
            response.getPortletOutputStream().write(responseJson.toString().getBytes());
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }

    @RequestMapping(params = {"action=replyToMembershipRequest"})
    public void respond(ActionRequest request, Model model, ActionResponse response,
                        @RequestParam("approve") String approve,
                        @RequestParam("comment") String comment,
                        @RequestParam("requestId") long requestId)
            throws PortalException, SystemException, IOException {
        if (PortalUtil.getUser(request) == null) {
            return;
        }

        long userId = PortalUtil.getUser(request).getUserId();
        long proposalId = proposalsContext.getProposal(request).getProposalId();

        MembershipRequest membershipRequest = null;
        for (MembershipRequest mr : MembershipRequestClient.getMembershipRequests(proposalId)) {
            if (mr.getMembershipRequestId() == requestId) {
                membershipRequest = mr;
            }
        }

        if (membershipRequest == null) {
            return;
        }
        comment = HtmlUtil.cleanAll(comment);
        if (comment == null || comment.equalsIgnoreCase("Optional response")) {
            comment = "no comments";
        }
        if (approve.equalsIgnoreCase("APPROVE")) {
            MembershipRequestClient.approveMembershipRequest(proposalId, membershipRequest.getUserId(), membershipRequest, comment, userId);
            sendMessage(proposalsContext.getUser(request).getUserId(), membershipRequest.getUserId(), MSG_MEMBERSHIP_RESPONSE_SUBJECT, MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED + comment);
        } else if (approve.equalsIgnoreCase("DENY")) {
            MembershipRequestClient.denyMembershipRequest(proposalId, membershipRequest.getUserId(), requestId, comment, userId);
            sendMessage(proposalsContext.getUser(request).getUserId(), membershipRequest.getUserId(), MSG_MEMBERSHIP_RESPONSE_SUBJECT, MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED + comment);
        }
        response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/ADMIN");
    }

    private void sendMessage(long sender, long recipient, String subject, String content) {
        List<Long> recipients = new ArrayList<>();
        recipients.add(recipient);

        MessagingClient.sendMessage(subject, content, sender, sender, recipients);
    }

    private List<User> getRecipientSuggestions(String input, long proposalId) throws PortalException, SystemException {
        String[] inputParts = input.split(" ");
        if (inputParts.length == 0) {
            return new ArrayList<>();
        }

        List<Long> contributorIds = new ArrayList<>();
        for (Member contributor : ProposalsClient.getProposalMembers(proposalId)) {
            contributorIds.add(contributor.getUserId());
        }

        List<User> recipients = new ArrayList<>();

        Criterion criterion = RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.in("userId", contributorIds));
        // For the sake of performance we only search the first word for either screenname, firstname or last name match
        // Search by screen name
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
        query.add(RestrictionsFactoryUtil.ilike("screenName", String.format("%s%%", inputParts[0])));
        query.add(criterion);
        query.setLimit(0, 5);
        List<User> result = UserLocalServiceUtil.dynamicQuery(query);

        if (!result.isEmpty()) {
            recipients.addAll(result);
        }

        // Search by last name
        DynamicQuery lnQuery = DynamicQueryFactoryUtil.forClass(User.class);
        lnQuery.add(RestrictionsFactoryUtil.ilike("lastName", String.format("%s%%", inputParts[0])));
        lnQuery.add(criterion);
        lnQuery.setLimit(0, 5);
        List<User> lnRecipients = UserLocalServiceUtil.dynamicQuery(lnQuery);

        for (User user : lnRecipients) {
            if (!recipients.contains(user)) {
                recipients.add(user);
            }
        }

        return recipients;
    }

}
