package org.xcolab.utils.emailnotification;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import org.xcolab.enums.ContestPhaseType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class is responsible for sending out email notifications regarding the creation of a new proposal
 *
 * Created by kmang on 21/05/14.
 */
public class ProposalCreationNotification extends EmailNotification {
    /**
     * The subject of the proposal creation success notification
     */
    private static final String PROPOSAL_CREATION_SUCCESS_NOTIFICATION_SUBJECT = "You created a proposal!  Here's what's next...";
    /**
     * The body template for the proposal creation success notification
     */
    private static final String PROPOSAL_CREATION_SUCCESS_NOTIFICATION_BODY_TEMPLATE = "Dear FIRSTNAME_PLACEHOLDER,<br/>" +
            "<br/>Congratulations!  You recently created the proposal, PROPOSAL_PLACEHOLDER, in the CONTEST_PLACEHOLDER contest on the Climate CoLab." +
            "<br/><br/>" +
            "<b>What's next:</b>" +
            "<ul>" +
            "<li><b>Share your proposal</b> with your friends and colleagues over TWITTER_PLACEHOLDER, FACEBOOK_PLACEHOLDER, PINTEREST_PLACEHOLDER, LINKEDIN_PLACEHOLDER & more.  " +
            "Ask for support, comments, or just to check out what you're working on.  Don't forget to mention us @ClimateCoLab!</li>" +
            "<li><b>The contest deadline is DEADLINE_PLACEHOLDER</b> (except for proposals created in the Workspace, where there is no deadline). You can edit your proposal as many times as you want before then, but make sure your final " +
            "version is updated before the deadline.  At that point, the most recent version of your proposal will be frozen and submitted to the Judges for review.  " +
            "To learn more about the judging process, click <a href='http://climatecolab.org/web/guest/crowdsourcing'>click here</a>.</li>" +
            "</ul>" +
            "Happy CoLaborating!" +
            "<br/><br/>" +
            "Sincerely," +
            "<br/><br/>" +
            "The Climate CoLab Team<br/>" +
            "<a href='mailto:admin@climatecolab.org'>admin@climatecolab.org</a><br/>" +
            "<a href='http://www.climatecolab.org'>www.climatecolab.org</a>";

    private static final String PROPOSAL_SHARE_TEXT = "I just created a new proposal at the Climate CoLab. Check it out!";
    private static final String PROPOSAL_SHARE_TITLE = "New proposal at @ClimateCoLab";

    private static final String YEAR_FALLBACK = "2015";
    private static final String DATE_FALLBACK = "July 20, 11:59:59 PM";

    // Placeholder strings
    private static final String FIRSTNAME_PLACEHOLDER = "FIRSTNAME_PLACEHOLDER";
    private static final String PROPOSAL_PLACEHOLDER = "PROPOSAL_PLACEHOLDER";
    private static final String CONTEST_PLACEHOLDER = "CONTEST_PLACEHOLDER";
    private static final String TWITTER_PLACEHOLDER = "TWITTER_PLACEHOLDER";
    private static final String FACEBOOK_PLACEHOLDER = "FACEBOOK_PLACEHOLDER";
    private static final String PINTEREST_PLACEHOLDER = "PINTEREST_PLACEHOLDER";
    private static final String LINKEDIN_PLACEHOLDER = "LINKEDIN_PLACEHOLDER";
    private static final String YEAR_PLACEHOLDER = "YEAR_PLACEHOLDER";
    private static final String DEADLINE_PLACEHOLDER = "DEADLINE_PLACEHOLDER";

    private Proposal createdProposal;
    private Contest contest;

    public ProposalCreationNotification(Proposal createdProposal, Contest contest, ServiceContext serviceContext) {
        super(serviceContext);
        this.createdProposal = createdProposal;
        this.contest = contest;
    }

    @Override
    public void sendEmailNotification() throws PortalException, SystemException {
        String subject = PROPOSAL_CREATION_SUCCESS_NOTIFICATION_SUBJECT;
        String body = populateBodyString();
        sendMessage(subject, body, getProposalAuthor(createdProposal));

    }

    private String populateBodyString() throws PortalException, SystemException {
        String body = StringUtil.replace(PROPOSAL_CREATION_SUCCESS_NOTIFICATION_BODY_TEMPLATE, FIRSTNAME_PLACEHOLDER, getProposalAuthor(createdProposal).getFirstName());
        body = StringUtil.replace(body, PROPOSAL_PLACEHOLDER, getProposalLink(contest, createdProposal));
        body = StringUtil.replace(body, CONTEST_PLACEHOLDER, getContestLink(contest));
        body = StringUtil.replace(body, TWITTER_PLACEHOLDER, getProposalTwitterShareLink(contest, createdProposal, PROPOSAL_SHARE_TEXT));
        body = StringUtil.replace(body, FACEBOOK_PLACEHOLDER, getProposalFacebookShareLink(contest, createdProposal));
        body = StringUtil.replace(body, PINTEREST_PLACEHOLDER, getProposalPinterestShareLink(contest, createdProposal, PROPOSAL_SHARE_TEXT));
        body = StringUtil.replace(body, LINKEDIN_PLACEHOLDER, getProposalLinkedInShareLink(contest, createdProposal, PROPOSAL_SHARE_TITLE, PROPOSAL_SHARE_TEXT));

        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        // This should never happen when contests are properly set up
        if (Validator.isNull(contest.getCreated())) {
            body = StringUtil.replace(body, YEAR_PLACEHOLDER, YEAR_FALLBACK);
        } else {
            body = StringUtil.replace(body, YEAR_PLACEHOLDER, yearFormat.format(contest.getCreated()));
        }

        DateFormat customDateFormat = new SimpleDateFormat("MMMM dd, HH:mm:ss a", Locale.US);
        // This should never happen when the contest phases are set up properly
        if (Validator.isNull(getProposalCreationDeadline())) {
            body = StringUtil.replace(body, DEADLINE_PLACEHOLDER, DATE_FALLBACK + " EDT");

        } else {
            body = StringUtil.replace(body, DEADLINE_PLACEHOLDER, customDateFormat.format(getProposalCreationDeadline()) + " EDT");
        }
        return body;
    }

    private Date getProposalCreationDeadline() throws SystemException, PortalException {
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(contest);

        for (ContestPhase phase : contestPhases) {
            if (phase.getContestPhaseType() == ContestPhaseType.PROPOSAL_CREATION.getTypeId()) {
                return phase.getPhaseEndDate();
            }
        }

        throw new SystemException("Proposal creation phase was not found for contest with id " + contest.getContestPK());
    }
}
