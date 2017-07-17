package org.xcolab.entity.utils.notifications.member;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.notifications.basic.MemberNotification;

public class MemberForgotPasswordNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_RESET_PASSWORD_DEFAULT";

    private static final String SENDER_IP_PLACEHOLDER = "sender-ip";
    private static final String PASSWORD_RESET_LINK_PLACEHOLDER = "password-reset-link";
    private static final String SYSTEM_LINK_PLACEHOLDER = "system-link";

    private final String memberIp;

    private final String passwordResetLink;

    public MemberForgotPasswordNotification(String memberIp, String passwordResetLink,
            Member recipient) {
        super(recipient, TEMPLATE_NAME);
        this.memberIp = memberIp;
        this.passwordResetLink = passwordResetLink;
    }

    @Override
    protected boolean isEssentialTransactionMessage() {
        return true;
    }

    @Override
    protected EmailNotificationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final ContestEmailTemplate emailTemplate =
                EmailTemplateClientUtil.getContestEmailTemplateByType(templateName);
        templateWrapper = new MemberForgotPasswordTemplate(emailTemplate, "", "");

        return templateWrapper;
    }

    private String getPasswordLink() {

        return String.format(LINK_FORMAT_STRING, passwordResetLink , "here");
    }

    protected class MemberForgotPasswordTemplate extends EmailNotificationTemplate {

        public MemberForgotPasswordTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case SENDER_IP_PLACEHOLDER:
                    return new TextNode(memberIp, "");
                case PASSWORD_RESET_LINK_PLACEHOLDER:
                    return parseXmlNode(getPasswordLink());
                case SYSTEM_LINK_PLACEHOLDER:
                    return new TextNode(PlatformAttributeKey.PLATFORM_COLAB_URL.get(), "");
                default:
            }
            return null;
        }
    }
}
