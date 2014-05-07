package org.xcolab.portlets.notificationunregister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.ext.utils.NotificationUnregisterUtils;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

//import javax.validation.Validator;

@Controller
@RequestMapping("view")
public class NotificationUnregisterController {

	private final static Log _log = LogFactoryUtil.getLog(NotificationUnregisterController.class);
	
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
	            subscription = ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionId);
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

	    // unregister user
	    if (subscription != null) {
	        ActivitySubscriptionLocalServiceUtil.delete(subscription);
	    }

	    if (user != null) {
            getUnregisterUserHandler(typeId).unregister(user);
	    }

        model.addAttribute("unregisteringSubscription", unregisteringSubscription);
	    
	    return "view";
	}

    private NotificationUnregisterHandler getUnregisterUserHandler(int type) {
        if (type == NotificationUnregisterUtils.ACTIVITY_TYPE) {
            return new ActivityNotificationUnregisterHandler();
        } else if (type == NotificationUnregisterUtils.MASSMESSAGING_TYPE) {
            return new MassmessagingNotificationUnregisterHandler();
        } else {
            return null;
        }
    }
}

interface NotificationUnregisterHandler {
    public void unregister(User user) throws SystemException;
}

class MassmessagingNotificationUnregisterHandler implements NotificationUnregisterHandler {

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
}

class ActivityNotificationUnregisterHandler implements NotificationUnregisterHandler {

    @Override
    public void unregister(User user) throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(user.getUserId());
        prefs.setEmailActivityDailyDigest(false);
        prefs.setEmailOnActivity(false);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
    }
}
