package org.xcolab.utils.emailnotification.basic;

import com.ext.portlet.ProposalAttributeKeys;


import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;

public class ProposalNotification extends ContestNotification {

    protected final Proposal proposal;

    private ProposalNotificationTemplate templateWrapper;

    public ProposalNotification(Proposal proposal, Contest contest, Member recipient, String templateName,
                                ServiceContext serviceContext) {
        super(contest, recipient, templateName, serviceContext);
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

        final ContestEmailTemplate emailTemplate =
                EmailTemplateClient.getContestEmailTemplateByType(templateName);

        templateWrapper = new ProposalNotificationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    protected class ProposalNotificationTemplate extends ContestNotificationTemplate {

        public ProposalNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }
    }
}
