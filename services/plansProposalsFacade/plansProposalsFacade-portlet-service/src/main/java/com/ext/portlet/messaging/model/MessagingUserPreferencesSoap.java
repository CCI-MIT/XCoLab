package com.ext.portlet.messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.messaging.service.http.MessagingUserPreferencesServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.messaging.service.http.MessagingUserPreferencesServiceSoap
 * @generated
 */
public class MessagingUserPreferencesSoap implements Serializable {
    private long _messagingPreferencesId;
    private long _userId;
    private boolean _emailOnSend;
    private boolean _emailOnReceipt;
    private boolean _emailOnActivity;

    public MessagingUserPreferencesSoap() {
    }

    public static MessagingUserPreferencesSoap toSoapModel(
        MessagingUserPreferences model) {
        MessagingUserPreferencesSoap soapModel = new MessagingUserPreferencesSoap();

        soapModel.setMessagingPreferencesId(model.getMessagingPreferencesId());
        soapModel.setUserId(model.getUserId());
        soapModel.setEmailOnSend(model.getEmailOnSend());
        soapModel.setEmailOnReceipt(model.getEmailOnReceipt());
        soapModel.setEmailOnActivity(model.getEmailOnActivity());

        return soapModel;
    }

    public static MessagingUserPreferencesSoap[] toSoapModels(
        MessagingUserPreferences[] models) {
        MessagingUserPreferencesSoap[] soapModels = new MessagingUserPreferencesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingUserPreferencesSoap[][] toSoapModels(
        MessagingUserPreferences[][] models) {
        MessagingUserPreferencesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingUserPreferencesSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingUserPreferencesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingUserPreferencesSoap[] toSoapModels(
        List<MessagingUserPreferences> models) {
        List<MessagingUserPreferencesSoap> soapModels = new ArrayList<MessagingUserPreferencesSoap>(models.size());

        for (MessagingUserPreferences model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingUserPreferencesSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _messagingPreferencesId;
    }

    public void setPrimaryKey(long pk) {
        setMessagingPreferencesId(pk);
    }

    public long getMessagingPreferencesId() {
        return _messagingPreferencesId;
    }

    public void setMessagingPreferencesId(long messagingPreferencesId) {
        _messagingPreferencesId = messagingPreferencesId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public boolean getEmailOnSend() {
        return _emailOnSend;
    }

    public boolean isEmailOnSend() {
        return _emailOnSend;
    }

    public void setEmailOnSend(boolean emailOnSend) {
        _emailOnSend = emailOnSend;
    }

    public boolean getEmailOnReceipt() {
        return _emailOnReceipt;
    }

    public boolean isEmailOnReceipt() {
        return _emailOnReceipt;
    }

    public void setEmailOnReceipt(boolean emailOnReceipt) {
        _emailOnReceipt = emailOnReceipt;
    }

    public boolean getEmailOnActivity() {
        return _emailOnActivity;
    }

    public boolean isEmailOnActivity() {
        return _emailOnActivity;
    }

    public void setEmailOnActivity(boolean emailOnActivity) {
        _emailOnActivity = emailOnActivity;
    }
}
