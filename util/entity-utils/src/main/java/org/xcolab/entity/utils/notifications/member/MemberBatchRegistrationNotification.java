package org.xcolab.entity.utils.notifications.member;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.EmailTemplate;
import org.xcolab.client.members.pojo.LoginToken;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.notifications.basic.MemberNotification;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MemberBatchRegistrationNotification extends MemberNotification {

    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
    private static final String TEMPLATE_NAME = "MEMBER_BATCH_REGISTERED_DEFAULT";

    private static final String LOGIN_LINK_PLACEHOLDER = "login-link";
    private static final String LOGIN_LINK_EXPIRATION_PLACEHOLDER = "link-expiration";

    private final LoginToken loginToken;

    public MemberBatchRegistrationNotification(Member recipient, LoginToken loginToken) {
        super(recipient, TEMPLATE_NAME);
        this.loginToken = loginToken;
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

        final EmailTemplate emailTemplate =
                EmailTemplateClientUtil.getContestEmailTemplateByType(templateName);
        templateWrapper = new MemberBatchRegistrationTemplate(emailTemplate);

        return templateWrapper;
    }

    private String getLoginLink() {
        String colabUrl = PlatformAttributeKey.COLAB_URL.get();
        String loginUrl = colabUrl + "/login/token/" + loginToken.getTokenId()
                + "?tokenKey=" + loginToken.getTokenKey();
        return String.format(LINK_FORMAT_STRING, loginUrl , "here");
    }

    protected class MemberBatchRegistrationTemplate extends MemberNotificationTemplate {

        public MemberBatchRegistrationTemplate(EmailTemplate template) {
            super(template, null, null);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case LOGIN_LINK_PLACEHOLDER:
                    return parseXmlNode(getLoginLink());
                case LOGIN_LINK_EXPIRATION_PLACEHOLDER:
                    final String expirationDate = DATE_TIME_FORMATTER
                            .format(loginToken.getTokenExpirationDate());
                    return new TextNode(expirationDate, "");
                default:
            }
            return null;
        }
    }
}
