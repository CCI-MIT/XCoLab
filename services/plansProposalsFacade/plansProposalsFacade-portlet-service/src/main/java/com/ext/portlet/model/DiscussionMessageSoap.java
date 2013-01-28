package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.DiscussionMessageServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.DiscussionMessageServiceSoap
 * @generated
 */
public class DiscussionMessageSoap implements Serializable {
    private long _pk;
    private long _messageId;
    private String _subject;
    private String _body;
    private long _threadId;
    private long _categoryId;
    private long _categoryGroupId;
    private long _authorId;
    private Date _createDate;
    private long _version;
    private Date _deleted;
    private int _responsesCount;
    private Date _lastActivityDate;
    private long _lastActivityAuthorId;

    public DiscussionMessageSoap() {
    }

    public static DiscussionMessageSoap toSoapModel(DiscussionMessage model) {
        DiscussionMessageSoap soapModel = new DiscussionMessageSoap();

        soapModel.setPk(model.getPk());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setSubject(model.getSubject());
        soapModel.setBody(model.getBody());
        soapModel.setThreadId(model.getThreadId());
        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setCategoryGroupId(model.getCategoryGroupId());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setVersion(model.getVersion());
        soapModel.setDeleted(model.getDeleted());
        soapModel.setResponsesCount(model.getResponsesCount());
        soapModel.setLastActivityDate(model.getLastActivityDate());
        soapModel.setLastActivityAuthorId(model.getLastActivityAuthorId());

        return soapModel;
    }

    public static DiscussionMessageSoap[] toSoapModels(
        DiscussionMessage[] models) {
        DiscussionMessageSoap[] soapModels = new DiscussionMessageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DiscussionMessageSoap[][] toSoapModels(
        DiscussionMessage[][] models) {
        DiscussionMessageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DiscussionMessageSoap[models.length][models[0].length];
        } else {
            soapModels = new DiscussionMessageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DiscussionMessageSoap[] toSoapModels(
        List<DiscussionMessage> models) {
        List<DiscussionMessageSoap> soapModels = new ArrayList<DiscussionMessageSoap>(models.size());

        for (DiscussionMessage model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DiscussionMessageSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(long pk) {
        setPk(pk);
    }

    public long getPk() {
        return _pk;
    }

    public void setPk(long pk) {
        _pk = pk;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
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

    public long getThreadId() {
        return _threadId;
    }

    public void setThreadId(long threadId) {
        _threadId = threadId;
    }

    public long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;
    }

    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public int getResponsesCount() {
        return _responsesCount;
    }

    public void setResponsesCount(int responsesCount) {
        _responsesCount = responsesCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }
}
