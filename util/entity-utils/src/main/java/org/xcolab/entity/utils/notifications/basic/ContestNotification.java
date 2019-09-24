package org.xcolab.entity.utils.notifications.basic;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

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
    protected final ContestWrapper contest;
    protected final String templateName;
    private final UserWrapper recipient;
    private ContestNotificationTemplate templateWrapper;

    public ContestNotification(ContestWrapper contest, UserWrapper recipient, String templateName) {
        this.contest = contest;
        this.recipient = recipient;
        this.templateName = templateName;
    }

    @Override
    protected UserWrapper getRecipient() {
        return recipient;
    }

    @Override
    protected ContestWrapper getContest() {
        return contest;
    }

    @Override
    protected ContestNotificationTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(templateName);

        templateWrapper = new ContestNotificationTemplate(emailTemplate, "", contest.getTitle());

        return templateWrapper;
    }

    @Override
    protected Long getReferenceId(){
        return this.contest.getId();
    }
    private Date getActivePhaseDeadline() {
        return StaticContestContext.getContestClient().getActivePhase(contest.getId())
                .getPhaseEndDate();
    }

    private String getOtherContestLink(String linkText) {
        return String.format(LINK_FORMAT_STRING,
                baseUrl + contest.getContestType().getContestBaseUrl(), linkText);
    }

    protected class ContestNotificationTemplate extends EmailNotificationTemplate {

        public ContestNotificationTemplate(IEmailTemplate template, String proposalName, String contestName) {
            super(template, proposalName, contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case YEAR_PLACEHOLDER:
                    if (contest.getCreatedAt() == null) {
                        return new TextNode(Long.toString(contest.getContestYear()));
                    } else {
                        DateFormat yearFormat = new SimpleDateFormat("yyyy");
                        return new TextNode(yearFormat.format(contest.getCreatedAt()));
                    }
                case DEADLINE_PLACEHOLDER:
                    final Date phaseDeadline = getActivePhaseDeadline();
                    if (phaseDeadline == null) {
                        return new TextNode("");
                    } else {
                        final DateTimeFormatter dateTimeFormatterWithTimeZone = DATE_TIME_FORMATTER.withZone(
                                DateTimeZone.forID(ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get()));
                        return new TextNode(new DateTime(phaseDeadline).toString(dateTimeFormatterWithTimeZone));
                    }
                case CONTEST_DEADLINE_SECTION_PLACEHOLDER:
                    if (getActivePhaseDeadline() == null) {
                        return new TextNode("");
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
