package org.xcolab.view.util.entity;

import org.apache.commons.codec.digest.DigestUtils;

import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.members.pojo.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class NotificationUnregisterUtils {

    private static final String UNREGISTER_LINK =
            "DOMAIN/notifications/unsubscribe/member/USER_ID/subscription/SUBSCRIPTION_ID/token"
                    + "/TOKEN/type/TYPE_ID";
    private static final String TOKEN_PARAM = "TOKEN";
    private static final String SUBSCRIPTION_ID = "SUBSCRIPTION_ID";
    private static final String TYPE_ID = "TYPE_ID";
    private static final String USER_ID = "USER_ID";
    private static final String DOMAIN_PLACEHOLDER = "DOMAIN";

    public static final int ACTIVITY_TYPE = 0;

    private NotificationUnregisterUtils() {
    }

    public static String getUnregisterLink(ActivitySubscription subscription) {
        return getUnregisterLink(subscription, null, ACTIVITY_TYPE);
    }

    public static String getActivityUnregisterLink(Member user) {
        return getUnregisterLink(null, user, ACTIVITY_TYPE);
    }

    public static boolean isTokenValid(String token, ActivitySubscription subscription) {
        return getToken(subscription).equals(token);
    }

    public static boolean isTokenValid(String token, Member user) {
        return getToken(user).equals(token);
    }

    private static String getUnregisterLink(ActivitySubscription subscription, Member user,
            int type) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, "0");
        params.put(SUBSCRIPTION_ID, "0");
        if (subscription != null) {
            params.put(SUBSCRIPTION_ID, String.valueOf(subscription.getId()));
            params.put(TOKEN_PARAM, getToken(subscription));
            params.put(TYPE_ID, String.valueOf(type));
        } else {
            params.put(USER_ID, String.valueOf(user.getId()));
            params.put(TOKEN_PARAM, getToken(user));
            params.put(TYPE_ID, String.valueOf(type));
        }

        String unregisterLink = UNREGISTER_LINK;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            unregisterLink = unregisterLink.replace(entry.getKey(), entry.getValue());
        }

        unregisterLink = unregisterLink.replaceAll(Pattern.quote(DOMAIN_PLACEHOLDER),
                PlatformAttributeKey.COLAB_URL.get());

        return unregisterLink;
    }

    public static String getToken(ActivitySubscription subscription) {
        return getToken(Math.floor(1.0 * subscription.getCreatedAt().getTime() / 1000.f) + ""
                + subscription.getId());
    }

    public static String getToken(Member user) {
        return getToken(
                Math.floor(1.0 * user.getCreatedAt().getTime() / 1000.f) + "" + user.getId());
    }

    private static String getToken(String baseString) {
        return DigestUtils.md5Hex(baseString.getBytes());
    }

}
