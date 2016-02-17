package com.ext.portlet.Activity;

import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import org.xcolab.enums.ConfigurationAttributeKey;

public enum LoginRegisterActivityKeys {
    USER_REGISTERED(1, "New account created", "%s joined the %s community");
    private static final Log _log = LogFactoryUtil.getLog(LoginRegisterActivityKeys.class);

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

    public int getType() {
        return type;
    }

    public String getTitle(User user) {
        return String.format(title, getUserLink(user));
    }

    public String getBody(User user) throws SystemException {
        String colabName = ConfigurationAttributeLocalServiceUtil.getAttributeStringValue(
                ConfigurationAttributeKey.COLAB_NAME.name(), 0L, "");
        return String.format(body, getUserLink(user), colabName);
    }

    private String getUserLink(User user) {
        try {
            return CommunityUtil.generateUserURL(user.getUserId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }
}
