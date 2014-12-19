package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.TrackedVisitor2UserServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.TrackedVisitor2UserServiceSoap
 * @generated
 */
public class TrackedVisitor2UserSoap implements Serializable {
    private long _id;
    private String _uuid;
    private long _userId;
    private Date _createDate;

    public TrackedVisitor2UserSoap() {
    }

    public static TrackedVisitor2UserSoap toSoapModel(TrackedVisitor2User model) {
        TrackedVisitor2UserSoap soapModel = new TrackedVisitor2UserSoap();

        soapModel.setId(model.getId());
        soapModel.setUuid(model.getUuid());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static TrackedVisitor2UserSoap[] toSoapModels(
        TrackedVisitor2User[] models) {
        TrackedVisitor2UserSoap[] soapModels = new TrackedVisitor2UserSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static TrackedVisitor2UserSoap[][] toSoapModels(
        TrackedVisitor2User[][] models) {
        TrackedVisitor2UserSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new TrackedVisitor2UserSoap[models.length][models[0].length];
        } else {
            soapModels = new TrackedVisitor2UserSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static TrackedVisitor2UserSoap[] toSoapModels(
        List<TrackedVisitor2User> models) {
        List<TrackedVisitor2UserSoap> soapModels = new ArrayList<TrackedVisitor2UserSoap>(models.size());

        for (TrackedVisitor2User model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new TrackedVisitor2UserSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long pk) {
        setId(pk);
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
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
