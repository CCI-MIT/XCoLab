package org.xcolab.utils.emailnotification.proposal;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.xcolab.utils.emailnotification.basic.ProposalNotification;

public class ProposalUserActionNotification extends ProposalNotification {

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";

    private ProposalUserActionNotificationTemplate templateWrapper;

    private final User sender;
    private final String templateName;

    public ProposalUserActionNotification(Proposal proposal, Contest contest, User sender, User recipient,
            String templateName, ServiceContext serviceContext) {
        super(proposal, contest, recipient, null, serviceContext);
        this.sender = sender;
        this.templateName = templateName;
    }

    @Override
    protected ProposalUserActionNotificationTemplate getTemplateWrapper() throws SystemException, PortalException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(templateName);
        if (emailTemplate == null) {
            throw new SystemException("Could not load template \""+templateName+"\" for "+this.getClass().getName());
        }
        templateWrapper = new ProposalUserActionNotificationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    protected class ProposalUserActionNotificationTemplate extends ProposalNotificationTemplate {

        public ProposalUserActionNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
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
                    return new TextNode(sender.getFullName(), "");
                default:
            }
            return null;
        }
    }
}
