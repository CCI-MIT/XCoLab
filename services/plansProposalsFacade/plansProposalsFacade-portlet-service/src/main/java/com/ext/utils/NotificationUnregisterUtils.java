package com.ext.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import org.apache.commons.codec.digest.DigestUtils;

import com.ext.portlet.model.ActivitySubscription;
import com.liferay.portal.model.User;

public class NotificationUnregisterUtils {

    private final static String UNREGISTER_LINK = "DOMAIN/notificationunregister/-/notificationunregister/subscriptionId/SUBSCRIPTION_ID/userId/USER_ID/token/TOKEN";
    private final static String TOKEN_PARAM = "TOKEN";
    private final static String SUBSCRIPTION_ID = "SUBSCRIPTION_ID";
    private final static String USER_ID = "USER_ID";
    private final static String DOMAIN_PLACEHOLDER = "DOMAIN";
    
    private NotificationUnregisterUtils() {
    }
    
    public static String getUnregisterLink(ActivitySubscription subscription, ServiceContext serviceContext) {
        return getUnregisterLink(subscription, null, serviceContext);
    }
    
    public static String getUnregisterLink(User user, ServiceContext serviceContext) {
        return getUnregisterLink(null, user, serviceContext);
    }
    
    public static boolean isTokenValid(String token, ActivitySubscription subscription) {
        return getToken(subscription).equals(token);
    }
    
    public static boolean isTokenValid(String token, User user) {
        return getToken(user).equals(token);
    }
    
    private static String getUnregisterLink(ActivitySubscription subscription, User user, ServiceContext serviceContext) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, "0");
        params.put(SUBSCRIPTION_ID, "0");
        if (subscription != null) {
            params.put(SUBSCRIPTION_ID, String.valueOf(subscription.getPrimaryKey()));
            params.put(TOKEN_PARAM, getToken(subscription));
        }
        else {
            params.put(USER_ID, String.valueOf(user.getUserId()));
            params.put(TOKEN_PARAM, getToken(user));
        }
        
        String unregisterLink = UNREGISTER_LINK;
        for (Map.Entry<String, String> entry: params.entrySet()) {
            unregisterLink = unregisterLink.replace(entry.getKey(), entry.getValue());
        }

        if (Validator.isNotNull(serviceContext.getPortalURL())) {
            unregisterLink = unregisterLink.replaceAll(Pattern.quote(DOMAIN_PLACEHOLDER), serviceContext.getPortalURL());
        } else {
            unregisterLink = unregisterLink.replaceAll(Pattern.quote(DOMAIN_PLACEHOLDER), "http://climatecolab.org");
        }
        
        return unregisterLink;
    }
    
    public static String getToken(ActivitySubscription subscription) {
        return getToken(subscription.getCreateDate().toString() + subscription.getPk());
    }
    
    public static String getToken(User user) {
        return getToken(user.getCreateDate() + "" + user.getUserId());
    }
    
    private static String getToken(String baseString) {
        return DigestUtils.md5Hex(baseString.getBytes());
    }

}
