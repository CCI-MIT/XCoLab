package org.xcolab.utils.emailnotification;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
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
public class ContestCreationNotification extends EmailNotification {

    private static final String TEMPLATE_NAME = "CONTEST_CREATION_DEFAULT";

    private static final String YEAR_FALLBACK = "2014";

    // Placeholder strings
    private static final String CONTEST_LINK_PLACEHOLDER = "contest-link";
    private static final String TWITTER_PLACEHOLDER = "twitter";
    private static final String FACEBOOK_PLACEHOLDER = "facebook";
    private static final String PINTEREST_PLACEHOLDER = "pinterest";
    private static final String LINKEDIN_PLACEHOLDER = "linkedin";
    private static final String YEAR_PLACEHOLDER = "year";
    private static final String DEADLINE_PLACEHOLDER = "deadline";


    private final Contest createdContest;
    private ContestCreationTemplate templateWrapper;

    public ContestCreationNotification(Contest contest, ServiceContext serviceContext) {
        super(serviceContext);
        this.createdContest = contest;
    }

    @Override
    protected User getRecipient() throws SystemException, PortalException {
        return getContestAuthor(createdContest);
    }

    @Override
    protected ContestCreationTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final ContestEmailTemplate emailTemplate = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(TEMPLATE_NAME);
        if (emailTemplate == null) {
            throw new SystemException("Could not load template \""+TEMPLATE_NAME+"\" for "+this.getClass().getName());
        }
        templateWrapper = new ContestCreationTemplate(emailTemplate, createdContest.getContestShortName());

        return templateWrapper;
    }

    private Date getProposalCreationDeadline() throws SystemException, PortalException {
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(createdContest);

        for (ContestPhase phase : contestPhases) {
            if (phase.getContestPhaseType() == ContestPhaseType.PROPOSAL_CREATION.getTypeId()) {
                return phase.getPhaseEndDate();
            }
        }

        throw new SystemException("Proposal creation phase was not found for createdContest with id " + createdContest.getContestPK());
    }

    private class ContestCreationTemplate extends EmailNotificationTemplate {

        public ContestCreationTemplate(ContestEmailTemplate template, String contestName) {
            super(template, "", contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case CONTEST_LINK_PLACEHOLDER:
                    return parseXmlNode(getContestLink(createdContest));
                case TWITTER_PLACEHOLDER:
                    return parseXmlNode(getTwitterShareLink(getContestLinkUrl(createdContest), tag.ownText()));
                case PINTEREST_PLACEHOLDER:
                    return parseXmlNode(getPinterestShareLink(getContestLinkUrl(createdContest), tag.ownText()));
                case FACEBOOK_PLACEHOLDER:
                    return parseXmlNode(getFacebookShareLink(getContestLinkUrl(createdContest)));
                case LINKEDIN_PLACEHOLDER:
                    return parseXmlNode(getLinkedInShareLink(getContestLinkUrl(createdContest), tag.attr("title"), tag.ownText()));
                case YEAR_PLACEHOLDER:
                    DateFormat yearFormat = new SimpleDateFormat("yyyy");
                    // This should never happen when contests are properly set up
                    if (Validator.isNull(createdContest.getCreated())) {
                        return new TextNode(YEAR_FALLBACK, "");
                    } else {
                        return new TextNode(yearFormat.format(createdContest.getCreated()), "");
                    }
                case DEADLINE_PLACEHOLDER:
                    DateFormat customDateFormat = new SimpleDateFormat("MMMM dd, HH:mm:ss a", Locale.US);
                    return new TextNode(customDateFormat.format(getProposalCreationDeadline()) + " EDT", "");
            }
            return null;
        }
    }
}
