package org.xcolab.entity.utils.notifications.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;

public class ProposalMembershipInviteNotification extends ProposalUserActionNotification {

    private static final String DEFAULT_TEMPLATE_NAME = "PROPOSAL_MEMBERSHIP_INVITE_DEFAULT";

    private static final String MEMBERSHIP_INVITE_RESPONSE_URL = "/membershipRequests/reply";

    private static final String MESSAGE_PLACEHOLDER = "message";
    private static final String ACCEPT_LINK_PLACEHOLDER = "accept-link";
    private static final String DECLINE_LINK_PLACEHOLDER = "decline-link";
    private final MembershipRequest membershipRequest;
    private final String message;
    private ProposalMembershipRequestTemplate templateWrapper;

    public ProposalMembershipInviteNotification(Proposal proposal, Contest contest, Member sender,
            Member invitee, MembershipRequest membershipRequest, String message) {
        super(proposal, contest, sender, invitee, null,
                PlatformAttributeKey.COLAB_URL.get());
        this.membershipRequest = membershipRequest;
        this.message = message;
    }

    @Override
    protected ProposalMembershipRequestTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = getProposalAttributeHelper()
                .getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final ContestEmailTemplate emailTemplate =
                EmailTemplateClientUtil.getContestEmailTemplateByType(DEFAULT_TEMPLATE_NAME);

        templateWrapper = new ProposalMembershipRequestTemplate(emailTemplate,
                proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    private String getAcceptLink(String text) {
        final String acceptUrl = UriComponentsBuilder.fromHttpUrl(getMembershipResponseUrl())
                .queryParam( "do", "accept")
                .toUriString();
        return String.format(LINK_FORMAT_STRING, acceptUrl, text);
    }

    private String getMembershipResponseUrl() {
        return UriComponentsBuilder.fromHttpUrl(this.baseUrl + MEMBERSHIP_INVITE_RESPONSE_URL)
                .queryParam("contestId", contest.getContestPK())
                .queryParam("requestId", membershipRequest.getMembershipRequestId())
                .queryParam("proposalId", proposal.getProposalId())
                .toUriString();
    }

    private String getDeclineLink(String text) {
        final String declineUrl = UriComponentsBuilder.fromHttpUrl(getMembershipResponseUrl())
                .queryParam("do", "decline")
                .toUriString();
        return String.format(LINK_FORMAT_STRING, declineUrl, text);
    }

    private class ProposalMembershipRequestTemplate extends ProposalUserActionNotificationTemplate {

        public ProposalMembershipRequestTemplate(ContestEmailTemplate template,
                String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case MESSAGE_PLACEHOLDER:
                    return new TextNode(message, "");
                case ACCEPT_LINK_PLACEHOLDER:
                    return parseXmlNode(getAcceptLink(tag.ownText()));
                case DECLINE_LINK_PLACEHOLDER:
                    return parseXmlNode(getDeclineLink(tag.ownText()));
                default:
            }
            return null;
        }
    }
}

