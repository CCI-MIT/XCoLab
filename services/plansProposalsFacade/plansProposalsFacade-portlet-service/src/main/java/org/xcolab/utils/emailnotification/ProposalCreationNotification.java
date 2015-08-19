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
 * This class is responsible for sending out email notifications regarding the creation of a new proposal
 *
 * Created by kmang on 21/05/14.
 */
public class ProposalCreationNotification extends EmailNotification {

    private static final String YEAR_FALLBACK = "2015";
    //private static final String DATE_FALLBACK = "July 20, 11:59:59 PM";

    // Additional placeholder strings
    private static final String YEAR_PLACEHOLDER = "year";
    private static final String DEADLINE_PLACEHOLDER = "deadline";
    private static final String CONTEST_DEADLINE_SECTION_PLACEHOLDER = "contest-deadline-section";

    private static final DateFormat customDateFormat = new SimpleDateFormat("MMMM dd, HH:mm:ss a", Locale.US);

    private Proposal createdProposal;
    private Contest contest;

    private ProposalCreationTemplate templateWrapper = null;

    public ProposalCreationNotification(Proposal createdProposal, Contest contest, ServiceContext serviceContext) {
        super(serviceContext);
        this.createdProposal = createdProposal;
        this.contest = contest;
    }

    @Override
    protected User getRecipient() throws SystemException, PortalException {
        return getProposalAuthor(createdProposal);
    }

    @Override
    protected Contest getContest() {
        return contest;
    }

    @Override
    protected Proposal getProposal() {
        return createdProposal;
    }

    @Override
    protected ProposalCreationTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final String proposalName = ProposalLocalServiceUtil.getAttribute(createdProposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        final String proposalCreationTemplateString = contest.getProposalCreationTemplateString();
        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(proposalCreationTemplateString);
        if (emailTemplate == null) {
            throw new SystemException("Could not load template \""+proposalCreationTemplateString+"\" for "+this.getClass().getName());
        }
        templateWrapper = new ProposalCreationTemplate(emailTemplate, proposalName, contest.getContestShortName());

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

    private class ProposalCreationTemplate extends EmailNotificationTemplate {

        public ProposalCreationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
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
                    final Date proposalCreationDeadline = getProposalCreationDeadline();
                    if (Validator.isNull(proposalCreationDeadline)) {
                        return new TextNode("", "");
                    } else {
                        return new TextNode(customDateFormat.format(proposalCreationDeadline) + " EDT", "");
                    }
                case CONTEST_DEADLINE_SECTION_PLACEHOLDER:
                    if (Validator.isNull(getProposalCreationDeadline())) {
                        return new TextNode("", "");
                    } else {
                        //need to call another layer of replace variables to replace plaholders inside the tag
                        return parseXmlNode(replaceVariables(tag.html()));
                    }
            }
            return null;
        }

    }
}
