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
import com.liferay.util.mail.MailEngineException;
import org.apache.commons.lang.StringEscapeUtils;

import javax.mail.internet.AddressException;
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

    protected void sendMessage(String subject, String body, List<Long> recipientIds) {
        try {
            MessageUtil.sendMessage(subject, body, ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, recipientIds, null);
        } catch (MailEngineException | AddressException e) {
            _log.error("Could not send vote message", e);
        } catch (PortalException | SystemException e) {
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
        return String.format(FACEBOOK_PROPOSAL_SHARE_LINK, proposalUrl);
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
    protected String getProposalTwitterShareLink(Contest contest, Proposal proposalToShare, String shareMessage) throws SystemException, PortalException {
        final String proposalUrl = serviceContext.getPortalURL() + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposalToShare);
        return String.format(TWITTER_PROPOSAL_SHARE_LINK, proposalUrl, StringEscapeUtils.escapeHtml(shareMessage));
    }

    public abstract void sendEmailNotification() throws SystemException, PortalException;
}
