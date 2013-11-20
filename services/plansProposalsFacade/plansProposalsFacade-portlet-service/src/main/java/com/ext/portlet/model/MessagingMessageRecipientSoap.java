package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessagingMessageRecipientServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.MessagingMessageRecipientServiceSoap
 * @generated
 */
public class MessagingMessageRecipientSoap implements Serializable {
    private long _recipientId;
    private long _messageId;
    private long _userId;
    private String _emailAddress;

    public MessagingMessageRecipientSoap() {
    }

    public static MessagingMessageRecipientSoap toSoapModel(
        MessagingMessageRecipient model) {
        MessagingMessageRecipientSoap soapModel = new MessagingMessageRecipientSoap();

        soapModel.setRecipientId(model.getRecipientId());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setUserId(model.getUserId());
        soapModel.setEmailAddress(model.getEmailAddress());

        return soapModel;
    }

    public static MessagingMessageRecipientSoap[] toSoapModels(
        MessagingMessageRecipient[] models) {
        MessagingMessageRecipientSoap[] soapModels = new MessagingMessageRecipientSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageRecipientSoap[][] toSoapModels(
        MessagingMessageRecipient[][] models) {
        MessagingMessageRecipientSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageRecipientSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageRecipientSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageRecipientSoap[] toSoapModels(
        List<MessagingMessageRecipient> models) {
        List<MessagingMessageRecipientSoap> soapModels = new ArrayList<MessagingMessageRecipientSoap>(models.size());

        for (MessagingMessageRecipient model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageRecipientSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _recipientId;
    }

    public void setPrimaryKey(long pk) {
        setRecipientId(pk);
    }

    public long getRecipientId() {
        return _recipientId;
    }

    public void setRecipientId(long recipientId) {
        _recipientId = recipientId;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getEmailAddress() {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }
}
