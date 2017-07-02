package org.xcolab.view.pages.proposals.view.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.admin.pojo.ContestType;
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
import org.xcolab.client.proposals.pojo.team.MembershipRequest;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.view.pages.loginregister.SharedColabUtil;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

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
            @RequestParam long contestId, @RequestParam("do") String action)
            throws IOException {

        Contest contest = ContestClientUtil.getContest(contestId);
        MembershipClient membershipClient;
        ProposalClient proposalClient;
        ProposalAttributeClient proposalAttributeClient;
        if (contest.getIsSharedContestInForeignColab()) {
            RestService proposalService = new RefreshingRestService(CoLabService.CONTEST,
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );

            proposalClient = ProposalClient.fromService(proposalService);
            membershipClient = MembershipClient.fromService(proposalService);
            proposalAttributeClient = ProposalAttributeClient.fromService(proposalService);
        } else {
            membershipClient = MembershipClientUtil.getClient();
            proposalClient = ProposalClientUtil.getClient();
            proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        }

        MembershipRequest membershipRequest = membershipClient.getMembershipRequest(requestId);

        List<Long> recipients = new ArrayList<>();
        List<Member> contributors = proposalClient.getProposalMembers(proposalId);

        for (Member user : contributors) {
            recipients.add(user.getUserId());
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
                membershipClient
                        .approveMembershipRequest(proposalId, membershipRequest.getUserId(),
                                membershipRequest, "The invitation was accepted.",
                                invitee.getUserId());
                final String membershipAcceptedMessage = TemplateReplacementUtil
                        .replaceContestTypeStrings(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED,
                                contestType);
                //TODO: turn into template
                sendMessage(invitee.getUserId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
                        String.format(membershipAcceptedMessage, invitee.getFullName(),
                                proposalLink));

                if (contest.getIsSharedContest()) {
                    SharedColabUtil.registerMemberInSharedColab(invitee.getId_());
                }
                AlertMessage.success("You are now a contributor of this " + contestType.getProposalName()
                        + "!").flash(request);

            } else if (action.equalsIgnoreCase("DECLINE")) {
                membershipClient
                        .denyMembershipRequest(proposalId, membershipRequest.getUserId(),
                                requestId, "The invitation was rejected.", invitee.getUserId());
                final String membershipRejectedMessage = TemplateReplacementUtil
                        .replaceContestTypeStrings(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED,
                                contestType);
                //TODO: turn into template
                sendMessage(invitee.getUserId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
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
        MessagingClient.sendMessage(subject, content, sender, sender, recipients);
    }
}
