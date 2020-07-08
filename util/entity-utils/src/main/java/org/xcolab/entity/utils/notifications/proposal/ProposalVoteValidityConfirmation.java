package org.xcolab.entity.utils.notifications.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalVoteValidityConfirmation extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_CONFIRMATION_DEFAULT";
    private static final String CONFIRMATION_LINK_PLACEHOLDER = "confirmation-link";

    private final ProposalWrapper votedProposal;
    private final String confirmationToken;
    private final UserWrapper recipient;
    private ProposalVoteConfirmationTemplate templateWrapper;

    public ProposalVoteValidityConfirmation(ProposalWrapper votedProposal, ContestWrapper contest,
            UserWrapper recipient, String confirmationToken) {
        super(votedProposal, contest, recipient, null);
        this.confirmationToken = confirmationToken;
        this.votedProposal = votedProposal;
        this.recipient = recipient;
    }

    @Override
    protected boolean isEssentialTransactionMessage() {
        return true;
    }

    @Override
    protected ProposalVoteConfirmationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName =
                getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        String proposalVoteConfirmationTemplateString = contest.getProposalVoteConfirmationTemplateString();
        if (proposalVoteConfirmationTemplateString.isEmpty()) {
            proposalVoteConfirmationTemplateString = DEFAULT_TEMPLATE_STRING;
        }

        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(proposalVoteConfirmationTemplateString);
        templateWrapper =
                new ProposalVoteConfirmationTemplate(emailTemplate, proposalName, contest.getTitle());

        return templateWrapper;
    }

    private String getConfirmationLink(String linkText) {
        final String confirmationUrl = String.format("%s" + this.proposal.getProposalUrl() + "/confirmVote/%d/%s",
                baseUrl, recipient.getId(), confirmationToken);
        return String.format(LINK_FORMAT_STRING, confirmationUrl, linkText);
    }

    private class ProposalVoteConfirmationTemplate extends ProposalNotificationTemplate {

        public ProposalVoteConfirmationTemplate(IEmailTemplate template, String proposalName,
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
                case CONFIRMATION_LINK_PLACEHOLDER:
                    return parseXmlNode(getConfirmationLink(tag.ownText()));
                //missing default case
                default:
                    // add default case
                    break;

            }
            return null;
        }
    }
}
