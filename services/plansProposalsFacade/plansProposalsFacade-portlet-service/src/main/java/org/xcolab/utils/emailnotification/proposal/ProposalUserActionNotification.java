package org.xcolab.utils.emailnotification.proposal;

import com.ext.portlet.ProposalAttributeKeys;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.utils.emailnotification.basic.ProposalNotification;

public class ProposalUserActionNotification extends ProposalNotification {

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";
    private final Member sender;
    private final String templateName;
    private ProposalUserActionNotificationTemplate templateWrapper;

    public ProposalUserActionNotification(Proposal proposal, Contest contest, Member sender, Member recipient,
                                          String templateName, ServiceContext serviceContext) {
        super(proposal, contest, recipient, null, serviceContext);
        this.sender = sender;
        this.templateName = templateName;
    }

    @Override
    protected ProposalUserActionNotificationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName =
                getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final ContestEmailTemplate emailTemplate =
                EmailTemplateClient.getContestEmailTemplateByType(templateName);
        templateWrapper = new ProposalUserActionNotificationTemplate(emailTemplate,
                proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    protected class ProposalUserActionNotificationTemplate extends ProposalNotificationTemplate {

        public ProposalUserActionNotificationTemplate(ContestEmailTemplate template, String proposalName,
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
                    return new TextNode(sender.getFullName(), "");
                default:
            }
            return null;
        }
    }
}
