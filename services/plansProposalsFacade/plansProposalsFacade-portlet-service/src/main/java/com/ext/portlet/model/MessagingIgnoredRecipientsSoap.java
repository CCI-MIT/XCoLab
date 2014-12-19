package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessagingIgnoredRecipientsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.MessagingIgnoredRecipientsServiceSoap
 * @generated
 */
public class MessagingIgnoredRecipientsSoap implements Serializable {
    private long _ignoredRecipientId;
    private String _email;
    private String _name;
    private long _userId;
    private Date _createDate;

    public MessagingIgnoredRecipientsSoap() {
    }

    public static MessagingIgnoredRecipientsSoap toSoapModel(
        MessagingIgnoredRecipients model) {
        MessagingIgnoredRecipientsSoap soapModel = new MessagingIgnoredRecipientsSoap();

        soapModel.setIgnoredRecipientId(model.getIgnoredRecipientId());
        soapModel.setEmail(model.getEmail());
        soapModel.setName(model.getName());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static MessagingIgnoredRecipientsSoap[] toSoapModels(
        MessagingIgnoredRecipients[] models) {
        MessagingIgnoredRecipientsSoap[] soapModels = new MessagingIgnoredRecipientsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingIgnoredRecipientsSoap[][] toSoapModels(
        MessagingIgnoredRecipients[][] models) {
        MessagingIgnoredRecipientsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingIgnoredRecipientsSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingIgnoredRecipientsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingIgnoredRecipientsSoap[] toSoapModels(
        List<MessagingIgnoredRecipients> models) {
        List<MessagingIgnoredRecipientsSoap> soapModels = new ArrayList<MessagingIgnoredRecipientsSoap>(models.size());

        for (MessagingIgnoredRecipients model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingIgnoredRecipientsSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _ignoredRecipientId;
    }

    public void setPrimaryKey(long pk) {
        setIgnoredRecipientId(pk);
    }

    public long getIgnoredRecipientId() {
        return _ignoredRecipientId;
    }

    public void setIgnoredRecipientId(long ignoredRecipientId) {
        _ignoredRecipientId = ignoredRecipientId;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
