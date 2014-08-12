package org.xcolab.utils.emailnotification;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import java.util.List;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteQuestionNotification extends EmailNotification {

    private static final String MESSAGE_BODY_FORMAT_STRING = "Hi %s,<br/><br/>" +
            "This year's voting period has started! Vote now to help your favorite proposals win the Popular Choice award. " +
            "We have noted that you have supported the following proposals in the contest <b>%s</b>:<br/><br/>" +
            "%s<br/><br/>" +
            "Click the links above to visit the proposal page and cast your vote.  Please note that you can only vote for one proposal per contest." +
            "<br/><br/>Thank you!" +
            "<br/><br/>Sincerely,<br/>The Climate Colab Team";

    private static final String SUBJECT_FORMAT_STRING = "Please vote for your favorite proposal in contest %s";


    private User recipient;
    private Contest contest;
    private List<Proposal> supportedProposals;

    public ContestVoteQuestionNotification(User recipient, Contest contest, List<Proposal> supportedProposals, ServiceContext serviceContext) {
        super(serviceContext);
        this.recipient = recipient;
        this.contest = contest;
        this.supportedProposals = supportedProposals;
    }

    @Override
    public void sendEmailNotification() throws SystemException, PortalException {
        StringBuilder messageBody = new StringBuilder();

        for (Proposal proposal : supportedProposals) {

            messageBody.append(String.format(getProposalLinkForDirectVoting(contest, proposal) + "<br />"));
        }

        String subject = String.format(SUBJECT_FORMAT_STRING, contest.getContestShortName());
        String body = String.format(MESSAGE_BODY_FORMAT_STRING, recipient.getFullName(), contest.getContestShortName(), messageBody.toString());
        sendMessage(subject, body, recipient);
    }






}
