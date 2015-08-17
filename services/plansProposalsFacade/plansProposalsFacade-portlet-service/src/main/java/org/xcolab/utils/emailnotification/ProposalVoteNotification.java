package org.xcolab.utils.emailnotification;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.xcolab.enums.ContestPhaseType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by johannes on 8/14/15.
 */
public class ProposalVoteNotification extends EmailNotification {

    private static final String YEAR_FALLBACK = "2015";
    //private static final String DATE_FALLBACK = "July 20, 11:59:59 PM";

    // Additional placeholder strings
    private static final String YEAR_PLACEHOLDER = "year";
    private static final String DEADLINE_PLACEHOLDER = "deadline";
    private static final String CONTEST_DEADLINE_SECTION_PLACEHOLDER = "contest-deadline-section";
    private static final String OTHER_CONTESTS_PLACEHOLDER = "other-contests-link";

    private static final DateFormat customDateFormat = new SimpleDateFormat("MMMM dd, HH:mm:ss a", Locale.US);

    private User recipient;
    private Proposal votedProposal;
    private Contest contest;

    private ProposalVoteTemplate templateWrapper = null;

    public ProposalVoteNotification(Proposal votedProposal, Contest contest, User recipient, ServiceContext serviceContext) {
        super(serviceContext);
        this.votedProposal = votedProposal;
        this.contest = contest;
        this.recipient = recipient;
    }

    @Override
    protected User getRecipient() throws SystemException, PortalException {
        return recipient;
    }

    @Override
    protected Contest getContest() {
        return contest;
    }

    @Override
    protected Proposal getProposal() {
        return votedProposal;
    }

    @Override
    protected ProposalVoteTemplate getTemplateWrapper() throws SystemException, PortalException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = ProposalLocalServiceUtil.getAttribute(votedProposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        templateWrapper = new ProposalVoteTemplate(
                ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(contest.getVoteConfirmationTemplateString()),
                proposalName,
                contest.getContestShortName()
        );

        return templateWrapper;
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

    private String getOtherContestLink(String linkText) {
        final String baseUrl = serviceContext.getPortalURL();
        return String.format(LINK_FORMAT_STRING, baseUrl + "/web/guest/plans", linkText);
    }

    private class ProposalVoteTemplate extends EmailNotificationTemplate {

        public ProposalVoteTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case YEAR_PLACEHOLDER:
                    DateFormat yearFormat = new SimpleDateFormat("yyyy");
                    // This should never happen when contests are properly set up
                    if (Validator.isNull(contest.getCreated())) {
                        return new TextNode(YEAR_FALLBACK, "");
                    } else {
                        return new TextNode(yearFormat.format(contest.getCreated()), "");
                    }
                case DEADLINE_PLACEHOLDER:
                    return new TextNode(customDateFormat.format(getProposalCreationDeadline()) + " EDT", "");
                case CONTEST_DEADLINE_SECTION_PLACEHOLDER:
                    if (Validator.isNull(getProposalCreationDeadline())) {
                        return new TextNode("", "");
                    } else {
                        final String contestDeadlineSectionBody = StringUtil.replace(tag.data(),
                                DEADLINE_PLACEHOLDER, customDateFormat.format(getProposalCreationDeadline()) + " EDT");
                        return new TextNode(contestDeadlineSectionBody, "");
                    }
                case OTHER_CONTESTS_PLACEHOLDER:
                    return parseXmlNode(getOtherContestLink(tag.ownText()));
            }
            return null;
        }

    }
}
