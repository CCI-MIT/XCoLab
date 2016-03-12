package org.xcolab.utils.emailnotification.proposal;

import com.ext.portlet.NoSuchConfigurationAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class ProposalMembershipInviteNotification extends ProposalUserActionNotification {

    private static final String DEFAULT_TEMPLATE_NAME = "PROPOSAL_MEMBERSHIP_INVITE_DEFAULT";

    private static final String MEMBERSHIP_INVITE_STRUTS_ACTION_URL = "/c/portal/proposal_invite_response";

    private static final String MESSAGE_PLACEHOLDER = "message";
    private static final String ACCEPT_LINK_PLACEHOLDER = "accept-link";
    private static final String DECLINE_LINK_PLACEHOLDER = "decline-link";
    private final MembershipRequest membershipRequest;
    private final String message;
    private ProposalMembershipRequestTemplate templateWrapper;

    public ProposalMembershipInviteNotification(Proposal proposal, Contest contest, User sender, User invitee,
            MembershipRequest membershipRequest, String message, ServiceContext serviceContext) {
        super(proposal, contest, sender, invitee, null, serviceContext);
        this.membershipRequest = membershipRequest;
        this.message = message;
    }

    @Override
    protected ProposalMembershipRequestTemplate getTemplateWrapper() throws SystemException, PortalException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = getProposalAttributeHelper()
                .getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil
                .getEmailTemplateByType(DEFAULT_TEMPLATE_NAME);
        if (emailTemplate == null) {
            throw new SystemException(
                    "Could not load template \"" + DEFAULT_TEMPLATE_NAME + "\" for " + this.getClass().getName());
        }
        templateWrapper = new ProposalMembershipRequestTemplate(emailTemplate,
                proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    private String getAcceptLink(String text) throws SystemException, NoSuchConfigurationAttributeException {
        final String acceptUrl = HttpUtil.addParameter(getMembershipResponseUrl(), "do", "accept");
        return String.format(LINK_FORMAT_STRING, acceptUrl, text);
    }

    private String getMembershipResponseUrl() throws SystemException, NoSuchConfigurationAttributeException {
        String baseUrl = serviceContext.getPortalURL() + MEMBERSHIP_INVITE_STRUTS_ACTION_URL;
        baseUrl = HttpUtil.addParameter(baseUrl, "contestId", contest.getContestPK());
        baseUrl = HttpUtil.addParameter(baseUrl, "requestId", membershipRequest.getMembershipRequestId());
        baseUrl = HttpUtil.addParameter(baseUrl, "proposalId", proposal.getProposalId());
        return baseUrl;
    }

    private String getDeclineLink(String text) throws NoSuchConfigurationAttributeException, SystemException {
        final String declineUrl = HttpUtil.addParameter(getMembershipResponseUrl(), "do", "decline");
        return String.format(LINK_FORMAT_STRING, declineUrl, text);
    }

    private class ProposalMembershipRequestTemplate extends ProposalUserActionNotificationTemplate {

        public ProposalMembershipRequestTemplate(ContestEmailTemplate template,
                String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
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

