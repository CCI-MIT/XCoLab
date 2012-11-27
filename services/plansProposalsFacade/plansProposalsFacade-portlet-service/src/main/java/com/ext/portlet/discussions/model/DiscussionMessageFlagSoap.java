package com.ext.portlet.discussions.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.discussions.service.http.DiscussionMessageFlagServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.discussions.service.http.DiscussionMessageFlagServiceSoap
 * @generated
 */
public class DiscussionMessageFlagSoap implements Serializable {
    private Long _pk;
    private Long _messageId;
    private String _flagType;
    private String _data;
    private Date _created;
    private Long _userId;

    public DiscussionMessageFlagSoap() {
    }

    public static DiscussionMessageFlagSoap toSoapModel(
        DiscussionMessageFlag model) {
        DiscussionMessageFlagSoap soapModel = new DiscussionMessageFlagSoap();

        soapModel.setPk(model.getPk());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setFlagType(model.getFlagType());
        soapModel.setData(model.getData());
        soapModel.setCreated(model.getCreated());
        soapModel.setUserId(model.getUserId());

        return soapModel;
    }

    public static DiscussionMessageFlagSoap[] toSoapModels(
        DiscussionMessageFlag[] models) {
        DiscussionMessageFlagSoap[] soapModels = new DiscussionMessageFlagSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DiscussionMessageFlagSoap[][] toSoapModels(
        DiscussionMessageFlag[][] models) {
        DiscussionMessageFlagSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DiscussionMessageFlagSoap[models.length][models[0].length];
        } else {
            soapModels = new DiscussionMessageFlagSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DiscussionMessageFlagSoap[] toSoapModels(
        List<DiscussionMessageFlag> models) {
        List<DiscussionMessageFlagSoap> soapModels = new ArrayList<DiscussionMessageFlagSoap>(models.size());

        for (DiscussionMessageFlag model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DiscussionMessageFlagSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long pk) {
        setPk(pk);
    }

    public Long getPk() {
        return _pk;
    }

    public void setPk(Long pk) {
        _pk = pk;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public String getFlagType() {
        return _flagType;
    }

    public void setFlagType(String flagType) {
        _flagType = flagType;
    }

    public String getData() {
        return _data;
    }

    public void setData(String data) {
        _data = data;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }
}
