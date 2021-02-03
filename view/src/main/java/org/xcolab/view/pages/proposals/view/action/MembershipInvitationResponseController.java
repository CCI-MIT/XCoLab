package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.ConflictException;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.user.IMessagingClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

    @Autowired
    private IContestClient contestClient;

    @Autowired
    private IMembershipClient membershipClient;

    @Autowired
    private IProposalAttributeClient proposalAttributeClient;

    @Autowired
    private IProposalClient proposalClient;
    
    @Autowired
    private IUserClient userClient;
    
    @Autowired
    private IMessagingClient messagingClient;


    @PostMapping("/membershipRequests/reply")
    private void execute(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long requestId, @RequestParam long proposalId,
            @RequestParam long contestId, @RequestParam String action)
            throws IOException, MembershipRequestNotFoundException, ConflictException {

        ContestWrapper contest = contestClient.getContest(contestId);
        ProposalTeamMembershipRequestWrapper membershipRequest =
                membershipClient.getMembershipRequest(requestId);

        List<Long> recipients = new ArrayList<>();
        List<UserWrapper> contributors = proposalClient.getProposalMembers(proposalId);

        for (UserWrapper user : contributors) {
            recipients.add(user.getId());
        }

        ProposalWrapper proposal = new ProposalWrapper(proposalClient.getProposal(proposalId));

        if(proposal == null) {
            System.out.println(" PROPOSAL NULL IN MembershipInvitationResponseController ");
        } else {
            if(proposal.getContest() == null) {
                System.out.println(" CONTEST NULL IN MembershipInvitationResponseController ");
            }
        }
        ContestType contestType = new ContestWrapper(proposal.getContest()).getContestType();

        String proposalName = proposalAttributeClient
                .getProposalAttribute(proposalId, ProposalAttributeKeys.NAME, 0L).getStringValue();
        String proposalLink = String.format("<a href='%s'>%s</a>",
                proposal.getProposalLinkUrl(proposal.getContest()), proposalName);

        if (membershipRequest != null) {
            UserWrapper invitee = userClient.getMemberUnchecked(membershipRequest.getUserId());

            if (action.equalsIgnoreCase("ACCEPT")) {
                membershipClient.approveMembershipRequest(proposal, membershipRequest.getUserId(),
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
                        .denyMembershipRequest(proposal, membershipRequest.getUserId(), requestId,
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
        messagingClient.sendMessage(subject, content, sender, null, recipients);
    }
}
