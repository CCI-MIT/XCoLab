package org.xcolab.entity.utils.notifications.basic;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public class MemberNotification extends EmailNotification {

    protected final String templateName;
    protected final UserWrapper recipient;
    protected EmailNotificationTemplate templateWrapper;

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";
    private static final String SENDER_LASTNAME_PLACEHOLDER = "sender-lastname";
    private static final String SENDER_SCREENNAME_PLACEHOLDER = "sender-screenname";

    public MemberNotification(UserWrapper recipient, String templateName) {
        this.recipient = recipient;
        this.templateName = templateName;
    }

    @Override
    protected UserWrapper getRecipient() {
        return recipient;
    }

    @Override
    protected EmailNotificationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(templateName);
        templateWrapper = new MemberNotificationTemplate(emailTemplate, "", "");

        return templateWrapper;
    }

    @Override
    protected Long getReferenceId(){
        return this.recipient.getId();
    }

    protected class MemberNotificationTemplate extends EmailNotificationTemplate {

        public MemberNotificationTemplate(IEmailTemplate template, String proposalName, String contestName) {
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
                    return new TextNode(recipient.getFirstName());
                case SENDER_LASTNAME_PLACEHOLDER:
                    return new TextNode(recipient.getLastName());
                case SENDER_SCREENNAME_PLACEHOLDER:
                    return new TextNode(recipient.getScreenName());
                default:
            }
            return null;
        }
    }
}
