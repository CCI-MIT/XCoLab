package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessagingMessageServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.MessagingMessageServiceSoap
 * @generated
 */
public class MessagingMessageSoap implements Serializable {
    private long _messageId;
    private String _name;
    private String _description;
    private String _subject;
    private String _body;
    private String _replyTo;
    private boolean _sendToAll;
    private long _conversionCount;
    private String _redirectURL;
    private long _creatorId;
    private Date _createDate;
    private Date _modifiedDate;

    public MessagingMessageSoap() {
    }

    public static MessagingMessageSoap toSoapModel(MessagingMessage model) {
        MessagingMessageSoap soapModel = new MessagingMessageSoap();

        soapModel.setMessageId(model.getMessageId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setSubject(model.getSubject());
        soapModel.setBody(model.getBody());
        soapModel.setReplyTo(model.getReplyTo());
        soapModel.setSendToAll(model.getSendToAll());
        soapModel.setConversionCount(model.getConversionCount());
        soapModel.setRedirectURL(model.getRedirectURL());
        soapModel.setCreatorId(model.getCreatorId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static MessagingMessageSoap[] toSoapModels(MessagingMessage[] models) {
        MessagingMessageSoap[] soapModels = new MessagingMessageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageSoap[][] toSoapModels(
        MessagingMessage[][] models) {
        MessagingMessageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageSoap[] toSoapModels(
        List<MessagingMessage> models) {
        List<MessagingMessageSoap> soapModels = new ArrayList<MessagingMessageSoap>(models.size());

        for (MessagingMessage model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _messageId;
    }

    public void setPrimaryKey(long pk) {
        setMessageId(pk);
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String body) {
        _body = body;
    }

    public String getReplyTo() {
        return _replyTo;
    }

    public void setReplyTo(String replyTo) {
        _replyTo = replyTo;
    }

    public boolean getSendToAll() {
        return _sendToAll;
    }

    public boolean isSendToAll() {
        return _sendToAll;
    }

    public void setSendToAll(boolean sendToAll) {
        _sendToAll = sendToAll;
    }

    public long getConversionCount() {
        return _conversionCount;
    }

    public void setConversionCount(long conversionCount) {
        _conversionCount = conversionCount;
    }

    public String getRedirectURL() {
        return _redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        _redirectURL = redirectURL;
    }

    public long getCreatorId() {
        return _creatorId;
    }

    public void setCreatorId(long creatorId) {
        _creatorId = creatorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
