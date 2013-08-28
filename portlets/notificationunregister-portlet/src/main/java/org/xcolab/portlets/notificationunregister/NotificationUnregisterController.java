package org.xcolab.portlets.notificationunregister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

//import javax.validation.Validator;

@Controller
@RequestMapping("view")
public class NotificationUnregisterController {

	private long DEFAULT_COMPANY_ID = 10112L;
	
	@RequestMapping
	public String register(PortletRequest request, PortletResponse response, Model model) throws SystemException {
	    
	    Long userId = ParamUtil.getLong(request, "userId", 0);
	    Long subscriptionId = ParamUtil.getLong(request, "subscriptionId", 0);
	    String token = ParamUtil.getString(request, "token", "");
	    
	    
	    User user = null;
	    ActivitySubscription subscription = null;
	    boolean error = false;
	    boolean unregisteringSubscription = false;
	    if (userId > 0) {
	        try {
	            user = UserLocalServiceUtil.getUser(userId);
	            System.out.println(NotificationUnregisterUtils.getToken(user));
	            System.out.println(token);
	            error = ! NotificationUnregisterUtils.isTokenValid(token, user);
	        }
	        catch (Exception e) {
	            error = true;
	        }
	    }
	    if (subscriptionId > 0) {
	        try {
	            subscription = ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionId);
                error = ! NotificationUnregisterUtils.isTokenValid(token, subscription);
                unregisteringSubscription = true;
	        }
	        catch (Exception e) {
                error = true;
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
	        MessagingIgnoredRecipients ignoredRecipients = null;
	        try {
	            ignoredRecipients = MessagingIgnoredRecipientsLocalServiceUtil.findByUserId(userId);
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
	    
	    
	    
	    
        model.addAttribute("unregisteringSubscription", unregisteringSubscription);
	    
	    
	    
	    
	    return "view";
	}	

}
