package org.xcolab.view.filters.membership;

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
        long contestId = parseLongParam(request.getParameter("contestId"));
        String action = request.getParameter("do");


        Contest c = ContestClientUtil.getContest(contestId);
        MembershipClient membershipClient;
        ProposalClient proposalClient;
        ProposalAttributeClient proposalAttributeClient;
        if(c.getIsSharedContestInForeignColab()){
            RestService proposalService = new RefreshingRestService(CoLabService.PROPOSAL,
                    ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                    ConfigurationAttributeKey.PARTNER_COLAB_PORT);

            proposalClient = ProposalClient.fromService(proposalService);
            membershipClient = MembershipClient.fromService(proposalService);
            proposalAttributeClient = ProposalAttributeClient.fromService(proposalService);
        }else{
            membershipClient = MembershipClientUtil.getClient();
            proposalClient = ProposalClientUtil.getClient();
            proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        }

        MembershipRequest membershipRequest = membershipClient.getMembershipRequest(membershipId);

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
                sendMessage(invitee.getUserId(), recipients, MSG_MEMBERSHIP_INVITE_RESPONSE_SUBJECT,
                        String.format(membershipAcceptedMessage, invitee.getFullName(),
                                proposalLink));

                if(c.getIsSharedContest()) {
                    LoginRegisterUtil.registerMemberInSharedColab(invitee.getId_());
                }
            } else if (action.equalsIgnoreCase("DECLINE")) {
                membershipClient
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
