package org.xcolab.view.pages.proposals.view.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.ProposalTeamMembershipRequest;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.TemplateReplacementUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MembershipInvitationResponseController {

    private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT =
            "Response to your membership invite";

    /**
     * The response message when the proposal membership invitee accepts the invitation
     */
    private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED =
            "Your invitation of %s to join the <proposal/> %s has been accepted.";

    /**
     * The response message when the proposal membership invitee rejects the invitation
     */
    private static final String MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED =
            "Your invitation of %s to join the <proposal/> %s has been rejected.";


    @GetMapping("/membershipRequests/reply")
    private void execute(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long requestId, @RequestParam long proposalId,
            @RequestParam long contestId, @RequestParam("do") String action) throws IOException {

        Contest contest = ContestClientUtil.getContest(contestId);
        MembershipClient membershipClient = MembershipClientUtil.getClient();
        ProposalClient proposalClient = ProposalClientUtil.getClient();
        ProposalAttributeClient proposalAttributeClient = ProposalAttributeClientUtil.getClient();

        ProposalTeamMembershipRequest membershipRequest = membershipClient.getMembershipRequest(requestId);

        List<Long> recipients = new ArrayList<>();
        List<Member> contributors = proposalClient.getProposalMembers(proposalId);

        for (Member user : contributors) {
            recipients.add(user.getId());
        }

        Proposal proposal = proposalClient.getProposal(proposalId);
        ContestType contestType = proposal.getContest().getContestType();

        String proposalName = proposalAttributeClient
                .getProposalAttribute(proposalId, ProposalAttributeKeys.NAME, 0L).getStringValue();
        String proposalLink = String.format("<a href='%s'>%s</a>",
                proposal.getProposalLinkUrl(proposal.getContest()), proposalName);

        if (membershipRequest != null) {
            Member invitee = MembersClient.getMemberUnchecked(membershipRequest.getUserId());

            if (action.equalsIgnoreCase("ACCEPT")) {
                membershipClient.approveMembershipRequest(proposalId, membershipRequest.getUserId(),
                        membershipRequest, "The invitation was accepted.", invitee.getId());
                final String membershipAcceptedMessage = TemplateReplacementUtil
                        .replaceContestTypeStrings(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED,
                                contestType);
                sendMessage(invitee.getId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
                        String.format(membershipAcceptedMessage, invitee.getFullName(),
                                proposalLink));
                AlertMessage.success(
                        "You are now a contributor of this " + contestType.getProposalName() + "!")
                        .flash(request);

            } else if (action.equalsIgnoreCase("DECLINE")) {
                membershipClient
                        .denyMembershipRequest(proposalId, membershipRequest.getUserId(), requestId,
                                "The invitation was rejected.", invitee.getId());
                final String membershipRejectedMessage = TemplateReplacementUtil
                        .replaceContestTypeStrings(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED,
                                contestType);
                sendMessage(invitee.getId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
                        String.format(membershipRejectedMessage, invitee.getFullName(),
                                proposalLink));
                AlertMessage.warning("Membership request DECLINED!").flash(request);
            }
        } else {
            AlertMessage.danger("Membership request not found!").flash(request);
        }

        response.sendRedirect(proposal.getProposalLinkUrl(proposal.getContest()));
    }

    private void sendMessage(long sender, List<Long> recipients, String subject, String content) {
        MessagingClient.sendMessage(subject, content, sender, null, recipients);
    }
}
