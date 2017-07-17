package org.xcolab.view.pages.loginregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

public enum LoginRegisterActivityKeys {
    USER_REGISTERED(1, "New account created", "%s joined the %s community");
    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterActivityKeys.class);
    private static final String USER_PROFILE_PATH = "/members/profile/";

    private final int type;
    private final String title;
    private final String body;

    LoginRegisterActivityKeys(int type, String title, String body) {
        this.type = type;
        this.title = title;
        this.body = body;
    }

    public static LoginRegisterActivityKeys getForType(int type) {
        for (LoginRegisterActivityKeys key : LoginRegisterActivityKeys.values()) {
            if (key.type == type) {
                return key;
            }
        }
        return null;
    }

    public static String generateUserURL(long userId) {
        if (userId <= 0) {
            return "";
        }
        Member u = MembersClient.getMemberUnchecked(userId);
        return "<a href='" + generateUserHref(userId)+ "'>" + u.getScreenName()+ "</a>";
    }

    private static String generateUserHref(long userId) {
        if (userId <= 0) {
            return "";
        }
        return USER_PROFILE_PATH + userId;
    }

    public int getType() {
        return type;
    }

    public String getTitle(Member user) {
        return String.format(title, getUserLink(user));
    }

    public String getBody(Member user) {
        String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        return String.format(body, getUserLink(user), colabName);
    }

    private String getUserLink(Member user) {
        return generateUserURL(user.getUserId());
    }
}
