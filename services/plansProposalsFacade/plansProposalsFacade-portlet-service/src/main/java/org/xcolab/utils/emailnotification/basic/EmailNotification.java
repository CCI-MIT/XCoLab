package org.xcolab.utils.emailnotification.basic;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.utils.TemplateReplacementUtil;
import org.xcolab.utils.judging.EmailTemplateWrapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

public abstract class EmailNotification {
    private static final long ADMINISTRATOR_USER_ID = 10144L;

    private static final String FIRSTNAME_PLACEHOLDER = "firstname";
    private static final String FULL_NAME_PLACEHOLDER = "fullname";

    private static final String PROPOSAL_LINK_PLACEHOLDER = "proposal-link";
    private static final String CONTEST_LINK_PLACEHOLDER = "contest-link";

    private static final String PROPOSAL_STRING_PLACEHOLDER = "proposal-string";
    private static final String PROPOSALS_STRING_PLACEHOLDER = "proposals-string";
    private static final String CONTEST_STRING_PLACEHOLDER = "contest-string";
    private static final String CONTESTS_STRING_PLACEHOLDER = "contests-string";

    private static final String TWITTER_PLACEHOLDER = "twitter";
    private static final String FACEBOOK_PLACEHOLDER = "facebook";
    private static final String PINTEREST_PLACEHOLDER = "pinterest";
    private static final String LINKEDIN_PLACEHOLDER = "linkedin";

    private static final String FACEBOOK_PROPOSAL_SHARE_LINK
            = "https://www.facebook.com/sharer/sharer.php?u=%s&display=popup";
    private static final String TWITTER_PROPOSAL_SHARE_LINK
            = "https://twitter.com/share?url=%s&text=%s";
    private static final String LINKEDIN_PROPOSAL_SHARE_LINK
            = "https://www.linkedin.com/shareArticle?mini=true&url=%s&title=%s&summary=%s&source=";
    private static final String PINTEREST_PROPOSAL_SHARE_LINK
            = "https://pinterest.com/pin/create/button/?url=g%s&media=http://climatecolab.org/climatecolab-theme/images/logo-climate-colab.png&description=%s";

    private ProposalAttributeHelper proposalAttributeHelper;

    protected static final String LINK_FORMAT_STRING = "<a href='%s' target='_blank'>%s</a>";

    protected ServiceContext serviceContext;

    protected Log _log;

    public EmailNotification(ServiceContext serviceContext) {
        this.serviceContext = serviceContext;
        _log = LogFactoryUtil.getLog(this.getClass());
    }

    private String getProposalLinkWithLinkText(Contest contest,
                                               Proposal proposal, String linkText, String tab) {

            String proposalLinkUrl = serviceContext.getPortalURL()
                    + proposal.getProposalLinkUrl((contest));
            if (tab != null) {
                proposalLinkUrl += "/tab/" + tab;
            }
            return String.format(LINK_FORMAT_STRING, proposalLinkUrl, linkText);

    }

    /**
     * Returns the HTML link for the passed proposal and contest
     *
     * @param contest  The contest object in which the proposal was written
     * @param proposal The proposal object (must not be null)
     * @return Proposal URL as String
     */
    protected String getProposalLinkForDirectVoting(Contest contest, Proposal proposal) {
        final String proposalName = new ProposalAttributeHelper(proposal)
                .getAttributeValueString(ProposalAttributeKeys.NAME, "");

            final String proposalLinkUrl = serviceContext.getPortalURL()
                    + proposal.getProposalLinkUrl(contest) + "/vote";
            return String.format(LINK_FORMAT_STRING, proposalLinkUrl, proposalName);

    }


    /**
     * Returns the HTML link for the passed contest
     *
     * @param contest The contest object
     * @return Contest URL as String
     */
    private String getContestLink(Contest contest) {
        final String contestLinkUrl =
                serviceContext.getPortalURL() + contest.getContestLinkUrl();
        return String.format(LINK_FORMAT_STRING, contestLinkUrl, contest.getContestShortName());
    }

    protected Contest getContest() {
        return null;
    }

    protected ProposalAttributeHelper getProposalAttributeHelper() {
        if (proposalAttributeHelper == null) {
            proposalAttributeHelper = new ProposalAttributeHelper(getProposal());
        }
        return proposalAttributeHelper;
    }

    protected Proposal getProposal() {
        return null;
    }

    /**
     * Returns the link url to the given proposal
     *
     * @param contest         Contest in which the proposal is in
     * @param proposalToShare The Proposal that should be shared
     */
    protected String getProposalLinkUrl(Contest contest, Proposal proposalToShare) {
        return serviceContext.getPortalURL() + proposalToShare.getProposalLinkUrl(contest);
    }

    /**
     * Returns the link url to the given contest
     *
     * @param contest Contest to be shared
     */
    protected String getContestLinkUrl(Contest contest) {
        return serviceContext.getPortalURL() + contest.getContestLinkUrl();
    }

    /**
     * Returns a fully prepared Facebook share link for the given url
     *
     * @param urlToShare The url to be shared
     */
    private String getFacebookShareLink(String urlToShare) {
        String url = String.format(FACEBOOK_PROPOSAL_SHARE_LINK, urlToShare);
        return String.format(LINK_FORMAT_STRING, url, "Facebook");
    }

    /**
     * Returns a fully prepared Twitter share link for the given url including the specified share message
     *
     * @param urlToShare   The url to be shared
     * @param shareMessage The message that should be included for sharing
     */
    private String getTwitterShareLink(String urlToShare, String shareMessage) {
        try {
            String url = String.format(TWITTER_PROPOSAL_SHARE_LINK, urlToShare,
                    URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "Twitter");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new InternalException(e);
        }
    }

    private String getLinkedInShareLink(String urlToShare,
            String shareTitle, String shareMessage) {
        try {
            String url = String.format(LINKEDIN_PROPOSAL_SHARE_LINK, urlToShare,
                    URLEncoder.encode(shareTitle, "UTF-8"),
                    URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "LinkedIn");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new InternalException(e);
        }
    }

    private String getPinterestShareLink(String urlToShare, String shareMessage) {
        try {
            String url = String.format(PINTEREST_PROPOSAL_SHARE_LINK, urlToShare,
                    URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "Pinterest");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new InternalException(e);
        }
    }

    public void sendEmailNotification() {
        EmailTemplateWrapper template = getTemplateWrapper();
        String subject = template.getSubject();
        String body = template.getHeader() + "\n" + template.getFooter();
        sendMessage(subject, body, getRecipient());
    }

    protected abstract EmailTemplateWrapper getTemplateWrapper();

    protected void sendMessage(String subject, String body, Member recipient) {
        try {
            InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();
            InternetAddress toEmail = new InternetAddress(recipient.getEmailAddress(), recipient.getFullName());

            EmailClient.sendEmail(fromEmail.getAddress(), toEmail.getAddress(), subject,body, true, fromEmail.getAddress());
        } catch (UnsupportedEncodingException e) {
            _log.error("Could not send message", e);
        }
    }

    protected abstract Member getRecipient();

    public void sendMessage() {
        List<Long> recipients = new ArrayList<>();
        recipients.add(getRecipient().getUserId());
        EmailTemplateWrapper template = getTemplateWrapper();
        String content = template.getHeader() + template.getFooter();
        content = content.replace("\n", " ").replace("\r", " ");
        MessagingClient.sendMessage(template.getSubject(), content,
                ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, recipients);
    }

    protected class EmailNotificationTemplate extends EmailTemplateWrapper {
        public EmailNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            final Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }
            Contest contest = getContest();
            Proposal proposal = getProposal();
            final boolean hasProposal = contest != null && proposal != null;
            final ContestType contestType;

            contestType = contest != null
                        ? ContestClientUtil.getContestType(contest.getContestTypeId()) : null;


            switch (tag.nodeName()) {
                case FIRSTNAME_PLACEHOLDER:
                    return new TextNode(getRecipient().getFirstName(), "");
                case FULL_NAME_PLACEHOLDER:
                    return new TextNode(getRecipient().getFullName(), "");
                case CONTEST_LINK_PLACEHOLDER:
                    if (contest != null) {
                        return parseXmlNode(getContestLink(contest));
                    }
                    break;
                case PROPOSAL_LINK_PLACEHOLDER:
                    if (hasProposal) {
                        final String tab = tag.hasAttr("tab") ? tag.attr("tab") : null;

                        final String linkText;
                        if (StringUtils.isNotBlank(tag.ownText())) {
                            linkText = tag.ownText();
                        } else {
                            linkText = getProposalAttributeHelper()
                                    .getAttributeValueString(ProposalAttributeKeys.NAME, "");
                        }
                        return parseXmlNode(getProposalLinkWithLinkText(contest, proposal, linkText, tab));
                    }
                    break;
                case PROPOSAL_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getProposalName(), "");
                    }
                    break;
                case PROPOSALS_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getProposalNamePlural(), "");
                    }
                    break;
                case CONTEST_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getContestName(), "");
                    }
                    break;
                case CONTESTS_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getContestNamePlural(), "");
                    }
                    break;
                case TWITTER_PLACEHOLDER:
                    if (hasProposal) {
                        return parseXmlNode(getTwitterShareLink(getProposalLinkUrl(contest, proposal), tag.ownText()));
                    }
                    break;
                case PINTEREST_PLACEHOLDER:
                    if (hasProposal) {
                        return parseXmlNode(
                                getPinterestShareLink(getProposalLinkUrl(contest, proposal), tag.ownText()));
                    }
                    break;
                case FACEBOOK_PLACEHOLDER:
                    if (hasProposal) {
                        return parseXmlNode(getFacebookShareLink(getProposalLinkUrl(contest, proposal)));
                    }
                    break;
                case LINKEDIN_PLACEHOLDER:
                    if (hasProposal) {
                        return parseXmlNode(getLinkedInShareLink(getProposalLinkUrl(contest, proposal),
                                tag.attr("title"), tag.ownText()));
                    }
                    break;
                default:
            }
            return null;
        }
    }
}
