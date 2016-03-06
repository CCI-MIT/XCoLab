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
import org.xcolab.utils.emailnotification.basic.ProposalNotification;

public class ProposalVoteValidityConfirmation extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_CONFIRMATION_DEFAULT";
    private static final String CONFIRMATION_LINK_PLACEHOLDER = "confirmation-link";

    private ProposalVoteConfirmationTemplate templateWrapper;

    private final Proposal votedProposal;
    private final String confirmationToken;
    private final User recipient;

    public ProposalVoteValidityConfirmation(Proposal votedProposal, Contest contest, User recipient,
                                            ServiceContext serviceContext, String confirmationToken) {
        super(votedProposal, contest, recipient, null, serviceContext);
        this.confirmationToken = confirmationToken;
        this.votedProposal = votedProposal;
        this.recipient = recipient;
    }

    @Override
    protected ProposalVoteConfirmationTemplate getTemplateWrapper() throws SystemException, PortalException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        String proposalVoteConfirmationTemplateString = contest.getProposalVoteConfirmationTemplateString();
        if (proposalVoteConfirmationTemplateString.isEmpty()) {
            proposalVoteConfirmationTemplateString = DEFAULT_TEMPLATE_STRING;
        }
        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(proposalVoteConfirmationTemplateString);
        if (emailTemplate == null) {
            throw new SystemException("Could not load template \""+proposalVoteConfirmationTemplateString+"\" for "+this.getClass().getName());
        }
        templateWrapper = new ProposalVoteConfirmationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    private String getConfirmationLink(String linkText) {
        final String confirmationUrl = String.format("%s/web/guest/plans/-/plans/confirmVote/%d/%d/%s",
                serviceContext.getPortalURL(), votedProposal.getProposalId(), recipient.getUserId() , confirmationToken);
        return String.format(LINK_FORMAT_STRING, confirmationUrl, linkText);
    }

    private class ProposalVoteConfirmationTemplate extends ProposalNotificationTemplate {

        public ProposalVoteConfirmationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
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
