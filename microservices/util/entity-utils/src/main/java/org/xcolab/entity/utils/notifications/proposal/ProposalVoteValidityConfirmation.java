package org.xcolab.entity.utils.notifications.proposal;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;
import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

public class ProposalVoteValidityConfirmation extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_CONFIRMATION_DEFAULT";
    private static final String CONFIRMATION_LINK_PLACEHOLDER = "confirmation-link";

    private final Proposal votedProposal;
    private final String confirmationToken;
    private final Member recipient;
    private ProposalVoteConfirmationTemplate templateWrapper;

    public ProposalVoteValidityConfirmation(Proposal votedProposal, Contest contest,
            Member recipient, String confirmationToken) {
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
        final EmailTemplateClient emailTemplateClient;
        if (contest.getIsSharedContestInForeignColab()) {
            RestService adminService = new RefreshingRestService(CoLabService.ADMIN,
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );

            emailTemplateClient = EmailTemplateClient.fromService(adminService);
        } else {
            emailTemplateClient = EmailTemplateClientUtil.getClient();
        }
        final ContestEmailTemplate emailTemplate =
                emailTemplateClient.getContestEmailTemplateByType(proposalVoteConfirmationTemplateString);
        templateWrapper =
                new ProposalVoteConfirmationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    private String getConfirmationLink(String linkText) {
        final String confirmationUrl = String.format("%s"+this.proposal.getProposalUrl()+"/confirmVote/%d/%d/%s",
                baseUrl, votedProposal.getProposalId(), recipient.getUserId(), confirmationToken);
        return String.format(LINK_FORMAT_STRING, confirmationUrl, linkText);
    }

    private class ProposalVoteConfirmationTemplate extends ProposalNotificationTemplate {

        public ProposalVoteConfirmationTemplate(ContestEmailTemplate template, String proposalName,
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
            }
            return null;
        }
    }
}
