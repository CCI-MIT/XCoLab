package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.User_ServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.User_ServiceSoap
 * @generated
 */
public class User_Soap implements Serializable {
    private long _userId;
    private Date _createDate;
    private String _screenName;

    public User_Soap() {
    }

    public static User_Soap toSoapModel(User_ model) {
        User_Soap soapModel = new User_Soap();

        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setScreenName(model.getScreenName());

        return soapModel;
    }

    public static User_Soap[] toSoapModels(User_[] models) {
        User_Soap[] soapModels = new User_Soap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static User_Soap[][] toSoapModels(User_[][] models) {
        User_Soap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new User_Soap[models.length][models[0].length];
        } else {
            soapModels = new User_Soap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static User_Soap[] toSoapModels(List<User_> models) {
        List<User_Soap> soapModels = new ArrayList<User_Soap>(models.size());

        for (User_ model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new User_Soap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _userId;
    }

    public void setPrimaryKey(long pk) {
        setUserId(pk);
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

    public String getScreenName() {
        return _screenName;
    }

    public void setScreenName(String screenName) {
        _screenName = screenName;
    }
}
