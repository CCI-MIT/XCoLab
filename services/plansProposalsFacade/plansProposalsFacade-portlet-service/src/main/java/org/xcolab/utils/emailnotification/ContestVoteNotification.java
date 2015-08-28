package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteNotification extends EmailNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_DEFAULT";

    private static final String OTHER_CONTESTS_PLACEHOLDER = "other-contests-link";

    private User recipient;
    private Contest contest;
    private Proposal votedProposal;
    private ContestVoteTemplate templateWrapper;

    public ContestVoteNotification(User recipient, Contest contest, Proposal votedProposal, ServiceContext serviceContext) {
        super(serviceContext);
        this.recipient = recipient;
        this.contest = contest;
        this.votedProposal = votedProposal;
    }

    @Override
    protected User getRecipient() throws SystemException, PortalException {
        return recipient;
    }

    @Override
    protected Contest getContest() {
        return contest;
    }

    @Override
    protected Proposal getProposal() {
        return votedProposal;
    }

    @Override
    protected ContestVoteTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = ProposalLocalServiceUtil.getAttribute(votedProposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        String voteTemplateString = contest.getVoteTemplateString();
        if (voteTemplateString.length() == 0) {
            voteTemplateString = DEFAULT_TEMPLATE_STRING;
        }
        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(voteTemplateString);
        if (emailTemplate == null) {
            throw new SystemException("Could not load template \""+voteTemplateString+"\" for "+this.getClass().getName());
        }
        templateWrapper = new ContestVoteTemplate(emailTemplate, proposalName, contest.getContestShortName());

        return templateWrapper;
    }

    private String getOtherContestLink(String linkText) {
        final String baseUrl = serviceContext.getPortalURL();
        return String.format(LINK_FORMAT_STRING, baseUrl + "/web/guest/plans", linkText);
    }

    private class ContestVoteTemplate extends EmailNotificationTemplate {

        public ContestVoteTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case OTHER_CONTESTS_PLACEHOLDER:
                    return parseXmlNode(getOtherContestLink(tag.ownText()));
            }
            return null;
        }
    }
}
