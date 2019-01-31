package org.xcolab.entity.utils.notifications.basic;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;

public class ProposalNotification extends ContestNotification {

    protected final ProposalWrapper proposal;

    private ProposalNotificationTemplate templateWrapper;

    public ProposalNotification(ProposalWrapper proposal, ContestWrapper contest, UserWrapper recipient,
            String templateName) {
        super(contest, recipient, templateName);
        this.proposal = proposal;
    }

    @Override
    protected ProposalWrapper getProposal() {
        return proposal;
    }

    @Override
    protected ProposalNotificationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName =
                getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(templateName);

        templateWrapper = new ProposalNotificationTemplate(emailTemplate, proposalName, contest.getTitle());

        return templateWrapper;
    }

    @Override
    protected Long getReferenceId(){
        return this.proposal.getContestId();
    }

    protected class ProposalNotificationTemplate extends ContestNotificationTemplate {

        public ProposalNotificationTemplate(IEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }
    }
}
