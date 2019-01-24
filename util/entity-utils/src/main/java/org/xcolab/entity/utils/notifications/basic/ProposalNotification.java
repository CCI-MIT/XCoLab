package org.xcolab.entity.utils.notifications.basic;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;

public class ProposalNotification extends ContestNotification {

    protected final Proposal proposal;

    private ProposalNotificationTemplate templateWrapper;

    public ProposalNotification(Proposal proposal, Contest contest, IUser recipient,
            String templateName) {
        super(contest, recipient, templateName);
        this.proposal = proposal;
    }

    @Override
    protected Proposal getProposal() {
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
        return this.proposal.getcontestId();
    }

    protected class ProposalNotificationTemplate extends ContestNotificationTemplate {

        public ProposalNotificationTemplate(IEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }
    }
}
