package org.xcolab.portlets.proposals.view.action;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;
import org.xcolab.entity.utils.email.notifications.proposal.ProposalMembershipInviteNotification;
import org.xcolab.entity.utils.email.notifications.proposal.ProposalUserActionNotification;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;
import org.xcolab.portlets.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class ProposalRequestMembershipActionController {

    private static final String MEMBERSHIP_REQUEST_TEMPLATE = "PROPOSAL_MEMBERSHIP_REQUEST_DEFAULT";

    private static final String MSG_MEMBERSHIP_RESPONSE_SUBJECT = "Response to your membership request";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED = "Your request has been accepted <br />Comments: ";
    private static final String MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED = "Your request has been rejected <br />Comments: ";

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=requestMembership"})
    public void show(ActionRequest request, Model model,
                     ActionResponse response, @Valid RequestMembershipBean requestMembershipBean,
            BindingResult result, @RequestParam("requestComment") String comment)
            throws IOException {


        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "requestMembership");
            return;
        }

        long memberId = MemberAuthUtil.getMemberId(request);
        if (memberId == 0) {
            return;
        }
        final Member sender = MembersClient.getMemberUnchecked(memberId);

        final Proposal proposal = proposalsContext.getProposal(request);
        final long proposalId = proposal.getProposalId();
        final Member proposalAuthor = MembersClient.getMemberUnchecked(proposal.getAuthorId());
        final Contest contest = proposalsContext.getContest(request);

        proposalsContext.getClients(request).getMembershipClient()
                .addRequestedMembershipRequest(proposalId, sender.getUserId(), comment);

        new ProposalUserActionNotification(proposal, contest, sender, proposalAuthor,
                MEMBERSHIP_REQUEST_TEMPLATE, ConfigurationAttributeKey.COLAB_URL.get()).sendMessage();

        SessionMessages.add(request, "membershipRequestSent");
        response.sendRedirect(proposal.getProposalLinkUrl(contest) + "/tab/TEAM");
    }

    @RequestMapping(params = {"action=inviteMember"})
    public void invite(ActionRequest request, Model model,
                       ActionResponse response, @Valid RequestMembershipInviteBean requestMembershipInviteBean, BindingResult result)
            throws IOException {


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

                    if (comment ==null) {
                        comment = "No message specified";
                    }
                    MembershipRequest memberRequest = proposalsContext.getClients(request).getMembershipClient()
                            .addInvitedMembershipRequest(proposalId, recipient.getUserId(),
                                    comment);

                    final Member sender = proposalsContext.getMember(request);

                    new ProposalMembershipInviteNotification(proposal, contest, sender,
                            recipient,
                            memberRequest, comment, ConfigurationAttributeKey.COLAB_URL.get()).sendMessage();

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
    public void validateRecipient(ResourceRequest request, ResourceResponse response) {
        String input = request.getParameter("term");

        List<Member> recipients = getRecipientSuggestions(input, proposalsContext.getProposal(request).getProposalId(),
                request);
        JSONArray responseJson = new JSONArray();
        for (Member user : recipients) {
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
                        @RequestParam("requestId") long requestId) throws IOException {
        if (MemberAuthUtil.getMemberOrNull(request) == null) {
            return;
        }

        long userId = MemberAuthUtil.getMemberId(request);
        long proposalId = proposalsContext.getProposal(request).getProposalId();

        MembershipRequest membershipRequest = null;
        for (MembershipRequest mr : ProposalsContextUtil.getClients(request).getMembershipClient().getMembershipRequests(proposalId)) {
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
            proposalsContext.getClients(request).getMembershipClient().approveMembershipRequest(proposalId, membershipRequest.getUserId(), membershipRequest, comment, userId);
            sendMessage(proposalsContext.getMember(request).getUserId(), membershipRequest.getUserId(), MSG_MEMBERSHIP_RESPONSE_SUBJECT, MSG_MEMBERSHIP_RESPONSE_CONTENT_ACCEPTED + comment);
        } else if (approve.equalsIgnoreCase("DENY")) {
            proposalsContext.getClients(request).getMembershipClient()
                    .denyMembershipRequest(proposalId, membershipRequest.getUserId(), requestId, comment, userId);
            sendMessage(proposalsContext.getMember(request).getUserId(), membershipRequest.getUserId(), MSG_MEMBERSHIP_RESPONSE_SUBJECT, MSG_MEMBERSHIP_RESPONSE_CONTENT_REJECTED + comment);
        }
        response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/ADMIN");
    }

    private void sendMessage(long sender, long recipient, String subject, String content) {
        List<Long> recipients = new ArrayList<>();
        recipients.add(recipient);

        MessagingClient.sendMessage(subject, content, sender, sender, recipients);
    }

    private List<Member> getRecipientSuggestions(String input, long proposalId,
            PortletRequest request) {
        String[] inputParts = input.split(" ");
        if (inputParts.length == 0) {
            return new ArrayList<>();
        }
        List<Long> contributorIds = new ArrayList<>();
        for (Member contributor : proposalsContext.getClients(request).getProposalClient().getProposalMembers(proposalId)) {
            contributorIds.add(contributor.getUserId());
        }
        List<Member> recipients = MembersClient.listMembers(null,inputParts[0],inputParts[0],null,true,0,Integer.MAX_VALUE);
        return recipients;
    }

}
