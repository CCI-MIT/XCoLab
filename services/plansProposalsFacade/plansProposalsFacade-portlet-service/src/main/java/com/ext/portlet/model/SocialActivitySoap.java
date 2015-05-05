package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.SocialActivityServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.SocialActivityServiceSoap
 * @generated
 */
public class SocialActivitySoap implements Serializable {
    private long _activityId;
    private long _userId;

    public SocialActivitySoap() {
    }

    public static SocialActivitySoap toSoapModel(SocialActivity model) {
        SocialActivitySoap soapModel = new SocialActivitySoap();

        soapModel.setActivityId(model.getActivityId());
        soapModel.setUserId(model.getUserId());

        return soapModel;
    }

    public static SocialActivitySoap[] toSoapModels(SocialActivity[] models) {
        SocialActivitySoap[] soapModels = new SocialActivitySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SocialActivitySoap[][] toSoapModels(SocialActivity[][] models) {
        SocialActivitySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SocialActivitySoap[models.length][models[0].length];
        } else {
            soapModels = new SocialActivitySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SocialActivitySoap[] toSoapModels(List<SocialActivity> models) {
        List<SocialActivitySoap> soapModels = new ArrayList<SocialActivitySoap>(models.size());

        for (SocialActivity model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SocialActivitySoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _activityId;
    }

    public void setPrimaryKey(long pk) {
        setActivityId(pk);
    }

    public long getActivityId() {
        return _activityId;
    }

    public void setActivityId(long activityId) {
        _activityId = activityId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }
}
