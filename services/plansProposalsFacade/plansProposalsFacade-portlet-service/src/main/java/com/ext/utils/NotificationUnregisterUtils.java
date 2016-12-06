package com.ext.utils;

import org.apache.commons.codec.digest.DigestUtils;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.members.pojo.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class NotificationUnregisterUtils {

    private final static String UNREGISTER_LINK = "DOMAIN/notificationunregister/-/notificationunregister/subscriptionId/SUBSCRIPTION_ID/userId/USER_ID/token/TOKEN/typeId/TYPE_ID/";
    private final static String TOKEN_PARAM = "TOKEN";
    private final static String SUBSCRIPTION_ID = "SUBSCRIPTION_ID";
    private final static String TYPE_ID = "TYPE_ID";
    private final static String USER_ID = "USER_ID";
    private final static String DOMAIN_PLACEHOLDER = "DOMAIN";

    public static final int ACTIVITY_TYPE = 0;
    
    private NotificationUnregisterUtils() {
    }
    
    public static String getUnregisterLink(ActivitySubscription subscription, ServiceContext serviceContext) {
        return getUnregisterLink(subscription, null, ACTIVITY_TYPE, serviceContext);
    }

    public static String getActivityUnregisterLink(Member user, ServiceContext serviceContext) {
        return getUnregisterLink(null, user, ACTIVITY_TYPE, serviceContext);
    }

    public static boolean isTokenValid(String token, ActivitySubscription subscription) {
        return getToken(subscription).equals(token);
    }
    
    public static boolean isTokenValid(String token, Member user) {
        return getToken(user).equals(token);
    }
    
    private static String getUnregisterLink(ActivitySubscription subscription, Member user, int type, ServiceContext serviceContext) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, "0");
        params.put(SUBSCRIPTION_ID, "0");
        if (subscription != null) {
            params.put(SUBSCRIPTION_ID, String.valueOf(subscription.getPk()));
            params.put(TOKEN_PARAM, getToken(subscription));
            params.put(TYPE_ID, String.valueOf(type));
        }
        else {
            params.put(USER_ID, String.valueOf(user.getUserId()));
            params.put(TOKEN_PARAM, getToken(user));
            params.put(TYPE_ID, String.valueOf(type));
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
        return getToken(Math.floor(1.0 * subscription.getCreateDate().getTime() / 1000.f) + "" + subscription.getPk());
    }
    
    public static String getToken(Member user) {
        return getToken(Math.floor(1.0 * user.getCreateDate().getTime() / 1000.f) + "" + user.getUserId());
    }
    
    private static String getToken(String baseString) {
        return DigestUtils.md5Hex(baseString.getBytes());
    }

}
