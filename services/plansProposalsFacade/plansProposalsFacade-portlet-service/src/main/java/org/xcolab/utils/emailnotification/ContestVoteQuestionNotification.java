package org.xcolab.utils.emailnotification;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.List;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteQuestionNotification extends EmailNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_QUESTION_DEFAULT";

    private static final String SUPPORTED_PROPOSALS_PLACEHOLDER = "supported-proposals";

    private final User recipient;
    private final Contest contest;
    private final List<Proposal> supportedProposals;
    private ContestVoteQuestionTemplate templateWrapper;

    public ContestVoteQuestionNotification(User recipient, Contest contest, List<Proposal> supportedProposals, ServiceContext serviceContext) {
        super(serviceContext);
        this.recipient = recipient;
        this.contest = contest;
        this.supportedProposals = supportedProposals;
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
    protected ContestVoteQuestionTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        String voteQuestionTemplateString = contest.getVoteQuestionTemplateString();
        if (voteQuestionTemplateString.isEmpty()) {
            voteQuestionTemplateString = DEFAULT_TEMPLATE_STRING;
        }
        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(voteQuestionTemplateString);
        if (emailTemplate == null) {
            throw new SystemException("Could not load template \""+voteQuestionTemplateString+"\" for "+this.getClass().getName());
        }
        templateWrapper = new ContestVoteQuestionTemplate(emailTemplate, contest.getContestShortName());

        return templateWrapper;
    }

    private class ContestVoteQuestionTemplate extends EmailNotificationTemplate {

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
