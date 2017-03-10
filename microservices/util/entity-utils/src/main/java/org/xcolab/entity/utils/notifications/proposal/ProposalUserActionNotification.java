package org.xcolab.entity.utils.notifications.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalUserActionNotification extends ProposalNotification {

    private static final String SENDER_NAME_PLACEHOLDER = "sender-name";
    private final Member sender;
    private final String templateName;
    private ProposalUserActionNotificationTemplate templateWrapper;

    public ProposalUserActionNotification(Proposal proposal, Contest contest, Member sender,
            Member recipient, String templateName, String baseUrl) {
        super(proposal, contest, recipient, null, baseUrl);
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
                EmailTemplateClientUtil.getContestEmailTemplateByType(templateName);
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
