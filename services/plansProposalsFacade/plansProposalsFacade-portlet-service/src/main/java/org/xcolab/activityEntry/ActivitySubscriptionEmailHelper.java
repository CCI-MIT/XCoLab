package org.xcolab.activityEntry;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.utils.subscriptions.ActivitySubscriptionConstraint;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivitySubscriptionEmailHelper {

    private static Date lastEmailNotification = new Date();

    // 1 am
    private final static int DAILY_DIGEST_TRIGGER_HOUR = 1;

    private final static Log _log = LogFactoryUtil.getLog(ActivitySubscriptionEmailHelper.class);

    private static Date lastDailyEmailNotification = getLastDailyEmailNotificationDate();

    public static void sendEmailNotifications(ServiceContext serviceContext) throws SystemException, PortalException {
        synchronized (lastEmailNotification) {
            List<ActivityEntry> res = getActivitiesAfter(lastEmailNotification);
            for (ActivityEntry activity : res) {
                try {
                    sendInstantNotifications(activity, serviceContext);
                }
                catch (Throwable e) {
                    _log.error(String.format("Can't process activity when sending notifications ( %s )", activity), e);
                }
            }
            lastEmailNotification = new Date();
        }

        /*
        synchronized (lastDailyEmailNotification) {
            Date now = new Date();

            // Send the daily digest at the predefined hour only
            if (now.getTime() - lastDailyEmailNotification.getTime() > 3600 * 1000
                    && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == DAILY_DIGEST_TRIGGER_HOUR) {
                try {
                    List<SocialActivity> res = getActivitiesAfter(lastDailyEmailNotification);
                    sendDailyDigestNotifications(res, serviceContext);
                } catch (SystemException | PortalException t) {
                    _log.error("Can't send daily email notification", t);
                }

                lastDailyEmailNotification = now;
            }
        }

        */
    }

    private static List<ActivityEntry> getActivitiesAfter(Date minDate) throws SystemException {

        List<ActivityEntry> activityObjects = null;// call the client to get data

        //clean list of activities first in order not to send out activities concerning the same proposal multiple times
        ActivityEntryMessageLimitationHelper h = new ActivityEntryMessageLimitationHelper(ActivityEntryType.PROPOSOSAL.getPrimaryTypeId());

        return h.process(activityObjects);
    }

    private static void sendInstantNotifications(ActivityEntry activity, ServiceContext serviceContext) throws SystemException, PortalException {



        String subject = "";//get old implementation for subject
        String messageTemplate = activity.getActivityEntryBody();

        Set<User> recipients = new HashSet<>();
        Map<Long, ActivitySubscription> subscriptionsPerUser = new HashMap<>();

        for (Object subscriptionObj : getActivitySubscribers(activity)) {
            ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;

            if (subscription.getReceiverId() == activity.getMemberId()) {
                continue;
            }
            User user = UserLocalServiceUtil.getUser(subscription.getReceiverId());
            recipients.add(user);
            // map users to subscriptions for unregistration links
            subscriptionsPerUser.put(user.getUserId(), subscription);
        }
        for (User recipient : recipients) {
            if (MessageUtil.getMessagingPreferences(recipient.getUserId()).getEmailOnActivity() &&
                    !MessageUtil.getMessagingPreferences(recipient.getUserId()).getEmailActivityDailyDigest()) {

                //TODO: fix this because this was only done so the code would compile
                //String unsubscribeFooter = getUnsubscribeIndividualSubscriptionFooter(serviceContext.getPortalURL(),
                //NotificationUnregisterUtils.getUnregisterLink(subscriptionsPerUser.get(recipient.getUserId()), serviceContext));
                //sendEmailMessage(recipient, subject, messageTemplate, unsubscribeFooter, serviceContext.getPortalURL());
            }

        }
    }

    private static List<ActivitySubscription> getActivitySubscribers(ActivityEntry activity) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);

        Criterion criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId", activity.getPrimaryType());
        Criterion criterionClassPK = RestrictionsFactoryUtil.eq("classPK", activity.getClassPrimaryKey());

        Criterion combinedCriterion = RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK);

        List<ActivitySubscription> ret = ActivitiesClient.getActivitySubscription(activity.getPrimaryType(),
                activity.getClassPrimaryKey(), null);

        // Check for constraints which users should receive notifications
        ActivitySubscriptionConstraint subscriptionConstraint = new ActivitySubscriptionConstraint(activity.getPrimaryType(), activity.getSecondaryType());
        if (subscriptionConstraint.areSubscribersConstrained()) {
            Criterion contrainedUsers = RestrictionsFactoryUtil.in("receiverId", subscriptionConstraint.getWhitelist(activity.getClassPrimaryKey()));
            query.add(RestrictionsFactoryUtil.and(combinedCriterion, contrainedUsers));
        } else {
            query.add(combinedCriterion);
        }

        return ActivitySubscriptionLocalServiceUtil.dynamicQuery(query);
    }
    private static Date getLastDailyEmailNotificationDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, DAILY_DIGEST_TRIGGER_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }
}
