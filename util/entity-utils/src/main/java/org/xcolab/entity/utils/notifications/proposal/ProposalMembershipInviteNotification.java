package org.xcolab.entity.utils.notifications.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;

public class ProposalMembershipInviteNotification extends ProposalUserActionNotification {

    private static final String DEFAULT_TEMPLATE_NAME = "PROPOSAL_MEMBERSHIP_INVITE_DEFAULT";

    private static final String MESSAGE_PLACEHOLDER = "message";
    private final String message;
    private ProposalMembershipRequestTemplate templateWrapper;

    public ProposalMembershipInviteNotification(ProposalWrapper proposal, ContestWrapper contest, UserWrapper sender,
            UserWrapper invitee, String message) {
        super(proposal, contest, sender, invitee, null,
                PlatformAttributeKey.COLAB_URL.get());
        this.message = message;
    }

    @Override
    protected ProposalMembershipRequestTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = getProposalAttributeHelper()
                .getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(DEFAULT_TEMPLATE_NAME);

        templateWrapper = new ProposalMembershipRequestTemplate(emailTemplate,
                proposalName, contest.getTitle());

        return templateWrapper;
    }

    private class ProposalMembershipRequestTemplate extends ProposalUserActionNotificationTemplate {

        public ProposalMembershipRequestTemplate(IEmailTemplate template,
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
                    return new TextNode(message);
                default:
            }
            return null;
        }
    }
}
