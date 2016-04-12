package org.xcolab.utils.emailnotification.basic;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.xcolab.enums.ConfigurationAttributeKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContestNotification extends EmailNotification {

    // Additional placeholder strings
    private static final String YEAR_PLACEHOLDER = "year";
    private static final String DEADLINE_PLACEHOLDER = "deadline";
    private static final String CONTEST_DEADLINE_SECTION_PLACEHOLDER = "contest-deadline-section";
    private static final String OTHER_CONTESTS_PLACEHOLDER = "other-contests-link";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("MMMM dd, HH:mm:ss a z");
    protected final Contest contest;
    protected final String templateName;
    private final User recipient;
    private ContestNotificationTemplate templateWrapper;

    public ContestNotification(Contest contest, User recipient, String templateName,
            ServiceContext serviceContext) {
        super(serviceContext);
        this.contest = contest;
        this.recipient = recipient;
        this.templateName = templateName;
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
    protected ContestNotificationTemplate getTemplateWrapper() throws PortalException, SystemException {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final ContestEmailTemplate emailTemplate =
                ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(templateName);
        if (emailTemplate == null) {
            throw new SystemException(
                    "Could not load template \"" + templateName + "\" for " + this.getClass().getName());
        }
        templateWrapper = new ContestNotificationTemplate(emailTemplate, "", contest.getContestShortName());

        return templateWrapper;
    }

    private Date getActivePhaseDeadline() throws SystemException, PortalException {
        try {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest).getPhaseEndDate();
        } catch (SystemException e) {
            return null;
        }
    }

    private String getOtherContestLink(String linkText) {
        final String baseUrl = serviceContext.getPortalURL();
        return String.format(LINK_FORMAT_STRING, baseUrl + "/web/guest/plans", linkText);
    }

    protected class ContestNotificationTemplate extends EmailNotificationTemplate {

        public ContestNotificationTemplate(ContestEmailTemplate template, String proposalName, String contestName) {
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
                    if (Validator.isNull(contest.getCreated())) {
                        return new TextNode(Long.toString(contest.getContestYear()), "");
                    } else {
                        return new TextNode(yearFormat.format(contest.getCreated()), "");
                    }
                case DEADLINE_PLACEHOLDER:
                    final Date phaseDeadline = getActivePhaseDeadline();
                    if (Validator.isNull(phaseDeadline)) {
                        return new TextNode("", "");
                    } else {
                        final DateTimeFormatter dateTimeFormatterWithTimeZone = DATE_TIME_FORMATTER.withZone(
                                DateTimeZone.forID(ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.getStringValue()));
                        return new TextNode(new DateTime(phaseDeadline).toString(dateTimeFormatterWithTimeZone), "");
                    }
                case CONTEST_DEADLINE_SECTION_PLACEHOLDER:
                    if (Validator.isNull(getActivePhaseDeadline())) {
                        return new TextNode("", "");
                    } else {
                        //need to call another layer of replace variables to replace placeholders inside the tag
                        return parseXmlNode(replaceVariables(tag.html()));
                    }
                case OTHER_CONTESTS_PLACEHOLDER:
                    return parseXmlNode(getOtherContestLink(tag.ownText()));
                default:
            }
            return null;
        }
    }
}
