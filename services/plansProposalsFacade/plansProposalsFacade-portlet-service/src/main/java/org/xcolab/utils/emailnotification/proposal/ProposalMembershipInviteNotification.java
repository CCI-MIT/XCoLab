package org.xcolab.utils.emailnotification.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import com.liferay.portal.kernel.util.HttpUtil;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;

public class ProposalMembershipInviteNotification extends ProposalUserActionNotification {

    private static final String DEFAULT_TEMPLATE_NAME = "PROPOSAL_MEMBERSHIP_INVITE_DEFAULT";

    private static final String MEMBERSHIP_INVITE_STRUTS_ACTION_URL = "/c/portal/proposal_invite_response";

    private static final String MESSAGE_PLACEHOLDER = "message";
    private static final String ACCEPT_LINK_PLACEHOLDER = "accept-link";
    private static final String DECLINE_LINK_PLACEHOLDER = "decline-link";
    private final MembershipRequest membershipRequest;
    private final String message;
    private ProposalMembershipRequestTemplate templateWrapper;

    public ProposalMembershipInviteNotification(Proposal proposal, Contest contest, Member sender,
            Member invitee, MembershipRequest membershipRequest, String message, String baseUrl) {
        super(proposal, contest, sender, invitee, null, baseUrl);
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
        final String acceptUrl = HttpUtil.addParameter(getMembershipResponseUrl(), "do", "accept");
        return String.format(LINK_FORMAT_STRING, acceptUrl, text);
    }

    private String getMembershipResponseUrl() {
        String baseUrl = this.baseUrl + MEMBERSHIP_INVITE_STRUTS_ACTION_URL;
        baseUrl = HttpUtil.addParameter(baseUrl, "contestId", contest.getContestPK());
        baseUrl = HttpUtil.addParameter(baseUrl, "requestId", membershipRequest.getMembershipRequestId());
        baseUrl = HttpUtil.addParameter(baseUrl, "proposalId", proposal.getProposalId());
        return baseUrl;
    }

    private String getDeclineLink(String text) {
        final String declineUrl = HttpUtil.addParameter(getMembershipResponseUrl(), "do", "decline");
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

