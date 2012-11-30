package com.ext.portlet.messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.messaging.service.http.MessageServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.messaging.service.http.MessageServiceSoap
 * @generated
 */
public class MessageSoap implements Serializable {
    private Long _messageId;
    private Long _fromId;
    private Long _repliesTo;
    private Date _createDate;
    private String _subject;
    private String _content;

    public MessageSoap() {
    }

    public static MessageSoap toSoapModel(Message model) {
        MessageSoap soapModel = new MessageSoap();

        soapModel.setMessageId(model.getMessageId());
        soapModel.setFromId(model.getFromId());
        soapModel.setRepliesTo(model.getRepliesTo());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setSubject(model.getSubject());
        soapModel.setContent(model.getContent());

        return soapModel;
    }

    public static MessageSoap[] toSoapModels(Message[] models) {
        MessageSoap[] soapModels = new MessageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessageSoap[][] toSoapModels(Message[][] models) {
        MessageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessageSoap[models.length][models[0].length];
        } else {
            soapModels = new MessageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessageSoap[] toSoapModels(List<Message> models) {
        List<MessageSoap> soapModels = new ArrayList<MessageSoap>(models.size());

        for (Message model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessageSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _messageId;
    }

    public void setPrimaryKey(Long pk) {
        setMessageId(pk);
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Long getFromId() {
        return _fromId;
    }

    public void setFromId(Long fromId) {
        _fromId = fromId;
    }

    public Long getRepliesTo() {
        return _repliesTo;
    }

    public void setRepliesTo(Long repliesTo) {
        _repliesTo = repliesTo;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }
}
