package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.ProposalTeamMembershipRequest;
import org.xcolab.entity.utils.notifications.proposal.ProposalMembershipInviteNotification;
import org.xcolab.entity.utils.notifications.proposal.ProposalUserActionNotification;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.RequestMembershipBean;
import org.xcolab.view.pages.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.commons.servlet.flash.AlertMessage;

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

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/requestMembership")
    public void requestMembership(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member sender,
            @Valid RequestMembershipBean requestMembershipBean, BindingResult result,
            @RequestParam("requestComment") String comment) throws IOException {

        if (result.hasErrors()) {
            //-- response.setRenderParameter("error", "true");
            //-- response.setRenderParameter("action", "requestMembership");
            return;
        }

        final Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();

        final String tabUrl = proposal.getProposalLinkUrl(contest) + "/tab/TEAM";

        if (sender == null) {
            AlertMessage.danger("You must be logged in to send a membership request")
                    .flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        final Member proposalAuthor = MembersClient.getMemberUnchecked(proposal.getauthorUserId());

        final ClientHelper clients = proposalContext.getClients();
        final MembershipClient membershipClient = clients.getMembershipClient();
        membershipClient.addRequestedMembershipRequest(proposal.getId(), sender.getId(),
                comment);

        new ProposalUserActionNotification(proposal, contest, sender, proposalAuthor,
                MEMBERSHIP_REQUEST_TEMPLATE, PlatformAttributeKey.COLAB_URL.get()).sendMessage();


        AlertMessage.success("A membership request has been sent!").flash(request);
        response.sendRedirect(tabUrl);
    }


    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/TEAM/inviteMember")
    public void invite(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member sender,
            @Valid RequestMembershipInviteBean requestMembershipInviteBean, BindingResult result)
            throws IOException {

        final Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();
        final String tabUrl = proposal.getProposalLinkUrl(contest) + "/tab/TEAM";

        String input = requestMembershipInviteBean.getInviteRecipient();
        if (StringUtils.isBlank(input)) {
            AlertMessage.danger("Please specify a member to invite.").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        final ClientHelper clients = proposalContext.getClients();
        final MembershipClient membershipClient = clients.getMembershipClient();

        String[] inputParts = input.split(" ");
        String screenName = inputParts[0];
        try {
            Member recipient = MembersClient.findMemberByScreenName(screenName);
            String comment = HtmlUtil.cleanAll(requestMembershipInviteBean.getInviteComment());

            if (StringUtils.isBlank(comment)) {
                comment = "No message specified";
            }
            ProposalTeamMembershipRequest memberRequest = membershipClient
                    .addInvitedMembershipRequest(proposal.getId(), recipient.getId(),
                            comment);

            new ProposalMembershipInviteNotification(proposal, contest, sender, recipient,
                    memberRequest, comment).sendMessage();
        } catch (MemberNotFoundException e) {
            AlertMessage.danger("Member " + screenName + " could not be found.").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        AlertMessage.success("The member has been invited to join this proposal's team!")
                .flash(request);
        response.sendRedirect(tabUrl);
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADMIN/replyToMembershipRequest")
    public void respond(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member loggedInMember, @RequestParam String approve,
            @RequestParam String comment, @RequestParam long requestId) throws IOException {

        final Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();
        final String tabUrl = proposal.getProposalLinkUrl(contest) + "/tab/ADMIN";

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanAdminProposal()) {
            AlertMessage.danger("Access denied!").flash(request);
            response.sendRedirect(tabUrl);
            return;
        }

        final ClientHelper clients = proposalContext.getClients();
        final MembershipClient membershipClient = clients.getMembershipClient();

        long proposalId = proposal.getId();

        ProposalTeamMembershipRequest membershipRequest = null;
        for (ProposalTeamMembershipRequest mr : membershipClient.getMembershipRequests(proposalId)) {
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
                    .approveMembershipRequest(proposalId, recipientId, membershipRequest, comment,
                            senderId);
            sendMessage(senderId, recipientId, MSG_MEMBERSHIP_RESPONSE_SUBJECT,
                    MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED + comment);
            AlertMessage.success("The membership request has been APPROVED!").flash(request);

        } else if (approve.equalsIgnoreCase("DENY")) {
            membershipClient
                    .denyMembershipRequest(proposalId, recipientId, requestId, comment, senderId);
            sendMessage(senderId, recipientId, MSG_MEMBERSHIP_RESPONSE_SUBJECT,
                    MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED + comment);
            AlertMessage.warning("The membership request has been DENIED!").flash(request);
        }
        response.sendRedirect(tabUrl);
    }

    private void sendMessage(long sender, long recipient, String subject, String content) {
        List<Long> recipients = new ArrayList<>();
        recipients.add(recipient);
        MessagingClient.sendMessage(subject, content, sender, null, recipients);
    }

}
