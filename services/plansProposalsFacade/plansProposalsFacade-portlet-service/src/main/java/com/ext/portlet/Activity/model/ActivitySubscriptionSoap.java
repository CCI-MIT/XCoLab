package com.ext.portlet.Activity.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.Activity.service.http.ActivitySubscriptionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.Activity.service.http.ActivitySubscriptionServiceSoap
 * @generated
 */
public class ActivitySubscriptionSoap implements Serializable {
    private Long _pk;
    private Long _classNameId;
    private Long _classPK;
    private Integer _type;
    private String _extraData;
    private Long _receiverId;
    private Date _createDate;
    private Date _modifiedDate;

    public ActivitySubscriptionSoap() {
    }

    public static ActivitySubscriptionSoap toSoapModel(
        ActivitySubscription model) {
        ActivitySubscriptionSoap soapModel = new ActivitySubscriptionSoap();

        soapModel.setPk(model.getPk());
        soapModel.setClassNameId(model.getClassNameId());
        soapModel.setClassPK(model.getClassPK());
        soapModel.setType(model.getType());
        soapModel.setExtraData(model.getExtraData());
        soapModel.setReceiverId(model.getReceiverId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static ActivitySubscriptionSoap[] toSoapModels(
        ActivitySubscription[] models) {
        ActivitySubscriptionSoap[] soapModels = new ActivitySubscriptionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ActivitySubscriptionSoap[][] toSoapModels(
        ActivitySubscription[][] models) {
        ActivitySubscriptionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ActivitySubscriptionSoap[models.length][models[0].length];
        } else {
            soapModels = new ActivitySubscriptionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ActivitySubscriptionSoap[] toSoapModels(
        List<ActivitySubscription> models) {
        List<ActivitySubscriptionSoap> soapModels = new ArrayList<ActivitySubscriptionSoap>(models.size());

        for (ActivitySubscription model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ActivitySubscriptionSoap[soapModels.size()]);
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

    public Long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(Long classNameId) {
        _classNameId = classNameId;
    }

    public Long getClassPK() {
        return _classPK;
    }

    public void setClassPK(Long classPK) {
        _classPK = classPK;
    }

    public Integer getType() {
        return _type;
    }

    public void setType(Integer type) {
        _type = type;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public Long getReceiverId() {
        return _receiverId;
    }

    public void setReceiverId(Long receiverId) {
        _receiverId = receiverId;
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
