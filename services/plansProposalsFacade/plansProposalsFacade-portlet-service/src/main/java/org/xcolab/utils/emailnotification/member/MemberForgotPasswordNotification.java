package org.xcolab.utils.emailnotification.member;

import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.utils.emailnotification.basic.MemberNotification;

public class MemberForgotPasswordNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_RESET_PASSWORD_DEFAULT";

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";
    private static final String SENDER_LASTNAME_PLACEHOLDER = "sender-lastname";
    private static final String SENDER_SCREENNAME_PLACEHOLDER = "sender-screenname";
    private static final String SENDER_IP_PLACEHOLDER = "sender-ip";
    private static final String PASSWORD_RESET_LINK_PLACEHOLDER = "password-reset-link";
    private static final String SYSTEM_LINK_PLACEHOLDER = "system-link";

    private String memberIp;

    private String passwordResetLink;

    public MemberForgotPasswordNotification(String memberIp, String passwordResetLink, User recipient, ServiceContext serviceContext)
            throws SystemException, PortalException {
        super(recipient, TEMPLATE_NAME, serviceContext);
        this.memberIp = memberIp;
        this.passwordResetLink = passwordResetLink;
    }

    @Override
    protected EmailNotificationTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final ContestEmailTemplate emailTemplate =
                ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(templateName);
        if (emailTemplate == null) {
            throw new SystemException(
                    "Could not load template \"" + templateName + "\" for " + this.getClass().getName());
        }
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
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case SENDER_NAME_PLACEHOLDER:
                    return new TextNode(recipient.getFirstName(), "");
                case SENDER_LASTNAME_PLACEHOLDER:
                    return new TextNode(recipient.getLastName(), "");
                case SENDER_SCREENNAME_PLACEHOLDER:
                    return new TextNode(recipient.getScreenName(), "");
                case SENDER_IP_PLACEHOLDER:
                    return new TextNode(memberIp, "");
                case PASSWORD_RESET_LINK_PLACEHOLDER:
                    return parseXmlNode(getPasswordLink());
                case SYSTEM_LINK_PLACEHOLDER:
                    return new TextNode(ConfigurationAttributeKey.COLAB_URL.getStringValue(), "");
                default:
            }
            return null;
        }
    }
}
