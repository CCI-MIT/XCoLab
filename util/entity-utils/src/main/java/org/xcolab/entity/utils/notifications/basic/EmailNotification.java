package org.xcolab.entity.utils.notifications.basic;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.entity.utils.notifications.EmailTemplateWrapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

public abstract class EmailNotification {
    private static final long ADMINISTRATOR_USER_ID = 10144L;

    private static final String COLAB_NAME_PLACEHOLDER = "colab-name";
    private static final String COLAB_URL_PLACEHOLDER = "colab-url";
    private static final String COLAB_ADMIN_EMAIL_PLACEHOLDER = "colab-admin-email";

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
            = "https://pinterest.com/pin/create/button/?url=g%s&media=https://cdn.climatecolab.org/images/climateColab-logo.png&description=%s";

    private ProposalAttributeHelper proposalAttributeHelper;

    protected static final String LINK_FORMAT_STRING = "<a href='%s' target='_blank'>%s</a>";

    protected String baseUrl;

    protected Logger _log;


    public EmailNotification() {
        this.baseUrl = PlatformAttributeKey.COLAB_URL.get();
        _log = LoggerFactory.getLogger(this.getClass());
    }

    private String getProposalLinkWithLinkText(ContestWrapper contest,
                                               ProposalWrapper proposal, String linkText, String tab) {

            String proposalLinkUrl = baseUrl + proposal.getProposalLinkUrl((contest));
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
    //TODO COLAB-2505: remove if we just use a normal proposal link
    protected String getProposalLinkForDirectVoting(ContestWrapper contest, ProposalWrapper proposal) {
        final String proposalName = new ProposalAttributeHelper(proposal,
                StaticProposalContext.getProposalAttributeClient())
                .getAttributeValueString(ProposalAttributeKeys.NAME, "");

            final String proposalLinkUrl = baseUrl + proposal.getProposalLinkUrl(contest);
            return String.format(LINK_FORMAT_STRING, proposalLinkUrl, proposalName);
    }

    /**
     * Returns the HTML link for the passed contest
     *
     * @param contest The contest object
     * @return Contest URL as String
     */
    private String getContestLink(ContestWrapper contest) {
        final String contestLinkUrl = baseUrl + contest.getContestLinkUrl();
        return String.format(LINK_FORMAT_STRING, contestLinkUrl, contest.getTitle());
    }

    protected ContestWrapper getContest() {
        return null;
    }

    protected ProposalAttributeHelper getProposalAttributeHelper() {
        if (proposalAttributeHelper == null) {
            proposalAttributeHelper = new ProposalAttributeHelper(getProposal(),
                    StaticProposalContext.getProposalAttributeClient());
        }
        return proposalAttributeHelper;
    }

    protected ProposalWrapper getProposal() {
        return null;
    }

    /**
     * Returns the link url to the given proposal
     *
     * @param contest         Contest in which the proposal is in
     * @param proposalToShare The Proposal that should be shared
     */
    protected String getProposalLinkUrl(ContestWrapper contest, ProposalWrapper proposalToShare) {
        return baseUrl + proposalToShare.getProposalLinkUrl(contest);
    }

    /**
     * Returns the link url to the given contest
     *
     * @param contest Contest to be shared
     */
    protected String getContestLinkUrl(ContestWrapper contest) {
        return baseUrl + contest.getContestLinkUrl();
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

    protected abstract Long getReferenceId();

    protected abstract UserWrapper getRecipient();

    protected abstract EmailTemplateWrapper getTemplateWrapper();

    /**
     * Indicates whether this message is essential for fundamental operations of the platform.
     *
     * If this method returns true, the notification will be sent even if transactional messages
     * are disabled on the platform or by the user.
     *
     * The default is false, overwrite this method if the subclass represents a notification
     * that is necessary for basic functionality. This is typically the case for messages that
     * require user action.
     *
     * @return true if message should always be sent, false if it is optional
     */
    protected boolean isEssentialTransactionMessage() {
        return false;
    }

    public void sendEmailNotification() {
        if (ConfigurationAttributeKey.MESSAGING_SEND_TRANSACTION_EMAILS.get()
                || isEssentialTransactionMessage()) {
            EmailTemplateWrapper template = getTemplateWrapper();
            String subject = template.getSubject();
            String body = template.getHeader() + "\n" + template.getFooter();
            sendEmail(subject, body, getRecipient());
        }
    }

    private void sendEmail(String subject, String body, UserWrapper recipient) {
        try {
            InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();
            InternetAddress toEmail = new InternetAddress(recipient.getEmailAddress(), recipient.getFullName());


              StaticEmailContext
                      .getEmailClient().sendEmail(fromEmail.getAddress(),ConfigurationAttributeKey.COLAB_NAME.get(),
                    toEmail.getAddress(), subject,body, true, fromEmail.getAddress(),
                    ConfigurationAttributeKey.COLAB_NAME.get(),getReferenceId());


        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }
    }

    public void sendMessage() {
        if (ConfigurationAttributeKey.MESSAGING_SEND_TRANSACTION_EMAILS.get()
                || isEssentialTransactionMessage()) {
            List<Long> recipients = new ArrayList<>();
            recipients.add(getRecipient().getId());
            EmailTemplateWrapper template = getTemplateWrapper();
            String content = template.getHeader() + template.getFooter();
            content = content.replace("\n", " ").replace("\r", " ");
            StaticUserContext.getMessagingClient()
                    .sendMessage(template.getSubject(), content, ADMINISTRATOR_USER_ID, null,
                                recipients);
        }
    }

    protected class EmailNotificationTemplate extends EmailTemplateWrapper {
        public EmailNotificationTemplate(IEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            final Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }
            ContestWrapper contest = getContest();
            ProposalWrapper proposal = getProposal();
            final boolean hasProposal = contest != null && proposal != null;

            final ContestType contestType =
                    contest != null ? StaticAdminContext.getContestTypeClient()
                            .getContestType(contest.getContestTypeId()) : null;


            switch (tag.nodeName()) {
                case COLAB_NAME_PLACEHOLDER:
                    return new TextNode(ConfigurationAttributeKey.COLAB_NAME.get());
                case COLAB_URL_PLACEHOLDER:
                    return new TextNode(PlatformAttributeKey.COLAB_URL.get());
                case COLAB_ADMIN_EMAIL_PLACEHOLDER:
                    return new TextNode(ConfigurationAttributeKey.ADMIN_EMAIL.get());
                case FIRSTNAME_PLACEHOLDER:
                    return new TextNode(getRecipient().getFirstName());
                case FULL_NAME_PLACEHOLDER:
                    return new TextNode(getRecipient().getFullName());
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
                        return new TextNode(contestType.getProposalName());
                    }
                    break;
                case PROPOSALS_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getProposalNamePlural());
                    }
                    break;
                case CONTEST_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getContestName());
                    }
                    break;
                case CONTESTS_STRING_PLACEHOLDER:
                    if (contest != null && contestType != null) {
                        return new TextNode(contestType.getContestNamePlural());
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
