package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.xcolab.utils.judging.ContestEmailTemplateWrapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteNotification extends EmailNotification {

    private static final String FIRSTNAME_PLACEHOLDER = "firstname";
    private static final String PROPOSAL_LINK_PLACEHOLDER = "proposal-link";
    private static final String CONTEST_LINK_PLACEHOLDER = "contest-link";
    private static final String TWITTER_PLACEHOLDER = "twitter";
    private static final String FACEBOOK_PLACEHOLDER = "facebook";
    private static final String PINTEREST_PLACEHOLDER = "pinterest";
    private static final String LINKEDIN_PLACEHOLDER = "linkedin";
    private static final String OTHER_CONTESTS_PLACEHOLDER = "other-contests-link";
    private static final String CONTEST_TITLE_PLACEHOLDER = "contest-title";

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
    protected ContestVoteTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = ProposalLocalServiceUtil.getAttribute(votedProposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        templateWrapper = new ContestVoteTemplate(
                ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(contest.getVoteTemplateString()), proposalName,
                contest.getContestShortName()
        );

        return templateWrapper;
    }

    private String getOtherContestLink(String linkText) {
        final String baseUrl = serviceContext.getPortalURL();
        return String.format(LINK_FORMAT_STRING, baseUrl + "/web/guest/plans", linkText);
    }

    private class ContestVoteTemplate extends ContestEmailTemplateWrapper {

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
                case FIRSTNAME_PLACEHOLDER:
                    return new TextNode(getProposalAuthor(votedProposal).getFirstName(), "");
                case CONTEST_LINK_PLACEHOLDER:
                    return parseXmlNode(getContestLink(contest));
                case CONTEST_TITLE_PLACEHOLDER:
                    return new TextNode(contest.getContestShortName(), "");
                case PROPOSAL_LINK_PLACEHOLDER:
                    if (tag.ownText().equals("")) {
                        return parseXmlNode(getProposalLink(contest, votedProposal));
                    } else {
                        return parseXmlNode(getProposalLinkWithLinkText(contest, votedProposal, tag.ownText()));
                    }
                case TWITTER_PLACEHOLDER:
                    return parseXmlNode(getTwitterShareLink(getProposalLinkUrl(contest, votedProposal), tag.ownText()));
                case PINTEREST_PLACEHOLDER:
                    return parseXmlNode(getPinterestShareLink(getProposalLinkUrl(contest, votedProposal), tag.ownText()));
                case FACEBOOK_PLACEHOLDER:
                    return parseXmlNode(getFacebookShareLink(getProposalLinkUrl(contest, votedProposal)));
                case LINKEDIN_PLACEHOLDER:
                    return parseXmlNode(getLinkedInShareLink(getProposalLinkUrl(contest, votedProposal), tag.attr("title") , tag.ownText()));
                case OTHER_CONTESTS_PLACEHOLDER:
                    return parseXmlNode(getOtherContestLink(tag.ownText()));
            }
            return null;
        }
    }
}
