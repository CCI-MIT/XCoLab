package org.xcolab.utils.emailnotification.contest;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.utils.emailnotification.basic.ContestNotification;

import java.util.List;

public class ContestVoteQuestionNotification extends ContestNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_QUESTION_DEFAULT";

    private static final String SUPPORTED_PROPOSALS_PLACEHOLDER = "supported-proposals";

    private final List<Proposal> supportedProposals;
    private ContestVoteQuestionTemplate templateWrapper;

    public ContestVoteQuestionNotification(User recipient, Contest contest, List<Proposal> supportedProposals,
            ServiceContext serviceContext) {
        super(contest, recipient, null, serviceContext);
        this.supportedProposals = supportedProposals;
    }

    @Override
    protected ContestVoteQuestionTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        String voteQuestionTemplateString = contest.getVoteQuestionTemplateString();
        if (voteQuestionTemplateString.isEmpty()) {
            voteQuestionTemplateString = DEFAULT_TEMPLATE_STRING;
        }
        final ContestEmailTemplate emailTemplate =
                EmailTemplateClient.getContestEmailTemplateByType(voteQuestionTemplateString);
        if (emailTemplate == null) {
            throw new SystemException(String.format("Could not load template \"%s\" for %s",
                    voteQuestionTemplateString, this.getClass().getName()));
        }
        templateWrapper = new ContestVoteQuestionTemplate(emailTemplate, contest.getContestShortName());

        return templateWrapper;
    }

    private class ContestVoteQuestionTemplate extends ContestNotificationTemplate {

        public ContestVoteQuestionTemplate(ContestEmailTemplate template, String contestName) {
            super(template, "", contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case SUPPORTED_PROPOSALS_PLACEHOLDER:
                    StringBuilder supportedProposalsLinks = new StringBuilder();
                    supportedProposalsLinks.append("<span>");
                    for (Proposal proposal : supportedProposals) {
                        supportedProposalsLinks.append(getProposalLinkForDirectVoting(contest, proposal)).append(" by ").append(UserLocalServiceUtil.getUser(proposal.getAuthorId()).getFullName()).append("<br />");
                    }
                    supportedProposalsLinks.append("</span>");
                    return parseXmlNode(supportedProposalsLinks.toString());
            }
            return null;
        }
    }
}
