package org.xcolab.utils.emailnotification.basic;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;

public class ProposalNotification extends ContestNotification {

    protected final Proposal proposal;

    private ProposalNotificationTemplate templateWrapper;

    public ProposalNotification(Proposal proposal, Contest contest, User recipient, String templateName,
            ServiceContext serviceContext) {
        super(contest, recipient, templateName, serviceContext);
        this.proposal = proposal;
    }

    @Override
    protected Proposal getProposal() {
        return proposal;
    }

    @Override
    protected ProposalNotificationTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName =
                getProposalAttributeHelper().getAttributeValueString(ProposalAttributeKeys.NAME, "");

        final ContestEmailTemplate emailTemplate =
                EmailTemplateClient.getContestEmailTemplateByType(templateName);
        if (emailTemplate == null) {
            throw new SystemException(
                    "Could not load template \"" + templateName + "\" for " + this.getClass().getName());
        }
        templateWrapper = new ProposalNotificationTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    protected class ProposalNotificationTemplate extends ContestNotificationTemplate {

        public ProposalNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }
    }
}
