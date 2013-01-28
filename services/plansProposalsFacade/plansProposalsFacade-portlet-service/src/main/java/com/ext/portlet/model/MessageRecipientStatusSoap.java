package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessageRecipientStatusServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.MessageRecipientStatusServiceSoap
 * @generated
 */
public class MessageRecipientStatusSoap implements Serializable {
    private long _messageRecipientId;
    private long _messageId;
    private long _userId;
    private boolean _opened;
    private boolean _archived;

    public MessageRecipientStatusSoap() {
    }

    public static MessageRecipientStatusSoap toSoapModel(
        MessageRecipientStatus model) {
        MessageRecipientStatusSoap soapModel = new MessageRecipientStatusSoap();

        soapModel.setMessageRecipientId(model.getMessageRecipientId());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setUserId(model.getUserId());
        soapModel.setOpened(model.getOpened());
        soapModel.setArchived(model.getArchived());

        return soapModel;
    }

    public static MessageRecipientStatusSoap[] toSoapModels(
        MessageRecipientStatus[] models) {
        MessageRecipientStatusSoap[] soapModels = new MessageRecipientStatusSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessageRecipientStatusSoap[][] toSoapModels(
        MessageRecipientStatus[][] models) {
        MessageRecipientStatusSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessageRecipientStatusSoap[models.length][models[0].length];
        } else {
            soapModels = new MessageRecipientStatusSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessageRecipientStatusSoap[] toSoapModels(
        List<MessageRecipientStatus> models) {
        List<MessageRecipientStatusSoap> soapModels = new ArrayList<MessageRecipientStatusSoap>(models.size());

        for (MessageRecipientStatus model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessageRecipientStatusSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _messageRecipientId;
    }

    public void setPrimaryKey(long pk) {
        setMessageRecipientId(pk);
    }

    public long getMessageRecipientId() {
        return _messageRecipientId;
    }

    public void setMessageRecipientId(long messageRecipientId) {
        _messageRecipientId = messageRecipientId;
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

    public boolean getOpened() {
        return _opened;
    }

    public boolean isOpened() {
        return _opened;
    }

    public void setOpened(boolean opened) {
        _opened = opened;
    }

    public boolean getArchived() {
        return _archived;
    }

    public boolean isArchived() {
        return _archived;
    }

    public void setArchived(boolean archived) {
        _archived = archived;
    }
}
