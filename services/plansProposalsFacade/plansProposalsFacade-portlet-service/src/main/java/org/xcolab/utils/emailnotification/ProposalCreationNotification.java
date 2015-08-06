package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import org.xcolab.enums.ContestPhaseType;
import org.xcolab.utils.judging.ContestEmailTemplateWrapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class is responsible for sending out email notifications regarding the creation of a new proposal
 *
 * Created by kmang on 21/05/14.
 */
public class ProposalCreationNotification extends EmailNotification {

    private static final String CONTEST_DEADLINE_SECTION_TEMPLATE = "<li><b>The contest deadline is <deadline/></b> (except for proposals created in the Workspace, where there is no deadline). You can edit your proposal as many times as you want before then, but make sure your final " +
            "version is updated before the deadline.  At that point, the most recent version of your proposal will be frozen and submitted to the Judges for review.  " +
            "To learn more about the judging process, click <a href='http://climatecolab.org/web/guest/crowdsourcing'>click here</a>.</li>";

    private static final String PROPOSAL_SHARE_TEXT = "I just created a new proposal at the Climate CoLab. Check it out!";
    private static final String PROPOSAL_SHARE_TITLE = "New proposal at @ClimateCoLab";

    private static final String YEAR_FALLBACK = "2015";
    //private static final String DATE_FALLBACK = "July 20, 11:59:59 PM";

    // Additional placeholder strings
    private static final String FIRSTNAME_PLACEHOLDER = "<firstname/>";
    private static final String YEAR_PLACEHOLDER = "<year/>";
    private static final String PROPOSAL_LINK_PLACEHOLDER = "<proposal-link/>";
    private static final String CONTEST_LINK_PLACEHOLDER = "<contest-link/>";
    private static final String TWITTER_PLACEHOLDER = "<twitter/>";
    private static final String FACEBOOK_PLACEHOLDER = "<facebook/>";
    private static final String PINTEREST_PLACEHOLDER = "<pinterest/>";
    private static final String LINKEDIN_PLACEHOLDER = "<linkedin/>";
    private static final String DEADLINE_PLACEHOLDER = "<deadline/>";
    private static final String CONTEST_DEADLINE_SECTION_PLACEHOLDER = "<contest-deadline-section/>";

    private Proposal createdProposal;
    private Contest contest;

    private ContestEmailTemplateWrapper templateWrapper = null;

    public ProposalCreationNotification(Proposal createdProposal, Contest contest, ServiceContext serviceContext) {
        super(serviceContext);
        this.createdProposal = createdProposal;
        this.contest = contest;
    }

    private ContestEmailTemplateWrapper getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = ProposalLocalServiceUtil.getAttribute(createdProposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        templateWrapper = new ContestEmailTemplateWrapper(
                ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(contest.getTemplateTypeString()),
                proposalName,
                contest.getContestShortName()
        );

        return templateWrapper;
    }

    @Override
    public void sendEmailNotification() throws PortalException, SystemException {
        String subject = getTemplateWrapper().getSubject();
        String body = populateBodyString();
        sendMessage(subject, body, getProposalAuthor(createdProposal));
    }

    private String populateBodyString() throws PortalException, SystemException {
        String body = getTemplateWrapper().getHeader()+"\n"+getTemplateWrapper().getFooter();
        body = StringUtil.replace(body, FIRSTNAME_PLACEHOLDER, getProposalAuthor(createdProposal).getFirstName());
        body = StringUtil.replace(body, PROPOSAL_LINK_PLACEHOLDER, getProposalLink(contest, createdProposal));
        body = StringUtil.replace(body, CONTEST_LINK_PLACEHOLDER, getContestLink(contest));
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
        // This should never happen when the contest phases are set up properly - do not include the deadline section in this case
        if (Validator.isNull(getProposalCreationDeadline())) {
            body = StringUtil.replace(body, CONTEST_DEADLINE_SECTION_PLACEHOLDER, "");

        } else {
            // Todo test before committing
            String contestDeadlineSectionBody = StringUtil.replace(CONTEST_DEADLINE_SECTION_TEMPLATE,
                    DEADLINE_PLACEHOLDER, customDateFormat.format(getProposalCreationDeadline()) + " EDT");
            body = StringUtil.replace(body, CONTEST_DEADLINE_SECTION_PLACEHOLDER, contestDeadlineSectionBody);
        }
        return body;
    }

    private Date getProposalCreationDeadline() throws SystemException, PortalException {
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(contest);
        try {
            return getActiveCreationPhase(contestPhases).getPhaseEndDate();
        }
        // No active proposal creation phase could be found -
        // should never be the case unless an admin is creating a proposal in a non-creation phase
        catch(SystemException exception) {
            return null;
        }
    }

    private ContestPhase getActiveCreationPhase(List<ContestPhase> contestPhases) throws SystemException {
        for (ContestPhase phase : contestPhases) {
            if (phase.getContestPhaseType() == ContestPhaseType.PROPOSAL_CREATION.getTypeId() &&
                    ContestPhaseLocalServiceUtil.getPhaseActive(phase)) {

                return phase;
            }
        }

        throw new SystemException("Active proposal creation phase was not found for createdContest with id " + contest.getContestPK());
    }
}
