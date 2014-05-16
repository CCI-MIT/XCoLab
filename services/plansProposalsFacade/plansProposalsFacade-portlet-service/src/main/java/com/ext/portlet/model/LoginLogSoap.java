package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.LoginLogServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.LoginLogServiceSoap
 * @generated
 */
public class LoginLogSoap implements Serializable {
    private long _pk;
    private long _userId;
    private Date _createDate;
    private String _ipAddress;
    private String _city;
    private String _country;
    private String _entryUrl;

    public LoginLogSoap() {
    }

    public static LoginLogSoap toSoapModel(LoginLog model) {
        LoginLogSoap soapModel = new LoginLogSoap();

        soapModel.setPk(model.getPk());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setIpAddress(model.getIpAddress());
        soapModel.setCity(model.getCity());
        soapModel.setCountry(model.getCountry());
        soapModel.setEntryUrl(model.getEntryUrl());

        return soapModel;
    }

    public static LoginLogSoap[] toSoapModels(LoginLog[] models) {
        LoginLogSoap[] soapModels = new LoginLogSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static LoginLogSoap[][] toSoapModels(LoginLog[][] models) {
        LoginLogSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new LoginLogSoap[models.length][models[0].length];
        } else {
            soapModels = new LoginLogSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static LoginLogSoap[] toSoapModels(List<LoginLog> models) {
        List<LoginLogSoap> soapModels = new ArrayList<LoginLogSoap>(models.size());

        for (LoginLog model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new LoginLogSoap[soapModels.size()]);
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

    public String getIpAddress() {
        return _ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city = city;
    }

    public String getCountry() {
        return _country;
    }

    public void setCountry(String country) {
        _country = country;
    }

    public String getEntryUrl() {
        return _entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        _entryUrl = entryUrl;
    }
}
