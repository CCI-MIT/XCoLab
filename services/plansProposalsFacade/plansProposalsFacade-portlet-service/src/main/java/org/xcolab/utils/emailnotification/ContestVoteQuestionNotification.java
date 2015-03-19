package org.xcolab.utils.emailnotification;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * Created by kmang on 21/05/14.
 */
public class ContestVoteQuestionNotification extends EmailNotification {


    private static final String MESSAGE_BODY_FORMAT_STRING = "Dear %s," +
            "<br/><br/>The voting period for the %s contest has started! Vote now to help your favorite proposal win the Popular Choice award." +
            "<br/><br/>You are currently supporting the following Finalist proposal(s) in the <b>%s</b> contest:<br/><br/>" +
            "%s<br/>" +
            "<b>To cast your vote, click the link above of the proposal you would like to win.</b>" +
            "<br/><br/>Please note that you can vote for only one proposal per contest. You must have a valid email address for your vote to be counted. Votes cast by members with invalid email addresses will be removed. See the other proposals in this contest by visiting %s." +
            "<br/><br/>Thank you! And be sure to stay tuned for the results." +
            "<br/><br/>Sincerely,<br/>The Climate Colab Team";

    private static final String SUBJECT_FORMAT_STRING = "Vote for your favorite proposal in the %s contest";


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
            messageBody.append(String.format(getProposalLinkForDirectVoting(contest, proposal) + " by " +  UserLocalServiceUtil.getUser(proposal.getAuthorId()).getFullName() + "<br />"));
        }

        String subject = String.format(SUBJECT_FORMAT_STRING, contest.getContestShortName());
        String body = String.format(MESSAGE_BODY_FORMAT_STRING, recipient.getFullName(),contest.getContestShortName(), contest.getContestShortName(), messageBody.toString(),getContestLink(contest));
        sendMessage(subject, body, recipient);
    }






}
