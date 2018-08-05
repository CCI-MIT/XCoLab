package org.xcolab.entity.utils.notifications.basic;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.EmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;

public class ProposalNotification extends ContestNotification {

    protected final Proposal proposal;

    private ProposalNotificationTemplate templateWrapper;

    public ProposalNotification(Proposal proposal, Contest contest, Member recipient,
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

        final EmailTemplateClient emailTemplateClient = EmailTemplateClientUtil.getClient();

        final EmailTemplate emailTemplate =
                emailTemplateClient.getContestEmailTemplateByType(templateName);

        templateWrapper = new ProposalNotificationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    @Override
    protected Long getReferenceId(){
        return this.proposal.getContestPK();
    }

    protected class ProposalNotificationTemplate extends ContestNotificationTemplate {

        public ProposalNotificationTemplate(EmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }
    }
}
