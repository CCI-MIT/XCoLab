package org.xcolab.utils.emailnotification.proposal;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;


import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.utils.emailnotification.basic.ProposalNotification;

public class ProposalVoteValidityConfirmation extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_CONFIRMATION_DEFAULT";
    private static final String CONFIRMATION_LINK_PLACEHOLDER = "confirmation-link";

    private final Proposal votedProposal;
    private final String confirmationToken;
    private final Member recipient;
    private ProposalVoteConfirmationTemplate templateWrapper;

    public ProposalVoteValidityConfirmation(Proposal votedProposal, Contest contest, Member recipient,
                                            ServiceContext serviceContext, String confirmationToken) {
        super(votedProposal, contest, recipient, null, serviceContext);
        this.confirmationToken = confirmationToken;
        this.votedProposal = votedProposal;
        this.recipient = recipient;
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
        final ContestEmailTemplate emailTemplate =
                EmailTemplateClientUtil.getContestEmailTemplateByType(proposalVoteConfirmationTemplateString);
        templateWrapper =
                new ProposalVoteConfirmationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    private String getConfirmationLink(String linkText) {
        final String confirmationUrl = String.format("%s/web/guest/plans/-/plans/confirmVote/%d/%d/%s",
                serviceContext.getPortalURL(), votedProposal.getProposalId(), recipient.getUserId(), confirmationToken);
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
