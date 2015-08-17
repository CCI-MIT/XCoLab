package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.xcolab.utils.judging.EmailTemplateWrapper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class defines common helper methods for Email Notification Util classes
 *
 * Created by kmang on 21/05/14.
 */
public abstract class EmailNotification {
    protected static final long ADMINISTRATOR_USER_ID = 10144L;

    private static final String FIRSTNAME_PLACEHOLDER = "firstname";
    private static final String FULL_NAME_PLACEHOLDER = "fullname";
    private static final String PROPOSAL_LINK_PLACEHOLDER = "proposal-link";
    private static final String CONTEST_LINK_PLACEHOLDER = "contest-link";
    private static final String TWITTER_PLACEHOLDER = "twitter";
    private static final String FACEBOOK_PLACEHOLDER = "facebook";
    private static final String PINTEREST_PLACEHOLDER = "pinterest";
    private static final String LINKEDIN_PLACEHOLDER = "linkedin";

    protected static final String FACEBOOK_PROPOSAL_SHARE_LINK = "https://www.facebook.com/sharer/sharer.php?u=%s&display=popup";
    protected static final String TWITTER_PROPOSAL_SHARE_LINK = "https://twitter.com/share?url=%s&text=%s";
    protected static final String LINKEDIN_PROPOSAL_SHARE_LINK = "https://www.linkedin.com/shareArticle?mini=true&url=%s&title=%s&summary=%s&source=";
    protected static final String PINTEREST_PROPOSAL_SHARE_LINK = "https://pinterest.com/pin/create/button/?url=g%s&media=http://climatecolab.org/climatecolab-theme/images/logo-climate-colab.png&description=%s";

    protected static final String LINK_FORMAT_STRING = "<a href='%s' target='_blank'>%s</a>";

    protected ServiceContext serviceContext;

    protected Log _log;

    public EmailNotification(ServiceContext serviceContext) {
        this.serviceContext = serviceContext;
        _log = LogFactoryUtil.getLog(this.getClass());
    }

    /**
     * Returns the HTML link for the passed proposal and contest
     *
     * @param contest   The contest object in which the proposal was written
     * @param proposal  The proposal object (must not be null)
     * @return          Proposal URL as String
     */
    protected  String getProposalLink(Contest contest, Proposal proposal) throws SystemException, PortalException {
        final String proposalName = ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
        return getProposalLinkWithLinkText(contest, proposal, proposalName);
    }

    protected String getProposalLinkWithLinkText(Contest contest, Proposal proposal, String linkText) throws SystemException, PortalException {
        final String proposalLinkUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal);
        return String.format(LINK_FORMAT_STRING, proposalLinkUrl, linkText);
    }

    /**
     * Returns the HTML link for the passed proposal and contest
     *
     * @param contest   The contest object in which the proposal was written
     * @param proposal  The proposal object (must not be null)
     * @return          Proposal URL as String
     */
    protected  String getProposalLinkForDirectVoting(Contest contest, Proposal proposal) throws SystemException, PortalException {
        final String proposalName = ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
        final String proposalLinkUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal) + "/vote";
        return String.format(LINK_FORMAT_STRING, proposalLinkUrl, proposalName);
    }

    /**
     * Returns the HTML link for the passed contest
     *
     * @param contest   The contest object
     * @return          Contest URL as String
     */
    protected String getContestLink(Contest contest) {
        final String contestLinkUrl = serviceContext.getPortalURL() + ContestLocalServiceUtil.getContestLinkUrl(contest);
        return String.format(LINK_FORMAT_STRING, contestLinkUrl, contest.getContestShortName());
    }

    protected User getProposalAuthor(Proposal proposal) throws SystemException, PortalException {
        return UserLocalServiceUtil.getUserById(proposal.getAuthorId());
    }

    protected User getContestAuthor(Contest contest) throws SystemException, PortalException {
        return UserLocalServiceUtil.getUserById(contest.getAuthorId());
    }

    protected Contest getContest() {
        return null;
    }

    protected Proposal getProposal() {
        return null;
    }

    protected void sendMessage(String subject, String body, User recipient) {
        try {
            InternetAddress fromEmail = new InternetAddress("no-reply@climatecolab.org", "MIT Climate CoLab");
            InternetAddress toEmail = new InternetAddress(recipient.getEmailAddress(), recipient.getFullName());
            MailEngine.send(fromEmail, toEmail, subject, body, true);
        } catch (MailEngineException | UnsupportedEncodingException e) {
            _log.error("Could not send vote message", e);
        }
    }

    /**
     * Returns the link url to the given proposal
     *
     * @param contest                       Contest in which the proposal is in
     * @param proposalToShare               The Proposal that should be shared
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    protected String getProposalLinkUrl(Contest contest, Proposal proposalToShare) throws SystemException, PortalException {
        return serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposalToShare);
    }

    /**
     * Returns the link url to the given contest
     *
     * @param contest                       Contest to be shared
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    protected String getContestLinkUrl(Contest contest) throws SystemException, PortalException {
        return serviceContext.getPortalURL() + ContestLocalServiceUtil.getContestLinkUrl(contest);
    }

    /**
     * Returns a fully prepared Facebook share link for the given url
     *
     * @param urlToShare            The url to be shared
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    protected String getFacebookShareLink(String urlToShare) throws SystemException, PortalException {
        String url = String.format(FACEBOOK_PROPOSAL_SHARE_LINK, urlToShare);
        return String.format(LINK_FORMAT_STRING, url, "Facebook");
    }


    /**
     * Returns a fully prepared Twitter share link for the given url including the specified share message
     *
     * @param urlToShare    The url to be shared
     * @param shareMessage                  The message that should be included for sharing
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    protected String getTwitterShareLink(String urlToShare, String shareMessage) throws SystemException {
        try {
            String url = String.format(TWITTER_PROPOSAL_SHARE_LINK, urlToShare, URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "Twitter");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new SystemException(e);
        }
    }

    protected String getLinkedInShareLink(String urlToShare, String shareTitle, String shareMessage) throws SystemException {
        try {
            String url = String.format(LINKEDIN_PROPOSAL_SHARE_LINK, urlToShare,
                    URLEncoder.encode(shareTitle, "UTF-8"),
                    URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "LinkedIn");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new SystemException(e);
        }
    }

    protected String getPinterestShareLink(String urlToShare, String shareMessage) throws SystemException {
        try {
            String url = String.format(PINTEREST_PROPOSAL_SHARE_LINK, urlToShare, URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "Pinterest");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new SystemException(e);
        }
    }

    protected abstract EmailTemplateWrapper getTemplateWrapper() throws SystemException, PortalException;

    protected abstract User getRecipient() throws SystemException, PortalException;

    public void sendEmailNotification() throws SystemException, PortalException {
        EmailTemplateWrapper template = getTemplateWrapper();
        String subject = template.getSubject();
        String body = template.getHeader()+"\n"+template.getFooter();
        sendMessage(subject, body, getRecipient());
    }

    public void sendMessage() throws SystemException, PortalException {
        List<Long> recipients = new ArrayList<Long>();
        recipients.add(getRecipient().getUserId());
        EmailTemplateWrapper template = getTemplateWrapper();
        try {
            String content = template.getHeader() + template.getFooter();
            content = content.replace("\n", " ").replace("\r", " ");
            MessageUtil.sendMessage(template.getSubject(), content, ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, recipients, null);
        } catch (MailEngineException | AddressException e) {
            throw new SystemException(e);
        }
    }

    protected class EmailNotificationTemplate extends EmailTemplateWrapper {
        public EmailNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
            final Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }
            Contest contest = getContest();
            Proposal proposal = getProposal();
            final boolean hasProposal = contest != null && proposal != null;

            switch (tag.nodeName()) {
                case FIRSTNAME_PLACEHOLDER:
                    return new TextNode(getRecipient().getFirstName(), "");
                case FULL_NAME_PLACEHOLDER:
                    return new TextNode(getRecipient().getFullName(), "");
                case CONTEST_LINK_PLACEHOLDER:
                    if (contest == null) {
                        break;
                    }
                    return parseXmlNode(getContestLink(contest));
                case PROPOSAL_LINK_PLACEHOLDER:
                    if (!hasProposal) {
                        break;
                    }
                    return parseXmlNode(getProposalLink(contest, proposal));
                case TWITTER_PLACEHOLDER:
                    if (!hasProposal) {
                        break;
                    }
                    return parseXmlNode(getTwitterShareLink(getProposalLinkUrl(contest, proposal), tag.ownText()));
                case PINTEREST_PLACEHOLDER:
                    if (!hasProposal) {
                        break;
                    }
                    return parseXmlNode(getPinterestShareLink(getProposalLinkUrl(contest, proposal), tag.ownText()));
                case FACEBOOK_PLACEHOLDER:
                    if (!hasProposal) {
                        break;
                    }
                    return parseXmlNode(getFacebookShareLink(getProposalLinkUrl(contest, proposal)));
                case LINKEDIN_PLACEHOLDER:
                    if (!hasProposal) {
                        break;
                    }
                    return parseXmlNode(getLinkedInShareLink(getProposalLinkUrl(contest, proposal), tag.attr("title") , tag.ownText()));
            }
            return null;
        }
    }
}
