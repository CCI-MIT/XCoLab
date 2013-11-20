package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingUserPreferences;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessagingUserPreferences in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferences
 * @generated
 */
public class MessagingUserPreferencesCacheModel implements CacheModel<MessagingUserPreferences>,
    Externalizable {
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

    @Override
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

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        messagingPreferencesId = objectInput.readLong();
        userId = objectInput.readLong();
        emailOnSend = objectInput.readBoolean();
        emailOnReceipt = objectInput.readBoolean();
        emailOnActivity = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(messagingPreferencesId);
        objectOutput.writeLong(userId);
        objectOutput.writeBoolean(emailOnSend);
        objectOutput.writeBoolean(emailOnReceipt);
        objectOutput.writeBoolean(emailOnActivity);
    }
}
