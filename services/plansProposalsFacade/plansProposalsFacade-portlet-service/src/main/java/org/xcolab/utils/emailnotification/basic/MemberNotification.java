package org.xcolab.utils.emailnotification.basic;

import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class MemberNotification extends EmailNotification {


    protected final String templateName;
    protected final User recipient;
    protected EmailNotificationTemplate templateWrapper;

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";
    private static final String SENDER_LASTNAME_PLACEHOLDER = "sender-lastname";
    private static final String SENDER_SCREENNAME_PLACEHOLDER = "sender-screenname";

    public MemberNotification( User recipient, String templateName,
                               ServiceContext serviceContext) {
        super(serviceContext);
        this.recipient = recipient;
        this.templateName = templateName;

    }

    @Override
    protected User getRecipient() throws SystemException, PortalException {
        return recipient;
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
        templateWrapper = new MemberNotificationTemplate(emailTemplate, "", "");

        return templateWrapper;
    }

    protected class MemberNotificationTemplate extends EmailNotificationTemplate {

        public MemberNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
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
                    return new TextNode(recipient.getFirstName(),"");
                case SENDER_LASTNAME_PLACEHOLDER:
                    return new TextNode(recipient.getLastName(),"");
                case SENDER_SCREENNAME_PLACEHOLDER:
                    return new TextNode(recipient.getScreenName(),"");
                default:
            }
            return null;
        }
    }
}
