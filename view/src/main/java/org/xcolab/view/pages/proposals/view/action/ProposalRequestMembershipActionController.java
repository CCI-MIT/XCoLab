package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.exceptions.ConflictException;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.user.IMessagingClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.notifications.proposal.ProposalMembershipInviteNotification;
import org.xcolab.entity.utils.notifications.proposal.ProposalUserActionNotification;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.RequestMembershipBean;
import org.xcolab.view.pages.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalRequestMembershipActionController {

    private static final String MEMBERSHIP_REQUEST_TEMPLATE = "PROPOSAL_MEMBERSHIP_REQUEST_DEFAULT";

    private static final String MSG_MEMBERSHIP_RESPONSE_SUBJECT =
            "Response to your membership request";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED =
            "Your request has been accepted <br />Comments: ";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED =
            "Your request has been rejected <br />Comments: ";

    @Autowired
    private IMembershipClient membershipClient;
    
    @Autowired
    private IUserClient userClient;
    
    @Autowired
    private IMessagingClient messagingClient;

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/requestMembership")
    public void requestMembership(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper sender,
            @Valid RequestMembershipBean requestMembershipBean, BindingResult result,
            @RequestParam("requestComment") String comment) throws IOException {

        if (result.hasErrors()) {
            //-- response.setRenderParameter("error", "true");
            //-- response.setRenderParameter("action", "requestMembership");
            return;
        }

        final ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();

        final String tabUrl = proposal.getProposalLinkUrl(contest) + "/tab/TEAM";

        if (sender == null) {
            AlertMessage.danger("You must be logged in to send a membership request")
                    .flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        final UserWrapper proposalAuthor = userClient.getMemberUnchecked(proposal.getAuthorUserId());

        final ClientHelper clients = proposalContext.getClients();

        if (membershipClient.hasUserRequestedMembership(proposal, sender.getId())) {
            AlertMessage.danger("You have already sent a membership request")
                    .flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        membershipClient.addRequestedMembershipRequest(proposal.getId(), sender.getId(),
                comment);

        new ProposalUserActionNotification(proposal, contest, sender, proposalAuthor,
                MEMBERSHIP_REQUEST_TEMPLATE, PlatformAttributeKey.COLAB_URL.get()).sendMessage();


        AlertMessage.success("A membership request has been sent!").flash(request);
        response.sendRedirect(tabUrl);
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/inviteMember")
    public void invite(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, UserWrapper sender,
            @Valid RequestMembershipInviteBean requestMembershipInviteBean, BindingResult result)
            throws IOException, ConflictException {

        final ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();
        final String tabUrl = proposal.getProposalLinkUrl(contest) + "/tab/TEAM";

        String input = requestMembershipInviteBean.getInviteRecipient();
        if (StringUtils.isBlank(input)) {
            AlertMessage.danger("Please specify a member to invite.").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        final ClientHelper clients = proposalContext.getClients();

        String[] inputParts = input.split(" ");
        String screenName = inputParts[0];
        UserWrapper recipient = null;
        try {
            recipient = userClient.findMemberByScreenName(screenName);
        } catch (MemberNotFoundException e) {
            AlertMessage.danger("Member " + screenName + " could not be found.").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        if (clients.getProposalClient().isUserInProposalTeam(proposal.getId(), recipient.getId())) {
            AlertMessage.danger("The member is already part of this proposal's team!")
                    .flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        if (membershipClient.hasUserRequestedMembership(proposal, recipient.getId())) {
            AlertMessage.danger("The member has already been invited to this proposal's team!")
                    .flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        if (requestMembershipInviteBean.isSkipInvitation()
                && proposalContext.getPermissions().getCanAdminAll()) {
            membershipClient.addUserToProposalTeam(recipient.getId(), proposal);
            AlertMessage.success("The member has been added to this proposal's team!")
                    .flash(request);
        } else {
            String comment = HtmlUtil.cleanAll(requestMembershipInviteBean.getInviteComment());

            if (StringUtils.isBlank(comment)) {
                comment = "No message specified";
            }
            membershipClient
                    .addInvitedMembershipRequest(proposal.getId(), recipient.getId(), comment);

            new ProposalMembershipInviteNotification(proposal, contest, sender, recipient, comment)
                    .sendMessage();

            AlertMessage.success("The member has been invited to join this proposal's team!")
                    .flash(request);
        }
        response.sendRedirect(tabUrl);
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/replyToMembershipRequest")
    public void respond(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, UserWrapper loggedInMember, @RequestParam String approve,
            @RequestParam String comment, @RequestParam long requestId)
            throws IOException, MembershipRequestNotFoundException, ConflictException {

        final ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();
        final String tabUrl = proposal.getProposalLinkUrl(contest) + "/tab/ADMIN";

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanAdminProposal()) {
            AlertMessage.danger("Access denied!").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        final ClientHelper clients = proposalContext.getClients();

        long proposalId = proposal.getId();

        ProposalTeamMembershipRequestWrapper membershipRequest = null;
        for (ProposalTeamMembershipRequestWrapper mr : membershipClient
                .getMembershipRequests(proposalId)) {
            if (mr.getId() == requestId) {
                membershipRequest = mr;
            }
        }

        if (membershipRequest == null) {
            AlertMessage.danger("No membership request to approve!").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }
        comment = HtmlUtil.cleanAll(comment);
        if (comment == null || comment.equalsIgnoreCase("Optional response")) {
            comment = "no comments";
        }
        final long senderId = loggedInMember.getId();
        final long recipientId = membershipRequest.getUserId();
        if (approve.equalsIgnoreCase("APPROVE")) {
            membershipClient
                    .approveMembershipRequest(proposal, recipientId, membershipRequest, comment,
                            senderId);
            sendMessage(senderId, recipientId, MSG_MEMBERSHIP_RESPONSE_SUBJECT,
                    MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED + comment);
            AlertMessage.success("The membership request has been APPROVED!").flash(request);

        } else if (approve.equalsIgnoreCase("DENY")) {
            membershipClient
                    .denyMembershipRequest(proposal, recipientId, requestId, comment, senderId);
            sendMessage(senderId, recipientId, MSG_MEMBERSHIP_RESPONSE_SUBJECT,
                    MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED + comment);
            AlertMessage.warning("The membership request has been DENIED!").flash(request);
        }
        response.sendRedirect(tabUrl);
    }

    private void sendMessage(long sender, long recipient, String subject, String content) {
        List<Long> recipients = new ArrayList<>();
        recipients.add(recipient);
        messagingClient.sendMessage(subject, content, sender, null, recipients);
    }
}
