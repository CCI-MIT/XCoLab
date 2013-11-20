package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessagingRedirectLinkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.MessagingRedirectLinkServiceSoap
 * @generated
 */
public class MessagingRedirectLinkSoap implements Serializable {
    private long _redirectId;
    private String _link;
    private long _messageId;
    private Date _createDate;

    public MessagingRedirectLinkSoap() {
    }

    public static MessagingRedirectLinkSoap toSoapModel(
        MessagingRedirectLink model) {
        MessagingRedirectLinkSoap soapModel = new MessagingRedirectLinkSoap();

        soapModel.setRedirectId(model.getRedirectId());
        soapModel.setLink(model.getLink());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static MessagingRedirectLinkSoap[] toSoapModels(
        MessagingRedirectLink[] models) {
        MessagingRedirectLinkSoap[] soapModels = new MessagingRedirectLinkSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingRedirectLinkSoap[][] toSoapModels(
        MessagingRedirectLink[][] models) {
        MessagingRedirectLinkSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingRedirectLinkSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingRedirectLinkSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingRedirectLinkSoap[] toSoapModels(
        List<MessagingRedirectLink> models) {
        List<MessagingRedirectLinkSoap> soapModels = new ArrayList<MessagingRedirectLinkSoap>(models.size());

        for (MessagingRedirectLink model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingRedirectLinkSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _redirectId;
    }

    public void setPrimaryKey(long pk) {
        setRedirectId(pk);
    }

    public long getRedirectId() {
        return _redirectId;
    }

    public void setRedirectId(long redirectId) {
        _redirectId = redirectId;
    }

    public String getLink() {
        return _link;
    }

    public void setLink(String link) {
        _link = link;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
