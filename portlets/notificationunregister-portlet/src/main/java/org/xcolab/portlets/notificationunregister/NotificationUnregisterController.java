package org.xcolab.portlets.notificationunregister;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.ext.utils.NotificationUnregisterUtils;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.pojo.ActivitySubscription;

import java.util.Date;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

//import javax.validation.Validator;

@Controller
@RequestMapping("view")
public class NotificationUnregisterController {

	private final static Log _log = LogFactoryUtil.getLog(NotificationUnregisterController.class);

    private static final String UNSUBSCRIBE_TITLE = "You have been unsubscribed";
    private static final String UNSUBSCRIBE_INDIVIDUAL_SUBSCRIPTION_RESPONSE_TEXT = "You may still receive email notifications if you are subscribed to other activity on the Climate CoLab.  " +
            "To manage your subscriptions, please log in to your account, select “My profile”, and select the “Manage” " +
            "button underneath “Subscribed Activity” on the righthand side.";


    @RequestMapping
	public String register(PortletRequest request, PortletResponse response, Model model) throws SystemException {
	    
	    Long userId = ParamUtil.getLong(request, "userId", 0);
	    Long subscriptionId = ParamUtil.getLong(request, "subscriptionId", 0);
        Integer typeId = ParamUtil.getInteger(request, "typeId", 0);
	    String token = ParamUtil.getString(request, "token", "");
	    
	    
	    User user = null;
	    ActivitySubscription subscription = null;
	    boolean error = false;
	    boolean unregisteringSubscription = false;
	    if (userId > 0) {
	        try {
	            user = UserLocalServiceUtil.getUser(userId);
	            error = ! NotificationUnregisterUtils.isTokenValid(token, user) ||
                        (typeId != NotificationUnregisterUtils.ACTIVITY_TYPE && typeId != NotificationUnregisterUtils.MASSMESSAGING_TYPE);
	        }
	        catch (Exception e) {
	            _log.error("Error when unsubscribing", e);
	        }
	    }
	    if (subscriptionId > 0) {
	        try {
	            subscription = ActivitiesClient.getActivitySubscription(subscriptionId);
                //ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionId);
                error = ! NotificationUnregisterUtils.isTokenValid(token, subscription);
                unregisteringSubscription = true;
	        }
	        catch (Exception e) {
                _log.error("Error when unsubscribing", e);
                // error = true; ignore, but log exception
	            
	        }
	    }
	    
	    if (error) {
	        return "error";
	    }

        String responseText = null;
	    // unregister user
	    if (subscription != null) {
	        //ActivitySubscriptionLocalServiceUtil.delete(subscription);
            ActivitiesClient.deleteActivitySubscription(subscription.getPk());
            responseText = UNSUBSCRIBE_INDIVIDUAL_SUBSCRIPTION_RESPONSE_TEXT;
	    }

	    if (user != null) {
            NotificationUnregisterHandler handler = getUnregisterUserHandler(typeId);
            handler.unregister(user);
            responseText = handler.getSuccessResponse();
	    }

        model.addAttribute("responseTitle", UNSUBSCRIBE_TITLE);
        model.addAttribute("responseText", responseText);
	    
	    return "view";
	}

    private NotificationUnregisterHandler getUnregisterUserHandler(int type) {
        if (type == NotificationUnregisterUtils.ACTIVITY_TYPE) {
            return new ActivityDailyDigestNotificationUnregisterHandler();
        } else if (type == NotificationUnregisterUtils.MASSMESSAGING_TYPE) {
            return new MassmessagingNotificationUnregisterHandler();
        } else {
            return null;
        }
    }
}

interface NotificationUnregisterHandler {
    public void unregister(User user) throws SystemException;
    public String getSuccessResponse();
}

class MassmessagingNotificationUnregisterHandler implements NotificationUnregisterHandler {

    private static final String UNSUBSCRIBE_RESPONSE_TEXT =
            "Your address has been excluded from our newsletter recipients.";

    @Override
    public void unregister(User user) throws SystemException {
        MessagingIgnoredRecipients ignoredRecipients = null;
        try {
            ignoredRecipients = MessagingIgnoredRecipientsLocalServiceUtil.findByUserId(user.getUserId());
        }
        catch (Exception e) {
            //
        }
        if (ignoredRecipients == null) {
            // save
            Long ignoredRecipientId = CounterLocalServiceUtil.increment(MessagingIgnoredRecipients.class.getName());
            MessagingIgnoredRecipients ignoredRecipient = MessagingIgnoredRecipientsLocalServiceUtil
                    .createMessagingIgnoredRecipients(ignoredRecipientId);

            ignoredRecipient.setUserId(user.getUserId());
            ignoredRecipient.setName(user.getScreenName());
            ignoredRecipient.setEmail(user.getEmailAddress());

            ignoredRecipient.setCreateDate(new Date());

            MessagingIgnoredRecipientsLocalServiceUtil.addMessagingIgnoredRecipients(ignoredRecipient);
        }
    }

    @Override
    public String getSuccessResponse() {
        return UNSUBSCRIBE_RESPONSE_TEXT;
    }
}

class ActivityDailyDigestNotificationUnregisterHandler implements NotificationUnregisterHandler {

    private static final String UNSUBSCRIBE_RESPONSE_TEXT =
            "from the daily digest and all email notifications about activity on the Climate CoLab.  " +
                    "To resubscribe or manage your subscriptions, please log in to your account, select “My profile”, " +
                    "and select the “Manage” button underneath “Subscribed Activity” on the righthand side.";

    @Override
    public void unregister(User user) throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(user.getUserId());
        prefs.setEmailActivityDailyDigest(false);
        prefs.setEmailOnActivity(false);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
    }

    @Override
    public String getSuccessResponse() {
        return UNSUBSCRIBE_RESPONSE_TEXT;
    }
}
