package com.ext.portlet.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.ext.portlet.Activity.ActivitySubscriptionNameGeneratorServiceUtil;
import com.ext.portlet.Activity.SubscriptionType;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.base.ActivitySubscriptionLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionUtil;
import com.ext.utils.NotificationUnregisterUtils;
import com.ext.utils.SocialActivityMessageLimitationHelper;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

/**
 * The implementation of the activity subscription local service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ActivitySubscriptionLocalService} interface.
 * <p/>
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ActivitySubscriptionLocalServiceBaseImpl
 * @see com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil
 */
public class ActivitySubscriptionLocalServiceImpl
        extends ActivitySubscriptionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil} to access the activity subscription local service.
     */

    private Date lastEmailNotification = new Date();
    private final static String PROPERTY_CREATE_DATE = "createDate";

    private final static Log _log = LogFactoryUtil.getLog(ActivitySubscriptionLocalServiceImpl.class);
    private final static long DEFAULT_COMPANY_ID = 10112L;

	// 1 am
	private final static int DAILY_DIGEST_TRIGGER_HOUR = 1;
	private Date lastDailyEmailNotification = new Date();


    public List<ActivitySubscription> getActivitySubscriptions(Class clasz, Long classPK, Integer type, String extraData)
            throws PortalException, SystemException {
        return ActivitySubscriptionUtil.findByClassNameIdClassPKTypeExtraData(PortalUtil.getClassNameId(clasz), classPK, type, extraData);
    }

    public List<ActivitySubscription> findByUser(Long userId) throws SystemException {
        return ActivitySubscriptionUtil.findByreceiverId(userId);
    }

    public boolean isSubscribed(Long userId, Long classNameId, Long classPK, Integer type, String extraData)
            throws PortalException, SystemException {
        List<ActivitySubscription> ret = ActivitySubscriptionUtil.findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId, classPK,
                type, extraData, userId);
        return ret != null && !ret.isEmpty();
    }

    public boolean isSubscribed(Long userId, Class clasz, Long classPK, Integer type, String extraData)
            throws PortalException, SystemException {
        return isSubscribed(userId, PortalUtil.getClassNameId(clasz), classPK, type, extraData);
    }

    public void deleteSubscription(Long userId, Long classNameId, Long classPK, Integer type, String extraData) throws SystemException {
        deleteSubscription(userId, classNameId, classPK, type, extraData, false);
    }

    @Transactional
    public void deleteSubscription(Long userId, Long classNameId, Long classPK, Integer type, String extraData, boolean automatic) throws SystemException {
        List<ActivitySubscription> subscriptions =
                ActivitySubscriptionUtil.findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId, classPK, type, extraData, userId);
        for (ActivitySubscription subscription : subscriptions) {
            if (automatic) {
                // we are removing automatic subscriptions only
                if (subscription.getAutomaticSubscriptionCounter() == 1) {
                    delete(subscription);
                } else if (subscription.getAutomaticSubscriptionCounter() > 1) {
                    // decrement automatic subscription counter as this subscription can be still
                    // valid (ie it has been added as a result of regional->global link and because
                    // user is susbscribed to a contest)
                    subscription.setAutomaticSubscriptionCounter(subscription.getAutomaticSubscriptionCounter() - 1);
                    updateActivitySubscription(subscription);
                }
            } else {
                // remove subscription as it's probably requested directly by the user 
                delete(subscription);
            }
        }
    }


    public void deleteSubscription(Long userId, Class clasz, Long classPK, Integer type, String extraData, boolean automatic) throws SystemException {
        deleteSubscription(userId, PortalUtil.getClassNameId(clasz), classPK, type, extraData, automatic);
    }


    public void deleteSubscription(Long userId, Class clasz, Long classPK, Integer type, String extraData) throws SystemException {
        deleteSubscription(userId, clasz, classPK, type, extraData, false);
    }

    public void addSubscription(Long classNameId, Long classPK, Integer type, String extraData, Long userId)
            throws PortalException, SystemException {
        addSubscription(classNameId, classPK, type, extraData, userId, false);
    }

    @Transactional
    public void addSubscription(Long classNameId, Long classPK, Integer type, String extraData, Long userId, boolean automatic)
            throws PortalException, SystemException {

        List<ActivitySubscription> subscriptions = ActivitySubscriptionUtil
                .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId, classPK, type, extraData, userId);

        if (!subscriptions.isEmpty()) {
            // subscription exists
            ActivitySubscription subscription = subscriptions.get(0);
            if (automatic && subscription.getAutomaticSubscriptionCounter() > 0) {
                // this is an automatic subscription, if existing subscription is also automatic then increment the counter, otherwise do nothing
                subscription.setAutomaticSubscriptionCounter(subscription.getAutomaticSubscriptionCounter() + 1);
                ActivitySubscriptionLocalServiceUtil.updateActivitySubscription(subscription);
            }
            return;
        }

        Long pk = CounterLocalServiceUtil.increment(ActivitySubscription.class.getName());
        ActivitySubscription subscription = ActivitySubscriptionLocalServiceUtil.createActivitySubscription(pk);

        subscription.setClassNameId(classNameId);
        subscription.setClassPK(classPK);
        subscription.setType(type);
        subscription.setExtraData(extraData);
        subscription.setReceiverId(userId);
        subscription.setAutomaticSubscriptionCounter(automatic ? 1 : -1);

        subscription.setModifiedDate(new Date());
        subscription.setCreateDate(new Date());

        store(subscription);
    }

    public void addSubscription(Class clasz, Long classPK, Integer type, String extraData, Long userId)
            throws PortalException, SystemException {
        addSubscription(clasz, classPK, type, extraData, userId, false);
    }

    public void addSubscription(Class clasz, Long classPK, Integer type, String extraData, Long userId, boolean automatic)
            throws PortalException, SystemException {
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        addSubscription(classNameId, classPK, type, extraData, userId, automatic);
    }


    public List<SocialActivity> getActivities(Long userId, int start, int count) throws SystemException {
        List<ActivitySubscription> subscriptions = null;
        // for now no activity selection is made, TODO
        subscriptions = ActivitySubscriptionUtil.findByreceiverId(userId);

        if (subscriptions.size() == 0) {
            return new ArrayList<SocialActivity>();
        }

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(SocialActivity.class);
        Criterion crit = null;

        for (ActivitySubscription sub : subscriptions) {
            //Map<String, Number> criterion = new HashMap<String, Number>();
            Criterion criterion = RestrictionsFactoryUtil.eq("classNameId", sub.getClassNameId());
            if (sub.getClassPK() <= 0) {
                RestrictionsFactoryUtil.and(criterion, RestrictionsFactoryUtil.eq("classPk", sub.getClassPK()));
            }
            if (sub.getType() >= 0) {
                RestrictionsFactoryUtil.and(criterion, RestrictionsFactoryUtil.eq("type", sub.getType()));
            }

            if (sub.getExtraData() != null && sub.getExtraData().length() > 0) {
                criterion = RestrictionsFactoryUtil.and(criterion,
                        RestrictionsFactoryUtil.ilike("extraData", sub.getExtraData() + "%"));
            }

            if (crit == null) {
                crit = criterion;
            } else {
                crit = RestrictionsFactoryUtil.or(crit, criterion);
            }
        }
        query.add(crit).addOrder(OrderFactoryUtil.desc("createDate"));

        List<SocialActivity> activities = new ArrayList<SocialActivity>();
        List<Object> queryResults = SocialActivityLocalServiceUtil.dynamicQuery(query, start, start + count - 1);

        for (Object activity : queryResults) {
            activities.add((SocialActivity) activity);
        }

        return activities;

    }


    public void store(ActivitySubscription activitySubscription) throws SystemException {
        if (activitySubscription.isNew()) {
            ActivitySubscriptionLocalServiceUtil.addActivitySubscription(activitySubscription);
        } else {
            ActivitySubscriptionLocalServiceUtil.updateActivitySubscription(activitySubscription);
        }
    }


    public String getName(ActivitySubscription activitySubscription) {
        return ActivitySubscriptionNameGeneratorServiceUtil.getName(activitySubscription);
    }

    public SubscriptionType getSubscriptionType(ActivitySubscription activitySubscription) {
        return SubscriptionType.getSubscriptionType(activitySubscription);
    }


    public void delete(ActivitySubscription activitySubscription) throws SystemException {
        ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription(activitySubscription);
    }


    public void sendEmailNotifications(ServiceContext serviceContext) throws SystemException, PortalException {
        synchronized (lastEmailNotification) {
            List<SocialActivity> res = getActivitiesAfter(lastEmailNotification);
            for (SocialActivity activity : res) {
                sendInstantNotifications(activity, serviceContext);
            }
            lastEmailNotification = new Date();
        }

		synchronized (lastDailyEmailNotification) {
			Date now = new Date();

			// Send the daily digest at the predefined hour only
			if (now.getTime() - lastDailyEmailNotification.getTime() > 3600 * 1000 &&
					Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == DAILY_DIGEST_TRIGGER_HOUR) {

				List<SocialActivity> res = getActivitiesAfter(lastDailyEmailNotification);
				sendDailyDigestNotifications(res, serviceContext);

				lastDailyEmailNotification = now;
			}
		}
    }

    public List<User> getSubscribedUsers(Class clasz, long classPK) throws PortalException, SystemException {
        return getSubscribedUsers(ClassNameLocalServiceUtil.getClassNameId(clasz), classPK);
    }

    public List<User> getSubscribedUsers(long classNameId, long classPK) throws PortalException, SystemException {
        List<User> users = new ArrayList<>();
        for (ActivitySubscription subscription : activitySubscriptionPersistence.findByClassNameIdClassPK(classNameId, classPK)) {
            users.add(userLocalService.getUser(subscription.getReceiverId()));
        }
        return users;
    }

	private List<SocialActivity> getActivitiesAfter(Date minDate) throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivity.class);
		Criterion criterionCreateDate = RestrictionsFactoryUtil.gt(PROPERTY_CREATE_DATE, minDate.getTime());

		dynamicQuery.add(criterionCreateDate);
		List<Object> activityObjects = SocialActivityLocalServiceUtil.dynamicQuery(dynamicQuery);

		//clean list of activities first in order not to send out activities concerning the same proposal multiple times
		SocialActivityMessageLimitationHelper h = new SocialActivityMessageLimitationHelper(Proposal.class);

		return h.process(activityObjects);
	}

    private final String MESSAGE_FOOTER_TEMPLATE = "<br /><br />\n<hr /><br />\n"
            + "To configure your notification preferences, visit your <a href=\"USER_PROFILE_LINK\">profile</a> page";

    private final String USER_PROFILE_LINK_PLACEHOLDER = "USER_PROFILE_LINK";

    private final String USER_PROFILE_LINK_TEMPLATE = "DOMAIN_PLACEHOLDER/web/guest/member/-/member/userId/USER_ID";

    private final String USER_ID_PLACEHOLDER = "USER_ID";

    private final String DOMAIN_PLACEHOLDER = "DOMAIN_PLACEHOLDER";



    private void sendDailyDigestNotifications(List<SocialActivity> activities, ServiceContext serviceContext) throws SystemException, PortalException {
		Map<User, String> userDigestBodyMap = new HashMap<>();

		for (SocialActivity activity : activities) {

			SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(StringPool.BLANK, activity, serviceContext);
			if (entry == null) {
				continue;
			}

			String messageTemplate = getMailBody(entry);

			// Aggregate all activities for all users
			for (Object subscriptionObj : getActivitySubscribers(activity)) {
				ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;

				User recipient = UserLocalServiceUtil.getUser(subscription.getReceiverId());
				if (subscription.getReceiverId() == activity.getUserId()) {
					continue;
				}

				if (MessageUtil.getMessagingPreferences(recipient.getUserId()).getEmailOnActivity() &&
						MessageUtil.getMessagingPreferences(recipient.getUserId()).getEmailActivityDailyDigest()) {

					String userDigestBody = userDigestBodyMap.get(recipient);
					if (userDigestBody == null) {
						userDigestBody = "<ul>";
					}
					userDigestBody += "<li>" + messageTemplate
							.replaceAll("\'/web/guest", "\'" + serviceContext.getPortalURL() + "/web/guest") + "</li>";
					userDigestBodyMap.put(recipient, userDigestBody);
				}
			}
		}

		// Send the digest to each user which is included in the set of subscriptions
		for (User recipient : userDigestBodyMap.keySet()) {
			String subject = "CoLab Activities Daily digest";
			String body = userDigestBodyMap.get(recipient);
			body += "</ul>";
			sendEmailMessage(recipient, subject, body, NotificationUnregisterUtils.getActivityUnregisterLink(recipient, serviceContext), serviceContext);
		}
	}


    private void sendInstantNotifications(SocialActivity activity, ServiceContext serviceContext) throws SystemException, PortalException {

		SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(StringPool.BLANK, activity, serviceContext);
		if (entry == null) {
			return;
		}

		String subject = getMailSubject(entry);
		String messageTemplate = getMailBody(entry);

		Set<User> recipients = new HashSet<User>();
		Map<Long, ActivitySubscription> subscriptionsPerUser = new HashMap<>();

		for (Object subscriptionObj : getActivitySubscribers(activity)) {
			ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;

			if (subscription.getReceiverId() == activity.getUserId()) {
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

				sendEmailMessage(recipient, subject, messageTemplate, NotificationUnregisterUtils.getUnregisterLink(subscriptionsPerUser.get(recipient.getUserId()), serviceContext), serviceContext);
			}

		}
    }

    private List<ActivitySubscription> getActivitySubscribers(SocialActivity activity) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);
        Criterion criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId", activity.getClassNameId());
        Criterion criterionClassPK = RestrictionsFactoryUtil.eq("classPK", activity.getClassPK());
        query.add(RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK));

        return ActivitySubscriptionLocalServiceUtil.dynamicQuery(query);
    }

	private void sendEmailMessage(User recipient, String subject, String body, String unregisterLink, ServiceContext serviceContext) {
		try {
			InternetAddress fromEmail = new InternetAddress("no-reply@climatecolab.org", "The Climate CoLab Team");
			InternetAddress toEmail = new InternetAddress(recipient.getEmailAddress(), recipient.getFullName());

			body +=  MESSAGE_FOOTER_TEMPLATE;

			body  = body.replaceAll("\"/web/guest", "\"" + serviceContext.getPortalURL() + "/web/guest")
					.replaceAll("\"/web/guest", "\"" + serviceContext.getPortalURL() + "/web/guest").replaceAll("\n", "\n<br />");
			String message = body.replace(USER_PROFILE_LINK_PLACEHOLDER, getUserLink(recipient, serviceContext));

			// add link to unsubscribe
			message +=
					"<br /><br /><a href='" +
							unregisterLink +
							"'>Don't want to receive updates from the Climate CoLab?  Click here to unsubscribe.</a>";
			MailEngine.send(fromEmail, toEmail, subject, message, true);
		} catch (MailEngineException e) {
			_log.error("Can't send email message");
			_log.debug("Can't send email message", e);
		}
		catch (Throwable e) {
			_log.error("Can't send email motifications to users");
			_log.debug("Can't send email message", e);
		}
	}

    private String getMailBody(SocialActivityFeedEntry entry) {
        try {
            return (String) entry.getClass().getMethod("getMailBody").invoke(entry);
        } catch (NoSuchMethodException e) {
            // ignore
        } catch (IllegalArgumentException e) {
            _log.error(e);
        } catch (SecurityException e) {
            _log.error(e);
        } catch (IllegalAccessException e) {
            _log.error(e);
        } catch (InvocationTargetException e) {
            _log.error(e);
        }
        if (entry.getBody() != null) {
            return entry.getBody();
        }
        return entry.getTitle();

    }

    private String getMailSubject(SocialActivityFeedEntry entry) {
        try {
            return (String) entry.getClass().getMethod("getMailSubject").invoke(entry);
        } catch (NoSuchMethodException e) {
            // ignore
        } catch (IllegalArgumentException e) {
            _log.error(e);
        } catch (SecurityException e) {
            _log.error(e);
        } catch (IllegalAccessException e) {
            _log.error(e);
        } catch (InvocationTargetException e) {
            _log.error(e);
        }
        if (entry.getTitle() != null) {
            return entry.getTitle();
        }

        return entry.getBody();
    }

    private String getUserLink(User user, ServiceContext serviceContext) {
        return USER_PROFILE_LINK_TEMPLATE.replaceAll(USER_ID_PLACEHOLDER, String.valueOf(user.getUserId())).replaceAll(DOMAIN_PLACEHOLDER, serviceContext.getPortalURL());
    }

}
