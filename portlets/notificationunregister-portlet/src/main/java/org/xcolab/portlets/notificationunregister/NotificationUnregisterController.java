package org.xcolab.portlets.notificationunregister;

import com.ext.utils.NotificationUnregisterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

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
	    
	    
	    Member user = null;
        boolean error = false;
        if (userId > 0) {
	        try {

                user = MembersClient.getMember(userId);
	            error = ! NotificationUnregisterUtils.isTokenValid(token, user) ||
                        (typeId != NotificationUnregisterUtils.ACTIVITY_TYPE && typeId != NotificationUnregisterUtils.MASSMESSAGING_TYPE);
	        }
	        catch (Exception e) {
	            _log.error("Error when unsubscribing", e);
	        }
	    }
        ActivitySubscription subscription = null;
        if (subscriptionId > 0) {
	        try {
	            subscription = ActivitiesClientUtil.getActivitySubscription(subscriptionId);
                //ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionId);
                error = ! NotificationUnregisterUtils.isTokenValid(token, subscription);
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
            ActivitiesClientUtil.deleteSubscriptionById(subscription.getPk());
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

