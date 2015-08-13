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
import org.jsoup.nodes.TextNode;
import org.xcolab.utils.judging.ContestEmailTemplateWrapper;

import java.util.List;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteQuestionNotification extends EmailNotification {

    private static final String FIRSTNAME_PLACEHOLDER = "firstname";
    private static final String FULL_NAME_PLACEHOLDER = "fullname";
    private static final String CONTEST_LINK_PLACEHOLDER = "contest-link";
    private static final String SUPPORTED_PROPOSALS_PLACEHOLDER = "supported-proposals";

    private User recipient;
    private Contest contest;
    private List<Proposal> supportedProposals;
    private ContestVoteQuestionTemplate templateWrapper;

    public ContestVoteQuestionNotification(User recipient, Contest contest, List<Proposal> supportedProposals, ServiceContext serviceContext) {
        super(serviceContext);
        this.recipient = recipient;
        this.contest = contest;
        this.supportedProposals = supportedProposals;
    }

    private ContestVoteQuestionTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        templateWrapper = new ContestVoteQuestionTemplate(
                ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(contest.getVoteQuestionTemplateString()),
                contest.getContestShortName()
        );

        return templateWrapper;
    }

    @Override
    public void sendEmailNotification() throws PortalException, SystemException {
        ContestVoteQuestionTemplate template = getTemplateWrapper();
        String subject = template.getSubject();
        String body = template.getHeader()+"\n"+template.getFooter();
        sendMessage(subject, body, recipient);
    }

    private class ContestVoteQuestionTemplate extends ContestEmailTemplateWrapper {

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
                case FIRSTNAME_PLACEHOLDER:
                    return new TextNode(recipient.getFirstName(), "");
                case FULL_NAME_PLACEHOLDER:
                    return new TextNode(recipient.getFullName(), "");
                case CONTEST_LINK_PLACEHOLDER:
                    return parseXmlNode(getContestLink(contest));
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
