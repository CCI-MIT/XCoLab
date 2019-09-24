package org.xcolab.entity.utils.notifications.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalUserActionNotification extends ProposalNotification {

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";
    private final UserWrapper sender;
    private final String templateName;
    private ProposalUserActionNotificationTemplate templateWrapper;

    public ProposalUserActionNotification(ProposalWrapper proposal, ContestWrapper contest, UserWrapper sender,
            UserWrapper recipient, String templateName, String baseUrl) {
        super(proposal, contest, recipient, null);
        this.sender = sender;
        this.templateName = templateName;
    }

    @Override
    protected boolean isEssentialTransactionMessage() {
        return true;
    }

    @Override
    protected ProposalUserActionNotificationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName =
                getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(templateName);
        templateWrapper = new ProposalUserActionNotificationTemplate(emailTemplate,
                proposalName, contest.getTitle());

        return templateWrapper;
    }

    protected class ProposalUserActionNotificationTemplate extends ProposalNotificationTemplate {

        public ProposalUserActionNotificationTemplate(IEmailTemplate template, String proposalName,
                String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case SENDER_NAME_PLACEHOLDER:
                    return new TextNode(sender.getFullName());
                default:
            }
            return null;
        }
    }
}
