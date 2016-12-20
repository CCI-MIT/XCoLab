package org.xcolab.portlets.loginregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

public enum LoginRegisterActivityKeys {
    USER_REGISTERED(1, "New account created", "%s joined the %s community");
    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterActivityKeys.class);
    private static final String USER_PROFILE_PATH = "/web/guest/member/-/member/userId/";

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

    public static String generateUserURL(long userId) throws PortalException, SystemException {
        if (userId <= 0) {
            return StringPool.BLANK;
        }
        User u = UserLocalServiceUtil.getUserById(userId);
        return "<a href='" + generateUserHref(userId)+ "'>" + u.getScreenName()+ "</a>";
    }

    private static String generateUserHref(long userId) throws PortalException, SystemException {
        if (userId <= 0) {
            return StringPool.BLANK;
        }
        return USER_PROFILE_PATH + userId;
    }

    public int getType() {
        return type;
    }

    public String getTitle(User user) {
        return String.format(title, getUserLink(user));
    }

    public String getBody(User user) throws SystemException {
        String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        return String.format(body, getUserLink(user), colabName);
    }

    private String getUserLink(User user) {
        try {
            return generateUserURL(user.getUserId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }
}
