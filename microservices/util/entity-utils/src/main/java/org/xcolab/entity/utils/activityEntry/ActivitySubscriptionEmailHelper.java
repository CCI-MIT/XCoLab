package org.xcolab.entity.utils.activityEntry;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.entity.utils.NotificationUnregisterUtils;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.entity.utils.subscriptions.ActivitySubscriptionConstraint;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.html.HtmlUtil;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;

public class ActivitySubscriptionEmailHelper {

    private static Date lastEmailNotification = new Date();

    // 1 am
    private final static int DAILY_DIGEST_TRIGGER_HOUR = 1;

    private final static Logger _log = LoggerFactory.getLogger(ActivitySubscriptionEmailHelper.class);

    private static Date lastDailyEmailNotification = getLastDailyEmailNotificationDate();

    private static final String FAQ_DIGEST_URL_PATH = "/faqs#digest";

    private static final String UNSUBSCRIBE_INSTANT_NOTIFICATION_TEXT = "You are receiving this message because you subscribed to a <contest/>, <proposal/> or discussion post on the <colab-name/>.  " +
            "To receive all notifications in a daily digest, please click <a href='FAQ_DIGEST_LINK_PLACEHOLDER'>here</a> for instructions.  " +
            "To stop receiving notifications from this <contest/>, <proposal/> or discussion post, please click <a href='UNSUBSCRIBE_SUBSCRIPTION_LINK_PLACEHOLDER'>here</a>.";

    private static final String FAQ_DIGEST_LINK_PLACEHOLDER = "FAQ_DIGEST_LINK_PLACEHOLDER";

    private static final String UNSUBSCRIBE_SUBSCRIPTION_LINK_PLACEHOLDER = "UNSUBSCRIBE_SUBSCRIPTION_LINK_PLACEHOLDER";


    private final static String MESSAGE_FOOTER_TEMPLATE = "<br /><br />\n<hr /><br />\n"
            + "To configure your notification preferences, visit your <a href=\"USER_PROFILE_LINK\">profile</a> page";

    private final static String USER_PROFILE_LINK_PLACEHOLDER = "USER_PROFILE_LINK";

    private final static String USER_PROFILE_LINK_TEMPLATE = "DOMAIN_PLACEHOLDER/web/guest/member/-/member/userId/USER_ID";

    private final static String USER_ID_PLACEHOLDER = "USER_ID";

    private final static String DOMAIN_PLACEHOLDER = "DOMAIN_PLACEHOLDER";

    private static final String DAILY_DIGEST_NOTIFICATION_SUBJECT_TEMPLATE = "<colab-name/> Activities – Daily Digest DATE";

    private static final String DAILY_DIGEST_NOTIFICATION_SUBJECT_DATE_PLACEHOLDER = "DATE";

    private static final String DAILY_DIGEST_ENTRY_TEXT = "<colab-name/> Digest for DATE";

    private static final String UNSUBSCRIBE_DAILY_DIGEST_NOTIFICATION_TEXT = "You are receiving this message because you subscribed to receiving a daily digest of activities on the <colab-name/>.  " +
            "To stop receiving these notifications, please click <a href='UNSUBSCRIBE_SUBSCRIPTION_LINK_PLACEHOLDER'>here</a>.";

    public static void sendEmailNotifications() {


        //to ease debug please leave it here

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            lastEmailNotification = sdf.parse("2016-08-01 00:00:00");
            lastDailyEmailNotification = sdf.parse("2016-08-01 00:00:00");
        } catch (ParseException e) {
            lastEmailNotification = new Date();
        }*/


        synchronized (lastEmailNotification) {
            List<ActivityEntry> res = getActivitiesAfter(lastEmailNotification);
            for (ActivityEntry activity : res) {
                try {
                    sendInstantNotifications(activity);
                }
                catch (Throwable e) {
                    _log.error(String.format("Can't process activity when sending notifications ( %s )", activity), e);
                }
            }
            lastEmailNotification = new Date();
        }

        synchronized (lastDailyEmailNotification) {
            Date now = new Date();

            // Send the daily digest at the predefined hour only
            if (now.getTime() - lastDailyEmailNotification.getTime() > 3600 * 1000
                    && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == DAILY_DIGEST_TRIGGER_HOUR) {
                List<ActivityEntry> res = getActivitiesAfter(lastDailyEmailNotification);
                sendDailyDigestNotifications(res);
                lastDailyEmailNotification = now;
            }
        }


    }

    private static void sendDailyDigestNotifications(List<ActivityEntry> activities) {
        Map<Long, List<ActivityEntry>> userActivitiesDigestMap = getUserToActivityDigestMap(activities);

        String subject = StringUtils.replace(DAILY_DIGEST_NOTIFICATION_SUBJECT_TEMPLATE, DAILY_DIGEST_NOTIFICATION_SUBJECT_DATE_PLACEHOLDER, dateToDateString(lastDailyEmailNotification));
        // Send the digest to each user which is included in the set of subscriptions
        for (Map.Entry<Long, List<ActivityEntry>> entry : userActivitiesDigestMap.entrySet()) {
            try {
                final Member recipient = MembersClient.getMember(entry.getKey());
                final List<ActivityEntry> userDigestActivities = entry.getValue();
                String body = getDigestMessageBody(userDigestActivities);
                String unsubscribeFooter = getUnsubscribeDailyDigestFooter(NotificationUnregisterUtils.getActivityUnregisterLink(recipient));

                sendEmailMessage(recipient, subject, body, unsubscribeFooter, ConfigurationAttributeKey.COLAB_URL.get());
            } catch (MemberNotFoundException ignored) {
                _log.error("sendDailyDigestNotifications: MemberNotFound : {}",
                        ignored.getMessage());
            }
        }
    }

    private static String getUnsubscribeDailyDigestFooter(String unsubscribeUrl) {
        return StringUtils.replace(UNSUBSCRIBE_DAILY_DIGEST_NOTIFICATION_TEXT, UNSUBSCRIBE_SUBSCRIPTION_LINK_PLACEHOLDER, unsubscribeUrl);
    }

    private static String getDigestMessageBody(List<ActivityEntry> userDigestActivities) {
        Comparator<ActivityEntry> socialActivityClassIdComparator = new Comparator<ActivityEntry>() {
            @Override
            public int compare(ActivityEntry o1, ActivityEntry o2) {
                return (int)(o1.getPrimaryType() - o2.getPrimaryType());
            }
        };
        Comparator<ActivityEntry> socialActivityCreateDateComparator = new Comparator<ActivityEntry>() {
            @Override
            public int compare(ActivityEntry o1, ActivityEntry o2) {
                return (int)(o1.getCreateDate().getTime() - o2.getCreateDate().getTime());
            }
        };

        ComparatorChain comparatorChain = new ComparatorChain();
        comparatorChain.addComparator(socialActivityClassIdComparator);
        comparatorChain.addComparator(socialActivityCreateDateComparator);
        StringBuilder body = new StringBuilder();
        try {
            Collections.sort(userDigestActivities, comparatorChain);

            body.append(StringUtils.replace(DAILY_DIGEST_ENTRY_TEXT, DAILY_DIGEST_NOTIFICATION_SUBJECT_DATE_PLACEHOLDER, dateToDateString(lastDailyEmailNotification)));
            body.append("<br/><br/>");

            for (ActivityEntry socialActivity : userDigestActivities) {
                //prevent null pointer exceptions which might happen at this point
                if (socialActivity == null) {
                    continue;
                }
                if (socialActivity.getPrimaryType().equals(ActivityEntryType.DISCUSSION.getPrimaryTypeId())) {
                    try {
                        StringBuilder bodyWithComment = new StringBuilder();
                        bodyWithComment.append(socialActivity.getActivityEntryBody());
                        bodyWithComment.append("<br><br><div style='margin-left:20px;>");
                        bodyWithComment.append("<div style='margin-top:14pt;margin-bottom:14pt;'>");
                        Long commentId = new Long(socialActivity.getExtraData());
                        Comment comment = CommentClientUtil.getComment(commentId);
                        bodyWithComment.append(comment.getContent());
                        bodyWithComment.append("</div></div>");
                        body.append("<div style='margin-left: 10px'>").append(bodyWithComment.toString()).append("</div><br/><br/>");
                    } catch (CommentNotFoundException ex) {
                        body.append("<div style='margin-left: 10px'>").append(socialActivity.getActivityEntryBody()).append("</div><br/><br/>");
                    }

                } else {
                    body.append("<div style='margin-left: 10px'>").append(socialActivity.getActivityEntryBody()).append("</div><br/><br/>");
                }

            }
        } catch(IllegalArgumentException comparatorFailed) {
            _log.debug("Comparator failed {}", comparatorFailed.getLocalizedMessage());
        }
        return body.toString();
    }

    private static Map<Long, List<ActivityEntry>> getUserToActivityDigestMap(List<ActivityEntry> activities) {
        Map<Long, List<ActivityEntry>> userDigestActivitiesMap = new HashMap<>();


        for (ActivityEntry activity : activities) {
            // Aggregate all activities for all users
            for (ActivitySubscription subscriptionObj : getActivitySubscribers(activity)) {

                Long recipientId = subscriptionObj.getReceiverId();

                if (subscriptionObj.getReceiverId() == activity.getMemberId().longValue()) {
                    continue;
                }

                final MessagingUserPreferences messagingPreferences = MessagingClient
                        .getMessagingPreferencesForMember(recipientId);
                if (messagingPreferences.getEmailOnActivity()
                        && messagingPreferences.getEmailActivityDailyDigest()) {

                    List<ActivityEntry> userDigestActivities = userDigestActivitiesMap.get(recipientId);
                    if (userDigestActivities == null) {
                        userDigestActivities = new ArrayList<>();
                        userDigestActivitiesMap.put(recipientId, userDigestActivities);
                    }
                    userDigestActivities.add(activity);
                }
            }
        }

        return userDigestActivitiesMap;
    }

    private static List<ActivityEntry> getActivitiesAfter(Date minDate) {

        List<ActivityEntry> activityObjects = ActivitiesClientUtil.getActivityEntriesAfter(minDate);

        //clean list of activities first in order not to send out activities concerning the same proposal multiple times
        ActivityEntryMessageLimitationHelper h = new ActivityEntryMessageLimitationHelper(
                ActivityEntryType.PROPOSAL
                .getPrimaryTypeId());

        return h.process(activityObjects);
    }

    private static void sendInstantNotifications(ActivityEntry activity) {

        String subject = clearLinksInSubject(activity.getActivityEntryTitle()) + " ";//get old implementation for subject
        String messageTemplate = activity.getActivityEntryBody();

        Set<Member> recipients = new HashSet<>();
        Map<Long, ActivitySubscription> subscriptionsPerUser = new HashMap<>();

        for (Object subscriptionObj : getActivitySubscribers(activity)) {
            ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;

            if (subscription.getReceiverId() == activity.getMemberId().longValue()) {
                continue;
            }
            try {
                Member member = MembersClient.getMember(subscription.getReceiverId());
                recipients.add(member);
                subscriptionsPerUser.put(member.getUserId(), subscription);
            } catch (MemberNotFoundException ignored) {
            }


            // map users to subscriptions for unregistration links

        }
        for (Member recipient : recipients) {
            final MessagingUserPreferences messagingPreferences = MessagingClient
                    .getMessagingPreferencesForMember(recipient.getUserId());
            if (messagingPreferences.getEmailOnActivity()
                    && !messagingPreferences.getEmailActivityDailyDigest()) {

                //TODO: fix this because this was only done so the code would compile
                String unsubscribeFooter = getUnsubscribeIndividualSubscriptionFooter(
                        ConfigurationAttributeKey.COLAB_URL.get(),
                        NotificationUnregisterUtils.getUnregisterLink(subscriptionsPerUser.get(recipient.getUserId())));
                sendEmailMessage(recipient, subject, messageTemplate, unsubscribeFooter, ConfigurationAttributeKey.COLAB_URL.get());
            }

        }
    }

    private static String clearLinksInSubject(String activityEntryTitle) {
        return activityEntryTitle.replaceAll("\\<[^>]*>","");
    }

    private static void sendEmailMessage(Member recipient, String subject, String body, String unregisterFooter, String portalBaseUrl) {
        try {
            InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();
            InternetAddress toEmail = new InternetAddress(recipient.getEmailAddress(), recipient.getFullName());

            body +=  MESSAGE_FOOTER_TEMPLATE;

            body  = HtmlUtil.makeRelativeLinksAbsolute(body, portalBaseUrl);
            body = body.replaceAll("\n", "\n<br />");
            String message = body.replace(USER_PROFILE_LINK_PLACEHOLDER, getUserLink(recipient, portalBaseUrl));

            message = HtmlUtil.decodeHTMLEntitiesForEmail(message);

            // add link to unsubscribe
            message += "<br /><br />" + unregisterFooter;


            EmailClient.sendEmail(fromEmail.getAddress(),toEmail.getAddress(), TemplateReplacementUtil.replacePlatformConstants(subject),
                    TemplateReplacementUtil.replacePlatformConstants(message), true, fromEmail.getAddress());

        } catch ( UnsupportedEncodingException e) {
            _log.error("Can't send email notifications to users");
            _log.debug("Can't send email message", e);
        }
    }


    private static List<ActivitySubscription> getActivitySubscribers(ActivityEntry activity) {

        List<ActivitySubscription> filteredResults = new ArrayList<>();

        List<ActivitySubscription> ret = ActivitiesClientUtil.getActivitySubscriptions(activity.getPrimaryType(),
                activity.getClassPrimaryKey(), null);

        // Check for constraints which users should receive notifications
        ActivitySubscriptionConstraint subscriptionConstraint = new ActivitySubscriptionConstraint(activity.getPrimaryType(), activity.getSecondaryType());
        if (subscriptionConstraint.areSubscribersConstrained()) {
            for(Long userId : subscriptionConstraint.getWhitelist(activity.getClassPrimaryKey())){
                for(ActivitySubscription as : ret) {
                    if (as.getReceiverId() == userId.longValue()) {
                        filteredResults.add(as);
                    }
                }
            }
        } else {
            filteredResults.addAll(ret);
        }

        return filteredResults;
    }

    private static Date getLastDailyEmailNotificationDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, DAILY_DIGEST_TRIGGER_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    private static String dateToDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return formatter.format(date);
    }


    private static String getUnsubscribeIndividualSubscriptionFooter(String portalBaseUrl, String unsubscribeUrl) {
        String faqUrl = portalBaseUrl + FAQ_DIGEST_URL_PATH;
        String footer =  TemplateReplacementUtil.replaceContestTypeStrings(
                StringUtils.replace(UNSUBSCRIBE_INSTANT_NOTIFICATION_TEXT, FAQ_DIGEST_LINK_PLACEHOLDER, faqUrl), null);
        //TODO: select contest type above? -> uses generic word!
        footer = StringUtils.replace(footer, UNSUBSCRIBE_SUBSCRIPTION_LINK_PLACEHOLDER, unsubscribeUrl);
        return  footer;
    }

    private static String getUserLink(Member user, String portalBaseUrl) {
        return USER_PROFILE_LINK_TEMPLATE.replaceAll(USER_ID_PLACEHOLDER, String.valueOf(user.getUserId())).replaceAll(DOMAIN_PLACEHOLDER, portalBaseUrl);
    }
}
