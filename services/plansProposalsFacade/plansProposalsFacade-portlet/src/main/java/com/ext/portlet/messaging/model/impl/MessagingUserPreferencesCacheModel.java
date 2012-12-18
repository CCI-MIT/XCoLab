package com.ext.portlet.messaging.model.impl;

import com.ext.portlet.messaging.model.MessagingUserPreferences;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing MessagingUserPreferences in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferences
 * @generated
 */
public class MessagingUserPreferencesCacheModel implements CacheModel<MessagingUserPreferences>,
    Serializable {
    public long messagingPreferencesId;
    public long userId;
    public boolean emailOnSend;
    public boolean emailOnReceipt;
    public boolean emailOnActivity;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{messagingPreferencesId=");
        sb.append(messagingPreferencesId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", emailOnSend=");
        sb.append(emailOnSend);
        sb.append(", emailOnReceipt=");
        sb.append(emailOnReceipt);
        sb.append(", emailOnActivity=");
        sb.append(emailOnActivity);
        sb.append("}");

        return sb.toString();
    }

    public MessagingUserPreferences toEntityModel() {
        MessagingUserPreferencesImpl messagingUserPreferencesImpl = new MessagingUserPreferencesImpl();

        messagingUserPreferencesImpl.setMessagingPreferencesId(messagingPreferencesId);
        messagingUserPreferencesImpl.setUserId(userId);
        messagingUserPreferencesImpl.setEmailOnSend(emailOnSend);
        messagingUserPreferencesImpl.setEmailOnReceipt(emailOnReceipt);
        messagingUserPreferencesImpl.setEmailOnActivity(emailOnActivity);

        messagingUserPreferencesImpl.resetOriginalValues();

        return messagingUserPreferencesImpl;
    }
}
