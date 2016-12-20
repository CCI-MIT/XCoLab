package org.xcolab.hooks.climatecolab.membership;

import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;
import org.xcolab.entity.utils.TemplateReplacementUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProposalMembershipInvitationResponseFilter implements Filter {

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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //no initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        execute((HttpServletRequest) request, (HttpServletResponse) response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        long membershipId = parseLongParam(request.getParameter("requestId"));
        long proposalId = parseLongParam(request.getParameter("proposalId"));

        String action = request.getParameter("do");

        MembershipRequest membershipRequest =
                MembershipClientUtil.getMembershipRequest(membershipId);

        List<Long> recipients = new ArrayList<>();
        List<Member> contributors = ProposalClientUtil.getProposalMembers(proposalId);

        for (Member user : contributors) {
            recipients.add(user.getUserId());
        }

        //		TODO: get right client
        Proposal proposal = ProposalClientUtil.getProposal(proposalId);
        ContestType contestType = proposal.getContest().getContestType();

        String proposalName = ProposalAttributeClientUtil
                .getProposalAttribute(proposalId, ProposalAttributeKeys.NAME, 0L).getStringValue();
        String proposalLink = String.format("<a href='%s'>%s</a>",
                proposal.getProposalLinkUrl(proposal.getContest()), proposalName);

        if (membershipRequest != null) {
            Member invitee = MembersClient.getMemberUnchecked(membershipRequest.getUserId());
            if (action.equalsIgnoreCase("ACCEPT")) {
                MembershipClientUtil
                        .approveMembershipRequest(proposalId, membershipRequest.getUserId(),
                                membershipRequest, "The invitation was accepted.",
                                invitee.getUserId());
                final String membershipAcceptedMessage = TemplateReplacementUtil
                        .replaceContestTypeStrings(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_ACCEPTED,
                                contestType);
                sendMessage(invitee.getUserId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
                        String.format(membershipAcceptedMessage, invitee.getFullName(),
                                proposalLink));
            } else if (action.equalsIgnoreCase("DECLINE")) {
                MembershipClientUtil
                        .denyMembershipRequest(proposalId, membershipRequest.getUserId(),
                                membershipId, "The invitation was rejected.", invitee.getUserId());
                final String membershipRejectedMessage = TemplateReplacementUtil
                        .replaceContestTypeStrings(MSG_MEMBERSHIP_INVITE_RESPONSE_CONTENT_REJECTED,
                                contestType);
                sendMessage(invitee.getUserId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
                        String.format(membershipRejectedMessage, invitee.getFullName(),
                                proposalLink));
            }
        }

        response.sendRedirect(proposal.getProposalLinkUrl(proposal.getContest()));
    }

    private static long parseLongParam(String value) {
        return value == null ? 0L : Long.parseLong(value.trim());
    }

    private void sendMessage(long sender, List<Long> recipients, String subject, String content) {
        MessagingClient.sendMessage(subject, content, sender, sender, recipients);
    }

    @Override
    public void destroy() {
        //no destroy method
    }
}
