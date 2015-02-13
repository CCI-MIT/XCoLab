package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Contest;
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

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * This abstract class defines common helper methods for Email Notification Util classes
 *
 * Created by kmang on 21/05/14.
 */
public abstract class EmailNotification {
    protected static final long ADMINISTRATOR_USER_ID = 10144L;

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
        final String proposalLinkUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal);
        return String.format(LINK_FORMAT_STRING, proposalLinkUrl, proposalName);
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

    protected User getContestlAuthor(Contest contest) throws SystemException, PortalException {
        return UserLocalServiceUtil.getUserById(contest.getAuthorId());
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
     * Returns a fully prepared Facebook share link for the given proposal including the specified share message
     *
     * @param contest                       Contest in which the proposal is in
     * @param proposalToShare               The Proposal that should be shared
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    protected String getProposalFacebookShareLink(Contest contest, Proposal proposalToShare) throws SystemException, PortalException {
        final String proposalUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposalToShare);
        String url = String.format(FACEBOOK_PROPOSAL_SHARE_LINK, proposalUrl);
        return String.format(LINK_FORMAT_STRING, url, "Facebook");
    }


    /**
     * Returns a fully prepared Twitter share link for the given proposal including the specified share message
     *
     * @param contest                       Contest in which the proposal is in
     * @param proposalToShare               The Proposal that should be shared
     * @param shareMessage                  The message that should be included for sharing
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    protected String getProposalTwitterShareLink(Contest contest, Proposal proposalToShare, String shareMessage) throws SystemException {
        final String proposalUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposalToShare);
        try {
            String url = String.format(TWITTER_PROPOSAL_SHARE_LINK, proposalUrl, URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "Twitter");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new SystemException(e);
        }
    }

    protected String getProposalLinkedInShareLink(Contest contest, Proposal proposalToShare, String shareTitle, String shareMessage) throws SystemException {
        final String proposalUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposalToShare);
        try {
            String url = String.format(LINKEDIN_PROPOSAL_SHARE_LINK, proposalUrl,
                    URLEncoder.encode(shareTitle, "UTF-8"),
                    URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "LinkedIn");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new SystemException(e);
        }
    }

    protected String getProposalPinterestShareLink(Contest contest, Proposal proposalToShare, String shareMessage) throws SystemException {
        final String proposalUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposalToShare);
        try {
            String url = String.format(PINTEREST_PROPOSAL_SHARE_LINK, proposalUrl, URLEncoder.encode(shareMessage, "UTF-8"));
            return String.format(LINK_FORMAT_STRING, url, "Pinterest");
        } catch (UnsupportedEncodingException e) {
            // Should never happen
            throw new SystemException(e);
        }
    }

    public abstract void sendEmailNotification() throws SystemException, PortalException;
}
