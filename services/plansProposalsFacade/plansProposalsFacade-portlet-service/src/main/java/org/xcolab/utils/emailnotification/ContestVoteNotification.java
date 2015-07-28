package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteNotification extends EmailNotification {
    private static final String SUPPORT_TO_VOTE_SUCCESS_MESSAGE_BODY_FORMAT_STRING = "Hi %s,<br/><br/>" +
            "This year's voting period has started! We have automatically transferred your support for the proposal %s in the %s contest to an official vote." +
            "<br/><br/>Share this proposal with your friends!  %s  %s  Email" +
            "<br/><br/>Please note that you can only vote for one proposal per contest.  To change your vote in this contest, simply vote for another proposal in the %s contest and your vote will transfer. " +
            "To retract your vote in this contest, click \"Retract vote\" on the %s proposal page." +
            "<br /><br/>Thanks!" +
            "<br/><br/>Sincerely,<br/>The Climate Colab Team";
    private static final String SUPPORT_TO_VOTE_SUCCESS_SUBJECT_FORMAT_STRING = "Your vote in contest %s";

    private static final String PROPOSAL_SHARE_MESSAGE_FORMAT_STRING = "I have voted for the Climate CoLab's proposal <b>%s</b>. Check it out.";


    private User recipient;
    private Contest contest;
    private Proposal votedProposal;

    public ContestVoteNotification(User recipient, Contest contest, Proposal votedProposal, ServiceContext serviceContext) {
        super(serviceContext);
        this.recipient = recipient;
        this.contest = contest;
        this.votedProposal = votedProposal;
    }

    @Override
    public void sendEmailNotification() throws SystemException, PortalException {
        final String proposalName = ProposalLocalServiceUtil.getAttribute(votedProposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
        final String shareMessage = String.format(PROPOSAL_SHARE_MESSAGE_FORMAT_STRING, proposalName);

        String subject = String.format(SUPPORT_TO_VOTE_SUCCESS_SUBJECT_FORMAT_STRING, contest.getContestShortName());
        String body = String.format(SUPPORT_TO_VOTE_SUCCESS_MESSAGE_BODY_FORMAT_STRING,
                recipient.getFullName(), getProposalLink(contest, votedProposal),
                getContestLink(contest),
                getProposalTwitterShareLink(contest, votedProposal, shareMessage),
                getProposalFacebookShareLink(contest, votedProposal),
                getContestLink(contest),
                getProposalLink(contest, votedProposal));

        sendMessage(subject,body, recipient);
    }
}
