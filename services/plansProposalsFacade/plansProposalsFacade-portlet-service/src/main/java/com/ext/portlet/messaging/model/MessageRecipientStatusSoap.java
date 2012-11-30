package com.ext.portlet.messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.messaging.service.http.MessageRecipientStatusServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.messaging.service.http.MessageRecipientStatusServiceSoap
 * @generated
 */
public class MessageRecipientStatusSoap implements Serializable {
    private Long _messageRecipientId;
    private Long _messageId;
    private Long _userId;
    private Boolean _opened;
    private Boolean _archived;

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

    public Long getPrimaryKey() {
        return _messageRecipientId;
    }

    public void setPrimaryKey(Long pk) {
        setMessageRecipientId(pk);
    }

    public Long getMessageRecipientId() {
        return _messageRecipientId;
    }

    public void setMessageRecipientId(Long messageRecipientId) {
        _messageRecipientId = messageRecipientId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Boolean getOpened() {
        return _opened;
    }

    public void setOpened(Boolean opened) {
        _opened = opened;
    }

    public Boolean getArchived() {
        return _archived;
    }

    public void setArchived(Boolean archived) {
        _archived = archived;
    }
}
